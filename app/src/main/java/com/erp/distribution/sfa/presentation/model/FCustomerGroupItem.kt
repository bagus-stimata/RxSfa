package com.erp.distribution.sfa.presentation.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

class FCustomerGroupItem (
    var id : Int,

    var kode1 : String,
    var description: String,
    var isStatusActive : Boolean,

    //	private FDivision fdivisionBean;
    var fdivisionBean : FDivisionItem?,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean : Int?,
    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy : String //User ID

)