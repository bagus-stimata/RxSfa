package com.erp.distribution.sfa.model.modelenum

enum class EnumTipeFakturBeli(//Principal
    var stringId: String, var description: String
) {
    F("F", "Faktur"), FF("FF", "Faktur from GR"), FI("FI", "Pembelian Intern"), INFG(
        "INFG",
        "Free Good"
    ),
    R("R", "Retur"), DTR("DTR", "Destroy or Burning"), PO(
        "PO",
        "Purchase Order(Non Stockable)"
    ),
    GR("GR", "Good Receipt"), FDN("FDN", "Nota Hutang Debit(Non Stockable)"), RCN(
        "RCN",
        "Nota Hutang Credit(Non Stockable)"
    ),
    SR("SR", "Permintaan Barang Gudang Pusat(Non Stockable)"), PR(
        "PR",
        "Purchase Requisition(Non Stockable)"
    );

}