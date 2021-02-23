package com.erp.distribution.sfa.domain.utils

import com.erp.distribution.sfa.domain.model.FMaterial

open interface KonversiProductAndStockHelper {
    fun setValue(pcsOrUom4: Double, fMaterial: FMaterial?)

    fun getUom1FromSmallest(): Double
    fun getUom2FromSmallest(): Double
    fun getUom3FromSmallest(): Double
    fun getUom4FromSmallest(): Double

    fun getPPriceBeforePpnFromFMaterialBeforePpn(): Double
    fun getSPriceBeforePpnFromFMaterialBeforePpn(): Double

    fun getPPriceBeforePpnFromFMaterialAfterPpn(): Double
    fun getSPriceBeforePpnFromFMaterialAfterPpn(): Double

    fun getUom1234String(jumlahDigit: Double): String?
    fun getUom1234StringUom(): String?
    fun getUom1234StringUomHurufBesar(): String?
    fun getUom1234StringUom_FromConvfact(qtyStock_Smallest: Double, convfact1: Int, convfact2: Int, convfact3: Int, uom1: String?, uom2: String?, uom3: String?, uom4: String?): String?
    fun getUom1234StringUom(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String?
    fun getUom1234StringUomHtml(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String?
    fun getUom_234StringUomHtml(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String?

    fun getUom_234StringUom(): String?
    fun getUom_234StringUomHurufBesar(): String?

    fun getSmallestFromUom1234(fMaterialBean: FMaterial?, uom1: Double, uom2: Double, uom3: Double, uom4: Double): Double

}