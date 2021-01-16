package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Photo
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
class GetMainUseCase @Inject constructor(private val repository: UserRepository) : SingleUseCase<List<FUser>>() {


    override fun buildUseCaseSingle(): LiveData<List<FUser>> {
        return repository.getCacheAll()
    }


}