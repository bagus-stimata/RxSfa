package com.erp.distribution.sfa.model.modelenum

enum class EnumFormatFaktur(var intCode: Int, var strCode: String, var description: String) {
    FORMAT_FAKTUR_JUAL_1(1, "FFJ1", "Format Faktur Penjualan 1"), FORMAT_FAKTUR_JUAL_2(
        2,
        "FFJ2",
        "Format Faktur Penjualan 2"
    ),
    FORMAT_FAKTUR_JUAL_3(3, "FFJ3", "Format Faktur Penjualan 3"), FORMAT_FAKTUR_JUAL_4(
        4,
        "FFJ4",
        "Format Faktur Penjualan 4"
    ),
    FORMAT_FAKTUR_JUAL_5(
        5,
        "FFJ5",
        "Format Faktur Penjualan Scylla: dengan isi per-karton"
    ),
    FORMAT_FAKTUR_JUAL_6(
        6,
        "FFJ6",
        "Format Faktur Penjualan Scylla: dengan isi per-karton dan DPP"
    ),
    FORMAT_FAKTUR_JUAL_7(
        7,
        "FFJ7",
        "Format Faktur Penjualan Scylla: dengan isi per-karton, tiga satuan dan DPP"
    ),
    FORMAT_FAKTUR_JUAL_8(8, "FFJ8", "Format Faktur Penjualan Test"), FORMAT_FAKTUR_JUAL_9(
        9,
        "FFJ9",
        "Format Faktur NESTLE"
    ),
    FORMAT_FAKTUR_JUAL_10(10, "FFJ10", "Format Faktur Welhap"), FORMAT_FAKTUR_JUAL_11(
        11,
        "FFJ11",
        "Format Faktur Turen Indah"
    ),
    FORMAT_FAKTUR_JUAL_101(101, "FFJ101", "Format STRUK"), FORMAT_FAKTUR_JUAL_NOTITLE(
        20,
        "FFJ20",
        "Format Faktur No Title"
    ),
    FORMAT_FAKTUR_TRANSFER_61(
        61,
        "FFT61",
        "Format Faktur Transfer/Mutasi Gudang"
    ),
    FORMAT_FAKTUR_TRANSFER_62(
        62,
        "FFT62",
        "Surat Jalan Transfer/Mutasi Gudang"
    ),
    FORMAT_FAKTUR_BELI_21(21, "FFB21", "Format Faktur Pembelian 1"), FORMAT_FAKTUR_BELI_22(
        22,
        "FFB22",
        "Format Faktur Pembalian 2"
    ),
    FORMAT_UM_101(101, "FUM1", "Format Voucher Uang Muka Beli/Jual1");

}