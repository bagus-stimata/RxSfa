package com.erp.distribution.sfa.model.transient_model

import java.util.*

class KeyLongDate {
    var iD: Long = 0
    var date: Date? = null

    constructor() {}
    constructor(iD: Long, date: Date?) : super() {
        this.iD = iD
        this.date = date
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (iD xor (iD ushr 32)).toInt()
        result = prime * result + if (date == null) 0 else date.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as KeyLongDate
        if (iD != other.iD) return false
        if (date == null) {
            if (other.date != null) return false
        } else if (date != other.date) return false
        return true
    }
}