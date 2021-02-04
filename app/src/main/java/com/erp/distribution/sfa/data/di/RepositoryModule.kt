package com.erp.distribution.sfa.data.di

import com.erp.distribution.sfa.data.repository.*
import com.erp.distribution.sfa.data.source.entity.FMaterialEntityMapper
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3EntityMapper
import com.erp.distribution.sfa.data.source.remote.service_api.*
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.*
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
    fun provideFUserRepository(
            appDatabase: AppDatabase,
            retrofitService: RetrofitServiceSecurity
    ): FUserRepository {
        return UserRepositoryImp(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFCompanyRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFCompany
    ): FCompanyRepository {
        return FCompanyRepositoryImpl(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFDivisionRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFDivision
    ): FDivisionRepository {
        return FDivisionRepositoryImpl(appDatabase, retrofitService)
    }


    @Singleton
    @Provides
    fun provideFAreaRepository(
            appDatabase: AppDatabase,
            retrofitService: RetrofitServiceFArea
    ): FAreaRepository {
        return FAreaRepositoryImpl(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFSubAreaRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFSubArea
    ): FSubAreaRepository {
        return FSubAreaRepositoryImpl(appDatabase, retrofitService)
    }

    @Singleton
    @Provides
    fun provideFCustomerRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFCustomer
    ): FCustomerRepository {
        return FCustomerRepositoryImpl(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFMaterialRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFMaterial,
        fMaterialEntityMapper: FMaterialEntityMapper
    ): FMaterialRepository {
        return FMaterialRepositoryImpl(appDatabase, retrofitService, fMaterialEntityMapper)
    }
    @Singleton
    @Provides
    fun provideFMaterialGroup3Repository(
            appDatabase: AppDatabase,
            retrofitService: RetrofitServiceFMaterialGroup3,
            fMaterialGroup3EntityMapper: FMaterialGroup3EntityMapper
    ): FMaterialGroup3Repository {
        return FMaterialGroup3RepositoryImpl(appDatabase, retrofitService, fMaterialGroup3EntityMapper)
    }

    @Singleton
    @Provides
    fun provideFSalesmanRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFSalesman
    ): FSalesmanRepository {
        return FSalesmanRepositoryImpl(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFStockRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFStock
    ): FStockRepository {
        return FStockRepositoryImpl(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFWarehouseRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFWarehouse
    ): FWarehouseRepository {
        return FWarehouseRepositoryImpl(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFTaxRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFTax
    ): FTaxRepository {
        return FTaxRepositoryImpl(appDatabase, retrofitService)
    }


    @Singleton
    @Provides
    fun provideFtSaleshRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitServiceFtSalesh
    ): FtSaleshRepository {
        return FtSaleshRepositoryImpl(appDatabase, retrofitService)
    }
    @Singleton
    @Provides
    fun provideFtSalesdItemsRepository(
            appDatabase: AppDatabase,
            retrofitService: RetrofitServiceFtSalesdItems
    ): FtSalesdItemsRepository {
        return FtSalesdItemsRepositoryImpl(appDatabase, retrofitService)
    }

}