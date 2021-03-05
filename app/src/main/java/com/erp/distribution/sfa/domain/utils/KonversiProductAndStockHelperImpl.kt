package com.erp.distribution.sfa.domain.utils

import com.erp.distribution.sfa.domain.model.FMaterial
import java.text.NumberFormat

class KonversiProductAndStockHelperImpl : KonversiProductAndStockHelper {
    private val serialVersionUID = -3353307136697624454L
    var fMaterial: FMaterial? = FMaterial()
    var pcsOrUom4 = 0.0

    constructor() {}
    constructor(pcsOrUom4: Double, fMaterial: FMaterial?) {
        this.pcsOrUom4 = pcsOrUom4
        this.fMaterial = fMaterial
    }

    constructor(pcsOrUom4: Double, convfact1: Int, convfact2: Int, convfact3: Int) {
        this.pcsOrUom4 = pcsOrUom4
        fMaterial = FMaterial()
        fMaterial?.convfact1 = convfact1
        fMaterial?.convfact2 = convfact2
        fMaterial?.convfact3 = convfact3
    }

    override fun setValue(pcsOrUom4: Double, fMaterial: FMaterial?) {
        this.pcsOrUom4 = pcsOrUom4
        this.fMaterial = fMaterial
    }

    override fun getUom1FromSmallest(): Double {
        var valueBiggest: Int = 0
        if (fMaterial!!.convfact1 === 1) {
            try {
                valueBiggest = pcsOrUom4.toInt() / fMaterial!!.convfact1 //maka double
            } catch (ex: Exception) {
            }
        } else {
            try {
                valueBiggest = pcsOrUom4.toInt() / fMaterial!!.convfact1
            } catch (ex: Exception) {
            }
        }
        return valueBiggest.toDouble()
    }

    override fun getUom2FromSmallest(): Double {
        var valueUom2: Int = 0
        if (fMaterial!!.convfact1 === 1) { //Asumsi Convfact2,3 pasti 1
            valueUom2 = 0
        } else if (fMaterial!!.convfact2 === 1) { //dan seterusnya pasti satu
            try {
                val sisaQtyUom1: Int = pcsOrUom4.toInt() % fMaterial!!.convfact1
                valueUom2 = sisaQtyUom1 / fMaterial!!.convfact2
            } catch (ex: Exception) {
            }
        } else {
            try {
                val sisaQtyUom1: Int = pcsOrUom4.toInt() % fMaterial!!.convfact1
                valueUom2 = sisaQtyUom1.toInt() / fMaterial!!.convfact2
            } catch (ex: Exception) {
            }
        }
        return valueUom2.toDouble()
    }//				int sisaQtyUom1 = (int) pcsOrUom4 % fMaterial!!.convfact1;		//				double sisaQtyUom1 = pcsOrUom4 % fMaterial!!.convfact1;		//dan seterusnya pasti satu

    //Asumsi Convfact2,3 pasti 1
    val uom2_234FromSmallest: Double
        get() {
            var valueUom2: Int = 0
            if (fMaterial!!.convfact1 === 1) { //Asumsi Convfact2,3 pasti 1
                valueUom2 = 0
            } else if (fMaterial!!.convfact2 === 1) { //dan seterusnya pasti satu
                try {
//				double sisaQtyUom1 = pcsOrUom4 % fMaterial!!.convfact1;		
                    valueUom2 = pcsOrUom4.toInt() / fMaterial!!.convfact2
                } catch (ex: Exception) {
                }
            } else {
                try {
//				int sisaQtyUom1 = (int) pcsOrUom4 % fMaterial!!.convfact1;		
                    valueUom2 = pcsOrUom4.toInt() / fMaterial!!.convfact2
                } catch (ex: Exception) {
                }
            }
            return valueUom2.toDouble()
        }

    override fun getUom3FromSmallest(): Double {
        var valueUom3: Int = 0
        if (fMaterial?.convfact1 === 1) { //Asumsi Convfact2,3 pasti 1
            valueUom3 = 0
        } else if (fMaterial?.convfact2 === 1) { //Asumsi Convfact 3 pasti 1
            valueUom3 = 0
        } else if (fMaterial?.convfact3 === 1) {
            try {
                val sisaQtyUom1: Int = pcsOrUom4.toInt() % fMaterial!!.convfact1
                val sisaQtyUom2: Int = sisaQtyUom1 % fMaterial!!.convfact2
                valueUom3 = sisaQtyUom2.toInt() / fMaterial!!.convfact3
            } catch (ex: Exception) {
            }
        } else {
            try {
                val sisaQtyUom1: Int = pcsOrUom4.toInt() % fMaterial!!.convfact1
                val sisaQtyUom2: Int = sisaQtyUom1 % fMaterial!!.convfact2
                valueUom3 = sisaQtyUom2.toInt() / fMaterial!!.convfact3
            } catch (ex: Exception) {
            }
        }
        return valueUom3.toDouble()
    }

