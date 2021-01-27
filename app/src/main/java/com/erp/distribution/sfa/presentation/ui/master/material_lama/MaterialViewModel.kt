package com.erp.distribution.sfa.presentation.ui.master.material_lama

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import java.util.*

class MaterialViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFMaterialUseCase: GetFMaterialUseCase
) : ViewModel() {
    //    private FMaterialRepository repository;
    val allFMaterialEntityLive: LiveData<List<FMaterialEntity>>? = null
    private val listFMaterialEntity: List<FMaterialEntity> = ArrayList()
    protected var itemHeader: MutableLiveData<FMaterialEntity>? = null

    fun insert(fMaterialEntity: FMaterialEntity): FMaterialEntity {
//        repository.insert(fMaterial);
        return fMaterialEntity
    }

    fun update(fMaterialEntity: FMaterialEntity): FMaterialEntity {
//        repository.update(fMaterial);
        return fMaterialEntity
    }

    fun delete(fMaterialEntity: FMaterialEntity): FMaterialEntity {
//        repository.delete(fMaterial);
        return fMaterialEntity
    }

    fun deleteAllFMaterial() {
//        repository.deleteAllFMaterial();
    }

    val allFMaterialEntity: LiveData<List<FMaterialEntity>>
        get() = getFMaterialUseCase.getCacheAllFMaterial()

    fun getItemHeader(): LiveData<FMaterialEntity>? {
        return itemHeader
    }
}