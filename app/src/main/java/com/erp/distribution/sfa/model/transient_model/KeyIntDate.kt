package com.erp.distribution.sfa.model.transient_model

import java.util.*

class KeyIntDate {
    var id :Int = 0
    var date: Date? = null

    constructor() {}
    constructor(iD: Int, date: Date?) : super() {
        id = iD
        this.date = date
    }

    fun getID(): Long {
        return id.toLong()
    }

    fun setID(iD: Int) {
        id = iD
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + id
        result = prime * result + if (date == null) 0 else date.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is KeyIntDate) {
            return false
        }
        val other = obj
        if (id != other.id) {
            return false
        }
        if (date == null) {
            if (other.date != null) {
                return false
            }
        } else if (date != other.date) {
            return false
        }
        return true
    }
}