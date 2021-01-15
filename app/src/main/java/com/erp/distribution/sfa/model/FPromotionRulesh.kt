package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumPromoDiscFgMethod
import java.util.*

//@Entity(tableName = "fpromotion_rulesh")
@Entity(tableName = "FPromotionRulesh")
class FPromotionRulesh {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0
    var kode1 = ""
    var kode2 = ""
    var description = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var validPeriodDateFrom = Date()
    var validPeriodDateTo = Date()
    var isSharedToCompany = false //setting Shared to company khusus ditaruh disini, bukan di divisi
    var isStatusActive = true

    /*
    * AKAN DITAMBAHKAN
    * di freegood ditambahkan jumlah case maximal
    */
    //	FPromotionBudget fpromotionBudget;
    //	AccAccount accAccountBean;
    /*
	 * Account Mapping
	 * Account Biaya Diskon jika: Yang akan terbit saat akan membuat jurnal saat transaksi penjualan
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //	private AccAccount accAccountBean; //DEBIT
    var accAccountBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccount_CreditBean", referencedColumnName="ID")
    //	private AccAccount accAccount_CreditBean; //CREDIT
    var accAccount_CreditBean = 0
    var isClaimPabrik = false

    //	@Column(name="GANTI_BARANG")
    //	private boolean gantiBarang=false;
    //	@ManyToOne
    //	@JoinColumn(name="accAccountDebitBean", referencedColumnName="ID")
    //	private AccAccount accAccountDebitBean; //Acount Debit jika Claimable ke Pabrik
    var maxTargetValue = 0.0 // 0 (nol) berarti tidak ada target sampai behenti
    var maxTargetQty = 0 // 0 (nol) berarti tidak ada target sampai berhenti
    var totalValueApplied = 0.0
    var totalQtyApplied = 0

    @Ignore
    var totalQtyAppliedUom1234 = ""
    var amountPayRp = 0.0 //Menghilangkan DCV

    /*
    * DISCOUNT METHOD: What Customers Gets
    */
    var promoDiscMethod: EnumPromoDiscFgMethod? = null

    /*
    * Minimum Pengambilan
    */
    var discMinQtyOrValue = 0
    var discMaxQtyOrValue = 0

    /*
    * KALAU DISC KAN PASTI KELIPATAN YA?
    * INI BERARTI TIDAK PERLU: 
    * PERLU DIEVALUASI LAGI
    */
    var isDiscKelipatan = true
    var isDiscCashOnly = false
    var forDiscQtyOrValueLev1 = 0.0
    var disc1GetLev1 = 0.0
    var disc1GetLev1_Value = 0.0
    var disc2GetLev1 = 0.0
    var disc3GetLev1 = 0.0
    var disc1PlusGetLev1 = 0.0
    var disc2PlusGetLev1 = 0.0
    var forDiscQtyOrValueLev2 = 0.0
    var disc1GetLev2 = 0.0
    var disc1GetLev2_Value = 0.0
    var disc2GetLev2 = 0.0
    var disc3GetLev2 = 0.0
    var disc1PlusGetLev2 = 0.0
    var disc2PlusGetLev2 = 0.0
    var forDiscQtyOrValueLev3 = 0.0
    var disc1GetLev3 = 0.0
    var disc1GetLev3_Value = 0.0
    var disc2GetLev3 = 0.0
    var disc3GetLev3 = 0.0
    var disc1PlusGetLev3 = 0.0
    var disc2PlusGetLev3 = 0.0
    var forDiscQtyOrValueLev4 = 0.0
    var disc1GetLev4 = 0.0
    var disc1GetLev4_Value = 0.0
    var disc2GetLev4 = 0.0
    var disc3GetLev4 = 0.0
    var disc1PlusGetLev4 = 0.0
    var disc2PlusGetLev4 = 0.0
    var forDiscQtyOrValueLev5 = 0.0
    var disc1GetLev5 = 0.0
    var disc1GetLev5_Value = 0.0
    var disc2GetLev5 = 0.0
    var disc3GetLev5 = 0.0
    var disc1PlusGetLev5 = 0.0
    var disc2PlusGetLev5 = 0.0

    /*
    * Kadang dalam suatu promo mendapat 2 barang
    * FREE GOOD 1 METHOD: What Customers Gets
    */
    var promoFg1Method: EnumPromoDiscFgMethod? = null

    /*
        * Minimum Pengambilan
        */
    var fg1MinQtyOrValue = 0
    var isFg1Kelipatan = true

    //		@ManyToOne
    //		@JoinColumn(name="freeGood1MaterialBean", referencedColumnName="ID")
    //	    private FMaterial freeGood1MaterialBean;
    var freeGood1MaterialBean = 0
    var isFg1HargaJualNol = true

    /*
        * Dipakai untuk perhitungan berapa nominal rupiah
        * 
        */
    var fg1PricePcs = 0.0
    var forFg1QtyOrValueLev1 = 0
    var fg1QtyGetLev1 = 0
    var forFg1QtyOrValueLev2 = 0
    var fg1QtyGetLev2 = 0
    var forFg1QtyOrValueLev3 = 0
    var fg1QtyGetLev3 = 0
    var forFg1QtyOrValueLev4 = 0
    var fg1QtyGetLev4 = 0
    var forFg1QtyOrValueLev5 = 0
    var fg1QtyGetLev5 = 0

    /*
    * Kadang dalam suatu promo mendapat 2 barang
    * FREE GOOD 2 METHOD: What Customers Gets
    */
    var promoFg2Method: EnumPromoDiscFgMethod? = null

    /*
        * Minimum Pengambilan
        */
    var fg2MinQtyOrValue = 0
    var isFg2Kelipatan = true

    //		@ManyToOne
    //		@JoinColumn(name="freeGood2MaterialBean", referencedColumnName="ID")
    //	    private FMaterial freeGood2MaterialBean;
    var freeGood2MaterialBean = 0
    var isFg2HargaJualNol = true

    /*
        * Dipakai untuk perhitungan berapa nominal rupiah
        * 
        */
    var fg2PricePcs = 0.0
    var forFg2QtyOrValueLev1 = 0
    var fg2QtyGetLev1 = 0
    var forFg2QtyOrValueLev2 = 0
    var fg2QtyGetLev2 = 0
    var forFg2QtyOrValueLev3 = 0
    var fg2QtyGetLev3 = 0
    var forFg2QtyOrValueLev4 = 0
    var fg2QtyGetLev4 = 0
    var forFg2QtyOrValueLev5 = 0
    var fg2QtyGetLev5 = 0

    //CASHBACK	
    var cashBackValue1 = 0.0
    var cashBackGet1 = 0.0
    var cashBackValue2: Double? = null
    var cashBackGet2 = 0.0
    var cashBackValue3 = 0.0
    var cashBackGet3 = 0.0
    var cashBackValue4 = 0.0
    var cashBackGet4 = 0.0

    //LOG
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}