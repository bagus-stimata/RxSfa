package com.erp.distribution.sfa.model.modelenum

enum class EnumStatusGiro(
    var intCode: Int,
    var strCode: String,
    var stringCodeLong: String,
    var description: String
) {
    GIRO_OPEN(0, "O", "OPEN", "Open atau blum ada status"), GIRO_CAIR(
        1,
        "C",
        "CAIR",
        "Giro Cair"
    ),
    GIRO_TOLAK(2, "T", "REJECT", "Giro Tolak/Blong");

}