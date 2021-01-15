package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.*
import java.io.Serializable
import java.util.*

//@Entity(tableName = "ftsalesh")
@Entity(tableName = "FtSalesh")
class FtSalesh : Serializable {
    @PrimaryKey(autoGenerate = true)
    var refno: Long = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0

    //ORDERNO=SO
    var orderno = ""
    var isValidOrder = false

    //INVOICENO
    var invoiceno = ""
    var priority = 0

    /*
    * ignore/reject promotion rules setting
    */
    var isNoPromotionRules = false
    var taxNumber = ""
    var taxDate = Date()

    /*
    * SO: FROM GOOD RECEIVE
    */
    //	@ManyToOne
    //	@JoinColumn(name="fromGoodReceiptBean", referencedColumnName="refno", nullable=true)
    //	private  FtPurchaseh fromGoodReceiptBean;
    var fromGoodReceiptBean = 0

    /*
    * FAKTUR FROM SO
    */
    //	@ManyToOne
    //	@JoinColumn(name="fakturSOBean", referencedColumnName="refno")
    //	private  FtSalesh fakturSOBean; //me as Sales Invoice
    var fakturSOBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable=true)
    //	private  FtSalesh returAtasFakturBean;
    var returAtasFakturBean = 0

    /*
    * Status Nota: (1)O-Open Sedang dikirim, (2)T-Terkirim, 
    * 		(3)P-Pending Pengiriman, (4)B-Batal Nota Batal Seluruhnya
    */
    var statusPengiriman: EnumStatusPengiriman = EnumStatusPengiriman.NOTA_OPEN

    //SURAT JALAN PENGIRIMAIN = DO
    var sjPengirimanNo = ""
    var sjPengirimanDate = Date() //Jika tidak ada nomor SJ maka tidak berlaku

    //Driver
    //	@Column(name="DRIVER_NAME", length=40)
    //	private String driverName="";
    //	@ManyToOne
    //	@JoinColumn(name="driverBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman driverBean;
    var driverBean = 0
    var nopol = ""

    /*
    * HARUS DIGANTI MENGGUNAKAN LIST
    */
    //SJ Pengiriman, Delivery, SJ Penagihan
    //	@Column(name="SJ_AND_DELV_HISTORY", length=250)
    //	private String sjAndDelvHistory="";		
    var sjPenagihanNo = ""
    var sjPenagihanDate = Date()

    //	@ManyToOne
    //	@JoinColumn(name="collectorBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman collectorBean;
    var collectorBean = 0
    var invoiceDate = Date()
    var orderDate = Date()
    var top = 0
    var dueDate = Date()

    /*
    * SEPERTINYA KITA TIDAK PAKAI INI
    * SALDO AWAL ITU ada pada tipe transaksi
    */
    //	@Column(name="SALDO")
    //	private boolean saldo=false;
    /*
	 * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon) 
	 */
    var amountRp = 0.0
    var amountPpnRp = 0.0

    @Ignore
    var amountRpAfterPpn = 0.0

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
    var fuangMuka_SOBean = 0
    var disc1 = 0.0

    @Ignore
    var disc1Rp = 0.0

    @Ignore
    var disc1PpnRp = 0.0

    @Ignore
    var disc1RpAfterPpn = 0.0

    //###TAMBAHAN
    @Ignore
    var amountAfterDisc1Rp = 0.0

    @Ignore
    var amountAfterDisc1PpnRp = 0.0

    @Ignore
    var amountAfterDisc1RpAfterPpn = 0.0
    var disc2 = 0.0

    @Ignore
    var disc2Rp = 0.0

    @Ignore
    var disc2PpnRp = 0.0

    @Ignore
    var disc2RpAfterPpn = 0.0

    //AMOUNT AFTER DISC1 dan DISC2 dan DiscPlus
    @Ignore
    var amountAfterDisc2Rp = 0.0

    @Ignore
    var amountAfterDisc2PpnRp = 0.0

    @Ignore
    var amountAfterDisc2RpAfterPpn = 0.0
    var discPlus_FG = 0.0

    @Ignore
    var discPlusRp_FG = 0.0

    @Ignore
    var discPlusPpnRp_FG = 0.0

    @Ignore
    var discPlusRpAfterPpn_FG = 0.0

    ///Jika yes maka setiap FG yang harganya nol maka akan di hitung akumulasinya, lalu nilainya ditaruh di CashBack
    var isCalcCashBackFg = false

    /*
    * Sama dengan bawah: Jangan Lupa
    */
    //DPP
    var amountAfterDiscPlusRp_FG = 0.0
    var ppnRp = 0.0

    //DPP+PPN
    var amountAfterDiscPlusRpAfterPpn_FG = 0.0

    //AMOUNT PAY
    var amountPayRp = 0.0
    var isEndOfDay = false
    var isOpenLockInputPriceAndDiscount = false

    /*
    * REQUEST PLAFON
    */
    var statusRequestDiscount: EnumRequestStatus = EnumRequestStatus.OPEN
    var statusRequestPlafon: EnumRequestStatus = EnumRequestStatus.OPEN
    var notes = ""
    var tunaiKredit: EnumTunaiKredit = EnumTunaiKredit.T

    /* TIPE FAKTUR
    * F=FAKTUR PENJUALAN STANDART, R= RETURN PENJULAN, FI=PENJUALAN INTERN, 
    * FDN= DEBIT NOTE MANUAL, RCN=RETUR CREDIT NOTE MANUAL
    * */
    var tipeFaktur: EnumTipeFakturJual? = null

    /* TIPE JUAL
    * SHOP=SHOPSALE, TO=TAKING ORDER, C=CANVAS, TF=TASK FORCE, D=DENTED, BS=BAD STOCK
    * */
    var salesType: EnumSalesType? = null
    var printCounter = 0

    //ATURAN: update stok dan sumber apakah manual atau tidak
    //	@Column(name="SOURCE", length=3)
    //	private String source ="";	
    var isProses = false
    var isUsedSO = false

    //1.Cash 2.Debit 3.Kartu Kredit 4.Cek 5.E-Wallet 6.Lain-lain
    var tipeBayarPos = 0
    var amountKasirBayar = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable=false)
    //	private FSalesman fsalesmanBean;
    var fsalesmanBean = 0

    /*
    *	fcustomerBean = Bill To adalah yang melakuan Order Pertama kali
    *	fcustomerShipToBean = adalah tempat pengiriman barang. jika null maka Shipto adalah BillTo 
    *	fcustomerPromoToBean = adalah melakukan Switch Pengalihan Promo
    */
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean", referencedColumnName="ID", nullable=false)
    @Ignore
    var fcustomerBeanTemp: FCustomer? = null
    var fcustomerBean = 0

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerShipToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerShipToBean;
    var fcustomerShipToBean = 0

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerPromoToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerPromoToBean;
    var fcustomerPromoToBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
    var fwarehouseBean = 0

    /*
    * Account Mapping
    */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountArKbBean", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountArKbBean;
    var accAccountArKbBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountFtSaleshCredit", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountFtSaleshCredit;
    var accAccountFtSaleshCredit = 0

    @Ignore
    var mapFtSalesdTemp: Map<Long, FtSalesdItems> = HashMap()

    //PEGIRIMAN:
    //	@ManyToOne
    //	@JoinColumn(name="fexpedisiBean", referencedColumnName="ID", nullable=true)
    //	private FExpedisi fexpedisiBean;
    var fexpedisiBean = 0

    @Ignore
    var isSelected = false

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
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID

    companion object {
        /**
         *
         */
        const val serialVersionUID = 1L
    }
}