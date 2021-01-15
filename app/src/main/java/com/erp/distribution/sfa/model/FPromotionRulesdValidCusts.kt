package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "fpromotion_rulesd_valid_custs")
@Entity(tableName = "FPromotionRulesdValidCusts")
class FPromotionRulesdValidCusts : Serializable {
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
    //	@JoinColumn(name="validCustomerGroupBean", referencedColumnName="ID")
    //    private FCustomerGroup validCustomerGroupBean;
    var validCustomerGroupBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="validDistributionChannelBean", referencedColumnName="ID")
    //    private FDistributionChannel validDistributionChannelBean;
    var validDistributionChannelBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="validAreaBean", referencedColumnName="ID")
    //    private FArea validAreaBean;
    var validAreaBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="validCustomerBean", referencedColumnName="ID")
    //    private FCustomer validCustomerBean;
    var validCustomerBean = 0
}