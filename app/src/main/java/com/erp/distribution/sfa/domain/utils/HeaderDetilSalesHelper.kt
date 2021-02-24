package com.erp.distribution.sfa.domain.utils

import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh

interface HeaderDetilSalesHelper {
    val taxPercentFloat: Double
    fun setSpricePpnRp()

    //Pprice aja adalah harga asli: jadi tidak dipakai
    fun setDetilSpriceUom2()
    fun setDetilSpriceUom3()
    fun setDetilSpriceUom4()
    fun setDetilSpriceAfterPpn()
    fun setDetilSpriceUom2AfterPpn()
    fun setDetilSpriceUom3AfterPpn()
    fun setDetilSpriceUom4AfterPpn()

    //QTY --> SUBTOTAL
    fun setDetilQty1()
    fun setDetilQty2()
    fun setDetilQty3()
    fun setDetilQty4()
    fun setDetilQty1Kembali()
    fun setDetilQty2Kembali()
    fun setDetilQty3Kembali()
    fun setDetilQty4Kembali()
    fun setDetilQtyNET()
    fun setDetilSubtotalRp()
    fun setDetilSubtotalPpnRp()
    fun setDetilSubtotalRpAfterPpn()
    fun setDetilDisc1Rp()
    fun setDetilDisc1PpnRp()
    fun setDetilDisc1RpAfterPpn()
    fun setDetilDisc1RpAfterPpnUom1()
    fun setDetilDisc1RpAfterPpnUom2()
    fun setDetilDisc1RpAfterPpnUom3()
    fun setDetilDisc1RpAfterPpnUom4()
    fun setDetilDisc2Rp()
    fun setDetilDisc2PpnRp()
    fun setDetilDisc2RpAfterPpn()
    fun setDetilDisc2RpAfterPpnUom1()
    fun setDetilDisc2RpAfterPpnUom2()
    fun setDetilDisc2RpAfterPpnUom3()
    fun setDetilDisc2RpAfterPpnUom4()
    fun setDetilDisc3Rp()
    fun setDetilDisc3PpnRp()
    fun setDetilDisc3RpAfterPpn()
    fun setDetilDisc3RpAfterPpnUom1()
    fun setDetilDisc3RpAfterPpnUom2()
    fun setDetilDisc3RpAfterPpnUom3()
    fun setDetilDisc3RpAfterPpnUom4()
    fun setDetilSubtotalAfterDisc123Rp()
    fun setDetilSubtotalAfterDisc123PpnRp()
    fun setDetilSubtotalAfterDisc123RpAfterPpn()
    fun setDetilDisc1PlusRp()
    fun setDetilDisc1PlusPpnRp()
    fun setDetilDisc1PlusRpAfterPpn()
    fun setDetilDisc1PlusRpAfterPpnUom1()
    fun setDetilDisc1PlusRpAfterPpnUom2()
    fun setDetilDisc1PlusRpAfterPpnUom3()
    fun setDetilDisc1PlusRpAfterPpnUom4()
    fun setDetilSubtotalAfterDisc1PlusRp()
    fun setDetilSubtotalAfterDisc1PlusPpnRp()
    fun setDetilSubtotalAfterDisc1PlusRpAfterPpn()
    fun setDetilDisc2PlusRp()
    fun setDetilDisc2PlusPpnRp()
    fun setDetilDisc2PlusRpAfterPpn()
    fun setDetilDisc2PlusRpAfterPpnUom1()
    fun setDetilDisc2PlusRpAfterPpnUom2()
    fun setDetilDisc2PlusRpAfterPpnUom3()
    fun setDetilDisc2PlusRpAfterPpnUom4()
    fun setDetilSubtotalAfterDisc2PlusRp()
    fun setDetilSubtotalAfterDisc2PlusPpnRp()
    fun setDetilSubtotalAfterDisc2PlusRpAfterPpn()

    /*
	 * Untuk keperluan Harga NET
	 */
    fun setSpriceNET_Uom1AfterDiscAfterPpn()
    fun setSpriceNET_Uom2AfterDiscAfterPpn()
    fun setSpriceNET_Uom3AfterDiscAfterPpn()
    fun setSpriceNET_Uom4AfterDiscAfterPpn()

    //Disc Detil (Tapi Jika ditambahkan Nota)
    fun setDetilDiscNota1Rp()
    fun setDetilDiscNota1PpnRp()
    fun setDetilDiscNota1RpAfterPpn()

    //TAMBAHAN
    fun setDetilSubtotalAfterDiscNota1Rp()
    fun setDetilSubtotalAfterDiscNota1PpnRp()
    fun setDetilSubtotalAfterDiscNota1RpAfterPpn()
    fun setDetilDiscNota2Rp()
    fun setDetilDiscNota2PpnRp()
    fun setDetilDiscNota2RpAfterPpn()
    fun setDetilSubtotalAfterDiscNota2Rp()
    fun setDetilSubtotalAfterDiscNota2PpnRp()
    fun setDetilSubtotalAfterDiscNota2RpAfterPpn()
    fun setDetilDiscNotaPlusRp_FG()
    fun setDetilDiscNotaPlusPpnRp_FG()
    fun setDetilDiscNotaPlusRpAfterPpn_FG()
    fun setDetilSubtotalAfterDiscNotaPlusRp_FG()
    fun setDetilSubtotalAfterDiscNotaPlusPpnRp_FG()
    fun setDetilSubtotalAfterDiscNotaPlusRpAfterPpn_FG()
    val fillFtSalesd: FtSalesdItems
    val fillFtSalesdOnly: FtSalesdItems

    //GET FIELD AND UPDATE
    //	public FtPurchaseh getFillFtPurchaseh();
    fun getFillFtSaleshFromItems(items: Set<FtSalesdItems>): FtSalesh
    var isRoundedTotalDetil: Boolean
    var isRoundedTotalHeader: Boolean
    fun setSprice(newSprice: Double)
    fun setQty(newQty: Double)
    var taxPercent: Double
}