package com.erp.distribution.sfa.presentation.ui

import android.annotation.SuppressLint
import android.graphics.Color
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
import com.erp.distribution.sfa.domain.model.FMaterial
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

    val  mainViewModel: MainViewModel by viewModels<MainViewModel> ()
    val  dashboardViewModel: DashBoardViewModel by viewModels<DashBoardViewModel> ()
    
    lateinit var viewBinding: DashBoardFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = DashBoardFragmentBinding.bind(view)

//      viewBinding.mainViewModel = this.mainViewModel
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

        greeting()

        observerCallPlan()
    }


    fun observeUserOtenticationToView() {
        mainViewModel.userViewStateLive.observe(viewLifecycleOwner, Observer { userViewState ->

            when(userViewState){
                is Resource.Loading ->{
                }
                is Resource.Success ->{
                    val data = userViewState.data
                    /**
                     * Masih suka telah loading jadi
                     */
                    if (data.fUser ==null || data.fSalesman ==null || data.fDivision ==null || data.fWarehouse ==null) {
//                    if (data.fUser ==null) {
                        showLoginView() //-> jadi di non aktifkan dahulu
                    }else {
                        mainViewModel.userViewState = data
                        dashboardViewModel.userViewState = data
                        viewBinding.userViewState =  mainViewModel.userViewState
                    }
                }
                is Resource.Failure ->{
                }
            }
        })
    }
    fun observerCallPlan(){

    }

    fun showLoginView() {
        /**
         * Sebagai Pendukung jika pada aktiviti main
         * tidak bisa menjalankan fungsinya dengan baik
         */
        val action =
                DashBoardFragmentDirections.actionDashBoardFragmentToLoginFragment()
        findNavController().navigate(action)
    }


    fun menuSyncronize() {
        val action= DashBoardFragmentDirections.actionDashBoardFragmentToSyncronizeFragment(
                mainViewModel.userViewState
        )
        findNavController().navigate(action)
    }

    fun menuJourneyPlan() {
        dashboardViewModel.subscribeSalesCallPlanh()

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToCallPlanFragment(
                mainViewModel.userViewState
        )
        findNavController().navigate(action)
    }

    fun menuSalesOrder() {
        dashboardViewModel.subscribeSalesCallPlanh()

        dashboardViewModel.subscribeUpdateStock()
        dashboardViewModel.subscribeUpdatePriceAlth()

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToFtSaleshFragment(
                mainViewModel.userViewState
        )
        findNavController().navigate(action)
    }

    fun menuSummary() {

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToSummaryFragment(
            mainViewModel.userViewState
        )
        findNavController().navigate(action)

    }


    fun menuProduct() {
        dashboardViewModel.subscribeUpdateStock()

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToFMaterialFragment(
               mainViewModel.userViewState
        )
        findNavController().navigate(action)
    }

    fun menuCustomer() {

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToFCustomerFragment(
                mainViewModel.userViewState
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
                        },
                        {
                        }
                )

        compositeDisposable.add(observer)

    }


    @SuppressLint("SetTextI18n")
    private fun greeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar[Calendar.HOUR_OF_DAY]
//        val namaUser: String = mainViewModel.userActive.fullName
//        if (mainViewModelOld.userEntityActive.fullName.isEmpty()) mainViewModelOld.userEntityActive.username
        if (timeOfDay >= 0 && timeOfDay < 12) {
//            mainBinding.greetingText1.setText("Selamat Pagi")
            viewBinding.greetingImg.setImageResource(R.drawable.img_default_half_morning)
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
//            mainBinding.greetingText1.setText("Selamat Siang")
            viewBinding.greetingImg.setImageResource(R.drawable.img_default_half_afternoon)
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
//            mainBinding.greetingText1.setText("Selamat Sore")
            viewBinding.greetingImg.setImageResource(R.drawable.img_default_half_without_sun)
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
//            mainBinding.greetingText1.setText("Selamat Malam")
            viewBinding.greetingText1.setTextColor(Color.WHITE)
            viewBinding.greetingImg.setImageResource(R.drawable.img_default_half_night)
        }
//        mainBinding.greetingText2.setText(namaUser)
    }


}