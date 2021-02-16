package com.erp.distribution.sfa.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.databinding.DashBoardFragmentBinding
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.utils.network.NetworkChecker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*


@AndroidEntryPoint
//class DashBoardFragment : BaseFragment<DashBoardFragmentBinding, MainViewModel>() {
class DashBoardFragment : Fragment(R.layout.dash_board_fragment) {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val TAG = DashBoardFragment::class.java.simpleName

//    override val bindingVariable: Int
//        get() = BR.mainViewModel
//    override val  viewModel: MainViewModel by viewModels<MainViewModel> ()
    val  mainViewModel: MainViewModel by viewModels<MainViewModel> ()
    val  dashboardViewModel: DashBoardViewModel by viewModels<DashBoardViewModel> ()

//    override val layoutId: Int
//        get() = R.layout.dash_board_fragment


    lateinit var viewBinding: DashBoardFragmentBinding


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.dash_board_fragment, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = DashBoardFragmentBinding.bind(view)

//        viewBinding.mainViewModel = this.mainViewModel
        viewBinding.actionActivity = this
        viewBinding.userViewState = UserViewState()

        /**
         * Observer Common
         * 1. User Observer
         *   a. Cek From Database Live Data for List User: Jika tidak ada berarti keluarkan login
         */
        observeUserOtenticationToView()
        //        setupObserver()


        setFragmentResultListener("fromLoginFragment_requestKey") { _, bundle ->
            val result = bundle.getParcelable<FUser>("fromLoginFragment_resultKey")
//            val result = bundle.getParcelable("fromLoginFragment_resultKey")
            result?.let { resultFromLogin(result as FUser) }

        }
    }

    fun observeUserOtenticationToView() {
        mainViewModel.userViewStateLive.observe(viewLifecycleOwner, Observer { userViewState ->

            when(userViewState){
                is Resource.Loading ->{
                }
                is Resource.Success ->{

                    val data = userViewState.data
                    if (data.fUser ==null || data.fSalesman ==null || data.fDivision ==null || data.fWarehouse ==null) {

                    }else {
                        viewBinding.userViewState = data
                    }
                }
                is Resource.Failure ->{
                }
            }
        })

    }


    fun showLoginView() {
//        Log.e(TAG, "#result fragment atas \n\n")

        val action =
                DashBoardFragmentDirections.actionDashBoardFragmentToLoginFragment()
        findNavController().navigate(action)

//        Log.e(TAG, "#result fragment bawah \n\n")

    }


    fun menuSyncronize() {
    }

    fun menuSalesOrder() {
    }

    fun menuProduct() {
        /**
         * Sebagai Pendukung jika pada aktiviti main
         * tidak bisa menjalankan fungsinya dengan baik
         */
        val action = DashBoardFragmentDirections.actionDashBoardFragmentToFMaterialFragment(
                FUserEntity()
        )
        findNavController().navigate(action)
    }

    fun menuCustomer() {

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToFCustomerFragment(
                FUserEntity()
        )
        findNavController().navigate(action)

    }



    override fun onDestroyView() {
        super.onDestroyView()
//        searchView.setOnQueryTextListener(null)
    }

    fun resultFromLogin(resultObject: FUser) {

        //Password yang dipakai adalah passwordConfirm: Untuk seterusnya
        resultObject.passwordConfirm = resultObject.password
        val observer = mainViewModel.getRemoteFUserByUser(resultObject)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { newFUser ->
                    newFUser.lastModified = Date()
                    newFUser.created = Date()
                    newFUser.modifiedBy = "bagus"
                    newFUser.isLocked = false

                    newFUser
                }
                .subscribe(
                        {
                            val newFUser = it

                            newFUser.lastModified = Date()
                            newFUser.created = Date()
                            newFUser.modifiedBy = "bagus"

                            //kareana akan dipaki seterusnya
                            newFUser.password = resultObject.password
                            newFUser.passwordConfirm = resultObject.password

                            //CREATE JIKA SAMA SAJA
                            mainViewModel.getRemoteFDivisionAndSaveToCache(newFUser)
                            mainViewModel.getRemoteFSalesmanAndSaveToCache(newFUser)
                            mainViewModel.getRemoteFWarehouseAndSaveToCache(newFUser)

                            //Ketika Parent telat dalam Save maka
                            mainViewModel.insertCacheFUser(newFUser)


                        },
                        {
//                                Log.d(TAG, "#result Error")
//                                Timber.e("Get repo error: $it")
//                                mainViewModel.setThrowable(it)

                            showLoginView()
                        },
                        {
                        }
                )

        compositeDisposable.add(observer)

    }


}