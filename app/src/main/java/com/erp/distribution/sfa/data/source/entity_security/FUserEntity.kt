package com.erp.distribution.sfa.data.source.entity_security

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FDivisionEntity
import com.erp.distribution.sfa.data.source.entity.FSalesmanEntity
import com.erp.distribution.sfa.data.source.entity.FWarehouseEntity
import com.erp.distribution.sfa.domain.model.*
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*
@Parcelize
@Entity(tableName = "fUser")
data class FUserEntity(
    @PrimaryKey
    var id: Int = -1,
    var email: String = "",
    var username: String = "",

    //Encripted Password
    var password: String = "",

    //	@JsonIgnore
    var plainPassword: String = "",
    var passwordConfirm: String = "",
    var fullName: String = "",
    var phone: String = "",
    var notes: String = "",

//    @Ignore
//    var fuserRoles: List<FUserRoles>? = null,
//
//    @Ignore
//    var tempRoles: List<String>? = null,

    // @NotBlank
    // @Size(max = 255)
    // private String role;
    var isLocked: Boolean = false,

    /**
     * Tambahan for DES Setting
     */
    var fdivisionBean: Int? =0,
    var fwarehouseBean: Int? =0,
    var fsalesmanBean: Int? =0,

//    @Ignore
//    var tempInt1: Int?,

//    @Ignore
//    var isTempBol1: Boolean = false,
//
//    @Ignore
//    var isTempBol2: Boolean = false,

    var created: Date = Date(),
    var lastModified: Date = Date(),
    var modifiedBy: String = ""

) : Parcelable

internal fun FUserEntity.toDomain(): FUser {
    return FUser(
            id = id,
            email = email,
            username = username,
            password = password,
            plainPassword = plainPassword,
            passwordConfirm = passwordConfirm,
            fullName = fullName,
            phone = phone,
            notes = notes,

            isLocked = isLocked,
            fdivisionBean = fdivisionBean?.let { FDivision(fdivisionBean!!) },
            fwarehouseBean = fwarehouseBean?.let {  FWarehouse(fwarehouseBean!!)},
            fsalesmanBean = fsalesmanBean?.let { FSalesman(fsalesmanBean!!) },

            created = created,
            lastModified = lastModified,
            modifiedBy = modifiedBy
    )
}

data class FUserWithFDivisionAndSalesmanAndWarehouse(
        @Embedded val fUserEntity: FUserEntity,

        @Relation(
                parentColumn = "fdivisionBean",
                entityColumn = "id"
        )
        val fDivisionEntity: FDivisionEntity?, //I'am not prefer to Null

        @Relation(
                parentColumn = "fsalesmanBean",
                entityColumn = "id"
        )
        val fSalesmanEntity: FSalesmanEntity?, //I'am not prefer to Null

        @Relation(
                parentColumn = "fwarehouseBean",
                entityColumn = "id"
        )
        val fWarehouseEntity: FWarehouseEntity?, //I'am not prefer to Null
)