    override fun getUom4FromSmallest(): Double {
        var valueUom4: Int = 0
        if (fMaterial!!.convfact1 === 1) { //Asumsi Convfact2,3 pasti 1
            valueUom4 = 0
        } else if (fMaterial!!.convfact2 === 1) { //Asumsi Convfact 3 pasti 1
            valueUom4 = 0
        } else if (fMaterial!!.convfact3 === 1) {
            valueUom4 = 0
        } else {
//			System.out.println("Uom4 masuk rule4");
            try {
                val sisaQtyUom1: Int = pcsOrUom4.toInt() % fMaterial!!.convfact1
                val sisaQtyUom2: Int = sisaQtyUom1.toInt() % fMaterial!!.convfact2
                val sisaQtyUom3: Int = sisaQtyUom2.toInt() % fMaterial!!.convfact3
                valueUom4 = sisaQtyUom3
            } catch (ex: Exception) {
            }
        }
        return valueUom4.toDouble()
    }

    override fun getPPriceBeforePpnFromFMaterialBeforePpn(): Double {
        return pcsOrUom4 * fMaterial!!.pprice2
    }

    override fun getSPriceBeforePpnFromFMaterialBeforePpn(): Double {
        return pcsOrUom4 * fMaterial!!.sprice2
    }

    override fun getPPriceBeforePpnFromFMaterialAfterPpn(): Double {
        return pcsOrUom4 * fMaterial!!.pprice2AfterPpn
    }

    override fun getSPriceBeforePpnFromFMaterialAfterPpn(): Double {
        return pcsOrUom4 * fMaterial!!.sprice2AfterPpn
    }

    override fun getUom1234String(jumlahDigit: Double): String? {
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

        val strUom1 = nf_3.format(getUom1FromSmallest())
        var strUom2 = nf_3.format(getUom2FromSmallest())
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())
        val strUom1234 = ""
        var tambahanNol = ""
        var jumlahTambahanNol = 0.0
        val lenUom2 = getUom2FromSmallest().toString().length.toDouble()
        jumlahTambahanNol = jumlahDigit - lenUom2
        tambahanNol = ""
        run {
            var i = 0.0
            while (i < jumlahTambahanNol) {
                tambahanNol += "0"
                i++
            }
        }
        strUom2 = tambahanNol + strUom2
        val lenUom3 = getUom3FromSmallest().toString().length.toDouble()
        jumlahTambahanNol = jumlahDigit - lenUom3
        tambahanNol = ""
        run {
            var i = 0.0
            while (i < jumlahTambahanNol) {
                tambahanNol += "0"
                i++
            }
        }
        strUom3 = tambahanNol + strUom3
        val lenUom4 = getUom4FromSmallest().toString().length.toDouble()
        jumlahTambahanNol = jumlahDigit - lenUom4
        tambahanNol = ""
        var i = 0.0
        while (i < jumlahTambahanNol) {
            tambahanNol += "0"
            i++
        }
        strUom4 = tambahanNol + strUom4
        return "$strUom1.$strUom2.$strUom3.$strUom4"
    }

    override fun getUom1234StringUom(): String? {
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3
        var strUom1 = nf_3.format(getUom1FromSmallest())
        var strUom2 = nf_3.format(getUom2FromSmallest())
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())
        if (getUom1FromSmallest() == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " " + fMaterial!!.uom1
        }
        if (getUom2FromSmallest() == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterial!!.uom2
        }
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterial!!.uom3
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterial!!.uom4
        }
        return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom1234StringUomHurufBesar(): String? {
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
        var strUom1 = nf_3.format(getUom1FromSmallest())
        var strUom2 = nf_3.format(getUom2FromSmallest())
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())
        if (getUom1FromSmallest() == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " " + fMaterial!!.uom1.toUpperCase()
        }
        if (getUom2FromSmallest() == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterial!!.uom2.toUpperCase()
        }
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterial!!.uom3.toUpperCase()
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterial!!.uom4.toUpperCase()
        }
        return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom1234StringUom(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String {
        fMaterial = fmaterialBean
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
        var strUom1 = nf_3.format(getUom1FromSmallest())
        var strUom2 = nf_3.format(getUom2FromSmallest())
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())
        if (getUom1FromSmallest() == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " " + fMaterial!!.uom1
        }
        if (getUom2FromSmallest() == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterial!!.uom2
        }
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterial!!.uom3
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterial!!.uom4
        }
        return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom1234StringUomHtml(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String {
        fMaterial = fmaterialBean
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = "<font color=\"blue\">" + nf_3.format(getUom1FromSmallest()) + "</font>";
        var strUom1 = nf_3.format(getUom1FromSmallest())
        var strUom2 = nf_3.format(getUom2FromSmallest())
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())
        if (getUom1FromSmallest() == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " <small>" + fMaterial!!.uom1.toString() + "</small> "
        }
        if (getUom2FromSmallest() == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " <small>" + fMaterial!!.uom2.toString() + "</small> "
        }
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " <small>" + fMaterial!!.uom3.toString() + "</small> "
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " <small>" + fMaterial!!.uom4.toString() + "</small> "
        }
        return "$strUom1$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom_234StringUomHtml(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String {
        fMaterial = fmaterialBean
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = "<font color=\"blue\">" + String.valueOf(getUom1FromSmallest()) + "</font>";

