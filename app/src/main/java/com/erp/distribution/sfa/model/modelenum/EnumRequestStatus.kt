package com.erp.distribution.sfa.model.modelenum

enum class EnumRequestStatus(var intId: Int, var description: String, var shortCode: String) {
    OPEN(0, "OPEN", ""), REQUEST(1, "REQUEST", "req"), APPROVE(2, "APPROVE", "Apv"), APV_HALF(
        3,
        "APPV_HALF",
        "Apv Half"
    ),
    REJECTED(4, "REJECTED", "Rjt"), OTH1(10, "Lain-lain", "");

}