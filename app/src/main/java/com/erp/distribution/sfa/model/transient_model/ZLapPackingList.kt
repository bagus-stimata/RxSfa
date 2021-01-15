package com.erp.distribution.sfa.model.transient_model

import java.util.*

class ZLapPackingList {
    var id: Long? = null
    var grup1: String? = null
    var grup2: String? = null
    var grup3: String? = null
    var pcode: String? = null
    var pname: String? = null
    var convfact1: Int? = null
    var convfact2: Int? = null
    var uom1: String? = null
    var uom2: String? = null
    var uom3: String? = null
    var sjdate: Date? = null

    //**SALDO AWAL
    //**SALDO AKHIR
    var qtyPcs: Int? = null
    var qtyBes: Int? = null
    var qtySed: Int? = null
    var qtyKec: Int? = null
    override fun toString(): String {
        return "" + id + ""
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (id == null) 0 else id.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is ZLapPackingList) {
            return false
        }
        val other = obj
        if (id == null) {
            if (other.id != null) {
                return false
            }
        } else if (id != other.id) {
            return false
        }
        return true
    }
}