package com.erp.distribution.sfa.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FtSaleshRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.utils.DateTimeUtils
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.chrono.ChronoLocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.Callable
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFtSaleshUseCase @Inject constructor(
        private val repository: FtSaleshRepository,
        private val fCustomerRepository: FCustomerRepository,
    ) : SingleUseCase<List<FtSaleshEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FtSaleshEntity>> {
        return repository.getRemoteAllFtSalesh("authHeader")
    }
    fun getRemoteAllFtSalesh(authHeader: String): Single<List<FtSaleshEntity>>{
        return repository.getRemoteAllFtSalesh(authHeader)
    }

    fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSaleshEntity>{
        return repository.getRemoteFtSaleshById(authHeader, id)
    }
    fun getRemoteAllFtSaleshByDivision(authHeader: String, divisionId: Int): Single<List<FtSaleshEntity>>{
        return repository.getRemoteAllFtSaleshByDivision(authHeader, divisionId)
    }
    fun getRemoteAllTotalSalesByFSalesmanThisMonth(authHeader: String, fsalesmanBean: Int): Single<Map<String, Double>>{
        return repository.getRemoteAllTotalSalesByFSalesmanThisMonth(authHeader, fsalesmanBean)
    }

    fun createRemoteFtSalesh(authHeader: String, ftSalesh: FtSalesh): Single<FtSalesh>{
        return repository.createRemoteFtSalesh(authHeader, ftSalesh.toEntity()).map {
            it?.let {  it.toDomain()}
        }
    }
    fun addOrUpdateRemoteFtSaleshFromAndroid(authHeader: String, ftSalesh: FtSalesh): Single<FtSalesh>{
        return repository.createRemoteFtSaleshFromAndroid(authHeader, ftSalesh.toEntity()).map {
            it?.let {  it.toDomain()}
        }
    }

    fun putRemoteFtSalesh(authHeader: String, id: Long, ftSalesh: FtSalesh): Single<FtSalesh>{
        return repository.putRemoteFtSalesh(authHeader, id, ftSalesh.toEntity()).map {
            it?.let {  it.toDomain()}
        }
    }
    fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSaleshEntity>{
        return repository.deleteRemoteFtSalesh(authHeader, id)
    }



    fun getCacheAllFtSalesh(): LiveData<List<FtSaleshEntity>>{
        return repository.getCacheAllFtSalesh()
    }


    fun getCacheAllFtSaleshWithItemsSingle(): Single<List<FtSalesh>> {
        return Single.fromCallable ( Callable {
            repository.getCacheAllFtSaleshWithItems().map {
                val ftSaleshBean = it.ftSaleshEntity.toDomain()

                it.fCustomerEntity?.let {
                    ftSaleshBean.fcustomerBean = it.toDomain()
                }
                it.listFtSalesdItems?.let {
                    val mutableList = mutableListOf<FtSalesdItems>()
                    for (data in it) {
                        mutableList.add(data.toDomain())
                    }
                    ftSaleshBean.listFtSalesdItems = mutableList
                }
                ftSaleshBean

            }
        })
    }


    fun getCacheAllFtSaleshWithItemsByIdFlow(ftSaleshRefno: Long): Flow<FtSalesh>{
        return repository.getCacheFtSaleshWithItemsByIdFlow(ftSaleshRefno).map {
                val ftSaleshBean = it.ftSaleshEntity.toDomain()
                val division = it.fDivisionEntity!!.toDomain()
                ftSaleshBean.fdivisionBean = division
                it.fCustomerEntity?.let {
                    ftSaleshBean.fcustomerBean = it.toDomain()
                }
                ftSaleshBean
        }
    }
    fun getCacheAllFtSaleshWithItemsByIdLive(ftSaleshRefno: Long): LiveData<FtSalesh>{
        return repository.getCacheFtSaleshWithItemsByIdLive(ftSaleshRefno).map {
            var ftSaleshBean = FtSalesh()
            it?.let {
                it.ftSaleshEntity?.let {
                    ftSaleshBean = it.toDomain()
                }
                it.fDivisionEntity?.let {
                    ftSaleshBean.fdivisionBean = it.toDomain()
                }
                it.fCustomerEntity?.let {
                    ftSaleshBean.fcustomerBean = it.toDomain()
                }
            }
            ftSaleshBean

        }
    }


    fun getCacheAllFtSaleshLive(): LiveData<List<FtSalesh>>{
        return repository.getCacheAllFtSaleshLive().map {
            it.map {
                val ftSaleshBean = it.ftSaleshEntity.toDomain()
                it.fDivisionEntity?.let {
                    ftSaleshBean.fdivisionBean = it.toDomain()
                }
                it.fCustomerEntity?.let {
                    ftSaleshBean.fcustomerBean = it.toDomain()
                }
                ftSaleshBean
            }
        }
    }

    fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder,  limit: Int, currentOffset: Int, hideSelected: Boolean): Flow<List<FtSalesh>> {
        return repository.getCacheAllFtSaleshFlow(query, sortOrder, limit, currentOffset, hideSelected).map {
            it.map {
                val ftSaleshBean = it.ftSaleshEntity.toDomain()
                it.fDivisionEntity?.let {
                    ftSaleshBean.fdivisionBean = it.toDomain()
                }
                it.fCustomerEntity?.let {
                    ftSaleshBean.fcustomerBean = it.toDomain()
                }

                ftSaleshBean
            }.filter {
//                Log.d("#result", "#result ${hideSelected} >> ${DateTimeUtils.getZeroTimeDate(it.invoiceDate) }  >> ${DateTimeUtils.getZeroTimeDate(Date())} ")
                var isInCriteria = true
                if (! query.equals("")) {
                    if (! it.fcustomerBean.custname.toLowerCase().contains(query.toLowerCase())) isInCriteria =false
                }
                if (hideSelected==true){
                    DateTimeUtils.getZeroTimeDate(it.invoiceDate).equals(DateTimeUtils.getZeroTimeDate(Date())) && isInCriteria
                }else {
                    DateTimeUtils.getZeroTimeDate(it.invoiceDate).before(DateTimeUtils.getZeroTimeDate(Date()))  && isInCriteria
                }
            }

        }
    }

    /**
     * Masih Belum Berhasil digunakan. Kemungkinan ada pada DAO nya
     */
    fun getCacheFtSaleshWithItemsByIdFLow(id: Long): Flow<FtSalesh>{
        return repository.getCacheFtSaleshWithItemsByIdFlow(id).map {
                val ftSaleshBean = it.ftSaleshEntity.toDomain()
                it.fDivisionEntity?.let {
                    ftSaleshBean.fdivisionBean = it.toDomain()
                }
                it.fSalesmanEntity?.let {
                    ftSaleshBean.fsalesmanBean = it.toDomain()
                }
                it.fCustomerEntity?.let {
                    ftSaleshBean.fcustomerBean = it.toDomain()
                }

//                it.listFtSalesdItems!!.isNotEmpty().apply {
//                    val mapFtSalesdItems = mutableMapOf<Long, FtSalesdItems>()
//                    it.listFtSalesdItems.iterator().forEach {
//                        mapFtSalesdItems.put(it.id, it.toDomain())
//                    }
//                    ftSaleshBean.mapFtSalesdItems = mapFtSalesdItems
//                }

                ftSaleshBean
        }
    }

    fun getCacheAllFtSaleshWithItemsLive(): LiveData<List<FtSalesh>> {
        return repository.getCacheAllFtSaleshWithItemsLive().map {
            it.map {
                val ftSaleshBean = it.ftSaleshEntity.toDomain()
                it.fCustomerEntity?.let {
                    ftSaleshBean.fcustomerBean = it.toDomain()
                }
                it.listFtSalesdItems?.let {
                    val mutableList = mutableListOf<FtSalesdItems>()
                    for (data in it) {
                        mutableList.add(data.toDomain())
                    }
                    ftSaleshBean.listFtSalesdItems = mutableList
                }
                ftSaleshBean
            }
        }

    }

    fun getCacheFtSaleshById(id: Long): LiveData<FtSaleshEntity>{
        return repository.getCacheFtSaleshById(id)
    }
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSaleshEntity>>{
        return repository.getCacheAllFtSaleshByDivision(divisionId)
    }
    fun insertCacheFtSalesh(ftSalesh: FtSalesh){
        repository.insertCacheFtSalesh(ftSalesh.toEntity())
    }
    fun insertCacheFtSaleshNoReplace(ftSalesh: FtSalesh){
        repository.insertCacheFtSaleshNoReplace(ftSalesh.toEntity())
    }
    fun insertSingleCacheFtSalesh(ftSaleshEntity: FtSaleshEntity): Single<Long>{
        return repository.insertSingleCacheFtSalesh(ftSaleshEntity)
    }
    fun insertCacheListFtSalesh(list: List<FtSalesh>){
        repository.addCacheListFtSalesh(list.map {
            it.toEntity()
        })
    }
    fun putCacheFtSalesh(ftSalesh: FtSalesh){
        repository.putCacheFtSalesh(ftSalesh.toEntity())
    }
    fun deleteCacheFtSalesh(ftSalesh: FtSalesh){
        repository.deleteCacheFtSalesh(ftSalesh.toEntity())
    }
    fun deleteCacheFtSaleshDomain(ftSalesh: FtSalesh){
        repository.deleteCacheFtSalesh(ftSalesh.toEntity())
    }
    fun deleteAllCacheFtSalesh(){
        repository.deleteAllCacheFtSalesh()
    }
    fun deleteAllSingleCacheBeforeDate(){
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal.add(Calendar.DATE, -3)
        repository.deleteAllSingleCacheBeforeDate(cal.time)
    }

}