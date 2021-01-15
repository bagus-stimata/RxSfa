package com.erp.distribution.sfa.model.modelenum

enum class Enum3Level(var intCode: Int, var strCode: String, var description: String) {
    LOW(0, "LOW", "Low"), MEDIUM(1, "MED", "Medium"), HIGH(2, "HIGH", "High");

    var stringCodeLong: String? = null

}