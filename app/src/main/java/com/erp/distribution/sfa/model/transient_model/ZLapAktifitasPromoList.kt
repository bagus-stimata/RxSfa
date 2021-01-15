package com.erp.distribution.sfa.model.transient_model

import java.util.*

class ZLapAktifitasPromoList {
    var id: Long? = null
    var grup1: String? = null
    var grup2: String? = null
    var grup3: String? = null
    var promoid: String? = null
    var promodesc: String? = null
    var invoiceno: String? = null
    var invoicedate: Date? = null
    var custarea: String? = null
    var custsubarea: String? = null
    var custgroup: String? = null
    var custsubgroup: String? = null
    var custno: String? = null
    var custname: String? = null
    var address: String? = null
    var city: String? = null

    //NILAI NOTA
    var subtotalafterdiscafterppn: Double? = null

    //PROMO BARANG
    var freebonuspcode: String? = null
    var freebonuspname: String? = null
    var freebonusqtypcs: Int? = null
    var freebonusqtybes: Int? = null
    var freebonusqtysed: Int? = null
    var freebonusqtykec: Int? = null
    var freebonusafterppn: Double? = null

    //PROMO DISC
    var disc1: Double? = null
    var disc2: Double? = null
    var disc1afterppn: Double? = null
    var disc2afterppn: Double? = null

    //PROMO CASHBACK
    var cashbackafterppn: Double? = null
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
        if (obj !is ZLapAktifitasPromoList) {
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

    override fun toString(): String {
        return "" + id + ""
    }
}