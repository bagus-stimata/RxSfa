package com.erp.distribution.sfa.model.modelenum

enum class EnumStatusProteksi(val intCode: Int, val strCode: String, val description: String) {
    DO_NOTHING(0, "N", "Tidak ada operasi apapun"), WARNING(1, "W", "Sedang ada operasi"), REJECT(
        2,
        "R",
        "Sedang ada operasi penambahan"
    );

}