package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipeCallPlan
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class FSalesCallPlanh (

        var id :Long = 0L,

        var kode1 :String = "",

        var description :String = "",

//        @Transient
//        var tempString1 :String = "",
    /**
     * Dikunjungi pada minggu ke berapa saja
     */
    /**
     * 0. Daily:
     * 1. Weekly:
     * 2. BiWeekly:
     * 3. Monthly:
     */
    var tipeCallPlan: EnumTipeCallPlan = EnumTipeCallPlan.WEEKLY,

    /**
     * 0. Daily:
     * 1. Weekly:
     * 2. BiWeekly: Genap, Ganjil
     * 3. Monthly:
     */
    var param1 :Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
//    var fdivisionBean: FDivision = FDivision(),
    var fdivisionBean: Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fsalesmanBean", referencedColumnName = "ID")
//    var fsalesmanBean: FSalesman = FSalesman(),
    var fsalesmanBean: Int = 0,

//    @OneToMany(mappedBy = "fsalesCallPlanhBean", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @Fetch(FetchMode.JOIN)
//    var fSalesCallPlandItemsSet: Set<FSalesCallPlandItems> = HashSet<FSalesCallPlandItems>(),

    var statusActive :Boolean = true,

    var created :Date = Date(),
    var modified :Date = Date(),
    var modifiedBy :String = "" //User ID

): Parcelable

internal fun FSalesCallPlanh.toEntity(): FSalesCallPlanhEntity {
    return FSalesCallPlanhEntity(
        id = id,

        kode1 = kode1,
        description = description,
//        tipeCallPlan = tipeCallPlan,
        param1 = param1,

        fdivisionBean = fdivisionBean,
        fsalesmanBean = fsalesmanBean,

        statusActive = statusActive,

//        fsubareaSet = fsubareaSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}