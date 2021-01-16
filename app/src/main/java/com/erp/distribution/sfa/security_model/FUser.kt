package com.erp.distribution.sfa.security_model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "fUser")
data class FUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var email: String,
    var username: String,

    //Encripted Password
    var password: String,

    //	@JsonIgnore
    var plainPassword: String,
    var passwordConfirm: String,
    var fullName: String,
    var phone: String,
    var notes: String,

//    @Ignore
//    var fuserRoles: List<FUserRoles>? = null,
//
//    @Ignore
//    var tempRoles: List<String>? = null,

    // @NotBlank
    // @Size(max = 255)
    // private String role;
//    var isLocked: Boolean = false,

    /**
     * Tambahan for DES Setting
     */
    var fdivisionBean: Int,
    var fwarehouseBean: Int,

//    @Ignore
//    var tempInt1: Int?,

//    @Ignore
//    var isTempBol1: Boolean = false,
//
//    @Ignore
//    var isTempBol2: Boolean = false,

    var created: Date = Date(),
    var lastModified: Date = Date(),
    var modifiedBy: String

)