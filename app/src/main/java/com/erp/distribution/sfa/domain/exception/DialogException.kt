package com.erp.distribution.sfa.domain.exception

import com.erp.distribution.sfa.domain.exception.annotation.ExceptionType
import com.erp.distribution.sfa.domain.exception.model.Dialog


class DialogException(
    override val code: Int,
    val dialog: Dialog
) : CleanException(code, ExceptionType.DIALOG, null)