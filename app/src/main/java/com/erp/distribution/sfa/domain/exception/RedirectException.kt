package com.erp.distribution.sfa.domain.exception

import com.erp.distribution.sfa.domain.exception.annotation.ExceptionType
import com.erp.distribution.sfa.domain.exception.annotation.Redirect

class RedirectException(
    override val code: Int,
    val redirect: Redirect
) : CleanException(code, ExceptionType.REDIRECT, null)