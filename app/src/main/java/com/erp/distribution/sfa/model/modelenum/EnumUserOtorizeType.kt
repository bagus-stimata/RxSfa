package com.erp.distribution.sfa.model.modelenum

enum class EnumUserOtorizeType(val strCode: String, val description: String) {
    SUPER1("SUPER1", "Supervisor/Administartor Level 1"), SUPER2(
        "SUPER2",
        "Supervisor/Administartor Level 2"
    ),
    SLS_ADM1("SLS_ADM1", "Sales Admin Level 1"), SLS_ADM2(
        "SLS_ADM2",
        "Sales Admin Level 2"
    ),
    SLS_ADM3("SLS_ADM3", "Sales Admin Level 3"), SLS_SSP(
        "SLS_SSP",
        "Sales Admin SSP"
    ),
    WH_ADM1("WH_ADM1", "Gudang Level 1"), WH_ADM2("WH_ADM2", "Gudang Level 2"), WH_SSP(
        "WH_SSP",
        "Gudang Admin SSP"
    ),
    AR_ADM1("AR_ADM1", "Pelunasan Level 1"), AR_ADM2(
        "AR_ADM2",
        "Pelunasan Level 2"
    ),
    APAR_SSP("APAR_SSP", "AP/AR Admin SSP"), CB_ADM1("CB_ADM1", "Cash Bank/Kasir Level 1"), CB_ADM2(
        "CB_ADM2",
        "Cash Bank/Kasir Level 2"
    ),
    ACC_ADM1("ACC_ADM1", "Accounting Level 1"), ACC_ADM2(
        "ACC_ADM2",
        "Accounting Level 2"
    ),
    MANAGER1("MANAGER1", "Manajer Level 1"), MANAGER2(
        "MANAGER2",
        "Manajer Level 2"
    ),
    MANAGER3("MANAGER3", "Manajer Level 3/SPV"), SALESMAN1(
        "SALESMAN",
        "Salesman Level 1"
    ),
    SALESMAN2("SALESMAN", "Salesman Level 2"), GUEST("GUEST", "Guest");

}