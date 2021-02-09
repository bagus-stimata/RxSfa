package com.erp.distribution.sfa.domain.model

import com.erp.distribution.sfa.domain.exception.Redirect


data class Redirect(@Redirect val redirect: Int, val redirectObject: Any? = null)