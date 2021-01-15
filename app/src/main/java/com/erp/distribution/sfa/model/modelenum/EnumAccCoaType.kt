package com.erp.distribution.sfa.model.modelenum

enum class EnumAccCoaType(//LR
    var stringId: String, var description: String
) {
    CASH_BA("CASH_BA", "Cash And Bank"), ACC_REC(
        "ACC_REC",
        "Account Receivable"
    ),
    FIX_ASS("FIX_ASS", "Fix Assets"), ACCU_DEP(
        "ACCU_DEP",
        "Accumulated Depreciation"
    ),
    ACC_PAY("ACC_PAY", "Account Payable"), REVENUE("REVENUE", "Revenue"),  //LR
    COGS("COGS", "Cost of Goods Sold"),  //LR
    INVENTO("INVENTO", "Inventory"), EXPENSE("EXPENSE", "Expense"),  //LR
    EQUITY("EQUITY", "Equity/Modal"), OTH_INCO("OTH_INCO", "Other Income"),  //LR
    OTH_CU_L("OTH_CU_L", "Other Current Liability"), OTH_CU_A(
        "OTH_CU_S",
        "Other Current Asset"
    ),
    OTH_EXPE("OTH_EXPE", "Other Expense"),  //LR
    OTH_REVE("OTH_REVE", "Other Revenue"), DUMMY(
        "DUMMY", "Dummy" //Dummy adalah akun yang tidak akan dibaca: Coa type harus dikeluarkan
    );

}