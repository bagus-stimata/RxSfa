package com.erp.distribution.sfa.domain.exception

import androidx.annotation.IntDef
import com.erp.distribution.sfa.domain.exception.Action.Companion.CLOSE_SESSION
import com.erp.distribution.sfa.domain.exception.Action.Companion.RELOAD_PAGE

@IntDef(RELOAD_PAGE, CLOSE_SESSION)
annotation class Action {
    companion object {
        const val RELOAD_PAGE = 1
        const val CLOSE_SESSION = 2
    }
}