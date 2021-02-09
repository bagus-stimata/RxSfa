package com.erp.distribution.sfa.domain.exception


import androidx.annotation.IntDef
import com.erp.distribution.sfa.domain.exception.Redirect.Companion.OPEN_HOME_SCREEN

@IntDef(OPEN_HOME_SCREEN)
annotation class Redirect {
    companion object {
        const val OPEN_HOME_SCREEN = 1
    }
}