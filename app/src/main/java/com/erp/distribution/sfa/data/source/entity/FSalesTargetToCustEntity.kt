package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FSalesTargetToCust
import kotlinx.parcelize.Parcelize
import java.util.*


@Entity(tableName = "fSalesTargetToCust")
@Parcelize
data class FSalesTargetToCustEntity (

        @PrimaryKey(autoGenerate = true)
        var id :Long = 0L,

    /**
     * Target and Target Plan
     */
    var targetSalesAmount :Double = 0.0,

//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
//    var fdivisionBean: FDivision = FDivision(),
    var fdivisionBean: Int = 0,

    var fsalesmanBean: Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fcustomerBean", referencedColumnName = "ID")
//    var fcustomerBean: FCustomer = FCustomer(),
    var fcustomerBean: Int = 0,

    var statusActive :Boolean = true,

    //	 
    //	@OneToMany(mappedBy="fareaBean", fetch=FetchType.LAZY)
    //	@Fetch(FetchMode.JOIN)
    //	private Set<FSubArea> fsubareaSet;
    //	 
    //	@OneToMany(mappedBy="validAreaBean", fetch=FetchType.LAZY)
    //	@Fetch(FetchMode.JOIN)
    //	private Set<FPromotionRulesdValidCusts> fpromotionRulesdValidCustsSet;
    var created :Date = Date(),
    var modified :Date = Date(),
    var modifiedBy  :String= "" //User ID

): Parcelable

internal fun FSalesTargetToCustEntity.toDomain(): FSalesTargetToCust {
    return FSalesTargetToCust(
            id = id,
            targetSalesAmount = targetSalesAmount,

            fdivisionBean = fdivisionBean,
            fsalesmanBean = fsalesmanBean,
            fcustomerBean = fcustomerBean,

            statusActive = statusActive,

//        fsubareaSet = fsubareaSet,

            created = created,
            modified = modified,
            modifiedBy = modifiedBy
    )
}