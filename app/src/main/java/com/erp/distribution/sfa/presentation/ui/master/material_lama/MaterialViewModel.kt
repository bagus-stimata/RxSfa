package com.erp.distribution.sfa.presentation.ui.master.material_lama

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.domain.model.FMaterial
import java.util.*

class MaterialViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFMaterialUseCase: GetFMaterialUseCase
) : ViewModel() {
    //    private FMaterialRepository repository;
    val allFMaterialLive: LiveData<List<FMaterial>>? = null
    private val listFMaterial: List<FMaterial> = ArrayList()
    protected var itemHeader: MutableLiveData<FMaterial>? = null

    fun insert(fMaterialEntity: FMaterial): FMaterial {
//        repository.insert(fMaterial);
        return fMaterialEntity
    }

    fun update(fMaterialEntity: FMaterial): FMaterial {
//        repository.update(fMaterial);
        return fMaterialEntity
    }

    fun delete(fMaterialEntity: FMaterial): FMaterial {
//        repository.delete(fMaterial);
        return fMaterialEntity
    }

    fun deleteAllFMaterial() {
//        repository.deleteAllFMaterial();
    }

    val allFMaterial: LiveData<List<FMaterial>>
        get() = getFMaterialUseCase.getCacheAllFMaterial()

    fun getItemHeader(): LiveData<FMaterial>? {
        return itemHeader
    }
}