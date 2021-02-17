package com.erp.distribution.sfa.domain.usecase

import android.util.Log
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.repository.FUserRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.data.source.entity_security.toDomain
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FSalesman
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.domain.repository.FDivisionRepository
import com.erp.distribution.sfa.domain.repository.FSalesmanRepository
import com.erp.distribution.sfa.domain.repository.FWarehouseRepository
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFUserUseCase @Inject constructor(
        private val repository: FUserRepository,
        private val fDivisionRepository: FDivisionRepository,
        private val fSalesmanRepository: FSalesmanRepository,
        private val fWarehouseRepository: FWarehouseRepository

        ) : SingleUseCase<List<FUserEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FUserEntity>> {
        return repository.getRemoteAllFUser("aa")
    }
    fun getRemoteAllFUser(authHeader: String): Single<List<FUserEntity>>{
        return repository.getRemoteAllFUser(authHeader)
    }

    fun getRemoteFUserById(authHeader: String, id: Int): Single<FUserEntity>{
        return repository.getRemoteFUserById(authHeader, id)
    }
    fun getRemoteAllFUserByUsername(authHeader: String, username: String): Single<FUserEntity>{
        return repository.getRemoteFUserByUsername(authHeader, username)
    }

    fun getRemoteAllFUserByUsernamePassword(authHeader: String, username: String, password: String): Single<FUserEntity>{
        return repository.getRemoteFUserByUsernamePassword(authHeader, username, password)
    }
    fun getRemoteAllFUserByEmail(authHeader: String, email: String): Single<FUserEntity>{
        return repository.getRemoteFUserByEmail(authHeader, email)
    }
    fun createRemoteFUser(authHeader: String, fUserEntity: FUserEntity): Single<FUserEntity>{
        return repository.createRemoteFUser(authHeader, fUserEntity)
    }
    fun putRemoteFUser(authHeader: String, id: Int, fUserEntity: FUserEntity): Single<FUserEntity>{
        return repository.putRemoteFUser(authHeader, id, fUserEntity)
    }
    fun deleteRemoteFUser(authHeader: String, id: Int): Single<FUserEntity>{
        return repository.deleteRemoteFUser(authHeader, id)
    }


    fun getCacheAllFUser(): LiveData<List<FUserEntity>>{
        return repository.getCacheAllFUser()
    }
    fun getCacheAllFUserDomain(): LiveData<List<FUser>>{
        return repository.getCacheAllFUser().map {
            it.map {
                it.toDomain()
            }
        }
    }

    fun getCacheAllFUserBiasa(): List<FUserEntity>{
        return repository.getCacheAllFUserBiasa()
    }
    fun getCacheFUserById(id: Int): LiveData<FUserEntity>{
        return repository.getCacheFUserById(id)
    }
