package com.erp.distribution.sfa.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.databinding.DashBoardFragmentBinding
import com.erp.distribution.sfa.databinding.FragmentLoginBinding
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.customer.customer_addedit.AddEditCustomerViewModel
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
//class DashBoardFragment : BaseFragment<DashBoardFragmentBinding, MainViewModel>() {
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = LoginFragment::class.java.simpleName

    val  mainViewModel: MainViewModel by viewModels<MainViewModel> ()

    lateinit var viewBinding: FragmentLoginBinding

    var handler = Handler()
    var runnable = Runnable {
        viewBinding.rellay1.setVisibility(View.VISIBLE)
        viewBinding.rellay2.setVisibility(View.VISIBLE)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentLoginBinding.bind(view)


        handler.postDelayed(runnable, 2000) //2000 is the timeout for the splash
        initialize()



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.mainEventFlow.collect { event ->
                when (event){
                    is MainViewModel.MainEvent.ShowInvalidInputMessage -> {
//                        Toast.makeText(context, "Hehehe Show Invalid Input", Toast.LENGTH_SHORT).show()

                    }
                    is MainViewModel.MainEvent.NavigateBackWithResult -> {
                        val fUser = FUser()
                        fUser.username = viewBinding.username.text.toString()
                        fUser.password = viewBinding.password.text.toString()

//                        Toast.makeText(context, "this is Login: ${fUser}", Toast.LENGTH_SHORT).show()

                        setFragmentResult(
                                "fromLoginFragment_requestKey",
                                bundleOf("fromLoginFragment_resultKey" to  fUser)
                        )
                        findNavController().popBackStack()

                    }
                }
            }
        }



        /**
         * Note: Bukan NavigationUp tapiBack Press
         */
//        requireActivity().onBackPressedDispatcher
//            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    Toast.makeText(context, "Jancok", Toast.LENGTH_LONG).show()
//                }
//            })

        /**
         * Jika Fungsi ini dipasang pada Fragment maka
         * 1. PopBackStack akan dilakukan secara manual
         * 2. Jika findNavController().popBackStack() dinon aktifkan maka tidak akan bisa melakukan BackPress
         */
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
//                findNavController().popBackStack()
//                Toast.makeText(context, "hello", Toast.LENGTH_LONG).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


    }

    fun initialize() {
        viewBinding.btnLoginNow.setOnClickListener { v: View? -> loginNow(v) }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }


    fun loginNow(v: View?) {
//        mainViewModel.showInvalidInputMessage("Hello bos")
        mainViewModel.navBackWithResult(1)


//        Toast.makeText(context, "aaa", Toast.LENGTH_SHORT).show()

//        readBinderToItem()
//        if (itemHeader.username.trim().isNullOrBlank() || itemHeader.password.trim().isNullOrBlank()) {
//            Toast.makeText(this, "Username atau Password tidak boleh kosong!", Toast.LENGTH_SHORT)
//                    .show()
//        }else {
//            val data = Intent()
//            data.putExtra(LoginActivity.EXTRA_OBJECT, itemHeader)
//            setResult(Activity.RESULT_OK, data)
//            finish()
//        }
    }

    fun readBinderToItem() {
//        itemHeader.username = editTextUsername.text.toString()
//        itemHeader.password = editTextPassword.text.toString()
    }


}