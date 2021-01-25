package com.erp.distribution.sfa.data.source.entity_security

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "fUser")
data class FUser(
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
//    var isLocked: Boolean = false,

    /**
     * Tambahan for DES Setting
     */
    var fdivisionBean: Int =0,
    var fwarehouseBean: Int =0,
    var fsalesmanBean: Int =0,

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

) : Serializable{
    
}