//    fun getCacheAllFUserByDivision(divisionId: Int): LiveData<List<FUser>>{
//        return repository.getCacheAllFUserByDivision(divisionId)
//    }
    fun addCacheFUser(fUserEntity: FUserEntity){
        repository.createCacheFUser(fUserEntity)
    }
    fun putCacheFUser(fUserEntity: FUserEntity){
        repository.putCacheFUser(fUserEntity)
    }
    fun deleteCacheFUser(fUserEntity: FUserEntity){
        repository.deleteCacheFUser(fUserEntity)
    }
    fun deleteAllCacheFUser(){
        repository.deleteAllCacheFUser()
    }

    fun getCacheAllFUserDomainFlow(): Flow<List<FUser>> {
        return repository.getCacheAllFUserFlow().map {
            it.map { data ->
                val fUserBean = data.fUserEntity.toDomain()

                data.fSalesmanEntity?.let {
                    val division = data.fDivisionEntity?.let {
                        fUserBean.fdivisionBean = data.fDivisionEntity.toDomain()
                    }
                }
                data.fSalesmanEntity?.let {
                    val salesman = it.toDomain()
                    fUserBean.fsalesmanBean = salesman
                }
                data.fWarehouseEntity?.let {
                    val warehouse = data.fWarehouseEntity.toDomain()
                    fUserBean.fwarehouseBean = warehouse
                }

                fUserBean
            }
        }
    }

    fun getWhenChangeCacheFromUserViewStateLive(): LiveData<Resource<UserViewState>> {
        val liveData = MutableLiveData<Resource<UserViewState>>()
        return repository.getCacheAllFUserLive().switchMap {

            var newData = UserViewState()

            it.iterator().forEach { data ->
                data.fUserEntity?.let {
                    if (! data.fUserEntity.username.isNullOrEmpty()) {
                        newData = newData.copy(fUser = data.fUserEntity.toDomain())
                    }
                }
                data.fDivisionEntity?.let {
                    if (! data.fDivisionEntity.kode1.isNullOrEmpty()) {
                        newData = newData.copy(fDivision = data.fDivisionEntity.toDomain())
                    }
                }
                data.fSalesmanEntity?.let {
                    if (! data.fSalesmanEntity.spcode.isNullOrEmpty()) {
                        newData = newData.copy(fSalesman = data.fSalesmanEntity.toDomain())
                    }
                }
                data.fWarehouseEntity?.let {
                    if (! data.fWarehouseEntity.kode1.isNullOrEmpty()) {
                        newData = newData.copy(fWarehouse = data.fWarehouseEntity.toDomain())
                    }
                }

                return@forEach
            }


            liveData.postValue(Resource.Success(newData))
            liveData
        }


    }
    fun getWhenChangeCacheFromUserViewStateLiveXY(): LiveData<Resource<UserViewState>> {
        val resultMediator: MediatorLiveData<Resource<UserViewState>> = MediatorLiveData<Resource<UserViewState>>()
        var newData = UserViewState()

        try{
            val resultFUserEntity = repository.getCacheAllFUserFlow().map {
                it.map { data ->

                    val fUserBean = data.fUserEntity.toDomain()

                    data.fDivisionEntity?.let {
                        val division = it.toDomain()
                        fUserBean.fdivisionBean = division
                    }
                    data.fSalesmanEntity?.let {
                        val salesman = it.toDomain()
                        fUserBean.fsalesmanBean = salesman
                    }
                    data.fWarehouseEntity?.let {
                        val warehouse = data.fWarehouseEntity.toDomain()
                        fUserBean.fwarehouseBean = warehouse
                    }
                    fUserBean
                }
            }.asLiveData()

            resultMediator.addSource( resultFUserEntity, Observer {
                resultMediator.postValue(Resource.Loading(true))
                it?.let {
                    try {
                        if (it.size>0) {
                            it.forEach {
                                if (!it.isLocked) {

                                    newData = newData.copy(fUser = it)

                                    if (! it.fdivisionBean!!.kode1.isNullOrEmpty()) newData = newData.copy(fDivision = it.fdivisionBean)
                                    if (! it.fsalesmanBean!!.spcode.isNullOrEmpty()) newData = newData.copy(fSalesman = it.fsalesmanBean)
                                    if (! it.fwarehouseBean!!.kode1.isNullOrEmpty()) newData = newData.copy(fWarehouse = it.fwarehouseBean)

                                    resultMediator.postValue(Resource.Success(newData))
                                    return@forEach
                                }
                            }
                        }else{
                            resultMediator.postValue(Resource.Success(newData)) // -> Dari database tidak ada
                        }
                    }catch (e: Exception){
                        resultMediator.postValue(Resource.Failure(e))
                    }
                }
            })

        }catch (ex: Exception){
            resultMediator.postValue(Resource.Failure(ex))
        }
        return resultMediator
    }

    fun getWhenChangeCacheFromUserViewStateLiveX(): LiveData<Resource<UserViewState>> {
        val resultMediator: MediatorLiveData<Resource<UserViewState>> = MediatorLiveData<Resource<UserViewState>>()
//        var newData = UserViewState()

        try{
            val resourceFUser = Transformations.map(repository.getCacheAllFUser()) {
                try {
                    val fUserEntity = it.get(0)
                    var newData = UserViewState(fUser = fUserEntity.toDomain())
                    Resource.Success(newData)
                }catch (e: Exception){
                    Resource.Failure(e)
                }
            }
            resultMediator.addSource( resourceFUser, Observer {
                Log.d("result", "#result User {it}")
                resultMediator.postValue(it)
            })

            val resourceFDivision = Transformations.map(fDivisionRepository.getCacheAllFDivision()) {
                try {
                    val fdivisionEntity = it.get(0)
                    var newData = UserViewState(fDivision = fdivisionEntity.toDomain())
                    Resource.Success(newData)
                }catch (e: Exception){
                    Resource.Failure(e)
                }
            }
            resultMediator.addSource( resourceFDivision, Observer {
                Log.d("result", "#result Division {it}")
                resultMediator.postValue(it)
            })

        }catch (ex: Exception){
//            resultMediator.postValue(Resource.Failure(ex))
        }

        return resultMediator
    }

    fun getWhenChangeCacheFromUserViewStateLiveXX(userViewState: UserViewState): LiveData<Resource<UserViewState>> {
        /**
         * Tidak Efektif,
         * Lebih baik pakai LiveData biasa
         */
        val resultMediator: MediatorLiveData<Resource<UserViewState>> = MediatorLiveData<Resource<UserViewState>>()
        var newUserViewState = userViewState.let { userViewState.copy() }

        try{

            val resultFUser = repository.getCacheAllFUser().map {
                var newFUser: FUser? = null
                try {
                    it.forEach {
                        if (!it.isLocked) {
                            newFUser = it.toDomain()

                            return@forEach
                        }
                    }
                }catch (e: Exception){}
                newFUser
            }
            resultMediator.addSource( resultFUser, Observer {
                it?.let {
                    try {
                        newUserViewState = newUserViewState.copy(fUser = it)
                        resultMediator.postValue(Resource.Success(newUserViewState))
                    }catch (e: Exception){
                        resultMediator.postValue(Resource.Failure(e))
                    }
                }
            })

//            val resultFDivision = fDivisionRepository.getCacheAllFDivision().map {
//                var newFDivision: FDivision? = null
//                try {
//                    it.forEach {
//                        if (!it.isStatusActive) {
//                            newFDivision = it.toDomain()
//                            return@forEach
//                        }
//                    }
//                }catch (e: Exception){}
//                newFDivision
//            }
//            resultMediator.addSource( resultFDivision, Observer {
//                it?.let {
//                    try {
//                        newUserViewState = newUserViewState.copy(fDivision = it)
//                        resultMediator.postValue(Resource.Success(newUserViewState))
//                    }catch (e: Exception){
//                        resultMediator.postValue(Resource.Failure(e))
//                    }
//                }
//            })
//
//            val resultSalesman = fSalesmanRepository.getCacheAllFSalesman().map {
//                var newData: FSalesman? = null
//                try {
//                    it.forEach {
//                        if (!it.isStatusActive) {
//                            newData = it.toDomain()
//                            return@forEach
//                        }
//                    }
//                }catch (e: Exception){}
//                newData
//            }
//            resultMediator.addSource( resultSalesman, Observer {
//                it?.let {
//                    try {
//                        newUserViewState = newUserViewState.copy(fSalesman = it)
//                        resultMediator.postValue(Resource.Success(newUserViewState))
//                    }catch (e: Exception){
//                        resultMediator.postValue(Resource.Failure(e))
//                    }
//                }
//            })

//            val resultFWarehouse = fWarehouseRepository.getCacheAllFWarehouse().map {
//                var newData: FWarehouse? = null
//                try {
//                    it.forEach {
//                        if (!it.isStatusActive) {
//                            newData = it.toDomain()
//                            return@forEach
//                        }
//                    }
//                }catch (e: Exception){}
//                newData
//            }
//            resultMediator.addSource( resultFWarehouse, Observer {
//                it?.let {
//                    try {
//                        newUserViewState = newUserViewState.copy(fWarehouse = it)
//                        resultMediator.postValue(Resource.Success(newUserViewState))
//                    }catch (e: Exception){
//                        resultMediator.postValue(Resource.Failure(e))
//                    }
//                }
//            })

        }catch (ex: Exception){
            ex.printStackTrace()
            resultMediator.postValue(Resource.Failure(ex))
        }


        return resultMediator
    }

    fun testGetUserViewState(): UserViewState {
        var newData = UserViewState()
        val user = FUser()
        user.username = "user_bagus"
        val salesman = FSalesman(1, "Mas Ari")
        salesman.spcode = "001"
        val division = FDivision(1, "Div Kunjang")
        division.kode1 = "D1"
//        val warehouse = FWarehouse(1, "STANDART")
//        warehouse.kode1 = "D1"

//        newData = newData.copy(fUser =  user, fSalesman = salesman, fWarehouse = warehouse, fDivision = division)
        newData = newData.copy(fUser =  user, fSalesman = salesman, fDivision = division)

        return newData
    }


}