package com.erp.distribution.sfa.domain.exception.model

import com.erp.distribution.sfa.domain.exception.annotation.Redirect


data class Redirect(@Redirect val redirect: Int, val redirectObject: Any? = null)