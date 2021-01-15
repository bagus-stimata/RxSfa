package com.erp.distribution.sfa.security_model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "fUser")
class FUser : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var email: String? = ""
    var username = ""

    //Encripted Password
    var password = ""

    //	@JsonIgnore
    var plainPassword = ""
    var passwordConfirm = ""
    var fullName = ""
    var phone = ""
    var notes = ""

    @Ignore
    var fuserRoles: List<FUserRoles>? = null

    @Ignore
    var tempRoles: List<String>? = null

    // @NotBlank
    // @Size(max = 255)
    // private String role;
    var isLocked = false

    /**
     * Tambahan for DES Setting
     */
    var fdivisionBean = 0
    var fwarehouseBean = 0

    @Ignore
    var tempInt1 = 0

    @Ignore
    var isTempBol1 = false

    @Ignore
    var isTempBol2 = false
    var created = Date()
    var lastModified = Date()
    var modifiedBy = ""
    private fun prepareData() {
        email = if (email == null) null else email!!.toLowerCase()
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + id
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as FUser
        return if (id != other.id) false else true
    }

    override fun toString(): String {
        return "FUser [id=$id]"
    }
}