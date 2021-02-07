package com.erp.distribution.sfa.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.erp.distribution.sfa.data.source.local.dao.*
import com.erp.distribution.sfa.data.source.local.dao.PhotoDao
import com.erp.distribution.sfa.domain.model.Photo

import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.data.source.entity.utils.RoomDateConverters
import com.erp.distribution.sfa.data.source.entity_security.FUser
import com.erp.distribution.sfa.data.source.entity_security.FUserRoles
import com.erp.distribution.sfa.data.repository_security.FUserDao
import com.erp.distribution.sfa.data.repository_security.FUserRolesDao


/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * @Created by ZARA
 */
@Database(entities = [
    FUser::class, FUserRoles::class,

    FAreaEntity::class,
    FCompanyEntity::class,
    FCustomerEntity::class,
    FCustomerGroupEntity::class,
    FCustomerPicEntity::class,
//    FCustomerSalesman::class,
    FDistributionChannelEntity::class,
    FDivisionEntity::class,
//    FGiro::class,
    FMaterialEntity::class,
    FMaterialGroup1Entity::class,
    FMaterialGroup2Entity::class,
    FMaterialGroup3Entity::class,
    FMaterialPicEntity::class,
    FMaterialSalesBrandEntity::class,
//    FParamDiskonItem::class,
//    FParamDiskonItemVendor::class,
//    FParamDiskonNota::class,
//    FPromotionRulesdPayments::class,
//    FPromotionRulesdValidCusts::class,
//    FPromotionRulesdValidProducts::class,
//    FPromotionRulesh::class,
//    FRegion::class,
    FSalesmanEntity::class,
    FStockEntity::class,
    FSubAreaEntity::class,
//    FtApPaymentd::class,
//    FtApPaymenth::class,
//    FtArPaymentd::class,
//    FtArPaymenth::class,
    FTaxEntity::class,
//    FtOpnamedItems::class,
//    FtOpnameh::class,
//    FtPriceAltdItems::class,
//    FtPriceAlth::class,
//    FtPricedItems::class,
//    FtPriceh::class,
//    FtPurchasedItems::class,
//    FtPurchaseh::class,
    FtSalesdItemsEntity::class,
    FtSaleshEntity::class,
//    FtStockTransferdItems::class,
//    FtStockTransferh::class,
//    FUangMuka::class,
    FVendorEntity::class,
    FWarehouseEntity::class,
    SysvarEntity::class,

    Photo::class,
    Task::class



                     ], version = 39, exportSchema = false)
@TypeConverters(RoomDateConverters::class) //Karena ada Native Date
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao
    abstract val taskDao: TaskDao

    abstract val userDao: FUserDao
    abstract val userRolesDao: FUserRolesDao

    abstract val areaDao: FAreaDao
    abstract val companyDao: FCompanyDao
    abstract val customerDao: FCustomerDao
    abstract val customerGroupDao: FCustomerGroupDao
    abstract val customerPicDao: FCustomerPicDao
//    abstract val customerSalesmanDao: FCustomerSalesmanDao
    abstract val distributionChannelDao: FDistributionChannelDao
    abstract val divisionDao: FDivisionDao
//    abstract val expedisiDao: FExpedisiDao
//    abstract val giroDao: FGiroDao
    abstract val materialDao: FMaterialDao
    abstract val materialGroup1Dao: FMaterialGroup1Dao
    abstract val materialGroup2Dao: FMaterialGroup2Dao
    abstract val materialGroup3Dao: FMaterialGroup3Dao
    abstract val materialPicDao: FMaterialPicDao
    abstract val materialSalesBrandDao: FMaterialSalesBrandDao
//    abstract val paramDiskonItemDao: FParamDiskonItemDao
//    abstract val paramDiskonItemVendorDao: FParamDiskonItemVendorDao
//    abstract val paramDiskonNotaDao: FParamDiskonNotaDao
//    abstract val promotionRulesdPaymentsDao: FPromotionRulesdPaymentsDao
//    abstract val promotionRulesdValidCustsDao: FPromotionRulesdValidCustsDao
//    abstract val promotionRulesdValidProductsDao: FPromotionRulesdValidProductsDao
//    abstract val promotionRuleshDao: FPromotionRuleshDao
//    abstract val regionDao: FRegionDao
    abstract val salesmanDao: FSalesmanDao
    abstract val stockDao: FStockDao
    abstract val subAreaDao: FSubAreaDao
//    abstract val apPaymentdDao: FtApPaymentdDao
//    abstract val apPaymenthDao: FtApPaymenthDao
//    abstract val arPaymentdDao: FtArPaymentdDao
//    abstract val arPaymenthDao: FtArPaymenthDao
    abstract val taxDao: FTaxDao
//    abstract val opnamedItemsDao: FtOpnamedItemsDao
//    abstract val opnamehDao: FtOpnamehDao
//    abstract val priceAltdItemsDao: FtPriceAltdItemsDao
//    abstract val priceAlthDao: FtPriceAlthDao
//    abstract val pricedItemsDao: FtPricedItemsDao
//    abstract val pricehDao: FtPricehDao
//    abstract val purchasedItemsDao: FtPurchasedItemsDao
//    abstract val purchasehDao: FtPurchasehDao
    abstract val salesdItemsDao: FtSalesdItemsDao
    abstract val saleshDao: FtSaleshDao
//    abstract val stockTransferdItemsDao: FtStockTransferdItemsDao
//    abstract val stockTransferhDao: FtStockTransferhDao
//    abstract val uangMukaDao: FUangMukaDao
    abstract val vendorDao: FVendorDao
    abstract val warehouseDao: FWarehouseDao
    abstract val sysvarDao: SysvarDao

    companion object {
        const val DB_NAME = "des_sfa.db"
    }

 }
