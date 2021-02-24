package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.modelenum.*
import com.erp.distribution.sfa.domain.model.*
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.DateFormat
import java.util.*

//@Entity(tableName = "ftsalesh")
@Entity(tableName = "ftSalesh")
@Parcelize
data class FtSaleshEntity  (
    @PrimaryKey(autoGenerate = true)
    val refno: Long =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    val sourceId: Long =0,

    //ORDERNO=SO
    val orderno : String ="",
    val isValidOrder : Boolean =false,

    //INVOICENO
    val invoiceno : String ="",
    val priority : Int =0,

    /*
    * ignore/reject promotion rules setting
    */
    val isNoPromotionRules : Boolean =false,
    val taxNumber : String ="",
    val taxDate : Date =Date(),

    /*
    * SO: FROM GOOD RECEIVE
    */
    //	@ManyToOne
    //	@JoinColumn(name="fromGoodReceiptBean", referencedColumnName="refno", nullable=true)
    //	private  FtPurchaseh fromGoodReceiptBean;
    val fromGoodReceiptBean : Long =0,

    /*
    * FAKTUR FROM SO
    */
    //	@ManyToOne
    //	@JoinColumn(name="fakturSOBean", referencedColumnName="refno")
    //	private  FtSalesh fakturSOBean; //me as Sales Invoice
    val fakturSOBean : Long =0,

    //	@ManyToOne
    //	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable=true)
    //	private  FtSalesh returAtasFakturBean;
    val returAtasFakturBean : Long =0,

    /*
    * Status Nota: (1)O-Open Sedang dikirim, (2)T-Terkirim, 
    * 		(3)P-Pending Pengiriman, (4)B-Batal Nota Batal Seluruhnya
    */
    val statusPengiriman: EnumStatusPengiriman = EnumStatusPengiriman.NOTA_OPEN,

    //SURAT JALAN PENGIRIMAIN = DO
    val sjPengirimanNo : String ="",
    val sjPengirimanDate : Date =Date(), //Jika tidak ada nomor SJ maka tidak berlaku

    //Driver
    //	@Column(name="DRIVER_NAME", length=40)
    //	private String driverName="";
    //	@ManyToOne
    //	@JoinColumn(name="driverBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman driverBean;
    val driverBean : Int =0,
    val nopol : String ="",

    /*
    * HARUS DIGANTI MENGGUNAKAN LIST
    */
    //SJ Pengiriman, Delivery, SJ Penagihan
    //	@Column(name="SJ_AND_DELV_HISTORY", length=250)
    //	private String sjAndDelvHistory="";		
    val sjPenagihanNo : String ="",
    val sjPenagihanDate : Date =Date(),

    //	@ManyToOne
    //	@JoinColumn(name="collectorBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman collectorBean;
    val collectorBean : Int =0,
    val invoiceDate : Date =Date(),
    val orderDate : Date =Date(),
    val top : Int =0,
    val dueDate : Date =Date(),

    /*
    * SEPERTINYA KITA TIDAK PAKAI INI
    * SALDO AWAL ITU ada pada tipe transaksi
    */
    //	@Column(name="SALDO")
    //	private boolean saldo=false;
    /*
	 * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon) 
	 */
    val amountRp : Double =0.0,
    val amountPpnRp : Double =0.0,

//    @Ignore
//    val amountRpAfterPpn : Double =0.0,

    /*
    * berhubungan dengan Account -> menjadi apa
    * 	Kas (kas Masuk) pada
    * 		Uang Muka Penjulaan
    * Dengan Giro/Transfer, dan jika kosong maka Artinya Cash
    * Asumsi: Uang muka adalah sekali
    */
    //	@Column(name="UANG_MUKA_RP")
    //	private Double uangMukaRp=0.0;
    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    /*
	 * ATAS TIDAK DIPAKAI LAGI
	 * Untuk SO saja
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fuangMuka_SOBean", referencedColumnName="ID", nullable=true)
    //	private FUangMuka fuangMuka_SOBean;
    val fuangMuka_SOBean : Int =0,
    val disc1 : Double =0.0,

//    @Ignore
//    val disc1Rp : Int =0,

//    @Ignore
//    val disc1PpnRp : Int =0,

//    @Ignore
//    val disc1RpAfterPpn : Int =0,

    //###TAMBAHAN
//    @Ignore
//    val amountAfterDisc1Rp : Int =0,

//    @Ignore
//    val amountAfterDisc1PpnRp : Int =0,

//    @Ignore
//    val amountAfterDisc1RpAfterPpn : Int =0,
    val disc2 : Double =0.0,

//    @Ignore
//    val disc2Rp : Int =0,

//    @Ignore
//    val disc2PpnRp : Int =0,

//    @Ignore
//    val disc2RpAfterPpn : Int =0,

    //AMOUNT AFTER DISC1 dan DISC2 dan DiscPlus
//    @Ignore
//    val amountAfterDisc2Rp : Int =0,

//    @Ignore
//    val amountAfterDisc2PpnRp : Int =0,

//    @Ignore
//    val amountAfterDisc2RpAfterPpn : Int =0,
    val discPlus_FG : Double =0.0,

//    @Ignore
//    val discPlusRp_FG : Int =0,

//    @Ignore
//    val discPlusPpnRp_FG : Int =0,

//    @Ignore
//    val discPlusRpAfterPpn_FG : Int =0,

    ///Jika yes maka setiap FG yang harganya nol maka akan di hitung akumulasinya, lalu nilainya ditaruh di CashBack
    val isCalcCashBackFg : Boolean =false,

    /*
    * Sama dengan bawah: Jangan Lupa
    */
    //DPP
    val amountAfterDiscPlusRp_FG : Double =0.0,
    val ppnRp : Double =0.0,

    //DPP+PPN
    val amountAfterDiscPlusRpAfterPpn_FG : Double =0.0,

    //AMOUNT PAY
    val amountPayRp : Int =0,
    val isEndOfDay : Boolean =false,
    val isOpenLockInputPriceAndDiscount : Boolean =false,

    /*
    * REQUEST PLAFON
    */
    val statusRequestDiscount: EnumRequestStatus = EnumRequestStatus.OPEN,
    val statusRequestPlafon: EnumRequestStatus = EnumRequestStatus.OPEN,
    val notes : String ="",
    val tunaiKredit: EnumTunaiKredit = EnumTunaiKredit.T,

    /* TIPE FAKTUR
    * F=FAKTUR PENJUALAN STANDART, R= RETURN PENJULAN, FI=PENJUALAN INTERN, 
    * FDN= DEBIT NOTE MANUAL, RCN=RETUR CREDIT NOTE MANUAL
    * */
    val tipeFaktur: EnumTipeFakturJual = EnumTipeFakturJual.F,

    /* TIPE JUAL
    * SHOP=SHOPSALE, TO=TAKING ORDER, C=CANVAS, TF=TASK FORCE, D=DENTED, BS=BAD STOCK
    * */
    val salesType: EnumSalesType = EnumSalesType.TO,
    val printCounter : Int =0,

    //ATURAN: update stok dan sumber apakah manual atau tidak
    //	@Column(name="SOURCE", length=3)
    //	private String source ="";	
    val isProses : Boolean =false,
    val isUsedSO : Boolean =false,

    //1.Cash 2.Debit 3.Kartu Kredit 4.Cek 5.E-Wallet 6.Lain-lain
    val tipeBayarPos : Int =0,
    val amountKasirBayar : Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    val fdivisionBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable=false)
    //	private FSalesman fsalesmanBean;
    val fsalesmanBean : Int =0,

    /*
    *	fcustomerBean = Bill To adalah yang melakuan Order Pertama kali
    *	fcustomerShipToBean = adalah tempat pengiriman barang. jika null maka Shipto adalah BillTo 
    *	fcustomerPromoToBean = adalah melakukan Switch Pengalihan Promo
    */
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean", referencedColumnName="ID", nullable=false)
//    @Ignore
//    val fcustomerBeanTemp: FCustomer? = null,
    val fcustomerBean : Int =0,

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerShipToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerShipToBean;
    val fcustomerShipToBean : Int =0,

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerPromoToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerPromoToBean;
    val fcustomerPromoToBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
    val fwarehouseBean : Int =0,

    /*
    * Account Mapping
    */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountArKbBean", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountArKbBean;
    val accAccountArKbBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="accAccountFtSaleshCredit", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountFtSaleshCredit;
    val accAccountFtSaleshCredit : Int =0,

