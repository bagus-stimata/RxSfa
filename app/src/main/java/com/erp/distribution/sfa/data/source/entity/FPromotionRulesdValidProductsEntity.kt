package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "fpromotion_rulesd_valid_products")
@Entity(tableName = "FPromotionRulesdValidProducts")
class FPromotionRulesdValidProductsEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var noUrut = 0

    //	@ManyToOne
    //	@JoinColumn(name="fpromotionRuleshBean", referencedColumnName="ID")
    //    private FPromotionRulesh fpromotionRuleshBean;
    var fpromotionRuleshBean = 0

    /*
    * Customer Classfication = null berarti semua/All
    */
    //	@ManyToOne
    //	@JoinColumn(name="validVendorBean", referencedColumnName="ID")
    //    private FVendor validVendorBean;
    var validVendorBean = 0
    var validVendorAccumulation = false

    //	@ManyToOne
    //	@JoinColumn(name="validMaterialSalesBrandBean", referencedColumnName="ID")
    //    private FMaterialSalesBrand validMaterialSalesBrandBean;
    var validMaterialSalesBrandBean = 0
    var validSalesBrandAccumulation = false

    //	@ManyToOne
    //	@JoinColumn(name="validMaterialGroup2Bean", referencedColumnName="ID")
    //    private FMaterialGroup2 validMaterialGroup2Bean;
    var validMaterialGroup2Bean = 0
    var validMaterialGroup2Accumulation = false

    //	@ManyToOne
    //	@JoinColumn(name="validMaterialGroup3Bean", referencedColumnName="ID")
    //    private FMaterialGroup3 validMaterialGroup3Bean;
    var validMaterialGroup3Bean = 0
    var validMaterialGroup3Accumulation = false

    //	@ManyToOne
    //	@JoinColumn(name="validMaterialBean", referencedColumnName="ID")
    //    private FMaterial validMaterialBean;
    var validMaterialBean = 0
}