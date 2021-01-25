package com.erp.distribution.sfa.data.source.entity.modelenum

enum class EnumTipeSuratJalan(var intId: Int, var stringId: String, var description: String) {
    SJ_PENGIRIMAN(1, "SJ_PENGIRIMAN", "Surat Jalan Pengiriman"), SJ_PENAGIHAN(
        2,
        "SJ_PENAGIHAN",
        "Surat Jalan Penagihan"
    ),
    OTH1(10, "OTH1", "Other");

}