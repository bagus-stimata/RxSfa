package com.erp.distribution.sfa.domain.utils

import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh

class HeaderDetilSalesHelperImpl : HeaderDetilSalesHelper {
    private val serialVersionUID = 1L
    var konversiProductAndStock: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl()
    private var konversiProductAndStockQtyKembali: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl()

    /*
	 * ***********************
	 */  var ftSalesh = FtSalesh()
    var newFtSalesh = FtSalesh()
    var ftPurchased = FtSalesdItems()
    var newFtPurchased = FtSalesdItems()
    private var ppnPercent = 10.0
    var ppnFloat = 1.1 //Mengunakan Default
    override var isRoundedTotalHeader = true
    override var isRoundedTotalDetil = true

    constructor() {
        initVariable()
    }

    constructor(ftSalesh: FtSalesh, ftSalesd: FtSalesdItems) {
        this.ftSalesh = ftSalesh
        newFtSalesh = ftSalesh

        //Qty dan Qty Kembai Fungsinya untuk Input Detil
        konversiProductAndStock = KonversiProductAndStockHelperImpl(ftSalesd.qty, ftSalesd.fmaterialBean)
        konversiProductAndStockQtyKembali = KonversiProductAndStockHelperImpl(ftSalesd.qtyKembali, ftSalesd.fmaterialBean)
        ftPurchased = ftSalesd
        newFtPurchased = ftSalesd

        /*
		 * Tax Percent
		 */if (ftSalesd.tax) {
            taxPercent = ftSalesd.taxPercent
        } else {
            ftPurchased.taxPercent = 0.0
        }
        initVariable()
    }

    constructor(ftSalesh: FtSalesh) {
        this.ftSalesh = ftSalesh
        newFtSalesh = ftSalesh
        initVariable()
    }

    constructor(ftSalesd: FtSalesdItems) {
        ftPurchased = ftSalesd
        newFtPurchased = ftSalesd

        //Qty dan Qty Kembai Fungsinya untuk Input Detil
        konversiProductAndStock = KonversiProductAndStockHelperImpl(ftSalesd.qty, ftSalesd.fmaterialBean)
        konversiProductAndStockQtyKembali = KonversiProductAndStockHelperImpl(ftSalesd.qtyKembali, ftSalesd.fmaterialBean)
        ftSalesh = ftSalesd.ftSaleshBean
        newFtSalesh = ftSalesd.ftSaleshBean

        /*
		 * Tax Percent
		 */if (ftSalesd.tax) {
            taxPercent = ftSalesd.taxPercent
        } else {
            ftPurchased.taxPercent = 0.0
        }
        initVariable()
    }

    fun initVariable() {
        ppnFloat = ppnPercent / 100 + 1
    }

    //Pprice
    //Tax Percent
    override val taxPercentFloat: Double
        get() = ftPurchased.taxPercent / 100

    override fun setSpricePpnRp() {
        val ppricePpnRp = ftPurchased.sprice * taxPercentFloat
        newFtPurchased.spricePpnRp = ppricePpnRp
    }

    override fun setDetilQtyNET() {
        newFtPurchased.qtyNET = newFtPurchased.qty - newFtPurchased.qtyKembali
    }

    override fun setDetilQty1() {
        //Fungsinya untuk input Detil
        newFtPurchased.qty1 = konversiProductAndStock.getUom1FromSmallest()
    }

    override fun setDetilQty2() {
        //Fungsinya untuk input Detil
        newFtPurchased.qty2 = konversiProductAndStock.getUom2FromSmallest()
    }

    override fun setDetilQty3() {
        //Fungsinya untuk input Detil
        newFtPurchased.qty3 = konversiProductAndStock.getUom3FromSmallest()
    }

    override fun setDetilQty4() {
        newFtPurchased.qty4 = konversiProductAndStock.getUom4FromSmallest()
    }

    override fun setDetilQty1Kembali() {
        //Fungsinya untuk input Detil Kembeli/BTB
        newFtPurchased.qty1Kembali = konversiProductAndStockQtyKembali.getUom1FromSmallest()
    }

    override fun setDetilQty2Kembali() {
        //Fungsinya untuk input Detil Kembeli/BTB
        newFtPurchased.qty2Kembali = konversiProductAndStockQtyKembali.getUom2FromSmallest()
    }

    override fun setDetilQty3Kembali() {
        //Fungsinya untuk input Detil Kembeli/BTB
        newFtPurchased.qty3Kembali = konversiProductAndStockQtyKembali.getUom3FromSmallest()
    }

    override fun setDetilQty4Kembali() {
        //Fungsinya untuk input Detil Kembeli/BTB
        newFtPurchased.qty4Kembali = konversiProductAndStockQtyKembali.getUom4FromSmallest()
    }

    //##HARGA
    override fun setDetilSpriceUom2() {
        val newPpriceSmallest = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1
        val newPpriceUom2 = newPpriceSmallest * newFtPurchased.fmaterialBean.convfact2
        newFtPurchased.spriceUom2 = newPpriceUom2
    }

    override fun setDetilSpriceUom3() {
        val newPpriceSmallest = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1
        val newPpriceUom3 = newPpriceSmallest * newFtPurchased.fmaterialBean.convfact3
        newFtPurchased.spriceUom3 = newPpriceUom3
    }

    override fun setDetilSpriceUom4() {
        val newPpriceSmallest = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1
        newFtPurchased.spriceUom4 = newPpriceSmallest
    }

    override fun setDetilSpriceAfterPpn() {
        val newPpriceAfterPpn = newFtPurchased.sprice + newFtPurchased.spricePpnRp
        newFtPurchased.spriceAfterPpn = newPpriceAfterPpn
    }

    override fun setDetilSpriceUom2AfterPpn() {
        val newPpriceSmallestAfterPpn = (newFtPurchased.sprice + newFtPurchased.spricePpnRp) / newFtPurchased.fmaterialBean.convfact1
        val newPpriceUom2AfterPpn = Math.round(newPpriceSmallestAfterPpn * newFtPurchased.fmaterialBean.convfact2).toDouble()
        newFtPurchased.spriceUom2AfterPpn = newPpriceUom2AfterPpn
    }

    override fun setDetilSpriceUom3AfterPpn() {
        val newPpriceSmallestAfterPpn = (newFtPurchased.sprice + newFtPurchased.spricePpnRp) / newFtPurchased.fmaterialBean.convfact1
        val newPpriceUom3AfterPpn = Math.round(newPpriceSmallestAfterPpn * newFtPurchased.fmaterialBean.convfact3).toDouble()
        newFtPurchased.spriceUom3AfterPpn = newPpriceUom3AfterPpn
    }

    override fun setDetilSpriceUom4AfterPpn() {
        val newPpriceSmallestAfterPpn = (newFtPurchased.sprice + newFtPurchased.spricePpnRp) / newFtPurchased.fmaterialBean.convfact1
        val newPpriceUom4AfterPpn = Math.round(newPpriceSmallestAfterPpn).toDouble()
        newFtPurchased.spriceUom4AfterPpn = newPpriceUom4AfterPpn
    }

    override fun setDetilSubtotalRp() {
        var fProduct = FMaterial()
        fProduct = newFtPurchased.fmaterialBean
        val pricePcsNoPpn = newFtPurchased.sprice / fProduct.convfact1
        val subtotalRp = pricePcsNoPpn * newFtPurchased.qtyNET
        newFtPurchased.subtotalRp = subtotalRp
    }

    override fun setDetilSubtotalPpnRp() {
        val subtotalPpnRp = newFtPurchased.subtotalRp * taxPercentFloat
        newFtPurchased.subtotalPpnRp = subtotalPpnRp
    }

    override fun setDetilSubtotalRpAfterPpn() {
        val subtotalRpAfterPpn = newFtPurchased.subtotalRp + newFtPurchased.subtotalPpnRp
        newFtPurchased.subtotalRpAfterPpn = subtotalRpAfterPpn
    }