//		double qtyUom2 = pcsOrUom4 / fMaterial!!.convfact2;		
//		String strUom2 = nf_3.format(qtyUom2);
        var strUom2 = nf_3.format(uom2_234FromSmallest)
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())

//		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " <small>" + fMaterial!!.uom1 + "</small> ";
//		}
        if (uom2_234FromSmallest == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " <small>" + fMaterial!!.uom2.toString() + "</small> "
        }
        //		if (getUom2FromSmallest()==0){
//			strUom2="";
//		}else {
//			strUom2 += " <small>" + fMaterial!!.uom2 + "</small> ";			
//		}
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " <small>" + fMaterial!!.uom3.toString() + "</small> "
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " <small>" + fMaterial!!.uom4.toString() + "</small> "
        }
        return "$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

//    override fun getSmallestFromUom1234(fMaterialBean: FMaterial, uom1: Double, uom2: Double,
//                                        uom3: Double, uom4: Double): Double {
//        var qtyPcs = 0.0
//        qtyPcs = uom1 * fMaterialBean.convfact1 + uom2 * fMaterialBean.convfact2 + uom3 * fMaterialBean.convfact3 + uom4
//        return qtyPcs.toDouble()
//    }
    override fun getSmallestFromUom1234(fMaterialBean: FMaterial, uom1: Double, uom2: Double, uom3: Double, uom4: Double): Double {
        var qtyPcs = 0.0
        qtyPcs = uom1 * fMaterialBean.convfact1 + uom2 * fMaterialBean.convfact2 + uom3 * fMaterialBean.convfact3 + uom4

        return qtyPcs
    }

    override fun getUom1234StringUom_FromConvfact(qtyStock_Smallest: Double, convfact1: Int, convfact2: Int, convfact3: Int,
                                                  uom1: String?, uom2: String?, uom3: String?, uom4: String?): String? {
        fMaterial = FMaterial()
        fMaterial!!.convfact1 = convfact1
        fMaterial!!.convfact2 = convfact2
        fMaterial!!.convfact3 = convfact3
        fMaterial!!.uom1 = uom1!!
        fMaterial!!.uom2 = uom2!!
        fMaterial!!.uom3 = uom3!!
        fMaterial!!.uom4 = uom4!!
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3
        var strUom1 = nf_3.format(getUom1FromSmallest())
        var strUom2 = nf_3.format(getUom2FromSmallest())
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())
        if (getUom1FromSmallest() == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " " + fMaterial!!.uom1
        }
        if (getUom2FromSmallest() == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterial!!.uom2
        }
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterial!!.uom3
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterial!!.uom4
        }
        return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom_234StringUom(): String? {
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		double qtyUom2 = pcsOrUom4 / fMaterial!!.convfact2;	
//		String strUom2 = nf_3.format(qtyUom2);
        var strUom2 = nf_3.format(uom2_234FromSmallest)
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())

//		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " " + fMaterial!!.uom1.toUpperCase();
//		}
        if (uom2_234FromSmallest == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterial!!.uom2
        }
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterial!!.uom3
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterial!!.uom4
        }
        return "$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom_234StringUomHurufBesar(): String? {
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		double qtyUom2 = pcsOrUom4 / fMaterial!!.convfact2;		
//		String strUom2 = nf_3.format(qtyUom2);
        var strUom2 = nf_3.format(uom2_234FromSmallest)
        var strUom3 = nf_3.format(getUom3FromSmallest())
        var strUom4 = nf_3.format(getUom4FromSmallest())

//		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " " + fMaterial!!.uom1.toUpperCase();
//		}
        if (uom2_234FromSmallest == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterial!!.uom2.toUpperCase()
        }
        if (getUom3FromSmallest() == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterial!!.uom3.toUpperCase()
        }
        if (getUom4FromSmallest() == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterial!!.uom4.toUpperCase()
        }
        return "$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }


    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (fMaterial == null) 0 else fMaterial.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is KonversiProductAndStockHelperImpl) {
            return false
        }
        val other = obj
        if (fMaterial == null) {
            if (other.fMaterial != null) {
                return false
            }
        } else if (!fMaterial!!.equals(other.fMaterial)) {
            return false
        }
        return true
    }
}