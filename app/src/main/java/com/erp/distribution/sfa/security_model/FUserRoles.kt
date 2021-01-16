package com.erp.distribution.sfa.security_model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "fUserRoles")
data class FUserRoles (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var roleID: String = Role.GUEST, //as default

//    @Ignore
//    var fuserBean: FUser? = null

    //    @JsonIgnore
    var fuserBeanInt: Int

)