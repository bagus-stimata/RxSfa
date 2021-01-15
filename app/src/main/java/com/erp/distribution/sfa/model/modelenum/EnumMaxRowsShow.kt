package com.erp.distribution.sfa.model.modelenum

enum class EnumMaxRowsShow(//Principal
    var stringId: String, var intId: Int, var description: String
) {
    MAX_1("MAX_1", 1, "max 1 Bulan"), MAX_3("MAX_3", 2, "max 3 Bulan"), MAX_6(
        "MAX_6",
        3,
        "max 6 Bulan"
    ),
    MAX_12("MAX_12", 4, "max 12 Bulan"), ALL("ALL", 100, "Semua Data");

}