    /*
	 * Detil Disc
	 */
    override fun setDetilDisc1Rp() {
        var disc1persen = 0.0
        var disc1Rp = 0.0
        try {
            disc1persen = newFtPurchased.disc1 / 100
            disc1Rp = newFtPurchased.subtotalRp * disc1persen
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1Rp = Math.round(disc1Rp).toDouble()
        newFtPurchased.disc1Rp = disc1Rp
    }

    override fun setDetilDisc1PpnRp() {
        var disc1PpnRp = newFtPurchased.disc1Rp * taxPercentFloat
        if (isRoundedTotalDetil) disc1PpnRp = Math.round(disc1PpnRp).toDouble()
        newFtPurchased.disc1PpnRp = disc1PpnRp
    }

    override fun setDetilDisc1RpAfterPpn() {
        var disc1RpAfterPpn = newFtPurchased.disc1Rp + newFtPurchased.disc1PpnRp
        if (isRoundedTotalDetil) disc1RpAfterPpn = Math.round(disc1RpAfterPpn).toDouble()
        newFtPurchased.disc1RpAfterPpn = disc1RpAfterPpn
    }

    override fun setDetilDisc1RpAfterPpnUom1() {
        var disc1persen = 0.0
        var disc1RpUom1 = 0.0
        var disc1PpnRpUom1 = 0.0
        var disc1RpAfterPpnUom1 = 0.0
        try {
            disc1persen = newFtPurchased.disc1 / 100
            disc1RpUom1 = newFtPurchased.sprice * disc1persen
            disc1PpnRpUom1 = disc1RpUom1 * taxPercentFloat
            disc1RpAfterPpnUom1 = disc1RpUom1 + disc1PpnRpUom1
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1RpAfterPpnUom1 = Math.round(disc1RpAfterPpnUom1).toDouble()
        newFtPurchased.disc1RpAfterPpnUom1 = disc1RpAfterPpnUom1
    }

    override fun setDetilDisc1RpAfterPpnUom2() {
        var disc1persen = 0.0
        var disc1RpUom2 = 0.0
        var disc1PpnRpUom2 = 0.0
        var disc1RpAfterPpnUom2 = 0.0
        try {
            disc1persen = newFtPurchased.disc1 / 100
            disc1RpUom2 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * newFtPurchased.fmaterialBean.convfact2 * disc1persen
            disc1PpnRpUom2 = disc1RpUom2 * taxPercentFloat
            disc1RpAfterPpnUom2 = disc1RpUom2 + disc1PpnRpUom2
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1RpAfterPpnUom2 = Math.round(disc1RpAfterPpnUom2).toDouble()
        newFtPurchased.disc1RpAfterPpnUom2 = disc1RpAfterPpnUom2
    }

    override fun setDetilDisc1RpAfterPpnUom3() {
        var disc1persen = 0.0
        var disc1RpUom3 = 0.0
        var disc1PpnRpUom3 = 0.0
        var disc1RpAfterPpnUom3 = 0.0
        try {
            disc1persen = newFtPurchased.disc1 / 100
            disc1RpUom3 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * newFtPurchased.fmaterialBean.convfact3 * disc1persen
            disc1PpnRpUom3 = disc1RpUom3 * taxPercentFloat
            disc1RpAfterPpnUom3 = disc1RpUom3 + disc1PpnRpUom3
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1RpAfterPpnUom3 = Math.round(disc1RpAfterPpnUom3).toDouble()
        newFtPurchased.disc1RpAfterPpnUom3 = disc1RpAfterPpnUom3
    }

    override fun setDetilDisc1RpAfterPpnUom4() {
        var disc1persen = 0.0
        var disc1RpUom4 = 0.0
        var disc1PpnRpUom4 = 0.0
        var disc1RpAfterPpnUom4 = 0.0
        try {
            disc1persen = newFtPurchased.disc1 / 100
            disc1RpUom4 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * disc1persen
            disc1PpnRpUom4 = disc1RpUom4 * taxPercentFloat
            disc1RpAfterPpnUom4 = disc1RpUom4 + disc1PpnRpUom4
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1RpAfterPpnUom4 = Math.round(disc1RpAfterPpnUom4).toDouble()
        newFtPurchased.disc1RpAfterPpnUom4 = disc1RpAfterPpnUom4
    }

    override fun setDetilDisc2Rp() {
        var disc2persen = 0.0
        var disc2rp = 0.0
        try {
            disc2persen = newFtPurchased.disc2 / 100
            disc2rp = newFtPurchased.subtotalRp * disc2persen
        } catch (ex: Exception) {
        }
        newFtPurchased.disc2Rp = disc2rp
    }

    override fun setDetilDisc2PpnRp() {
        var disc2PpnRp = newFtPurchased.disc2Rp * taxPercentFloat
        if (isRoundedTotalDetil) disc2PpnRp = Math.round(disc2PpnRp).toDouble()
        newFtPurchased.disc2PpnRp = disc2PpnRp
    }

    override fun setDetilDisc2RpAfterPpn() {
        var disc2RpAfterPpn = newFtPurchased.disc2Rp + newFtPurchased.disc2PpnRp
        if (isRoundedTotalDetil) disc2RpAfterPpn = Math.round(disc2RpAfterPpn).toDouble()
        newFtPurchased.disc2RpAfterPpn = disc2RpAfterPpn
    }

    override fun setDetilDisc2RpAfterPpnUom1() {
        var disc2persen = 0.0
        var disc2RpUom1 = 0.0
        var disc2PpnRpUom1 = 0.0
        var disc2RpAfterPpnUom1 = 0.0
        try {
            disc2persen = newFtPurchased.disc2 / 100
            disc2RpUom1 = newFtPurchased.sprice * disc2persen
            disc2PpnRpUom1 = disc2RpUom1 * taxPercentFloat
            disc2RpAfterPpnUom1 = disc2RpUom1 + disc2PpnRpUom1
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2RpAfterPpnUom1 = Math.round(disc2RpAfterPpnUom1).toDouble()
        newFtPurchased.disc2RpAfterPpnUom1 = disc2RpAfterPpnUom1
    }

    override fun setDetilDisc2RpAfterPpnUom2() {
        var disc2persen = 0.0
        var disc2RpUom2 = 0.0
        var disc2PpnRpUom2 = 0.0
        var disc2RpAfterPpnUom2 = 0.0
        try {
            disc2persen = newFtPurchased.disc2 / 100
            disc2RpUom2 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * newFtPurchased.fmaterialBean.convfact2 * disc2persen
            disc2PpnRpUom2 = disc2RpUom2 * taxPercentFloat
            disc2RpAfterPpnUom2 = disc2RpUom2 + disc2PpnRpUom2
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2RpAfterPpnUom2 = Math.round(disc2RpAfterPpnUom2).toDouble()
        newFtPurchased.disc2RpAfterPpnUom2 = disc2RpAfterPpnUom2
    }

    override fun setDetilDisc2RpAfterPpnUom3() {
        var disc2persen = 0.0
        var disc2RpUom3 = 0.0
        var disc2PpnRpUom3 = 0.0
        var disc2RpAfterPpnUom3 = 0.0
        try {
            disc2persen = newFtPurchased.disc2 / 100
            disc2RpUom3 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * newFtPurchased.fmaterialBean.convfact3 * disc2persen
            disc2PpnRpUom3 = disc2RpUom3 * taxPercentFloat
            disc2RpAfterPpnUom3 = disc2RpUom3 + disc2PpnRpUom3
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2RpAfterPpnUom3 = Math.round(disc2RpAfterPpnUom3).toDouble()
        newFtPurchased.disc2RpAfterPpnUom3 = disc2RpAfterPpnUom3
    }

    override fun setDetilDisc2RpAfterPpnUom4() {
        var disc2persen = 0.0
        var disc2RpUom4 = 0.0
        var disc2PpnRpUom4 = 0.0
        var disc2RpAfterPpnUom4 = 0.0
        try {
            disc2persen = newFtPurchased.disc2 / 100
            disc2RpUom4 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * disc2persen
            disc2PpnRpUom4 = disc2RpUom4 * taxPercentFloat
            disc2RpAfterPpnUom4 = disc2RpUom4 + disc2PpnRpUom4
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2RpAfterPpnUom4 = Math.round(disc2RpAfterPpnUom4).toDouble()
        newFtPurchased.disc2RpAfterPpnUom4 = disc2RpAfterPpnUom4
    }

    override fun setDetilDisc3Rp() {
        var disc3persen = 0.0
        var disc3rp = 0.0
        try {
            disc3persen = newFtPurchased.disc3 / 100
            disc3rp = newFtPurchased.subtotalRp * disc3persen
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc3rp = Math.round(disc3rp).toDouble()
        newFtPurchased.disc3Rp = disc3rp
    }

    override fun setDetilDisc3PpnRp() {
        var disc3PpnRp = newFtPurchased.disc3Rp * taxPercentFloat
        if (isRoundedTotalDetil) disc3PpnRp = Math.round(disc3PpnRp).toDouble()
        newFtPurchased.disc3PpnRp = disc3PpnRp
    }

    override fun setDetilDisc3RpAfterPpn() {
        var disc3RpAfterPpn = newFtPurchased.disc3Rp + newFtPurchased.disc3PpnRp
        if (isRoundedTotalDetil) disc3RpAfterPpn = Math.round(disc3RpAfterPpn).toDouble()
        newFtPurchased.disc3RpAfterPpn = disc3RpAfterPpn
    }

    override fun setDetilDisc3RpAfterPpnUom1() {
        var disc3persen = 0.0
        var disc3RpUom1 = 0.0
        var disc3PpnRpUom1 = 0.0
        var disc3RpAfterPpnUom1 = 0.0
        try {
            disc3persen = newFtPurchased.disc3 / 100
            disc3RpUom1 = newFtPurchased.sprice * disc3persen
            disc3PpnRpUom1 = disc3RpUom1 * taxPercentFloat
            disc3RpAfterPpnUom1 = disc3RpUom1 + disc3PpnRpUom1
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc3RpAfterPpnUom1 = Math.round(disc3RpAfterPpnUom1).toDouble()
        newFtPurchased.disc3RpAfterPpnUom1 = disc3RpAfterPpnUom1
    }

    override fun setDetilDisc3RpAfterPpnUom2() {
        var disc3persen = 0.0
        var disc3RpUom2 = 0.0
        var disc3PpnRpUom2 = 0.0
        var disc3RpAfterPpnUom2 = 0.0
        try {
            disc3persen = newFtPurchased.disc3 / 100
            disc3RpUom2 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * newFtPurchased.fmaterialBean.convfact2 * disc3persen
            disc3PpnRpUom2 = disc3RpUom2 * taxPercentFloat
            disc3RpAfterPpnUom2 = disc3RpUom2 + disc3PpnRpUom2
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc3RpAfterPpnUom2 = Math.round(disc3RpAfterPpnUom2).toDouble()
        newFtPurchased.disc3RpAfterPpnUom2 = disc3RpAfterPpnUom2
    }

    override fun setDetilDisc3RpAfterPpnUom3() {
        var disc3persen = 0.0
        var disc3RpUom3 = 0.0
        var disc3PpnRpUom3 = 0.0
        var disc3RpAfterPpnUom3 = 0.0
        try {
            disc3persen = newFtPurchased.disc3 / 100
            disc3RpUom3 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * newFtPurchased.fmaterialBean.convfact3 * disc3persen
            disc3PpnRpUom3 = disc3RpUom3 * taxPercentFloat
            disc3RpAfterPpnUom3 = disc3RpUom3 + disc3PpnRpUom3
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc3RpAfterPpnUom3 = Math.round(disc3RpAfterPpnUom3).toDouble()
        newFtPurchased.disc3RpAfterPpnUom3 = disc3RpAfterPpnUom3
    }

    override fun setDetilDisc3RpAfterPpnUom4() {
        var disc3persen = 0.0
        var disc3RpUom4 = 0.0
        var disc3PpnRpUom4 = 0.0
        var disc3RpAfterPpnUom4 = 0.0
        try {
            disc3persen = newFtPurchased.disc3 / 100
            disc3RpUom4 = newFtPurchased.sprice / newFtPurchased.fmaterialBean.convfact1 * disc3persen
            disc3PpnRpUom4 = disc3RpUom4 * taxPercentFloat
            disc3RpAfterPpnUom4 = disc3RpUom4 + disc3PpnRpUom4
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc3RpAfterPpnUom4 = Math.round(disc3RpAfterPpnUom4).toDouble()
        newFtPurchased.disc3RpAfterPpnUom4 = disc3RpAfterPpnUom4
    }

    override fun setDetilSubtotalAfterDisc123Rp() {
        val subTotalAfterDisc123Rp = newFtPurchased.subtotalRp - newFtPurchased.disc1Rp - newFtPurchased.disc2Rp - newFtPurchased.disc3Rp

//		if (isRoundedTotalDetil()==true) subTotalAfterDisc123Rp = Math.round((double) subTotalAfterDisc123Rp);
        newFtPurchased.subtotalAfterDisc123Rp = subTotalAfterDisc123Rp
    }

    override fun setDetilSubtotalAfterDisc123PpnRp() {
        val subtotalAfterDisc123PpnRp = newFtPurchased.subtotalAfterDisc123Rp * taxPercentFloat

//		if (isRoundedTotalDetil()) subtotalAfterDisc123PpnRp = Math.round(subtotalAfterDisc123PpnRp);
        newFtPurchased.subtotalAfterDisc123PpnRp = subtotalAfterDisc123PpnRp
    }

    override fun setDetilSubtotalAfterDisc123RpAfterPpn() {
        val subtotalAfterDisc123RpAfterPpn = newFtPurchased.subtotalAfterDisc123Rp + newFtPurchased.subtotalAfterDisc123PpnRp

//		if (isRoundedTotalDetil()) subtotalAfterDisc123RpAfterPpn = Math.round(subtotalAfterDisc123RpAfterPpn);
        newFtPurchased.subtotalAfterDisc123RpAfterPpn = subtotalAfterDisc123RpAfterPpn
    }

    override fun setDetilDisc1PlusRp() {
        var disc1PlusPersen = 0.0
        var disc1PlusRp = 0.0
        try {
            disc1PlusPersen = newFtPurchased.disc1Plus / 100
            disc1PlusRp = newFtPurchased.subtotalAfterDisc123Rp * disc1PlusPersen
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1PlusRp = Math.round(disc1PlusRp).toDouble()
        newFtPurchased.disc1PlusRp = disc1PlusRp
    }

    override fun setDetilDisc1PlusPpnRp() {
        var disc1PlusPpnRp = newFtPurchased.disc1PlusRp * taxPercentFloat
        if (isRoundedTotalDetil) disc1PlusPpnRp = Math.round(disc1PlusPpnRp).toDouble()
        newFtPurchased.disc1PlusPpnRp = disc1PlusPpnRp
    }

    override fun setDetilDisc1PlusRpAfterPpn() {
        var disc1PlusRpAfterPpn = newFtPurchased.disc1PlusRp + newFtPurchased.disc1PlusPpnRp
        if (isRoundedTotalDetil) disc1PlusRpAfterPpn = Math.round(disc1PlusRpAfterPpn).toDouble()
        newFtPurchased.disc1PlusRpAfterPpn = disc1PlusRpAfterPpn
    }

    override fun setDetilDisc1PlusRpAfterPpnUom1() {
        var disc1PlusPersen = 0.0
        var hargaSetelahDisc123RpUom1 = 0.0
        var disc1PlusRpUom1 = 0.0
        var disc1PlusPpnRpUom1 = 0.0
        var disc1PlusRpAfterPpnUom1 = 0.0
        try {
            disc1PlusPersen = newFtPurchased.disc1Plus / 100
            hargaSetelahDisc123RpUom1 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact1
            disc1PlusRpUom1 = hargaSetelahDisc123RpUom1 * disc1PlusPersen
            disc1PlusPpnRpUom1 = disc1PlusRpUom1 * taxPercentFloat
            disc1PlusRpAfterPpnUom1 = disc1PlusRpUom1 + disc1PlusPpnRpUom1
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1PlusRpAfterPpnUom1 = Math.round(disc1PlusRpAfterPpnUom1).toDouble()
        newFtPurchased.disc1PlusRpAfterPpnUom1 = disc1PlusRpAfterPpnUom1
    }

    override fun setDetilDisc1PlusRpAfterPpnUom2() {
        var disc1PlusPersen = 0.0
        var hargaSetelahDisc123RpUom2 = 0.0
        var disc1PlusRpUom2 = 0.0
        var disc1PlusPpnRpUom2 = 0.0
        var disc1PlusRpAfterPpnUom2 = 0.0
        try {
            disc1PlusPersen = newFtPurchased.disc1Plus / 100
            hargaSetelahDisc123RpUom2 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact2
            disc1PlusRpUom2 = hargaSetelahDisc123RpUom2 * disc1PlusPersen
            disc1PlusPpnRpUom2 = disc1PlusRpUom2 * taxPercentFloat
            disc1PlusRpAfterPpnUom2 = disc1PlusRpUom2 + disc1PlusPpnRpUom2
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1PlusRpAfterPpnUom2 = Math.round(disc1PlusRpAfterPpnUom2).toDouble()
        newFtPurchased.disc1PlusRpAfterPpnUom2 = disc1PlusRpAfterPpnUom2
    }

    override fun setDetilDisc1PlusRpAfterPpnUom3() {
        var disc1PlusPersen = 0.0
        var hargaSetelahDisc123RpUom3 = 0.0
        var disc1PlusRpUom3 = 0.0
        var disc1PlusPpnRpUom3 = 0.0
        var disc1PlusRpAfterPpnUom3 = 0.0
        try {
            disc1PlusPersen = newFtPurchased.disc1Plus / 100
            hargaSetelahDisc123RpUom3 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact3
            disc1PlusRpUom3 = hargaSetelahDisc123RpUom3 * disc1PlusPersen
            disc1PlusPpnRpUom3 = disc1PlusRpUom3 * taxPercentFloat
            disc1PlusRpAfterPpnUom3 = disc1PlusRpUom3 + disc1PlusPpnRpUom3
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1PlusRpAfterPpnUom3 = Math.round(disc1PlusRpAfterPpnUom3).toDouble()
        newFtPurchased.disc1PlusRpAfterPpnUom3 = disc1PlusRpAfterPpnUom3
    }

    override fun setDetilDisc1PlusRpAfterPpnUom4() {
        var disc1PlusPersen = 0.0
        var hargaSetelahDisc123RpUom4 = 0.0
        var disc1PlusRpUom4 = 0.0
        var disc1PlusPpnRpUom4 = 0.0
        var disc1PlusRpAfterPpnUom4 = 0.0
        try {
            disc1PlusPersen = newFtPurchased.disc1Plus / 100
            hargaSetelahDisc123RpUom4 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET
            disc1PlusRpUom4 = hargaSetelahDisc123RpUom4 * disc1PlusPersen
            disc1PlusPpnRpUom4 = disc1PlusRpUom4 * taxPercentFloat
            disc1PlusRpAfterPpnUom4 = disc1PlusRpUom4 + disc1PlusPpnRpUom4
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc1PlusRpAfterPpnUom4 = Math.round(disc1PlusRpAfterPpnUom4).toDouble()
        newFtPurchased.disc1PlusRpAfterPpnUom4 = disc1PlusRpAfterPpnUom4
    }

    override fun setDetilSubtotalAfterDisc1PlusRp() {
        val subtotalAfterDisc1PlusRp = newFtPurchased.subtotalAfterDisc123Rp - newFtPurchased.disc1PlusRp

//		if (isRoundedTotalDetil()==true) subtotalAfterDisc1PlusRp = Math.round((double) subtotalAfterDisc1PlusRp);	
        newFtPurchased.subtotalAfterDisc1PlusRp = subtotalAfterDisc1PlusRp
    }

    override fun setDetilSubtotalAfterDisc1PlusPpnRp() {
        val subtotalAfterDisc1PlusPpnRp = newFtPurchased.subtotalAfterDisc1PlusRp * taxPercentFloat

//		if (isRoundedTotalDetil()) subtotalAfterDisc1PlusPpnRp = Math.round(subtotalAfterDisc1PlusPpnRp);	
        newFtPurchased.subtotalAfterDisc1PlusPpnRp = subtotalAfterDisc1PlusPpnRp
    }

    override fun setDetilSubtotalAfterDisc1PlusRpAfterPpn() {
        val subtotalAfterDisc1PlusRpAfterPpn = newFtPurchased.subtotalAfterDisc1PlusRp + newFtPurchased.subtotalAfterDisc1PlusPpnRp

//		if (isRoundedTotalDetil()) subtotalAfterDisc1PlusRpAfterPpn = Math.round(subtotalAfterDisc1PlusRpAfterPpn);	
        newFtPurchased.subtotalAfterDisc1PlusRpAfterPpn = subtotalAfterDisc1PlusRpAfterPpn
    }

    override fun setDetilDisc2PlusRp() {
        var disc2PlusPersen = 0.0
        var disc2PlusRp = 0.0
        try {
            disc2PlusPersen = newFtPurchased.disc2Plus / 100
            disc2PlusRp = newFtPurchased.subtotalAfterDisc1PlusRp * disc2PlusPersen
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2PlusRp = Math.round(disc2PlusRp).toDouble()
        newFtPurchased.disc2PlusRp = disc2PlusRp
    }

    override fun setDetilDisc2PlusPpnRp() {
        var disc2PlusPpnRp = newFtPurchased.disc2PlusRp * taxPercentFloat
        if (isRoundedTotalDetil) disc2PlusPpnRp = Math.round(disc2PlusPpnRp).toDouble()
        newFtPurchased.disc2PlusPpnRp = disc2PlusPpnRp
    }

    override fun setDetilDisc2PlusRpAfterPpn() {
        var disc2PlusRpAfterPpn = newFtPurchased.disc2PlusRp + newFtPurchased.disc2PlusPpnRp
        if (isRoundedTotalDetil) disc2PlusRpAfterPpn = Math.round(disc2PlusRpAfterPpn).toDouble()
        newFtPurchased.disc2PlusRpAfterPpn = disc2PlusRpAfterPpn
    }

    override fun setDetilDisc2PlusRpAfterPpnUom1() {
        var disc2PlusPersen = 0.0
        var hargaSetelahDisc123RpUom1 = 0.0
        var disc2PlusRpUom1 = 0.0
        var disc2PlusPpnRpUom1 = 0.0
        var disc2PlusRpAfterPpnUom1 = 0.0
        try {
            disc2PlusPersen = newFtPurchased.disc2Plus / 100
            hargaSetelahDisc123RpUom1 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact1
            disc2PlusRpUom1 = hargaSetelahDisc123RpUom1 * disc2PlusPersen
            disc2PlusPpnRpUom1 = disc2PlusRpUom1 * taxPercentFloat
            disc2PlusRpAfterPpnUom1 = disc2PlusRpUom1 + disc2PlusPpnRpUom1
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2PlusRpAfterPpnUom1 = Math.round(disc2PlusRpAfterPpnUom1).toDouble()
        newFtPurchased.disc2PlusRpAfterPpnUom1 = disc2PlusRpAfterPpnUom1
    }

    override fun setDetilDisc2PlusRpAfterPpnUom2() {
        var disc2PlusPersen = 0.0
        var hargaSetelahDisc123RpUom2 = 0.0
        var disc2PlusRpUom2 = 0.0
        var disc2PlusPpnRpUom2 = 0.0
        var disc2PlusRpAfterPpnUom2 = 0.0
        try {
            disc2PlusPersen = newFtPurchased.disc2Plus / 100
            hargaSetelahDisc123RpUom2 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact2
            disc2PlusRpUom2 = hargaSetelahDisc123RpUom2 * disc2PlusPersen
            disc2PlusPpnRpUom2 = disc2PlusRpUom2 * taxPercentFloat
            disc2PlusRpAfterPpnUom2 = disc2PlusRpUom2 + disc2PlusPpnRpUom2
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2PlusRpAfterPpnUom2 = Math.round(disc2PlusRpAfterPpnUom2).toDouble()
        newFtPurchased.disc2PlusRpAfterPpnUom2 = disc2PlusRpAfterPpnUom2
    }

    override fun setDetilDisc2PlusRpAfterPpnUom3() {
        var disc2PlusPersen = 0.0
        var hargaSetelahDisc123RpUom3 = 0.0
        var disc2PlusRpUom3 = 0.0
        var disc2PlusPpnRpUom3 = 0.0
        var disc2PlusRpAfterPpnUom3 = 0.0
        try {
            disc2PlusPersen = newFtPurchased.disc2Plus / 100
            hargaSetelahDisc123RpUom3 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact3
            disc2PlusRpUom3 = hargaSetelahDisc123RpUom3 * disc2PlusPersen
            disc2PlusPpnRpUom3 = disc2PlusRpUom3 * taxPercentFloat
            disc2PlusRpAfterPpnUom3 = disc2PlusRpUom3 + disc2PlusPpnRpUom3
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2PlusRpAfterPpnUom3 = Math.round(disc2PlusRpAfterPpnUom3).toDouble()
        newFtPurchased.disc2PlusRpAfterPpnUom3 = disc2PlusRpAfterPpnUom3
    }

    override fun setDetilDisc2PlusRpAfterPpnUom4() {
        var disc2PlusPersen = 0.0
        var hargaSetelahDisc123RpUom4 = 0.0
        var disc2PlusRpUom4 = 0.0
        var disc2PlusPpnRpUom4 = 0.0
        var disc2PlusRpAfterPpnUom4 = 0.0
        try {
            disc2PlusPersen = newFtPurchased.disc2Plus / 100
            hargaSetelahDisc123RpUom4 = newFtPurchased.subtotalAfterDisc123Rp / newFtPurchased.qtyNET
            disc2PlusRpUom4 = hargaSetelahDisc123RpUom4 * disc2PlusPersen
            disc2PlusPpnRpUom4 = disc2PlusRpUom4 * taxPercentFloat
            disc2PlusRpAfterPpnUom4 = disc2PlusRpUom4 + disc2PlusPpnRpUom4
        } catch (ex: Exception) {
        }
        if (isRoundedTotalDetil) disc2PlusRpAfterPpnUom4 = Math.round(disc2PlusRpAfterPpnUom4).toDouble()
        newFtPurchased.disc2PlusRpAfterPpnUom4 = disc2PlusRpAfterPpnUom4
    }

    override fun setDetilSubtotalAfterDisc2PlusRp() {
        var subtotalAfterDisc2PlusRp = newFtPurchased.subtotalAfterDisc1PlusRp - newFtPurchased.disc2PlusRp
        if (isRoundedTotalDetil == true) subtotalAfterDisc2PlusRp = Math.round(subtotalAfterDisc2PlusRp).toDouble()
        newFtPurchased.subtotalAfterDisc2PlusRp = subtotalAfterDisc2PlusRp
    }

    override fun setDetilSubtotalAfterDisc2PlusPpnRp() {
        var subtotalAfterDisc2PlusPpnRp = newFtPurchased.subtotalAfterDisc2PlusRp * taxPercentFloat
        if (isRoundedTotalDetil) subtotalAfterDisc2PlusPpnRp = Math.round(subtotalAfterDisc2PlusPpnRp).toDouble()
        newFtPurchased.subtotalAfterDisc2PlusPpnRp = subtotalAfterDisc2PlusPpnRp
    }

    override fun setDetilSubtotalAfterDisc2PlusRpAfterPpn() {
        var subtotalAfterDisc2PlusRpAfterPpn = newFtPurchased.subtotalAfterDisc2PlusRp + newFtPurchased.subtotalAfterDisc2PlusPpnRp
        if (isRoundedTotalDetil) subtotalAfterDisc2PlusRpAfterPpn = Math.round(subtotalAfterDisc2PlusRpAfterPpn).toDouble()
        newFtPurchased.subtotalAfterDisc2PlusRpAfterPpn = subtotalAfterDisc2PlusRpAfterPpn
    }

    //HARGA SETELAH DISKON SETELAH PPN
    override fun setSpriceNET_Uom1AfterDiscAfterPpn() {
        var value = newFtPurchased.subtotalAfterDisc2PlusRpAfterPpn / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact1
        value = Math.round(value).toDouble()
        newFtPurchased.spriceNET_Uom1AfterDiscAfterPpn = value
    }

    override fun setSpriceNET_Uom2AfterDiscAfterPpn() {
        var value = newFtPurchased.subtotalAfterDisc2PlusRpAfterPpn / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact2
        value = Math.round(value).toDouble()
        newFtPurchased.spriceNET_Uom2AfterDiscAfterPpn = value
    }

    override fun setSpriceNET_Uom3AfterDiscAfterPpn() {
        var value = newFtPurchased.subtotalAfterDisc2PlusRpAfterPpn / newFtPurchased.qtyNET * newFtPurchased.fmaterialBean.convfact3
        value = Math.round(value).toDouble()
        newFtPurchased.spriceNET_Uom3AfterDiscAfterPpn = value
    }

    override fun setSpriceNET_Uom4AfterDiscAfterPpn() {
        var value = newFtPurchased.subtotalAfterDisc2PlusRpAfterPpn / newFtPurchased.qtyNET
        value = Math.round(value).toDouble()
        newFtPurchased.spriceNET_Uom4AfterDiscAfterPpn = value
    }

    /*
	 * DISC NOTA **************
	 */
    override fun setDetilDiscNota1Rp() {
        var discNota1persen = 0.0
        var discNota1Rp = 0.0
        try {
            newFtPurchased.discNota1 = newFtSalesh.disc1
            discNota1persen = newFtPurchased.discNota1 / 100
            discNota1Rp = newFtPurchased.subtotalAfterDisc2PlusRp * discNota1persen
        } catch (ex: Exception) {
        }
        if (isRoundedTotalHeader) discNota1Rp = Math.round(discNota1Rp).toDouble()
        newFtPurchased.discNota1Rp = discNota1Rp
    }

    override fun setDetilDiscNota1PpnRp() {
        var discNota1PpnRp = newFtPurchased.discNota1Rp * taxPercentFloat
        if (isRoundedTotalHeader) discNota1PpnRp = Math.round(discNota1PpnRp).toDouble()
        newFtPurchased.discNota1PpnRp = discNota1PpnRp
    }

    override fun setDetilDiscNota1RpAfterPpn() {
        var discNota1RpAfterPpn = newFtPurchased.discNota1Rp + newFtPurchased.discNota1PpnRp
        if (isRoundedTotalHeader) discNota1RpAfterPpn = Math.round(discNota1RpAfterPpn).toDouble()
        newFtPurchased.discNota1RpAfterPpn = discNota1RpAfterPpn
    }

    //TAMBAHAN: SUB TOTAL AFTER DISC NOTA 1 RP
    override fun setDetilSubtotalAfterDiscNota1Rp() {
        val subtotalAfterDiscNota1Rp = newFtPurchased.subtotalAfterDisc2PlusRp - newFtPurchased.discNota1Rp
        newFtPurchased.subtotalAfterDiscNota1Rp = subtotalAfterDiscNota1Rp
    }

    override fun setDetilSubtotalAfterDiscNota1PpnRp() {
        val subtotalAfterDiscNota1PpnRp = newFtPurchased.subtotalAfterDiscNota1Rp * taxPercentFloat
        newFtPurchased.subtotalAfterDiscNota1PpnRp = subtotalAfterDiscNota1PpnRp
    }

    override fun setDetilSubtotalAfterDiscNota1RpAfterPpn() {
        val subtotalAfterDiscNota1RpAfterPpn = newFtPurchased.subtotalAfterDiscNota1Rp + newFtPurchased.subtotalAfterDiscNota1PpnRp
        newFtPurchased.subtotalAfterDiscNota1RpAfterPpn = subtotalAfterDiscNota1RpAfterPpn
    }

    override fun setDetilDiscNota2Rp() {
        var discNota2persen = 0.0
        var discNota2rp = 0.0
        try {
            newFtPurchased.discNota2 = newFtSalesh.disc2
            discNota2persen = newFtPurchased.discNota2 / 100
            discNota2rp = newFtPurchased.subtotalAfterDiscNota1Rp * discNota2persen
        } catch (ex: Exception) {
        }
        if (isRoundedTotalHeader) discNota2rp = Math.round(discNota2rp).toDouble()
        newFtPurchased.discNota2Rp = discNota2rp
    }

    override fun setDetilDiscNota2PpnRp() {
        var discNota2PpnRp = newFtPurchased.discNota2Rp * taxPercentFloat
        if (isRoundedTotalHeader) discNota2PpnRp = Math.round(discNota2PpnRp).toDouble()
        newFtPurchased.discNota2PpnRp = discNota2PpnRp
    }

    override fun setDetilDiscNota2RpAfterPpn() {
        var discNota2RpAfterPpn = newFtPurchased.discNota2Rp + newFtPurchased.discNota2PpnRp
        if (isRoundedTotalHeader) discNota2RpAfterPpn = Math.round(discNota2RpAfterPpn).toDouble()
        newFtPurchased.discNota2RpAfterPpn = discNota2RpAfterPpn
    }

    override fun setDetilSubtotalAfterDiscNota2Rp() {
        val subtotalAfterDiscNota2Rp = newFtPurchased.subtotalAfterDiscNota1Rp - newFtPurchased.discNota2Rp
        newFtPurchased.subtotalAfterDiscNota2Rp = subtotalAfterDiscNota2Rp
    }

    override fun setDetilSubtotalAfterDiscNota2PpnRp() {
        val subtotalAfterDiscNota2PpnRp = newFtPurchased.subtotalAfterDiscNota2Rp * taxPercentFloat
        newFtPurchased.subtotalAfterDiscNota2PpnRp = subtotalAfterDiscNota2PpnRp
    }

    override fun setDetilSubtotalAfterDiscNota2RpAfterPpn() {
        val subtotalAfterDiscNota2RpAfterPpn = newFtPurchased.subtotalAfterDiscNota2Rp + newFtPurchased.subtotalAfterDiscNota2PpnRp
        newFtPurchased.subtotalAfterDiscNota2RpAfterPpn = subtotalAfterDiscNota2RpAfterPpn
    }

    override fun setDetilDiscNotaPlusRp_FG() {
        var discNotaPersen = 0.0
        var discNotaPlusRp = 0.0
        try {
            newFtPurchased.discNotaPlus_FG = newFtSalesh.discPlus_FG
            discNotaPersen = newFtPurchased.discNotaPlus_FG / 100
            discNotaPlusRp = newFtPurchased.subtotalAfterDiscNota2Rp * discNotaPersen
        } catch (ex: Exception) {
        }
        if (isRoundedTotalHeader) discNotaPlusRp = Math.round(discNotaPlusRp).toDouble()
        newFtPurchased.discNotaPlusRp_FG = discNotaPlusRp
    }

    override fun setDetilDiscNotaPlusPpnRp_FG() {
        var discNotaPlusPpnRp = newFtPurchased.discNotaPlusRp_FG * taxPercentFloat
        if (isRoundedTotalHeader) discNotaPlusPpnRp = Math.round(discNotaPlusPpnRp).toDouble()
        newFtPurchased.discNotaPlusPpnRp_FG = discNotaPlusPpnRp
    }

    override fun setDetilDiscNotaPlusRpAfterPpn_FG() {
        var discNotaPlusRpAfterPpn = newFtPurchased.discNotaPlusRp_FG + newFtPurchased.discNotaPlusPpnRp_FG
        if (isRoundedTotalHeader) discNotaPlusRpAfterPpn = Math.round(discNotaPlusRpAfterPpn).toDouble()
        newFtPurchased.discNotaPlusRpAfterPpn_FG = discNotaPlusRpAfterPpn
    }

    override fun setDetilSubtotalAfterDiscNotaPlusRp_FG() {
        var subtotalAfterDiscNotaPlusRp = newFtPurchased.subtotalAfterDiscNota2Rp - newFtPurchased.discNotaPlusRp_FG
        if (isRoundedTotalHeader == true) subtotalAfterDiscNotaPlusRp = Math.round(subtotalAfterDiscNotaPlusRp).toDouble()
        newFtPurchased.subtotalAfterDiscNotaPlusRp_FG = subtotalAfterDiscNotaPlusRp
    }

    override fun setDetilSubtotalAfterDiscNotaPlusPpnRp_FG() {
        var subtotalAfterDiscNotaPlusPpnRp = newFtPurchased.subtotalAfterDiscNotaPlusRp_FG * taxPercentFloat
        if (isRoundedTotalHeader) subtotalAfterDiscNotaPlusPpnRp = Math.round(subtotalAfterDiscNotaPlusPpnRp).toDouble()
        newFtPurchased.subtotalAfterDiscNotaPlusPpnRp_FG = subtotalAfterDiscNotaPlusPpnRp
    }

    override fun setDetilSubtotalAfterDiscNotaPlusRpAfterPpn_FG() {
        var subtotalAfterDiscNotaPlusRpAfterPpn = newFtPurchased.subtotalAfterDiscNotaPlusRp_FG + newFtPurchased.subtotalAfterDiscNotaPlusPpnRp_FG
        if (isRoundedTotalHeader) subtotalAfterDiscNotaPlusRpAfterPpn = Math.round(subtotalAfterDiscNotaPlusRpAfterPpn).toDouble()
        newFtPurchased.subtotalAfterDiscNotaPlusRpAfterPpn_FG = subtotalAfterDiscNotaPlusRpAfterPpn
    }

    //		setDetilPprice();
    override val fillFtSalesdOnly: FtSalesdItems
        get() {

//		setDetilPprice();
            setDetilSpriceUom2()
            setDetilSpriceUom3()
            setDetilSpriceUom4()
            setSpricePpnRp()
            setDetilSpriceAfterPpn()
            setDetilSpriceUom2AfterPpn()
            setDetilSpriceUom3AfterPpn()
            setDetilSpriceUom4AfterPpn()
            setDetilQtyNET()
            setDetilQty1()
            setDetilQty2()
            setDetilQty3()
            setDetilQty4()
            setDetilQty1Kembali()
            setDetilQty2Kembali()
            setDetilQty3Kembali()
            setDetilQty4Kembali()
            setDetilSubtotalRp()
            setDetilSubtotalPpnRp()
            setDetilSubtotalRpAfterPpn()
            setDetilDisc1Rp()
            setDetilDisc1PpnRp()
            setDetilDisc1RpAfterPpn()
            setDetilDisc1RpAfterPpnUom1()
            setDetilDisc1RpAfterPpnUom2()
            setDetilDisc1RpAfterPpnUom3()
            setDetilDisc1RpAfterPpnUom4()
            setDetilDisc2Rp()
            setDetilDisc2PpnRp()
            setDetilDisc2RpAfterPpn()
            setDetilDisc2RpAfterPpnUom1()
            setDetilDisc2RpAfterPpnUom2()
            setDetilDisc2RpAfterPpnUom3()
            setDetilDisc2RpAfterPpnUom4()
            setDetilDisc3Rp()
            setDetilDisc3PpnRp()
            setDetilDisc3RpAfterPpn()
            setDetilDisc3RpAfterPpnUom1()
            setDetilDisc3RpAfterPpnUom2()
            setDetilDisc3RpAfterPpnUom3()
            setDetilDisc3RpAfterPpnUom4()
            setDetilSubtotalAfterDisc123Rp()
            setDetilSubtotalAfterDisc123PpnRp()
            setDetilSubtotalAfterDisc123RpAfterPpn()
            setDetilDisc1PlusRp()
            setDetilDisc1PlusPpnRp()
            setDetilDisc1PlusRpAfterPpn()
            setDetilDisc1PlusRpAfterPpnUom1()
            setDetilDisc1PlusRpAfterPpnUom2()
            setDetilDisc1PlusRpAfterPpnUom3()
            setDetilDisc1PlusRpAfterPpnUom4()
            setDetilSubtotalAfterDisc1PlusRp()
            setDetilSubtotalAfterDisc1PlusPpnRp()
            setDetilSubtotalAfterDisc1PlusRpAfterPpn()
            setDetilDisc2PlusRp()
            setDetilDisc2PlusPpnRp()
            setDetilDisc2PlusRpAfterPpn()
            setDetilDisc2PlusRpAfterPpnUom1()
            setDetilDisc2PlusRpAfterPpnUom2()
            setDetilDisc2PlusRpAfterPpnUom3()
            setDetilDisc2PlusRpAfterPpnUom4()
            setDetilSubtotalAfterDisc2PlusRp()
            setDetilSubtotalAfterDisc2PlusPpnRp()
            setDetilSubtotalAfterDisc2PlusRpAfterPpn()
            setSpriceNET_Uom1AfterDiscAfterPpn()
            setSpriceNET_Uom2AfterDiscAfterPpn()
            setSpriceNET_Uom3AfterDiscAfterPpn()
            setSpriceNET_Uom4AfterDiscAfterPpn()
            return newFtPurchased
        }
    //FILL FtSalesd From Nota

    //#TAMBAHAN
    override val fillFtSalesd: FtSalesdItems
        get() {
            fillFtSalesdOnly

            //FILL FtSalesd From Nota
            setDetilDiscNota1Rp()
            setDetilDiscNota1PpnRp()
            setDetilDiscNota1RpAfterPpn()

            //#TAMBAHAN
            setDetilSubtotalAfterDiscNota1Rp()
            setDetilSubtotalAfterDiscNota1PpnRp()
            setDetilSubtotalAfterDiscNota1RpAfterPpn()
            setDetilDiscNota2Rp()
            setDetilDiscNota2PpnRp()
            setDetilDiscNota2RpAfterPpn()
            setDetilSubtotalAfterDiscNota2Rp()
            setDetilSubtotalAfterDiscNota2PpnRp()
            setDetilSubtotalAfterDiscNota2RpAfterPpn()
            setDetilDiscNotaPlusRp_FG()
            setDetilDiscNotaPlusPpnRp_FG()
            setDetilDiscNotaPlusRpAfterPpn_FG()
            setDetilSubtotalAfterDiscNotaPlusRp_FG()
            setDetilSubtotalAfterDiscNotaPlusPpnRp_FG()
            setDetilSubtotalAfterDiscNotaPlusRpAfterPpn_FG()
            return newFtPurchased
        }

    override fun setSprice(newSprice: Double) {
        ftPurchased.sprice = newSprice
    }

    override fun setQty(newQty: Double) {
        ftPurchased.qty = newQty
    }

    override fun getFillFtSaleshFromItems(items: Set<FtSalesdItems>): FtSalesh {

//		System.out.println("Nomor Sales Order :" + newFtSalesh.getInvoiceno());

        //1. Hitung Semua
        var jumlahRec = 0
        /*
		 * Detil
		 */
        var sumDetilSubtotalRp = 0.0
        var sumDetilSubtotalPpnRp = 0.0
        var sumDetilSubtotalRpAfterPpn = 0.0
        var sumDetilDisc1Rp = 0.0
        var sumDetilDisc1PpnRp = 0.0
        var sumDetilDisc1RpAfterPpn = 0.0
        var sumDetilDisc2Rp = 0.0
        var sumDetilDisc2PpnRp = 0.0
        var sumDetilDisc2RpAfterPpn = 0.0
        var sumDetilDisc3Rp = 0.0
        var sumDetilDisc3PpnRp = 0.0
        var sumDetilDisc3RpAfterPpn = 0.0
        var sumDetilSubtotalAfterDisc123Rp = 0.0
        var sumDetilSubtotalAfterDisc123PpnRp = 0.0
        var sumDetilSubtotalAfterDisc123RpAfterPpn = 0.0
        var sumDetilDisc1PlusRp = 0.0
        var sumDetilDisc1PlusPpnRp = 0.0
        var sumDetilDisc1PlusRpAfterPpn = 0.0
        var sumDetilSubtotalAfterDisc1PlusRp = 0.0
        var sumDetilSubtotalAfterDisc1PlusPpnRp = 0.0
        var sumDetilSubtotalAfterDisc1PlusAfterPpn = 0.0
        var sumDetilDisc2PlusRp = 0.0
        var sumDetilDisc2PlusPpnRp = 0.0
        var sumDetilDisc2PlusRpAfterPpn = 0.0
        var sumDetilSubtotalAfterDisc2PlusRp = 0.0
        var sumDetilSubtotalAfterDisc2PlusPpnRp = 0.0
        var sumDetilSubtotalAfterDisc2PlusAfterPpn = 0.0
        /*
		 * Header
		 */
        var sumHeaderDiscNota1 = 0.0
        var sumHeaderDiscNota1Rp = 0.0
        var sumHeaderDiscNota1PpnRp = 0.0 //ppn
        var sumHeaderDiscNota1RpAfterPpn = 0.0

        //TAMBAHAN
        var sumHeaderSubtotalAfterDiscNota1Rp = 0.0
        var sumHeaderSubtotalAfterDiscNota1PpnRp = 0.0 //ppn
        var sumHeaderSubtotalAfterDiscNota1RpAfterPpn = 0.0
        var sumHeaderDiscNota2 = 0.0
        var sumHeaderDiscNota2Rp = 0.0
        var sumHeaderDiscNota2PpnRp = 0.0 //ppn
        var sumHeaderDiscNota2RpAfterPpn = 0.0
        var sumHeaderSubtotalAfterDiscNota2Rp = 0.0
        var sumHeaderSubtotalAfterDiscNota2PpnRp = 0.0 //ppn
        var sumHeaderSubtotalAfterDiscNota2RpAfterPpn = 0.0
        var sumHeaderDiscNotaPlus = 0.0
        var sumHeaderDiscNotaPlusRp = 0.0
        var sumHeaderDiscNotaPlusPpnRp = 0.0 //ppn
        var sumHeaderDiscNotaPlusRpAfterPpn = 0.0
        var sumHeaderSubtotalAfterDiscNotaPlusRp = 0.0
        var sumHeaderSubtotalAfterDiscNotaPlusPpnRp = 0.0 //ppn
        var sumHeaderSubtotalAfterDiscNotaPlusRpAfterPpn = 0.0
        for (item in items) {
            jumlahRec++
            /*
			 * Kasihan berat jika dihitung lagi: Tapi aman
			 */
            var newItem = FtSalesdItems()
            val hdp: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(newFtSalesh, item)
            hdp.isRoundedTotalHeader = true
            newItem = hdp.fillFtSalesd
            sumDetilSubtotalRp += item.subtotalRp
            sumDetilSubtotalPpnRp += item.subtotalPpnRp
            sumDetilSubtotalRpAfterPpn += item.subtotalRpAfterPpn
            sumDetilDisc1Rp += item.disc1Rp
            sumDetilDisc1PpnRp += item.disc1PpnRp
            sumDetilDisc1RpAfterPpn += item.disc1RpAfterPpn
            sumDetilDisc2Rp += item.disc2Rp
            sumDetilDisc2PpnRp += item.disc2PpnRp
            sumDetilDisc2RpAfterPpn += item.disc2RpAfterPpn
            sumDetilDisc3Rp += item.disc3Rp
            sumDetilDisc3PpnRp += item.disc3PpnRp
            sumDetilDisc3RpAfterPpn += item.disc3RpAfterPpn
            sumDetilSubtotalAfterDisc123Rp += item.subtotalAfterDisc123Rp
            sumDetilSubtotalAfterDisc123PpnRp += item.subtotalAfterDisc123PpnRp
            sumDetilSubtotalAfterDisc123RpAfterPpn += item.subtotalAfterDisc123RpAfterPpn
            sumDetilDisc1PlusRp += item.disc1PlusRp
            sumDetilDisc1PlusPpnRp += item.disc1PlusPpnRp
            sumDetilDisc1PlusRpAfterPpn += item.disc1PlusRpAfterPpn
            sumDetilSubtotalAfterDisc1PlusRp += item.subtotalAfterDisc1PlusRp
            sumDetilSubtotalAfterDisc1PlusPpnRp += item.subtotalAfterDisc1PlusPpnRp
            sumDetilSubtotalAfterDisc1PlusAfterPpn += item.subtotalAfterDisc1PlusRpAfterPpn
            sumDetilDisc2PlusRp += item.disc2PlusRp
            sumDetilDisc2PlusPpnRp += item.disc2PlusPpnRp
            sumDetilDisc2PlusRpAfterPpn += item.disc2PlusRpAfterPpn
            sumDetilSubtotalAfterDisc2PlusRp += item.subtotalAfterDisc2PlusRp
            sumDetilSubtotalAfterDisc2PlusPpnRp += item.subtotalAfterDisc2PlusPpnRp
            sumDetilSubtotalAfterDisc2PlusAfterPpn += item.subtotalAfterDisc2PlusRpAfterPpn

            /*
			 * Header
			 */sumHeaderDiscNota1 = item.discNota1
            sumHeaderDiscNota1Rp += item.discNota1Rp
            sumHeaderDiscNota1PpnRp += item.discNota1PpnRp
            sumHeaderDiscNota1RpAfterPpn += item.discNota1RpAfterPpn

//			System.out.println("After Ppn: " + newItem.getDiscNota1() + " = " + newItem.getDiscNota1Rp() + " + " + newItem.getDiscNota1RpAfterPpn());
//			System.out.println("Dijalankan Items: " + sumHeaderDiscNota1Rp + "  " + sumHeaderDiscNota1RpAfterPpn);

            //#TAMBAHAN
            sumHeaderSubtotalAfterDiscNota1Rp += item.subtotalAfterDiscNota1Rp
            sumHeaderSubtotalAfterDiscNota1PpnRp += item.subtotalAfterDiscNota1PpnRp
            sumHeaderSubtotalAfterDiscNota1RpAfterPpn += item.subtotalAfterDiscNota1RpAfterPpn
            sumHeaderDiscNota2 = item.discNota2
            sumHeaderDiscNota2Rp += item.discNota2Rp
            sumHeaderDiscNota2PpnRp += item.discNota2PpnRp
            sumHeaderDiscNota2RpAfterPpn += item.discNota2RpAfterPpn
            sumHeaderSubtotalAfterDiscNota2Rp += item.subtotalAfterDiscNota2Rp
            sumHeaderSubtotalAfterDiscNota2PpnRp += item.subtotalAfterDiscNota2PpnRp
            sumHeaderSubtotalAfterDiscNota2RpAfterPpn += item.subtotalAfterDiscNota2RpAfterPpn
            sumHeaderDiscNotaPlus = item.discNotaPlus_FG
            sumHeaderDiscNotaPlusRp += item.discNotaPlusRp_FG
            sumHeaderDiscNotaPlusPpnRp += item.discNotaPlusPpnRp_FG
            sumHeaderDiscNotaPlusRpAfterPpn += item.discNotaPlusRpAfterPpn_FG
            sumHeaderSubtotalAfterDiscNotaPlusRp += item.subtotalAfterDiscNotaPlusRp_FG
            sumHeaderSubtotalAfterDiscNotaPlusPpnRp += item.subtotalAfterDiscNotaPlusPpnRp_FG
            sumHeaderSubtotalAfterDiscNotaPlusRpAfterPpn += item.subtotalAfterDiscNotaPlusRpAfterPpn_FG
        }
        newFtSalesh.amountRp = sumDetilSubtotalAfterDisc2PlusRp //Kasih baru hasil perhitungan
        newFtSalesh.amountPpnRp = sumDetilSubtotalAfterDisc2PlusPpnRp //Kasih baru hasil perhitungan
        newFtSalesh.amountRpAfterPpn = sumDetilSubtotalAfterDisc2PlusAfterPpn //Kasih baru hasil perhitungan
        newFtSalesh.disc1Rp = sumHeaderDiscNota1Rp
        newFtSalesh.disc1PpnRp = sumHeaderDiscNota1PpnRp
        newFtSalesh.disc1RpAfterPpn = sumHeaderDiscNota1RpAfterPpn

        //TAMBAHAN
        newFtSalesh.amountAfterDisc1Rp = sumHeaderSubtotalAfterDiscNota1Rp
        newFtSalesh.amountAfterDisc1PpnRp = sumHeaderSubtotalAfterDiscNota1PpnRp
        newFtSalesh.amountAfterDisc1RpAfterPpn = sumHeaderSubtotalAfterDiscNota1RpAfterPpn
        newFtSalesh.disc2Rp = sumHeaderDiscNota2Rp
        newFtSalesh.disc2PpnRp = sumHeaderDiscNota2PpnRp
        newFtSalesh.disc2RpAfterPpn = sumHeaderDiscNota2RpAfterPpn
        newFtSalesh.amountAfterDisc2Rp = sumHeaderSubtotalAfterDiscNota2Rp
        newFtSalesh.amountAfterDisc2PpnRp = sumHeaderSubtotalAfterDiscNota2PpnRp
        newFtSalesh.amountAfterDisc2RpAfterPpn = sumHeaderSubtotalAfterDiscNota2RpAfterPpn
        newFtSalesh.discPlusRp_FG = sumHeaderDiscNotaPlusRp
        newFtSalesh.discPlusPpnRp_FG = sumHeaderDiscNotaPlusPpnRp
        newFtSalesh.discPlusRpAfterPpn_FG = sumHeaderDiscNotaPlusRpAfterPpn


        //DPP
        newFtSalesh.amountAfterDiscPlusRp_FG = sumHeaderSubtotalAfterDiscNotaPlusRp
        //PPN
        newFtSalesh.ppnRp = sumHeaderSubtotalAfterDiscNotaPlusPpnRp
        //DPP+PPN
        newFtSalesh.amountAfterDiscPlusRpAfterPpn_FG = sumHeaderSubtotalAfterDiscNotaPlusRpAfterPpn
        return newFtSalesh
        //		return getFillFtSalesh();
    }

    /*
	 * HATI HATI MERUBAH INI *************
	 * Kita sudah tidak pakai Konsep PPN pada Header
	 */
    override var taxPercent: Double
        get() = ppnPercent
        set(taxPercent) {
            ppnFloat = taxPercent / 100 + 1
            ppnPercent = taxPercent
        }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (serialVersionUID xor (serialVersionUID ushr 32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is HeaderDetilSalesHelperImpl) {
            return false
        }
        return if (serialVersionUID != obj.serialVersionUID) {
            false
        } else true
    }
}