package com.erp.distribution.sfa.utils

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

object DisposableManager {
    private val TAG = DisposableManager::class.java.simpleName
    private var compositeDisposable: CompositeDisposable? = null
        private get() {
            if (field == null || field!!.isDisposed) {
                field = CompositeDisposable()
            }
            return field
        }

    fun add(disposable: Disposable?) {
        Log.e(TAG, "add")
        compositeDisposable!!.add(disposable!!)
    }

    fun dispose() {
        Log.e(TAG, "dispose")
        compositeDisposable!!.dispose()
    }
}