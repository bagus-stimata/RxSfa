package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.domain.model.DummyUser
import com.erp.distribution.sfa.domain.repository.AlbumRepository
import com.erp.distribution.sfa.domain.repository.UserRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetMainUseCase @Inject constructor(private val repository: UserRepository) : SingleUseCase<List<DummyUser>>() {

    override fun buildUseCaseSingle(): Single<List<DummyUser>> {
        return repository.getAllData()
    }
    fun getCacheData(): LiveData<List<FUser>>{
        return repository.getCacheData()
    }

    fun addCacheData(fUser: FUser){
        repository.addCacheData(fUser)
    }

    fun deleteAllCacheData(){
        repository.deleteAllCacheData()
    }
}