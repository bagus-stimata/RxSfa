package com.erp.distribution.sfa.model.modelenum

enum class EnumOrganizationLevel(var stringId: String, var description: String) {
    SYS("SYS", "System/Top Level"), CORP("CORP", "Corporation Level"), DIV(
        "DIV",
        "Division Level"
    ),
    OTH1("OTH1", "Others 1");

}