//    @Ignore
//    val mapFtSalesdTemp: Map<Long, FtSalesdItems> = HashMap()

    //PEGIRIMAN:
    //	@ManyToOne
    //	@JoinColumn(name="fexpedisiBean", referencedColumnName="ID", nullable=true)
    //	private FExpedisi fexpedisiBean;
    val fexpedisiBean : Int =0,

//    @Ignore
//    val isSelected : Boolean =false,
    val stared: Boolean? = false,
    val unread: Boolean? = true,
    val selected: Boolean? = false,

    /*
    * MAPPING ACCOUNT
    * Tidak Berubah Ubah
    * 1. Tax Account: ikut Mappint Sistem Divisi
    * 2. Persediaan: Tidak berubah ubah karena defaultnya per-divisi perbarang
    * 3. COGS/HPP: tidak berubah-ubah karena defaultnya per-divisi perbarang
    * Bisa di ubah-ubah
    * 1. Kas Besar
    * 2. Piutang 
    */
    val created : Date =Date(),
    val modified : Date =Date(),
    val modifiedBy : String ="", //User ID
): Parcelable{
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)

}


internal fun FtSaleshEntity.toDomain(): FtSalesh {
    return FtSalesh(
        refno = refno,
        sourceId = sourceId,
        orderno = orderno,
        isValidOrder = isValidOrder,
        invoiceno = invoiceno,
        isNoPromotionRules = isNoPromotionRules,
        taxNumber = taxNumber,
        taxDate = taxDate,

        statusPengiriman = statusPengiriman,
        sjPengirimanNo = sjPengirimanNo,
        sjPengirimanDate = sjPengirimanDate,
        driverBean = driverBean,
        nopol = nopol,

        sjPenagihanNo = sjPenagihanNo,
        sjPenagihanDate = sjPenagihanDate,

        collectorBean = collectorBean,
        invoiceDate = invoiceDate,
        orderDate = orderDate,
        top = top,
        dueDate = dueDate,

        amountRp = amountRp,
        amountPpnRp = amountPpnRp,
        disc1 = disc1,
        disc2 = disc2,
        discPlus_FG = discPlus_FG,
        isCalcCashBackFg = isCalcCashBackFg,
        amountAfterDiscPlusRp_FG = amountAfterDiscPlusRp_FG,
        ppnRp = ppnRp,
        amountAfterDiscPlusRpAfterPpn_FG = amountAfterDiscPlusRpAfterPpn_FG,

        amountPayRp = amountPayRp,
        isEndOfDay = isEndOfDay,
        isOpenLockInputPriceAndDiscount = isOpenLockInputPriceAndDiscount,

        statusRequestDiscount = statusRequestDiscount,
        statusRequestPlafon = statusRequestPlafon,
        notes = notes,
        tunaiKredit = tunaiKredit,
        tipeFaktur = tipeFaktur,

        salesType = salesType,
        printCounter = printCounter,

        isProses = isProses,
        isUsedSO = isUsedSO,

        tipeBayarPos = tipeBayarPos,
        amountKasirBayar = amountKasirBayar,

        fdivisionBean = FDivision(fdivisionBean),
        fsalesmanBean = FSalesman(fsalesmanBean),
        fcustomerBean = FCustomer(fcustomerBean),
        fcustomerShipToBean = FCustomer(fcustomerShipToBean),
        fcustomerPromoToBean = FCustomer(fcustomerPromoToBean),
        fwarehouseBean = FWarehouse(fwarehouseBean),

        stared = stared,
        unread = unread,
        selected = selected,


        created = created!!,
        modified = modified!!,
        modifiedBy = modifiedBy!!

    )
}

