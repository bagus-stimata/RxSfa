package com.erp.distribution.sfa.domain.exception

import com.erp.distribution.sfa.domain.exception.annotation.ExceptionType

class SnackBarException(
    override val code: Int,
    override val message: String
) : CleanException(code, ExceptionType.SNACK, message)