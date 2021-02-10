package com.erp.distribution.sfa.presentation.base

import android.annotation.SuppressLint
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.erp.distribution.sfa.domain.exception.annotation.Action
import com.erp.distribution.sfa.domain.exception.annotation.Redirect
import com.erp.distribution.sfa.presentation.extention.setVisible
import com.erp.distribution.sfa.presentation.extention.showDialog
import com.erp.distribution.sfa.utils.autoCleared
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel>: Fragment() {

    abstract val bindingVariable: Int
    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    var viewDataBinding by autoCleared<T>()


    private var toast: Toast? = null
    private var snackBar: Snackbar? = null
    @SuppressLint("ShowToast")
    private fun subscriberException() {
        viewModel.run {
            snackBarMessage.observe(viewLifecycleOwner) { message ->
                view?.let { snackBar = Snackbar.make(it, message, Snackbar.LENGTH_SHORT) }
                snackBar?.show()
            }

            toastMessage.observe(viewLifecycleOwner) { message ->
                context?.let { toast = Toast.makeText(it, message, Toast.LENGTH_SHORT) }
                toast?.show()
            }

            inlineException.observe(viewLifecycleOwner) { tags ->
                tags.forEach { tag ->
                    val currentView = view?.findViewWithTag<TextView>(tag.name)
                    currentView?.run {
                        tag.message?.let { text = it }
                        setVisible(true)
                    }
                }
            }

            alertException.observe(viewLifecycleOwner) { pair ->
                context?.showDialog(
                    title = pair.first, message = pair.second, positiveMessage = getString(
                        android.R.string.ok
                    )
                )
            }

            dialogException.observe(viewLifecycleOwner) { dialog ->
                context?.showDialog(
                    title = dialog.title,
                    message = dialog.message,
                    positiveMessage = dialog.positiveMessage,
                    negativeMessage = dialog.negativeMessage,
                    positiveAction = {
                        positiveAction(
                            dialog.positiveAction,
                            dialog.positiveObject
                        )
                    },
                    negativeAction = {
                        negativeAction(
                            dialog.negativeAction,
                            dialog.negativeObject
                        )
                    }
                )
            }

//            redirectException.observe(viewLifecycleOwner, Observer<Redirect> { redirect ->
//                redirectAction(redirect.redirect, redirect.redirectObject)
//            })

        }
    }

    open fun positiveAction(@Action action: Int?, data: Any? = null) { }

    open fun negativeAction(@Action action: Int?, data: Any? = null) { }

    open fun redirectAction(@Redirect redirect: Int?, data: Any? = null) { }

    open fun onBackPressed(): Boolean {
        return false
    }
}