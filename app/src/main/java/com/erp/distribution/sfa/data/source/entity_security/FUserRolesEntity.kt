package com.erp.distribution.sfa.data.source.entity_security

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fUserRoles")
data class FUserRolesEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var roleID: String = Role.GUEST, //as default

//    @Ignore
//    var fuserBean: FUser? = null

    //    @JsonIgnore
    var fuserBeanInt: Int

)