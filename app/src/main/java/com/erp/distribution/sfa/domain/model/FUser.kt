package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*
@Parcelize
data class FUser(
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

        var fuserRoles: List<FUserRoles>? = null,
        var tempRoles: List<String>? = null,

        var isLocked: Boolean = false,

        /**
     * Tambahan for DES Setting
     */
//    var fdivisionBean: Int =0,
//    var fwarehouseBean: Int =0,
//    var fsalesmanBean: Int =0,
        var fdivisionBean: FDivision? = FDivision(),
        var fsalesmanBean: FSalesman? = FSalesman(),
        var fwarehouseBean: FWarehouse? = FWarehouse(),

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

internal fun FUser.toEntity(): FUserEntity {
        return FUserEntity(
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
                fdivisionBean = fdivisionBean!!.id,
                fwarehouseBean = fwarehouseBean!!.id,
                fsalesmanBean = fsalesmanBean!!.id,

                created = created,
                lastModified = lastModified,
                modifiedBy = modifiedBy
        )
}


