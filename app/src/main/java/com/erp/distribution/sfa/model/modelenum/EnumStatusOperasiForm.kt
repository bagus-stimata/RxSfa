package com.erp.distribution.sfa.model.modelenum

enum class EnumStatusOperasiForm(val strCode: String, val description: String) {
    OPEN("OPEN", "Status Open"), ADD_NEW("ADD_NEW", "Status Add New"), EDIT_FORM(
        "EDIT_FORM",
        "Status Edit Form"
    ),
    ADD_ITEMS("ADD_ITEMS", "Status Add Items"), EDIT_ITEMS(
        "EDIT_ITEMS",
        "Status Edit Items"
    ),
    EDIT_PRICE("EDIT_PRICE", "Status Edit Item Prices"), OTHER("OTHER", "Other Reserved");

}