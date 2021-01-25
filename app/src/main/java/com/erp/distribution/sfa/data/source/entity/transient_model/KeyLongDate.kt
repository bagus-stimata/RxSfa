package com.erp.distribution.sfa.data.source.entity.transient_model

import java.util.*

class KeyLongDate {
    var iD: Long = 0
    var date: Date? = null

    constructor() {}
    constructor(iD: Long, date: Date?) : super() {
        this.iD = iD
        this.date = date
    }


}