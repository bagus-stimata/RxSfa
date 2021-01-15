package com.erp.distribution.sfa.model.modelenum

enum class EnumCurrency(
    var stringId: String,
    var numericCode: Int,
    var description: String,
    var country: String
) {
    IDR("IDR", 360, "Rupiah", "Indonesia"), USD("USD", 840, "US Dollar", "USA"), EUR(
        "EUR",
        978,
        "Euro",
        "Europian"
    ),
    JPY("JPY", 392, "Yen Japan", "Japan"), SGD(
        "SGD",
        702,
        "Singapore Dollar",
        "Singapore"
    ),
    MYR("MYR", 458, "Malaysian Ringgit", "Malaysia"), THB(
        "THB",
        764,
        "Bath",
        "Thailand"
    ),
    CNH("CNH", 156, "Yuan Renminbi", "China Republic"), HKD(
        "HKD",
        344,
        "Hongkong Dollar",
        "Hongkong"
    ),
    TWD("TWD", 901, "New Taiwan Dollar", "Taiwan"), OTH("OTH", 0, "Others", "");

}