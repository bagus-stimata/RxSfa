package com.erp.distribution.sfa.model.modelenum

enum class EnumTipeStockOpname(//Principal
    var stringId: String, var description: String
) {
    ALL("ALL", "Semua Barang"), PAR("PAR", "Parsial"), F_ADJ("F_ADJ", "Form Adjust");

}