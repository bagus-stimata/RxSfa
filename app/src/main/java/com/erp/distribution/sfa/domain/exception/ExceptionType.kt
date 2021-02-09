package com.erp.distribution.sfa.domain.exception

import androidx.annotation.IntDef
import com.erp.distribution.sfa.domain.exception.ExceptionType.Companion.ALERT
import com.erp.distribution.sfa.domain.exception.ExceptionType.Companion.DIALOG
import com.erp.distribution.sfa.domain.exception.ExceptionType.Companion.INLINE
import com.erp.distribution.sfa.domain.exception.ExceptionType.Companion.REDIRECT
import com.erp.distribution.sfa.domain.exception.ExceptionType.Companion.SNACK
import com.erp.distribution.sfa.domain.exception.ExceptionType.Companion.TOAST

/**
 * Clear exception from Throwable
 * @param SNACK is type of show message via Snack bar
 * @param TOAST is type of show message via Toast
 * @param INLINE is type of show or hide view warning, example: password in correct hint of password field
 * @param ALERT is type of show message type Alert Dialog, but only message & button `OK`
 * @param DIALOG is type of show Alert Dialog, with multiple attributes: title, message, positive, negative & action
 * @param REDIRECT is type of auto-redirect with view, action or finished, ...
 */
@IntDef(SNACK, TOAST, INLINE, ALERT, DIALOG, REDIRECT)
annotation class ExceptionType {
    companion object {
        const val SNACK = 1
        const val TOAST = 2
        const val INLINE = 3
        const val ALERT = 4
        const val DIALOG = 5
        const val REDIRECT = 6
    }
}