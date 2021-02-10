package com.erp.distribution.sfa.domain.exception

import com.erp.distribution.sfa.domain.exception.annotation.ExceptionType


class AlertException(
    override val code: Int,
    override val message: String,
    val title: String? = null
) : CleanException(code, ExceptionType.ALERT, message)