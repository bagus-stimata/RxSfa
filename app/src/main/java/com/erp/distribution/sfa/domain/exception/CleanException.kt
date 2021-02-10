package com.erp.distribution.sfa.domain.exception

import com.erp.distribution.sfa.domain.exception.annotation.ExceptionType

open class CleanException(
    open val code: Int,
    @ExceptionType val exceptionType: Int,
    override val message: String?
) : Throwable(message)