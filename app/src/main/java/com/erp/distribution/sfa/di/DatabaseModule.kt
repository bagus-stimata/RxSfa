package com.erp.distribution.sfa.di

import dagger.Provides
import androidx.room.Room
import android.app.Application
import com.erp.distribution.sfa.dao.*
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.data.source.local.dao.PhotoDao
import com.erp.distribution.sfa.model.FArea
import com.erp.distribution.sfa.security_repository.FUserDao
import com.erp.distribution.sfa.security_repository.FUserRolesDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    @Provides
    internal fun providePhotoDao(appDatabase: AppDatabase): PhotoDao {
        return appDatabase.photoDao
    }

    @Provides
    internal fun provideUserDao(appDatabase: AppDatabase): FUserDao = appDatabase.userDao
    @Provides
    internal fun provideUserRolesDao(appDatabase: AppDatabase): FUserRolesDao = appDatabase.userRolesDao

    @Provides
    internal fun provideAreaDao(appDatabase: AppDatabase): FAreaDao = appDatabase.areaDao
    @Provides
    internal fun provideFCompanyDao(appDatabase: AppDatabase): FCompanyDao = appDatabase.companyDao
    @Provides
    internal fun provideFCustomerDao(appDatabase: AppDatabase): FCustomerDao = appDatabase.customerDao
    @Provides
    internal fun provideFCustomerGroupDao(appDatabase: AppDatabase): FCustomerGroupDao = appDatabase.customerGroupDao
//    @Provides
//    internal fun provideFCustomerPicDao(appDatabase: AppDatabase): FCustomerPicDao = appDatabase.customerPicDao
//    @Provides
//    internal fun provideFCustomerSalesmanDao(appDatabase: AppDatabase): FCustomerSalesmanDao = appDatabase.customerSalesmanDao
//    @Provides
//    internal fun provideFDistributionChannelDao(appDatabase: AppDatabase): FDistributionChannelDao = appDatabase.distributionChannelDao
    @Provides
    internal fun provideFDivisionDao(appDatabase: AppDatabase): FDivisionDao = appDatabase.divisionDao
//    @Provides
//    internal fun provideFExpedisiDao(appDatabase: AppDatabase): FExpedisiDao = appDatabase.expedisiDao
//    @Provides
//    internal fun provideFGiroDao(appDatabase: AppDatabase): FGiroDao = appDatabase.giroDao
    @Provides
    internal fun provideFMaterialDao(appDatabase: AppDatabase): FMaterialDao = appDatabase.materialDao

    @Provides
    internal fun provideFMaterialGroup1Dao(appDatabase: AppDatabase): FMaterialGroup1Dao = appDatabase.materialGroup1Dao
    @Provides
    internal fun provideFMaterialGroup2Dao(appDatabase: AppDatabase): FMaterialGroup2Dao = appDatabase.materialGroup2Dao
    @Provides
    internal fun provideFMaterialGroup3Dao(appDatabase: AppDatabase): FMaterialGroup3Dao = appDatabase.materialGroup3Dao
//    @Provides
//    internal fun provideFMaterialPicDao(appDatabase: AppDatabase): FMaterialPicDao = appDatabase.materialPicDao
//    @Provides
//    internal fun provideFMaterialSalesBrandDao(appDatabase: AppDatabase): FMaterialSalesBrandDao = appDatabase.materialSalesBrandDao
//    @Provides
//    internal fun provideFParamDiskonItemDao(appDatabase: AppDatabase): FParamDiskonItemDao = appDatabase.paramDiskonItemDao
//    @Provides
//    internal fun provideFParamDiskonItemVendorDao(appDatabase: AppDatabase): FParamDiskonItemVendorDao = appDatabase.paramDiskonItemVendorDao
//    @Provides
//    internal fun provideFParamDiskonNotaDao(appDatabase: AppDatabase): FParamDiskonNotaDao = appDatabase.paramDiskonNotaDao
//    @Provides
//    internal fun provideFPromotionRulesdPaymentsDao(appDatabase: AppDatabase): FPromotionRulesdPaymentsDao = appDatabase.promotionRulesdPaymentsDao
//    @Provides
//    internal fun provideFPromotionRulesdValidCustsDao(appDatabase: AppDatabase): FPromotionRulesdValidCustsDao = appDatabase.promotionRulesdValidCustsDao
//    @Provides
//    internal fun provideFPromotionRulesdValidProductsDao(appDatabase: AppDatabase): FPromotionRulesdValidProductsDao = appDatabase.promotionRulesdValidProductsDao
//    @Provides
//    internal fun provideFPromotionRuleshDao(appDatabase: AppDatabase): FPromotionRuleshDao = appDatabase.promotionRuleshDao
//    @Provides
//    internal fun provideFRegionDao(appDatabase: AppDatabase): FRegionDao = appDatabase.regionDao
    @Provides
    internal fun provideFSalesmanDao(appDatabase: AppDatabase): FSalesmanDao = appDatabase.salesmanDao
    @Provides
    internal fun provideFStockDao(appDatabase: AppDatabase): FStockDao = appDatabase.stockDao
    @Provides
    internal fun provideFSubAreaDao(appDatabase: AppDatabase): FSubAreaDao = appDatabase.subAreaDao
