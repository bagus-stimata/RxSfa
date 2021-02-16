package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity_security.Role
import kotlinx.parcelize.Parcelize

@Parcelize
data class FUserRoles (
    var id: Int,
    var roleID: String = Role.GUEST, //as default

    var fuserBean: FUser = FUser()
//        var fuserBeanInt: Int

): Parcelable