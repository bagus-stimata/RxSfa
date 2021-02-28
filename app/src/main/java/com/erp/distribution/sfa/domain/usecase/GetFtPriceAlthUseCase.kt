package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FtPriceAlthRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FtPriceAlth
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFtPriceAlthUseCase @Inject constructor(private val repository: FtPriceAlthRepository) : SingleUseCase<List<FtPriceAlthEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FtPriceAlthEntity>> {
        return repository.getRemoteAllFtPriceAlthByDivisionAndShareToCompany("aa", 9999, 9999)
    }
    fun getRemoteAllFtPriceAlthByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FtPriceAlthEntity>>{
        return repository.getRemoteAllFtPriceAlthByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }


    fun getCacheAllFtPriceAlth(): LiveData<List<FtPriceAlthEntity>>{
        return repository.getCacheAllFtPriceAlth()
    }
    fun getCacheAllFtPriceAlthDomainLive(): LiveData<List<FtPriceAlth>>{
        return repository.getCacheAllFtPriceAlth().map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFtPriceAlthByDivision(divisionId: Int): LiveData<List<FtPriceAlth>>{
        return repository.getCacheAllFtPriceAlthByDivision(divisionId).map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFtPriceAlthByDivisionDomainLIve(divisionId: Int): LiveData<List<FtPriceAlth>>{
        return repository.getCacheAllFtPriceAlthByDivision(divisionId).map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun addCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity){
        repository.addCacheFtPriceAlth(ftPriceAlthEntity)
    }
    fun addCacheListFtPriceAlth(list: List<FtPriceAlthEntity>){
        repository.addCacheListFtPriceAlth(list)
    }
    fun putCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity){
        repository.putCacheFtPriceAlth(ftPriceAlthEntity)
    }
    fun deleteCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity){
        repository.deleteCacheFtPriceAlth(ftPriceAlthEntity)
    }
    fun deleteAllCacheFtPriceAlth(){
        repository.deleteAllCacheFtPriceAlth()
    }
}