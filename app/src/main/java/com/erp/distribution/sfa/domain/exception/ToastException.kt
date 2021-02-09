package com.erp.distribution.sfa.domain.exception


class ToastException(
    override val code: Int,
    override val message: String
) : CleanException(code, ExceptionType.TOAST, message)