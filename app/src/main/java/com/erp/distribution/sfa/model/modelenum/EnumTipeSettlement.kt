package com.erp.distribution.sfa.model.modelenum

enum class EnumTipeSettlement(var intId: Int, var stringId: String, var description: String) {
    AR_CB_SETORAN(1, "AR_CB_SETORAN", "Payment: AR Cash vs Cash Bank Deposit"), WHSTK_REALSTK(
        2,
        "WHSTK_REALSTK",
        "Warehouse Stock Card vs Real Stock Sales Admin"
    ),
    OTH1(10, "OTH1", "Other");

}