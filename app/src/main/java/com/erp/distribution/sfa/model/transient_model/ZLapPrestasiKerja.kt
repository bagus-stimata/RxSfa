package com.erp.distribution.sfa.model.transient_model

import java.util.*

class ZLapPrestasiKerja {
    var id: Long? = null
    var grup1: String? = null
    var grup2: String? = null
    var grup3: String? = null
    var hari: String? = null
    var tanggal: Date? = null

    //OUTLET ACTIVE
    var jumlahToko: Int? = null
    var jumlahNota: Int? = null
    var efectiveCall: Int? = null
    var skuSold: Int? = null
    var lines: Int? = null

    // SKU / EC
    var rataRata: Double? = null
    var rataRata2: Double? = null
    var totalBeforediscBeforeppn: Double? = null
    var discPerbarang: Double? = null
    var discNota: Double? = null
    var returdpp: Double? = null
    var returppn: Double? = null
    var dpp: Double? = null
    var ppn: Double? = null
    var totalAfterdiscAfterppn: Double? = null
    var string1: String? = null
    var string2: String? = null
    var integer1: Int? = null
    var integer2: Int? = null
    var double1: Double? = null
    var double2: Double? = null
    var date1: Date? = null
    var date2: Date? = null
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
        if (obj !is ZLapPrestasiKerja) {
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