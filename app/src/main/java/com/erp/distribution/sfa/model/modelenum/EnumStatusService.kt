package com.erp.distribution.sfa.model.modelenum

enum class EnumStatusService(val intCode: Int, val strCode: String, val description: String) {
    BELUM(0, "BELUM", "Belum Service/Baru Masuk"), PROSES(
        1,
        "PROSES",
        "Dalam Proses Service"
    ),
    SELESAI(2, "SELESAI", "Telah Selesai Service"), CANCEL(10, "CANCEL", "Batal Service");

}