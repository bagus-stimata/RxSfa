package com.erp.distribution.sfa.model.modelenum

enum class EnumMonth(
    var intId: Int,
    strCode: String,
    var descIndonesia: String,
    var descEnglish: String
) {
    JAN(0, "JAN", "Januari", "January"), FEB(1, "FEB", "Februari", "February"), MAR(
        2,
        "MAR",
        "Maret",
        "March"
    ),
    APRL(3, "APRL", "April", "April"), MEI(4, "MEI", "Mei", "May"), JUN(
        5,
        "JUN",
        "Juni",
        "June"
    ),
    JUL(6, "JUL", "Juli", "July"), AGT(7, "AGT", "Agustus", "August"), SEP(
        8,
        "SEP",
        "September",
        "September"
    ),
    OKT(9, "OKT", "Oktober", "October"), NOP(10, "NOP", "Nopember", "November"), DES(
        11,
        "DES",
        "Desember",
        "December"
    );

    var strCode: String? = null

    companion object {
        fun getMonth(month_Int: Int): EnumMonth? {
            var enumMonthSelected: EnumMonth? = null
            when (month_Int) {
                0 -> enumMonthSelected = JAN
                1 -> enumMonthSelected = FEB
                2 -> enumMonthSelected = MAR
                3 -> enumMonthSelected = APRL
                4 -> enumMonthSelected = MEI
                5 -> enumMonthSelected = JUN
                6 -> enumMonthSelected = JUL
                7 -> enumMonthSelected = AGT
                8 -> enumMonthSelected = SEP
                9 -> enumMonthSelected = OKT
                10 -> enumMonthSelected = NOP
                11 -> enumMonthSelected = DES
                else -> {
                }
            }
            return enumMonthSelected
        }
    }
}