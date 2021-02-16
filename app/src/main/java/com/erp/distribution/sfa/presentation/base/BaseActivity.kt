/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.erp.distribution.sfa.presentation.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.erp.distribution.sfa.utils.network.NetworkChecker
import com.erp.distribution.sfa.utils.network.NetworkUtils
import com.erp.distribution.sfa.utils.versionFrom
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {
     val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whiteStatusBar()
    }

    private fun whiteStatusBar() {
        if (versionFrom(Build.VERSION_CODES.M)) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = getColor(android.R.color.white)
        }
    }

    protected fun onNetworkChange(block: (Boolean) -> Unit) {
        NetworkUtils.getNetworkStatus(this)
            .observe(this, Observer { isConnected ->

                var connectionValidation = false;
                if (isConnected==false){
                    val netChecker = NetworkChecker(this)
                    connectionValidation = netChecker.isConnected
                }

                block(isConnected || connectionValidation)
            })
    }


}