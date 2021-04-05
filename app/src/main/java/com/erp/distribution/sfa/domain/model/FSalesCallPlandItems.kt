package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class FSalesCallPlandItems (

    var id :Long = 0L,

    var noUrut :Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fsalesCallPlanhBean", referencedColumnName = "ID")
    var fsalesCallPlanhBean: FSalesCallPlanh = FSalesCallPlanh(),
//    var fsalesCallPlanhBean: Long = 0L,

//    @ManyToOne
//    @JoinColumn(name = "fcustomerBean", referencedColumnName = "ID")
//    var fcustomerBean: FCustomer = FCustomer(),
    var fcustomerBean: Int = 0,

    /**
     * 0. Daily: value -> Minggu, Senin, Selasa...
     * 1. Weekly: value -> Minggu, Senin, Selasa...
     * 2. BiWeekly: Genap, Ganjil
     * 3. Monthly: Tanggal berapa saja
     */
    var value1 :Boolean =false,
    var value2 :Boolean =false,
    var value3 :Boolean =false,
    var value4 :Boolean =false,
    var value5 :Boolean =false,
    var value6 :Boolean =false,
    var value7 :Boolean =false,

    @Transient
    var tempInt1 :Int = 0,

    @Transient
    var tempString1 :String = ""

    //	@Column(name="CREATED")
    //	@Temporal(TemporalType.TIMESTAMP)
    //	private Date created = new Date();
    //	@Column(name="MODIFIED")
    //	@Temporal(TemporalType.TIMESTAMP)
    //	private Date modified = new Date();
    //	@Column(name="MODIFIED_BY", length=20)
    //	private String modifiedBy =""; //User ID

):Parcelable, Serializable

internal fun FSalesCallPlandItems.toEntity(): FSalesCallPlandItemsEntity {
    return FSalesCallPlandItemsEntity(
        id = id,

        noUrut = noUrut,

        fsalesCallPlanhBean = fsalesCallPlanhBean.id,
        fcustomerBean = fcustomerBean,

        value1 = value1,
        value2 = value2,
        value3 = value3,
        value4 = value4,
        value5 = value5,
        value6 = value6,
        value7 = value7,

        tempInt1 = tempInt1,
        tempString1 = tempString1

    )
}