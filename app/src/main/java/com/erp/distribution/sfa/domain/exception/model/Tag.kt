package com.erp.distribution.sfa.domain.exception.model

import com.erp.distribution.sfa.domain.exception.annotation.TagName


data class Tag(@TagName val name: String, val message: String?)