package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.ModelEntity

@Entity(tableName = "Photo")
data class PhotoEntity(
    @PrimaryKey var id: Long,
    var title: String,
    val url: String,
    val thumbnailUrl: String?
) : ModelEntity() {
    fun setName(text: String) {
        title = text
    }
}