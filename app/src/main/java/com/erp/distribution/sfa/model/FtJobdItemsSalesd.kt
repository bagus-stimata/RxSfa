package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "ftjobd_items_salesd")
@Entity(tableName = "FtJobdItemsSalesd")
class FtJobdItemsSalesd : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id: Long = 0
    private val noUrut = 0
    private val qty = 0.0
    private val priority = 0

    //Untuk Job Schedule
    //	@JoinColumn(name="ftJobItemsBean", referencedColumnName="ID", nullable = false)
    private val ftJobItemsBean: FtJobdItems? = null

    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdBean", referencedColumnName="ID", nullable = false)
    private val ftSalesdBean: FtSalesdItems? = null

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID", nullable = false)
    private val fmaterialBean: FMaterial? = null
}