package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipeCallPlan
import com.erp.distribution.sfa.domain.model.FSalesCallPlanh
import kotlinx.parcelize.Parcelize
import java.util.*


@Entity(tableName = "fSalesCallPlanh")
@Parcelize
data class FSalesCallPlanhEntity (

        @PrimaryKey(autoGenerate = true)
        val id :Long = 0L,

        val kode1 :String = "",

        val description :String = "",

//        @Transient
//        val tempString1 :String = "",
    /**
     * Dikunjungi pada minggu ke berapa saja
     */
        /**
     * 0. Daily:
     * 1. Weekly:
     * 2. BiWeekly:
     * 3. Monthly:
     */
    val tipeCallPlan: EnumTipeCallPlan = EnumTipeCallPlan.WEEKLY,

        /**
     * 0. Daily:
     * 1. Weekly:
     * 2. BiWeekly: Genap, Ganjil
     * 3. Monthly:
     */
    val param1 :Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
//    val fdivisionBean: FDivision = FDivision(),
        val fdivisionBean: Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fsalesmanBean", referencedColumnName = "ID")
//    val fsalesmanBean: FSalesman = FSalesman(),
        val fsalesmanBean: Int = 0,

//    @OneToMany(mappedBy = "fsalesCallPlanhBean", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @Fetch(FetchMode.JOIN)
//    val fSalesCallPlandItemsSet: Set<FSalesCallPlandItems> = HashSet<FSalesCallPlandItems>(),

        val statusActive :Boolean = true,

        val created :Date = Date(),
        val modified :Date = Date(),
        val modifiedBy :String = "" //User ID

): Parcelable

internal fun FSalesCallPlanhEntity.toDomain(): FSalesCallPlanh {
    return FSalesCallPlanh(
            id = id,

            kode1 = kode1,
            description = description,
//            tipeCallPlan = tipeCallPlan,
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