package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FDistributionChannelRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFDistributionChannelUseCase @Inject constructor(private val repository: FDistributionChannelRepository) : SingleUseCase<List<FDistributionChannelEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FDistributionChannelEntity>> {
        return repository.getRemoteAllFDistributionChannel("aa")
    }
    fun getRemoteAllFDistributionChannel(authHeader: String): Single<List<FDistributionChannelEntity>>{
        return repository.getRemoteAllFDistributionChannel(authHeader)
    }

    fun getRemoteFDistributionChannelById(authHeader: String, id: Int): Single<FDistributionChannelEntity>{
        return repository.getRemoteFDistributionChannelById(authHeader, id)
    }
    fun getRemoteAllFDistributionChannelByDivision(authHeader: String, divisionId: Int): Single<List<FDistributionChannelEntity>>{
        return repository.getRemoteAllFDistributionChannelByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFDistributionChannelByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FDistributionChannelEntity>>{
        return repository.getRemoteAllFDistributionChannelByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFDistributionChannel(authHeader: String, fDistributionChannelEntity: FDistributionChannelEntity): Single<FDistributionChannelEntity>{
        return repository.createRemoteFDistributionChannel(authHeader, fDistributionChannelEntity)
    }
    fun putRemoteFDistributionChannel(authHeader: String, id: Int, fDistributionChannelEntity: FDistributionChannelEntity): Single<FDistributionChannelEntity>{
        return repository.putRemoteFDistributionChannel(authHeader, id, fDistributionChannelEntity)
    }
    fun deleteRemoteFDistributionChannel(authHeader: String, id: Int): Single<FDistributionChannelEntity>{
        return repository.deleteRemoteFDistributionChannel(authHeader, id)
    }



    fun getCacheAllFDistributionChannel(): LiveData<List<FDistributionChannelEntity>>{
        return repository.getCacheAllFDistributionChannel()
    }
//    fun getCacheAllFDistributionChannelDomainLive(): LiveData<List<FDistributionChannel>>{
//        return repository.getCacheAllFDistributionChannel().map {
//            it.map {
//                it.toDomain()
//            }
//        }
//    }
    fun getCacheFDistributionChannelById(id: Int): LiveData<FDistributionChannelEntity>{
        return repository.getCacheFDistributionChannelById(id)
    }
//    fun getCacheFDistributionChannelByIdDomainLive(id: Int): LiveData<FDistributionChannel>{
//        return repository.getCacheFDistributionChannelById(id).map {
//            it.toDomain()
//        }
//    }
//    fun getCacheAllFDistributionChannelByDivisionDomainLive(divisionId: Int): LiveData<List<FDistributionChannel>>{
//        return repository.getCacheAllFDistributionChannelByDivision(divisionId).map {
//            it.map {
//                it.toDomain()
//            }
//        }
//    }
    fun addCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity){
        repository.addCacheFDistributionChannel(fDistributionChannelEntity)
    }
    fun addCacheListFDistributionChannel(list: List<FDistributionChannelEntity>){
        repository.addCacheListFDistributionChannel(list)
    }
    fun putCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity){
        repository.putCacheFDistributionChannel(fDistributionChannelEntity)
    }
    fun deleteCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity){
        repository.deleteCacheFDistributionChannel(fDistributionChannelEntity)
    }
    fun deleteAllCacheFDistributionChannel(){
        repository.deleteAllCacheFDistributionChannel()
    }
}