//    @Provides
//    internal fun provideFtApPaymentdDao(appDatabase: AppDatabase): FtApPaymentdDao = appDatabase.apPaymentdDao
//    @Provides
//    internal fun provideFtApPaymenthDao(appDatabase: AppDatabase): FtApPaymenthDao = appDatabase.apPaymenthDao
//    @Provides
//    internal fun provideFtArPaymentdDao(appDatabase: AppDatabase): FtArPaymentdDao = appDatabase.arPaymentdDao
//    @Provides
//    internal fun provideFtArPaymenthDao(appDatabase: AppDatabase): FtArPaymenthDao = appDatabase.arPaymenthDao
//    @Provides
//    internal fun provideFTaxDao(appDatabase: AppDatabase): FTaxDao = appDatabase.taxDao
//    @Provides
//    internal fun provideFtOpnamedItemsDao(appDatabase: AppDatabase): FtOpnamedItemsDao = appDatabase.opnamedItemsDao
//    @Provides
//    internal fun provideFtOpnamehDao(appDatabase: AppDatabase): FtOpnamehDao = appDatabase.opnamehDao
//    @Provides
//    internal fun provideFtPriceAltdItemsDao(appDatabase: AppDatabase): FtPriceAltdItemsDao = appDatabase.priceAltdItemsDao
//    @Provides
//    internal fun provideFtPriceAlthDao(appDatabase: AppDatabase): FtPriceAlthDao = appDatabase.priceAlthDao
//    @Provides
//    internal fun provideFtPricedItemsDao(appDatabase: AppDatabase): FtPricedItemsDao = appDatabase.pricedItemsDao
//    @Provides
//    internal fun provideFtPricehDao(appDatabase: AppDatabase): FtPricehDao = appDatabase.pricehDao
//    @Provides
//    internal fun provideFtPurchasedItemsDao(appDatabase: AppDatabase): FtPurchasedItemsDao = appDatabase.purchasedItemsDao
//    @Provides
//    internal fun provideFtPurchasehDao(appDatabase: AppDatabase): FtPurchasehDao = appDatabase.purchasehDao
//    @Provides
//    internal fun provideFtSalesdItemsDao(appDatabase: AppDatabase): FtSalesdItemsDao = appDatabase.salesdItemsDao
//    @Provides
//    internal fun provideFtSaleshDao(appDatabase: AppDatabase): FtSaleshDao = appDatabase.saleshDao
//    @Provides
//    internal fun provideFtStockTransferdItemsDao(appDatabase: AppDatabase): FtStockTransferdItemsDao = appDatabase.stockTransferdItemsDao
//    @Provides
//    internal fun provideFtStockTransferhDao(appDatabase: AppDatabase): FtStockTransferhDao = appDatabase.stockTransferhDao
//    @Provides
//    internal fun provideFUangMukaDao(appDatabase: AppDatabase): FUangMukaDao = appDatabase.uangMukaDao
//    @Provides
//    internal fun provideFVendorDao(appDatabase: AppDatabase): FVendorDao = appDatabase.vendorDao
    @Provides
    internal fun provideFWarehouseDao(appDatabase: AppDatabase): FWarehouseDao = appDatabase.warehouseDao
    @Provides
    internal fun provideSysvarDao(appDatabase: AppDatabase): SysvarDao = appDatabase.sysvarDao

}