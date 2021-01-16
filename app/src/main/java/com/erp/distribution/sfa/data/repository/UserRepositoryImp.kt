package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.data.source.remote.RetrofitService
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceDummy
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.model.DummyUser
import com.erp.distribution.sfa.domain.model.Photo
import com.erp.distribution.sfa.domain.repository.AlbumRepository
import com.erp.distribution.sfa.domain.repository.UserRepository
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class UserRepositoryImp(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceDummy
) :
    UserRepository {


    override fun getRemoteData(): Single<List<DummyUser>> {
        return retrofitService.getAlbums()
    }

    override fun getCacheData(): LiveData<List<FUser>> {
       return appDatabase.userDao.allFUserLive
    }

    override fun addCacheData(fUser: FUser) {
      appDatabase.userDao.insert(fUser)
    }

    override fun deleteAllCacheData() {
        appDatabase.userDao.deleteAllFUser()
    }

}