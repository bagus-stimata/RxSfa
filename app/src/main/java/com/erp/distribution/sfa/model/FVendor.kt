package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumCurrency
import java.util.*

//@Entity(tableName = "fvendor")
@Entity(tableName = "FVendor")
class FVendor {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var vcode = ""
    var vname = ""
    var address1 = ""
    var address2 = ""
    var city1 = ""
    var state1 = ""
    var phone = ""
    var email = ""
    var joinDate = Date()
    var lastTrans = Date()
    var noRekening = ""
    var currency: EnumCurrency? = null

    /*
    * Diskon Margin Barang: disc2 & Disc2Plus
    */
    var disc2Margin = 0.0
    var disc1PlusMargin = 0.0

    /*
    * PERPAJAKAN
    */
    var isPkp = true
    var namaPrshFakturPajak = ""
    var namaPengusahaKenaPajak = ""
    var npwp = ""
    var tanggalPengukuhanPkp = Date()
    var isStatusActive = true
    var top = 0

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    var wsport: String? = null
    var isDisc1RegManual = false
    var isDiscPlusRegManual = false

    /*
    * Tidak Wajib Disi: karena untuk aturan yang bersifat opsional
    * - Jika Salesman Exclusive maka hanya boleh menjual barang dari Satu vendor tertentu atau Beberapa Vendor
    */
    //    @ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable= true)
    //	private FSalesman fsalesmanBean;
    var fsalesmanBean = 0
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID

    //TAMBAHAN
    @Ignore
    var tempString1 = ""

    @Ignore
    var tempString2 = ""

    @Ignore
    var tempInt1 = 0

    @Ignore
    var tempInt2 = 0

    @Ignore
    var tempDouble1 = 0.0

    @Ignore
    var tempDouble2 = 0.0

    @Ignore
    var tempDouble31 = 0.0

    @Ignore
    var tempDouble32 = 0.0
}