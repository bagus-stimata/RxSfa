package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.*
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.DateFormat
import java.util.*

@Parcelize
data class FtSalesh  (
        var refno: Long =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long =0,

    //ORDERNO=SO
        var orderno : String ="",
        var isValidOrder : Boolean =false,

    //INVOICENO
        var invoiceno : String ="",
        var priority : Int =0,

    /*
    * ignore/reject promotion rules setting
    */
    var isNoPromotionRules : Boolean =false,
        var taxNumber : String ="",
        var taxDate : Date =Date(),

    /*
    * SO: FROM GOOD RECEIVE
    */
    //	@ManyToOne
    //	@JoinColumn(name="fromGoodReceiptBean", referencedColumnName="refno", nullable=true)
    //	private  FtPurchaseh fromGoodReceiptBean;
        var fromGoodReceiptBean : Int =0,

    /*
    * FAKTUR FROM SO
    */
    //	@ManyToOne
    //	@JoinColumn(name="fakturSOBean", referencedColumnName="refno")
    //	private  FtSalesh fakturSOBean; //me as Sales Invoice
        var fakturSOBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable=true)
    //	private  FtSalesh returAtasFakturBean;
        var returAtasFakturBean : Int =0,

    /*
    * Status Nota: (1)O-Open Sedang dikirim, (2)T-Terkirim, 
    * 		(3)P-Pending Pengiriman, (4)B-Batal Nota Batal Seluruhnya
    */
    var statusPengiriman: EnumStatusPengiriman = EnumStatusPengiriman.NOTA_OPEN,

    //SURAT JALAN PENGIRIMAIN = DO
        var sjPengirimanNo : String ="",
        var sjPengirimanDate : Date =Date(), //Jika tidak ada nomor SJ maka tidak berlaku

    //Driver
    //	@Column(name="DRIVER_NAME", length=40)
    //	private String driverName="";
    //	@ManyToOne
    //	@JoinColumn(name="driverBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman driverBean;
        var driverBean : Int =0,
        var nopol : String ="",

    /*
    * HARUS DIGANTI MENGGUNAKAN LIST
    */
    //SJ Pengiriman, Delivery, SJ Penagihan
    //	@Column(name="SJ_AND_DELV_HISTORY", length=250)
    //	private String sjAndDelvHistory="";		
        var sjPenagihanNo : String ="",
        var sjPenagihanDate : Date =Date(),

    //	@ManyToOne
    //	@JoinColumn(name="collectorBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman collectorBean;
        var collectorBean : Int =0,
        var invoiceDate : Date =Date(),
        var orderDate : Date =Date(),
        var top : Int =0,
        var dueDate : Date =Date(),

    /*
    * SEPERTINYA KITA TIDAK PAKAI INI
    * SALDO AWAL ITU ada pada tipe transaksi
    */
    //	@Column(name="SALDO")
    //	private boolean saldo=false;
    /*
	 * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon) 
	 */
    var amountRp : Int =0,
        var amountPpnRp : Int =0,

        @Ignore
    var amountRpAfterPpn : Int =0,

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
        var fuangMuka_SOBean : Int =0,
        var disc1 : Int =0,

//    @Ignore
//    var disc1Rp : Int =0,

//    @Ignore
//    var disc1PpnRp : Int =0,

//    @Ignore
//    var disc1RpAfterPpn : Int =0,

    //###TAMBAHAN
//    @Ignore
//    var amountAfterDisc1Rp : Int =0,

//    @Ignore
//    var amountAfterDisc1PpnRp : Int =0,

//    @Ignore
//    var amountAfterDisc1RpAfterPpn : Int =0,
        var disc2 : Int =0,

//    @Ignore
//    var disc2Rp : Int =0,

//    @Ignore
//    var disc2PpnRp : Int =0,

//    @Ignore
//    var disc2RpAfterPpn : Int =0,

    //AMOUNT AFTER DISC1 dan DISC2 dan DiscPlus
//    @Ignore
//    var amountAfterDisc2Rp : Int =0,

//    @Ignore
//    var amountAfterDisc2PpnRp : Int =0,

//    @Ignore
//    var amountAfterDisc2RpAfterPpn : Int =0,
        var discPlus_FG : Int =0,

//    @Ignore
//    var discPlusRp_FG : Int =0,

