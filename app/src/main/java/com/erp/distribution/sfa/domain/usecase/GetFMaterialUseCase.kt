package com.erp.distribution.sfa.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FMaterialRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.FStockEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FWarehouse
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.repository.FStockRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFMaterialUseCase @Inject constructor(
        private val repository: FMaterialRepository,
        private val fStockRepository: FStockRepository
        ) : SingleUseCase<List<FMaterialEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FMaterialEntity>> {
        return repository.getRemoteAllFMaterial("authHeader")
    }
    fun getRemoteAllFMaterial(authHeader: String): Single<List<FMaterialEntity>>{
        return repository.getRemoteAllFMaterial(authHeader)
    }

    fun getRemoteFMaterialById(authHeader: String, id: Int): Single<FMaterialEntity>{
        return repository.getRemoteFMaterialById(authHeader, id)
    }
    fun getRemoteAllFMaterialByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialEntity>>{
        return repository.getRemoteAllFMaterialByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterial>>{
        return repository.getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader, divisionId, companyId).map {
            it.map {
                Log.d("##### MatUseCase", "#result >>  ${it.fmaterialGroup3Bean} | ${it.fmaterialSalesBrandBean} " )
                it.toDomain()
            }
        }
    }
    fun getRemoteAllFMaterialEntityByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterialEntity>>{
        return repository.getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    fun createRemoteFMaterial(authHeader: String, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity>{
        return repository.createRemoteFMaterial(authHeader, fMaterialEntity)
    }
    fun putRemoteFMaterial(authHeader: String, id: Int, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity>{
        return repository.putRemoteFMaterial(authHeader, id, fMaterialEntity)
    }
    fun deleteRemoteFMaterial(authHeader: String, id: Int): Single<FMaterialEntity>{
        return repository.deleteRemoteFMaterial(authHeader, id)
    }


    fun getRemoteAllFStockByWarehouseOnly(authHeader: String, fwarehouseBean: Int, dateFrom: Date, dateTo: Date): Single<List<FStockEntity>>{
        return fStockRepository.getRemoteAllFStockByWarehouse(authHeader, fwarehouseBean, dateFrom, dateTo)
    }
    fun getRemoteAllFStockByWarehouseOnly(authHeader: String, fwarehouseBean: Int): Single<List<FStockEntity>>{
        return fStockRepository.getRemoteAllFStockByWarehouseOnly(authHeader, fwarehouseBean)
    }



    fun getCacheAllFMaterial(): LiveData<List<FMaterial>>{
        return repository.getCacheAllFMaterial().map {
            it.map { it.toDomain() }
        }
    }
    fun getCacheAllFMaterialFlow(query: String, sortOrder: SortOrder,  limit: Int, currentOffset: Int, hideSelected: Boolean): Flow<List<FMaterial>> {
        return repository.getCacheAllFMaterialFlow(query, sortOrder, limit, currentOffset, hideSelected).map {
            it.map {
                val fmaterialBean = it.fMaterialEntity.toDomain()
                val division = it.fDivisionEntity.toDomain()
                fmaterialBean.fdivisionBean = division
                it.fMaterialGroup3Entity?.let {
                    fmaterialBean.fmaterialGroup3Bean = it.toDomain()
                }
                it.fVendorEntity?.let {
                    fmaterialBean.fvendorBean = it.toDomain()
                }
                it.fStockEntity?.let {
                    fmaterialBean.saldoStock = it.saldoAkhir
                }
                fmaterialBean
            }
        }
    }
    fun getCacheFMaterialById(id: Int): LiveData<FMaterial>{
        return repository.getCacheFMaterialById(id).map { it.toDomain() }
    }
    fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterial>>{
        return repository.getCacheAllFMaterialByDivision(divisionId).map {
            it.map { it.toDomain() }
        }
    }
    fun addCacheFMaterial(fMaterial: FMaterial){
        repository.addCacheFMaterial(fMaterial.toEntity())
    }
    fun addCacheListFMaterial(list: List<FMaterial>){
        repository.addCacheListFMaterial(list.map { it.toEntity() })
    }
    fun addCacheListFMaterialEntity(list: List<FMaterialEntity>){
        repository.addCacheListFMaterial(list.map { it })
    }

    fun putCacheFMaterial(fMaterial: FMaterial){
        repository.putCacheFMaterial(fMaterial.toEntity())
    }
    fun deleteCacheFMaterial(fMaterial: FMaterial){
        repository.deleteCacheFMaterial(fMaterial.toEntity())
    }
    fun deleteAllCacheFMaterial(){
        repository.deleteAllCacheFMaterial()
    }
}