data class FtSaleshWithFDivisionAndFCustomer(
        @Embedded  val ftSaleshEntity: FtSaleshEntity,

        @Relation(
                parentColumn = "fdivisionBean",
                entityColumn = "id"
        )
        val fDivisionEntity: FDivisionEntity?,

        @Relation(
                parentColumn = "fcustomerBean",
                entityColumn = "id"
        )
        val fCustomerEntity: FCustomerEntity? //it mean boleh null, tapi jika ada null maka Room otomatis akan meberikan entity kosong
)

data class FtSaleshWithFDivisionAndFSalesmanAndFCustomer(
        @Embedded  val ftSaleshEntity: FtSaleshEntity,

        @Relation(
                parentColumn = "fdivisionBean",
                entityColumn = "id"
        )
        val fDivisionEntity: FDivisionEntity?,

        @Relation(
                parentColumn = "fsalesmanBean",
                entityColumn = "id"
        )
        val fSalesmanEntity: FSalesmanEntity?, //it mean boleh null, tapi jika ada null maka Room otomatis akan meberikan entity kosong

        @Relation(
                parentColumn = "fcustomerBean",
                entityColumn = "id"
        )
        val fCustomerEntity: FCustomerEntity? //it mean boleh null, tapi jika ada null maka Room otomatis akan meberikan entity kosong
)

data class FtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItems(
        @Embedded  val ftSaleshEntity: FtSaleshEntity,

        @Relation(
                parentColumn = "fdivisionBean",
                entityColumn = "id"
        )
        val fDivisionEntity: FDivisionEntity?,

        @Relation(
                parentColumn = "fsalesmanBean",
                entityColumn = "id"
        )
        val fSalesmanEntity: FSalesmanEntity?, //it mean boleh null, tapi jika ada null maka Room otomatis akan meberikan entity kosong

        @Relation(
                parentColumn = "fcustomerBean",
                entityColumn = "id"
        )
        val fCustomerEntity: FCustomerEntity?,

        @Relation(
                parentColumn = "refno",
                entityColumn = "ftSaleshBean"
        )
        val listFtSalesdItems: List<FtSalesdItemsEntity>

)
data class FtSaleshWithFCustomerAndItems(
        @Embedded  val ftSaleshEntity: FtSaleshEntity,

        @Relation(
                parentColumn = "fcustomerBean",
                entityColumn = "id"
        )
        val fCustomerEntity: FCustomerEntity?,

        @Relation(
                parentColumn = "refno",
                entityColumn = "ftSaleshBean"
        )
        val listFtSalesdItems: List<FtSalesdItemsEntity>

)
