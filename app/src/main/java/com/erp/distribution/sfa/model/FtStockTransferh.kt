package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumTipeStockTransfer
import java.util.*

//@Entity(tableName = "ftstock_transferh")
@Entity(tableName = "FtStockTransferh")
class FtStockTransferh {
    @PrimaryKey(autoGenerate = true)
    var refno: Long = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0
    var noRek = ""
    var trDate = Date()

    /*
    * PENGGUNAANYA MENGGUNAKAN PARAMETER SISTEM
    * JIKA TIPE 0 = MAKA DEFAULT DAN TIDAK MENGGUNAKAN JURNAL DAN TIDAK MODE DITERIMA ATAU TIDAK
    * JIKA TIPE 1= Maka terdapat jurnal
    * 		a. Saat Kirim (yaitu saat nomor gudang diterbitkan)
    * 		b. Saat Diterima 
    * 		User penerima hanya bisa memberi tanda diterima dan memasukkan Qty Kembali (Kekurangan jika barang tidak ada atau rusak)
    */
    //	@Column(name="RECEIVED")
    //	private boolean received =true; //Default adalah tidak memakai jurnal sehingga true
    var isReceiptByDest = false
    var goodReceiptDate = Date()
    var tipeStockTransfer: EnumTipeStockTransfer? = null
    var notes = ""

    /*
    * Jumlah keseluruhan
    */
    var amountRp = 0.0
    var isEndOfDay = false
    var printCounter = 0

    @Ignore
    var tempInt = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBeanFrom", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBeanFrom;
    var fwarehouseBeanFrom = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBeanTo", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBeanTo;
    var fwarehouseBeanTo = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchaseh_ReqBean", referencedColumnName="refno", nullable=true)
    //	private FtPurchaseh ftPurchaseh_ReqBean;
    var ftPurchaseh_ReqBean = 0
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}