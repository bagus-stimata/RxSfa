package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "ftpurchased_expenses")
@Entity(tableName = "FtPurchasedExpenses")
class FtPurchasedExpenses : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id: Long = 0
    private val noUrut = 0
    private val amount = 0.0
    private val notes = ""

    /*
	 * HPP Pasti Apply To Item untuk DES
	 * Jika ditaruh sebagai biaya maka pasti akan dihitungkan sebagai HPP
	 *  
	 */
    //	@Column(name="APPLY_TO_ITEM")
    //	private boolean applyToItem=false;
    /*
	 * Jika pay to other vendor=true, maka:
	 * - Akan menjadi Journal tersendiri:
	 * - Hutang vendor lain -> pada Biaya
	 * 
	 * Jika false
	 *  - akan ikut total pada nota tersebut
	 * 
	 */
    private val payToOtherVendor = false

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	private FtPurchaseh ftPurchasehBean;
    private val ftPurchasehBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //	private AccAccount accAccountBean;
    private val accAccountBean = 0
}