package com.erp.distribution.sfa.model.modelenum

enum class EnumTipeImporFromFile(var stringId: String, var description: String) {
    SO_DK3("SO_DK", "Sales Order DK3"), SO_DK_MN(
        "SO_DK_MN",
        "Sales Order DK Mini"
    ),
    PINV_DK("PINV_DK", "Purchase Invoice DK"), SO_BEE(
        "SO_BEE",
        "Sales Order Bee Accounting"
    ),
    SO_MATRIX_HEADER(
        "SO_MATRIX_HEADER",
        "Sales Order Header Matrix"
    ),
    SO_MATRIX_DETIL(
        "SO_MATRIX_DETIL",
        "Sales Order Detil Matrix"
    ),
    SO_MATRIX_CUSTOMER(
        "SO_MATRIX_CUSTOMER",
        "Sales Order Customer Matrix"
    ),
    SO_MATRIX_PRODUCT(
        "SO_MATRIX_PRODUCT",
        "Sales Order Product Matrix"
    ),
    SO_AGLIS_HEADER(
        "SO_AGLIS_HEADER",
        "Sales Order Header Aglis KAO"
    ),
    SO_AGLIS_DETIL(
        "SO_AGLIS_DETIL",
        "Sales Order Detil Aglis KAO"
    ),
    SO_AGLIS_HEADER_RETUR(
        "SO_AGLIS_HEADER_RET",
        "Sales Order Header Aglis KAO RETUR"
    ),
    SO_AGLIS_DETIL_RETUR(
        "SO_AGLIS_DETIL_RET",
        "Sales Order Detil Aglis KAO RETUR"
    ),
    SO_AGLIS_CUSTOMER(
        "SO_AGLIS_CUSTOMER",
        "Sales Order Customer Aglis KAO"
    ),
    SO_AGLIS_PRODUCT("SO_AGLIS_PRODUCT", "Sales Order Product Aglis KAO"), ND6_INV(
        "ND6_INV",
        "Sls Inv ND6"
    ),
    ND6_RET("ND6_F", "Sls Retur ND6"), INV_FDN(
        "ND6_RET",
        "Nota Piutang Debit-Credit"
    ),
    AP_FDN("AP_FDN", "Invoice Nota Hutang Debit"), STK_OPNAME_ITEMS(
        "OPN_ITMS",
        "Stok Opname Items"
    ),
    PRC_ALT_ITEMS("PRCALT_ITMS", "Harga Alternetif Items"), SLS_DTL(
        "SLS_DTL",
        "DES Green Sales Detil Excel"
    );

}