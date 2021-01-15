package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumRequestStatus
import com.erp.distribution.sfa.model.modelenum.EnumStatusUpload
import com.erp.distribution.sfa.model.modelenum.EnumTipeFakturBeli
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit
import java.io.Serializable
import java.util.*

//@Entity(tableName = "ftpurchaseh")
@Entity(tableName = "FtPurchaseh")
class FtPurchaseh : Serializable {
    @PrimaryKey(autoGenerate = true)
    var refno: Long = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0

    /*
    * TRANSAKSI BISA DIMULAI DARI PURCHASE INVOICE ATAU PO
    */
    var nopo = ""
    var invoiceno = ""
    var poDate = Date()
    var lamaCredit = 0
    var requestStatus = EnumRequestStatus.OPEN

    /*
    * TANGGAL BARANG DITERIMA: yang akan menjadi STOK dan HPP(Accounting)
    */
    var goodReceiptNo = ""
    var goodReceiptDate = Date()

    /*
    * Tanggal Invoice yang akan menjadi 
    * 1. Account Payable
    * 2. dan Menjadi Tanggal pada Faktur Pajak
    */
    var invoiceDate = Date()
    var dueDate = Date()

    /*
    * Posting Date atau Journal Date dipakai untuk alternatif tanggal posting
    * Sesuai dengan referensi SAP
    * 
    */
    var postingDate = Date()
    var taxNumber = ""

    /*
    * Tax Date adalah tanggal Faktur Pajak Masuk Jurnal
    */
    var taxDate = Date()

    /*
    * Defaultnya adalah mengikuti nomor invoice
    * Tapi bisa diubah-ubah sesuai bulan upload faktur pajak
    */
    var masaPajak = 0
    var statusUpload = EnumStatusUpload.OPEN

    //	@Column(name = "RETUR_ATAS_FAKTUR", length = 30)
    //	private String returAtasFaktur ="";
    /*
	 * DIBAYAR TUNAI ATAU SESUAI DENGAN AKUNNYA
	 * PADA LAPORAN HUTANG: Akan dianggal Sudah Lunas dan dilewati
	 */
    var isRtrDibayarTunai = false

    /*
    * FAKTUR FROM PO
    */
    //	@ManyToOne
    //	@JoinColumn(name="fakturPOBean", referencedColumnName="refno", nullable = true)
    //	private  FtPurchaseh fakturPOBean; //me as good receipt
    var fakturPOBean = 0

    /*
    * FAKTUR FROM GR
    */
    //	@ManyToOne
    //	@JoinColumn(name="fakturGRBean", referencedColumnName="refno", nullable = true)
    //	private  FtPurchaseh fakturGRBean; //me as good receipt
    var fakturGRBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable = true)
    //	private  FtPurchaseh returAtasFakturBean; //me as Retur
    var returAtasFakturBean = 0

    //RETUR ATAS FAKTUR	
    var tunaiKredit = EnumTunaiKredit.T

    /* TIPE FAKTUR
    * F=FAKTUR PEMBELIAN STANDART, R= RETURN PEMBELIAN DARI PABRIK, 
    * FI=PEMBELIAN INTERN, INFG=BARANG DATANG FREE GOOD
    * PO=PEMBELIAN to Factory
    * */
    var tipeFaktur: EnumTipeFakturBeli? = null

    /*
    * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon) 
    */
    var amountRp = 0.0
    var amountPpnRp = 0.0

    @Ignore
    var amountRpAfterPpn = 0.0

    /*
    * berhubungan dengan Account -> menjadi apa
    * 	Uang Muka Pembelian pada
    * 		Kas (kas keluar)
    * Dengan Giro/Transfer, dan jika kosong maka Artinya Cash
    * Asumsi: Uang muka adalah sekali
    */
    var uangMukaRp = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    var fgiroBean = 0

    /*
    * ATAS TIDAK DIPAKAI LAGI
    * Untuk PO saja
    */
    //	@ManyToOne
    //	@JoinColumn(name="fuangMuka_POBean", referencedColumnName="ID", nullable=true)
    //	private FUangMuka fuangMuka_POBean;
    var fuangMuka_POBean = 0
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

    /*
    * Sama dengan bawah: Jangan Lupa
    */
    //DPP: Pindah Bawah
    //	@Column(name="AMOUNT_AFTER_DISC_PLUS")
    //	private Double amountAfterDiscPlusRp_FG=0.0;
    /*
	 * TIDAK ADA YANG PAKAI SEMENTARA KITA NON AKTIFKAN
	 */
    @Ignore
    var amountAfterDiscPlusFgRp = 0.0

    @Ignore
    var amountAfterDiscPlusFgPpnRp = 0.0

    @Ignore
    var amountAfterDiscPlusFgRpAfterPpn = 0.0

    //======== TAMBAHAN ===========
    var discExclCogs = 0.0

    @Ignore
    var discExclCogsRp = 0.0

    @Ignore
    var discExclCogsPpnRp = 0.0

    @Ignore
    var discExclCogsRpAfterPpn = 0.0

    /*
    * PINDAHAN DARI ATAS: DAN TERPAKASA NAMANYA SEPERTI INI
    * Sama dengan bawah: Jangan Lupa
    */
    //DPP
    var amountAfterDiscPlusRp_FG = 0.0

    //	@Column(name="PPN_PERCENT")
    //	private Double ppnPercent=0.0;
    //	@Ignore
    var ppnRp = 0.0

    //DPP+PPN
    var amountAfterDiscPlusRpAfterPpn_FG = 0.0 //Sama dengan amountAfterDisc spt dibawah

    //	//AMOUNT AFTER DISC
    //	@Column(name="AMOUNT_AFTERDISC_ALL")
    //	private Double amountAfterDiscAll=0.0;
    //	@Column(name="AMOUNT_AFTERDISC_ALL_AFTERPPN")
    //	private Double amountAfterDiscAllAfterPpn=0.0;
    var saldo = false

    //AMOUNT PAY
    var amountPayRp = 0.0
    var isEndOfDay = false
    var isUsedGrInv = false
    var isUsedSO = false
    var printCounter = 0
    var isLunas = false

    //ATURAN: update stok dan sumber apakah manual atau tidak
    var source = ""
    var isProses = false
    var notes = ""
    var shipTo = ""
    var billTo = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID", nullable=false)
    //	private FVendor fvendorBean;
    var fvendorBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
    var fwarehouseBean = 0

    /*
    * Account Mapping
    */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountApKbBean", referencedColumnName="ID", nullable = false)
    //	private AccAccount accAccountApKbBean;
    var accAccountApKbBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountApKbBean_SecondStep", referencedColumnName="ID", nullable = false)
    //	private AccAccount accAccountApKbBean_SecondStep;
    var accAccountApKbBean_SecondStep = 0

    //Akun Potongan Khusus
    //	@ManyToOne
    //	@JoinColumn(name="accAccNonCogsFgBean", referencedColumnName="ID", nullable = true)
    //	private AccAccount accAccNonCogsFgBean;
    var accAccNonCogsFgBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccNonCogsDiscBean", referencedColumnName="ID", nullable = true)
    //	private AccAccount accAcNonCogsDiscBean;
    var accAccNonCogsDiscBean = 0
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}