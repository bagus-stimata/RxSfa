package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.EntityMapper
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.domain.model.FMaterialGroup3
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*
import javax.inject.Inject

@Parcelize
@Entity(tableName = "FMaterialGroup3")
class FMaterialGroup3Entity (
    @PrimaryKey(autoGenerate=false)
    var id :Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID :Int =0,
    var kode1 :String ="",
    var kode2 :String ="",
    var description :String ="",
    var tempInt1 :Int =0,
    var tempInt2 :Int =0,
    var tempInt3 :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup2Bean", referencedColumnName="ID")
    //	private FMaterialGroup2 fmaterialGroup2Bean;
    var fmaterialGroup2Bean :Int =0,
    var isStatusActive :Boolean =false,
    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy :String ="" //User ID

): ModelEntity(), Parcelable, Serializable

class FMaterialGroup3EntityMapper @Inject constructor(): EntityMapper<FMaterialGroup3, FMaterialGroup3Entity> {
    override fun mapToDomain(entity: FMaterialGroup3Entity): FMaterialGroup3 = FMaterialGroup3(
            id = entity.id,
            sourceID = entity.sourceID,
            kode1 = entity.kode1,
            kode2 = entity.kode2,
            description = entity.description,
            tempInt1 = entity.tempInt1,
            tempInt2 = entity.tempInt2,
            tempInt3 = entity.tempInt3,

            fmaterialGroup2Bean = entity.fmaterialGroup2Bean,
            isStatusActive = entity.isStatusActive,
            created = entity.created,
            modified = entity.modified,
            modifiedBy = entity.modifiedBy
    )

    override fun mapToEntity(model: FMaterialGroup3): FMaterialGroup3Entity = FMaterialGroup3Entity(
            id = model.id,
            sourceID = model.sourceID,
            kode1 = model.kode1,
            kode2 = model.kode2,
            description = model.description,
            tempInt1 = model.tempInt1,
            tempInt2 = model.tempInt2,
            tempInt3 = model.tempInt3,

            fmaterialGroup2Bean = model.fmaterialGroup2Bean,
            isStatusActive = model.isStatusActive,
            created = model.created,
            modified = model.modified,
            modifiedBy = model.modifiedBy
    )

}
