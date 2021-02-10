package com.erp.distribution.sfa.domain.exception

import com.erp.distribution.sfa.domain.exception.annotation.ExceptionType
import com.erp.distribution.sfa.domain.exception.model.Tag

class InlineException(
    override val code: Int,
    vararg val tags: Tag
) : CleanException(code, ExceptionType.INLINE, null)