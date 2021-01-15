package com.erp.distribution.sfa.model.transient_model

import java.util.*

class ZLapMutasiStock {
    var iD: Long = 0
    var noUrut = 0.0
    var idProduct = 0.0
    var hargaBeli_Pcs = 0.0
    var hargaJual_Pcs = 0.0
    var string1 = ""
    var string2 = ""
    var string3 = ""
    var string4 = ""
    var string5 = ""
    var string6 = ""
    var string7 = ""
    var grup1 = ""
    var grup2 = ""
    var grup3 = ""
    var wareHouse = ""
    var vendor = ""
    var pcode = ""
    var pname = ""
    var convfactString = ""
    var convfact1 = 0.0
    var convfact2 = 0.0
    var convfact3 = 0.0
    var stockdate: Date? = null

    //**SALDO AWAL
    var saldoAwalPcs = 0.0
    var saldoAwal_uom1 = 0.0
    var saldoAwal_uom2 = 0.0
    var saldoAwal_uom3 = 0.0
    var saldoAwal_uom4 = 0.0
    var saldoAwalString_uom1 = ""
    var saldoAwalString_uom2 = ""
    var saldoAwalString_uom3 = ""
    var saldoAwalString_uom4 = ""
    var saldoAwalString = ""
    var saldoAwalNilaiBeli = 0.00001
    var saldoAwalNilaiJual = 0.00001

    //**PENJUALAN(-)
    var penjualanPcs = 0.0
    var penjualan_uom1 = 0.0
    var penjualan_uom2 = 0.0
    var penjualan_uom3 = 0.0
    var penjualan_uom4 = 0.0
    var penjualanNilaiBeli = 0.00001
    var penjualanNilaiJual = 0.00001
    var penjualanString = ""

    //**RETUR PENJUALAN(+)
    var returPenjualanPcs = 0.0
    var returPenjualan_uom1 = 0.0
    var returPenjualan_uom2 = 0.0
    var returPenjualan_uom3 = 0.0
    var returPenjualan_uom4 = 0.0
    var returPenjualanNilaiBeli = 0.00001
    var returPenjualanNilaiJual = 0.00001
    var returPenjualanString = ""

    //**PEMBELIAN (+)
    var pembelianPcs = 0.0
    var pembelian_uom1 = 0.0
    var pembelian_uom2 = 0.0
    var pembelian_uom3 = 0.0
    var pembelian_uom4 = 0.0
    var pembelianNilaiBeli = 0.00001
    var pembelianNilaiJual = 0.00001
    var pembelianString = ""

    //**RETUR PEMBELIAN (-)
    var returPembelianPcs = 0.0
    var returPembelian_uom1 = 0.0
    var returPembelian_uom2 = 0.0
    var returPembelian_uom3 = 0.0
    var returPembelian_uom4 = 0.0
    var returPembelianNilaiBeli = 0.00001
    var returPembelianNilaiJual = 0.00001
    var returPembelianString = ""

    //**TRANSFER IN (+)
    var transferInPcs = 0.0
    var transferIn_uom1 = 0.0
    var transferIn_uom2 = 0.0
    var transferIn_uom3 = 0.0
    var transferIn_uom4 = 0.0
    var transferInNilaiBeli = 0.00001
    var transferInNilaiJual = 0.00001
    var transferInString = ""

    //**TRANSFER OUT (+)
    var transferOutPcs = 0.0
    var transferOut_uom1 = 0.0
    var transferOut_uom2 = 0.0
    var transferOut_uom3 = 0.0
    var transferOut_uom4 = 0.0
    var transferOutNilaiBeli = 0.00001
    var transferOutNilaiJual = 0.00001
    var transferOutString = ""

    //**PENYESUAIAN/STOCK OPNAME (+/-)
    var penyesuaianPcs = 0.0
    var penyesuaian_uom1 = 0.0
    var penyesuaian_uom2 = 0.0
    var penyesuaian_uom3 = 0.0
    var penyesuaian_uom4 = 0.0
    var penyesuaianNilaiBeli = 0.00001
    var penyesuaianNilaiJual = 0.00001
    var penyesuaianString = ""

    //**SALDO AKHIR
    var saldoAkhirPcs = 0.0
    var saldoAkhir_uom1 = 0.0
    var saldoAkhir_uom2 = 0.0
    var saldoAkhir_uom3 = 0.0
    var saldoAkhir_uom4 = 0.0
    var saldoAkhirString_uom1 = ""
    var saldoAkhirString_uom2 = ""
    var saldoAkhirString_uom3 = ""
    var saldoAkhirString_uom4 = ""
    var saldoAkhirNilaiBeli = 0.00001
    var saldoAkhirNilaiJual = 0.00001
    var saldoAkhirString = ""
    var saldoAkhirAvailableString = ""

    /*
       * Untuk laporan Posisi Stok pada Gudang
       */
    var saldoAkhirGudang1Desc = ""
    var saldoAkhirGudang2Desc = ""
    var saldoAkhirGudang3Desc = ""
    var saldoAkhirGudang4Desc = ""
    var saldoAkhirGudang5Desc = ""
    var saldoAkhirGudang1String = ""
    var saldoAkhirGudang2String = ""
    var saldoAkhirGudang3String = ""
    var saldoAkhirGudang4String = ""
    var saldoAkhirGudang5String = ""
    var saldoAkhirGudang1_Double1 = 0.0
    var saldoAkhirGudang1_Double2 = 0.0
    var saldoAkhirGudang2_Double1 = 0.0
    var saldoAkhirGudang2_Double2 = 0.0
    var saldoAkhirGudang3_Double1 = 0.0
    var saldoAkhirGudang3_Double2 = 0.0
    var saldoAkhirGudang4_Double1 = 0.0
    var saldoAkhirGudang4_Double2 = 0.0
    override fun toString(): String {
        return iD.toString() + ""
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (iD xor (iD ushr 32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is ZLapMutasiStock) {
            return false
        }
        return if (iD != obj.iD) {
            false
        } else true
    }
}