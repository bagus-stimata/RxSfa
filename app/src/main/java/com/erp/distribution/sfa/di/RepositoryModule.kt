package com.erp.distribution.sfa.di

import com.erp.distribution.sfa.data.repository.AlbumRepositoryImp
import com.erp.distribution.sfa.data.repository.FAreaRepositoryImpl
import com.erp.distribution.sfa.data.repository.PhotoRepositoryImp
import com.erp.distribution.sfa.data.repository.UserRepositoryImp
import com.erp.distribution.sfa.data.source.remote.RetrofitService
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFArea
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceSecurity
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.AlbumRepository
import com.erp.distribution.sfa.domain.repository.FAreaRepository
import com.erp.distribution.sfa.domain.repository.PhotoRepository
import com.erp.distribution.sfa.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAlbumRepository(
            retrofitService: RetrofitService
    ): AlbumRepository {
        return AlbumRepositoryImp(retrofitService)
    }


    @Singleton
    @Provides
    fun providePhotoRepository(
            appDatabase: AppDatabase,
            retrofitService: RetrofitService
    ): PhotoRepository {
        return PhotoRepositoryImp(appDatabase, retrofitService)
    }


    @Singleton
    @Provides
    fun provideDummyUserRepository(
            appDatabase: AppDatabase,
            retrofitService: RetrofitServiceSecurity
    ): UserRepository {
        return UserRepositoryImp(appDatabase, retrofitService)
    }

    @Singleton
    @Provides
    fun provideFAreaRepository(
            appDatabase: AppDatabase,
            retrofitService: RetrofitServiceFArea
    ): FAreaRepository {
        return FAreaRepositoryImpl(appDatabase, retrofitService)
    }


}