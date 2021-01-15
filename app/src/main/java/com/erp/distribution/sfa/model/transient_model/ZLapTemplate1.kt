package com.erp.distribution.sfa.model.transient_model

import java.util.*

class ZLapTemplate1 {
    var iD: Long = 0
    var noUrut = 0
    var grup1 = ""
    var grup2 = ""
    var grup3 = ""
    var string1 = ""
    var string2 = ""
    var string3 = ""
    var string4 = ""
    var string5 = ""
    var string6 = ""
    var string7 = ""
    var string8 = ""
    var string9 = ""
    var string10 = ""
    var int1 = 0
    var int2 = 0
    var int3 = 0
    var int4 = 0
    var int5 = 0
    var double1 = 0.0
    var double2 = 0.0
    var double3 = 0.0
    var double4 = 0.0
    var double5 = 0.0
    var date1: Date? = null
    var date2: Date? = null
    var date3: Date? = null
    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (iD xor (iD ushr 32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is ZLapTemplate1) {
            return false
        }
        return if (iD != obj.iD) {
            false
        } else true
    }

    override fun toString(): String {
        return "" + iD + ""
    }
}