//    @Ignore
//    var discPlusPpnRp_FG : Int =0,

//    @Ignore
//    var discPlusRpAfterPpn_FG : Int =0,

    ///Jika yes maka setiap FG yang harganya nol maka akan di hitung akumulasinya, lalu nilainya ditaruh di CashBack
        var isCalcCashBackFg : Boolean =false,

    /*
    * Sama dengan bawah: Jangan Lupa
    */
    //DPP
        var amountAfterDiscPlusRp_FG : Int =0,
        var ppnRp : Int =0,

    //DPP+PPN
        var amountAfterDiscPlusRpAfterPpn_FG : Int =0,

    //AMOUNT PAY
        var amountPayRp : Int =0,
        var isEndOfDay : Boolean =false,
        var isOpenLockInputPriceAndDiscount : Boolean =false,

    /*
    * REQUEST PLAFON
    */
    var statusRequestDiscount: EnumRequestStatus = EnumRequestStatus.OPEN,
        var statusRequestPlafon: EnumRequestStatus = EnumRequestStatus.OPEN,
        var notes : String ="",
        var tunaiKredit: EnumTunaiKredit = EnumTunaiKredit.T,

    /* TIPE FAKTUR
    * F=FAKTUR PENJUALAN STANDART, R= RETURN PENJULAN, FI=PENJUALAN INTERN, 
    * FDN= DEBIT NOTE MANUAL, RCN=RETUR CREDIT NOTE MANUAL
    * */
    var tipeFaktur: EnumTipeFakturJual = EnumTipeFakturJual.F,

    /* TIPE JUAL
    * SHOP=SHOPSALE, TO=TAKING ORDER, C=CANVAS, TF=TASK FORCE, D=DENTED, BS=BAD STOCK
    * */
    var salesType: EnumSalesType = EnumSalesType.TO,
        var printCounter : Int =0,

    //ATURAN: update stok dan sumber apakah manual atau tidak
    //	@Column(name="SOURCE", length=3)
    //	private String source ="";	
        var isProses : Boolean =false,
        var isUsedSO : Boolean =false,

    //1.Cash 2.Debit 3.Kartu Kredit 4.Cek 5.E-Wallet 6.Lain-lain
        var tipeBayarPos : Int =0,
        var amountKasirBayar : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
        var fdivisionBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable=false)
    //	private FSalesman fsalesmanBean;
        var fsalesmanBean : Int =0,

    /*
    *	fcustomerBean = Bill To adalah yang melakuan Order Pertama kali
    *	fcustomerShipToBean = adalah tempat pengiriman barang. jika null maka Shipto adalah BillTo 
    *	fcustomerPromoToBean = adalah melakukan Switch Pengalihan Promo
    */
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean", referencedColumnName="ID", nullable=false)
//    @Ignore
//    var fcustomerBeanTemp: FCustomer? = null,
//    var fcustomerBean : Int =0,
        var fcustomerBean : FCustomer = FCustomer(),

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerShipToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerShipToBean;
//        var fcustomerShipToBean : Int =0,
        var fcustomerShipToBean : FCustomer = FCustomer(),

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerPromoToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerPromoToBean;
//        var fcustomerPromoToBean : Int =0,
        var fcustomerPromoToBean : FCustomer = FCustomer(),

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
//        var fwarehouseBean : Int =0,
        var fwarehouseBean : FWarehouse = FWarehouse(),

    /*
    * Account Mapping
    */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountArKbBean", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountArKbBean;
        var accAccountArKbBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="accAccountFtSaleshCredit", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountFtSaleshCredit;
        var accAccountFtSaleshCredit : Int =0,

//    @Ignore
//    var mapFtSalesdTemp: Map<Long, FtSalesdItems> = HashMap()

    //PEGIRIMAN:
    //	@ManyToOne
    //	@JoinColumn(name="fexpedisiBean", referencedColumnName="ID", nullable=true)
    //	private FExpedisi fexpedisiBean;
        var fexpedisiBean : Int =0,

//    @Ignore
//    var isSelected : Boolean =false,
        var stared: Boolean? = false,
        var unread: Boolean? = false,
        var selected: Boolean? = false,

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
    var created : Date =Date(),
    var modified : Date =Date(),
    var modifiedBy : String ="" //User ID


): Model(), Parcelable, Serializable{
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)

}