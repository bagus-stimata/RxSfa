package com.erp.distribution.sfa.model.modelenum

enum class EnumJenisNomorUrut(
    var stringId: String,
    var sysvarCode: String,
    var columnToCek: String,
    var description: String
) {
    URUT_M_CUSTOMER(
        "URUT_M_CUSTOMER",
        "URUT_CUSTOMER_",
        "custno",
        "Nomor urut Master Customer/Outlet"
    ),
    URUT_M_MATERIAL(
        "URUT_M_MATERIAL",
        "URUT_MATERIAL_",
        "pcode",
        "Nomor urut Master Material/Product"
    ),
    URUT_M_SALESMAN(
        "URUT_M_SALESMAN",
        "URUT_VENDOR_",
        "spcode",
        "Nomor urut Master Salesman"
    ),
    URUT_M_VENDOR(
        "URUT_M_VENDOR",
        "URUT_SALESMAN_",
        "vcode",
        "Nomor urut Master Vendor/Supplier"
    ),
    URUT_PENJ_ORDER(
        "URUT_PENJ_ORDER",
        "URUT_ORDER_JUAL_",
        "orderno",
        "Nomor urut Order Penjualan"
    ),
    URUT_PENJ_INV(
        "URUT_PENJ_INV",
        "URUT_INV_JUAL_",
        "invoiceno",
        "Nomor urut Invoice Penjualan"
    ),
    URUT_PENJ_RET(
        "URUT_PENJ_RET",
        "URUT_RETUR_JUAL_",
        "invoiceno",
        "Nomor urut Retur Penjualan"
    ),
    URUT_SJ_PENGIRIMAN(
        "URUT_SJ_PENGIRIMAN",
        "URUT_SJ_KIRIM_",
        "sjPengirimanNo",
        "Nomor urut Surat Jalan Pengiriman"
    ),
    URUT_SJ_PENAGIHAN(
        "URUT_SJ_PENAGIHAN",
        "URUT_SJ_PENAGIH_",
        "sjPenagihanNo",
        "Nomor urut Surat Jalan Penagihan"
    ),
    URUT_AR(
        "URUT_AR",
        "URUT_AR_",
        "noRek",
        "Nomor urut Pelunasan Piutang/Account Receivable"
    ),
    URUT_BELI_PO(
        "URUT_BELI_PO",
        "URUT_PO_BELI_",
        "nopo",
        "Nomor urut PO Purchase INVOICE"
    ),
    URUT_BELI_PO_ORDER(
        "URUT_BELI_PO_ORDER",
        "URUT_PO_BELI_ORDER_",
        "nopo",
        "Nomor urut PO Purchase ORDER"
    ),
    URUT_BELI_GR(
        "URUT_BELI_GR",
        "URUT_BELI_GR_",
        "nopo",
        "Nomor urut Good Receipt (Purchase)"
    ),
    URUT_BELI_PI(
        "URUT_BELI_PI",
        "URUT_BELI_PI_",
        "nopo",
        "Nomor urut Purchase Invoice (Purchase)"
    ),
    URUT_BELI_RET(
        "URUT_BELI_RET",
        "URUT_RETUR_BELI_",
        "invoiceno",
        "Nomor urut Retur Pembelian"
    ),
    URUT_AP(
        "URUT_AP",
        "URUT_AP_",
        "noRek",
        "Nomor urut Pembayaran Hutang/Account Payable"
    ),
    URUT_STK_RE(
        "URUT_STK_RE",
        "URUT_STK_RE_",
        "nopo",
        "Nomor urut STOCK Requisition"
    ),
    URUT_BELI_PR(
        "URUT_BELI_PR",
        "URUT_BELI_PR_",
        "nopo",
        "Nomor urut Purchase Requisition"
    ),
    URUT_PAJAK_KEL(
        "URUT_TAX",
        "URUT_TAX_",
        "taxNumber",
        "Nomor urut Pajak Keluaran Berjalan"
    ),
    URUT_PRICEH(
        "URUT_PRICEH",
        "URUT_PRICEH_",
        "noRek",
        "Nomor urut Perubahan Harga"
    ),
    URUT_PRICEH_ALT("URUT_PRICEH_ALT", "URUT_PRICEH_ALT_", "noRek", "Nomor urut Harga Alternatif"),  /*
     * belum
     */
    URUT_KB_DEPOSIT(
        "URUT_KB_DEPOSIT",
        "URUT_KAS_MASUK_",
        "noRek",
        "Nomor urut Kash & Bank: Deposit/Kas Masuk"
    ),
    URUT_KB_PAYMENT(
        "URUT_KB_PAYMENT",
        "URUT_KAS_KELUAR_",
        "noRek",
        "Nomor urut Kash & Bank: Payment/Kas Keluar"
    ),
    URUT_JURNAL_MEM(
        "URUT_JURNAL_MEM",
        "URUT_JURNAL_",
        "noRek",
        "Nomor urut transaksi jurnal manual"
    ),  //*****
    URUT_STOK_OPNAME(
        "URUT_STOK_OPNAME",
        "URUT_STOK_OPNAM_",
        "noRek",
        "Nomor urut Stok Opname"
    ),
    URUT_STOK_TRANSFER(
        "URUT_STOK_TRANSFER",
        "URUT_MUTASI_PER_",
        "noRek",
        "Nomor urut Mutasi Stok Gudang"
    ),
    URUT_UM(
        "URUT_UM",
        "URUT_UM_",
        "noRek",
        "Nomor urut Uang Muka"
    ),
    URUT_PROMO_RULES(
        "URUT_PROMO_RULE",
        "URUT_PROMO_RULE_",
        "kode1",
        "Nomor urut Promotion Rules/Aktifitas Promo"
    ),
    URUT_DISC_NOTA("URUT_DISC_NOTA", "URUT_DISC_NOTA_", "kode1", "Nomor urut Diskon Nota");

}