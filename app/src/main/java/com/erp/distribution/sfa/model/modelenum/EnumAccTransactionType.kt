package com.erp.distribution.sfa.model.modelenum

enum class EnumAccTransactionType(
    var stringId: String,
    var shortCode: String,
    var description: String
) {
    PR("PR", "PR", "Purchase Requisition"), PO(
        "PO",
        "PO",
        "Purchase Order"
    ),
    RECE_GOODS("RECE_GOODS", "RCGD", "Receipt Goods"), PUR_INV(
        "PUR_INV",
        "PINV",
        "Purchase Invoice"
    ),
    PUR_RET("PUR_RET", "PRET", "Purchase Return"), AP("AP", "AP", "Account Payment"), SQ(
        "Sq",
        "SQ",
        "Sales Quotation"
    ),
    SO("SO", "SO", "Sales Order"), DO("DO", "DO", "Delivery Order"), SLS_INV(
        "SLS_INV",
        "SINV",
        "Sales Invoice"
    ),
    SLS_SRV("SLS_SRV", "SSRV", "Sales Service"), SLS_RET(
        "SLS_RET",
        "SRET",
        "Sales Return"
    ),
    AR("AR", "AR", "Account Receivable"), STK_ADJ(
        "STK_ADJ",
        "STKADJ",
        "Stock Adjustment/Stock Opname"
    ),
    GIRO_CAIR("GIRO_CAIR", "GRCR", "Pencairan Giro"), UANG_MUKA(
        "UANG_MUKA",
        "UMM",
        "Uang Muka"
    ),
    CB_DEPOSIT("CB_DEPOSIT", "CDEP", "Deposit/Kas Masuk"), CB_PAYMENT(
        "CB_PAYMENT",
        "CPAY",
        "Payment/Kas Keluar"
    ),
    JURNAL_MEM("JURNAL_MEM", "JRMEM", "Jurnal Memorial GL"), JRNL_SO_AW(
        "JRNL_SO_AW",
        "JRSOAW",
        "Saldo Awal Periode Akuntansi"
    ),
    JRNL_SO_AK("JRNL_SO_AK", "JRSOAK", "Saldo Akhir Periode Akuntansi"), PRMO_PAY(
        "PRMO_PAY",
        "PRMPY",
        "Promotion Payment"
    ),
    MUT_STK("MUT_STK", "MTSTK", "Mutasi Stock/Stock Transfer"), OTH_REVE(
        "OTH_REVE",
        "OTHRV",
        "Other Revenue"
    ),
    DN_AP("DN_AP", "DN_AP", "Hutang Awal"), DN_AR("DN_AR", "DN_AR", "Piutang Awal");

}