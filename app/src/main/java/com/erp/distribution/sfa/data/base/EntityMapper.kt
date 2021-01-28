package com.erp.distribution.sfa.data.base

import com.erp.distribution.sfa.domain.base.Model


interface EntityMapper<M : Model, ME : ModelEntity> {
    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME
}