package com.erp.distribution.sfa.security_model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "fUserRoles")
class FUserRoles {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var roleID: String? = Role.GUEST //as default

    @Ignore
    var fuserBean: FUser? = null

    //    @JsonIgnore
    var fuserBeanInt: Int? = null
    override fun toString(): String {
        return "Todo [description=$roleID]"
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = super.hashCode()
        result = prime * result + if (fuserBean == null) 0 else fuserBean.hashCode()
        result = prime * result + if (roleID == null) 0 else roleID.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (!super.equals(obj)) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as FUserRoles
        if (fuserBean == null) {
            if (other.fuserBean != null) return false
        } else if (fuserBean != other.fuserBean) return false
        if (roleID == null) {
            if (other.roleID != null) return false
        } else if (roleID != other.roleID) return false
        return true
    }
}