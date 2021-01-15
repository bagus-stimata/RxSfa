package com.erp.distribution.sfa.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.erp.distribution.sfa.dao.*
import com.erp.distribution.sfa.data.source.local.dao.PhotoDao
import com.erp.distribution.sfa.domain.model.Photo

import com.erp.distribution.sfa.model.*
import com.erp.distribution.sfa.model.utils.RoomDateConverters
import com.erp.distribution.sfa.security_model.FUser
import com.erp.distribution.sfa.security_model.FUserRoles
import com.erp.distribution.sfa.security_repository.FUserDao
import com.erp.distribution.sfa.security_repository.FUserRolesDao


/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * @Created by ZARA
 */
@Database(entities = [
    Photo::class
    ,
    FUser::class, FUserRoles::class
    ,
    FArea::class, FCompany::class, FCustomer::class, FCustomerGroup::class, FCustomerPic::class, FCustomerSalesman::class,
    FDistributionChannel::class, FDivision::class, FGiro::class, FMaterial::class, FMaterialGroup1::class,
    FMaterialGroup2::class, FMaterialGroup3::class, FMaterialPic::class, FMaterialSalesBrand::class,
    FParamDiskonItem::class, FParamDiskonItemVendor::class, FParamDiskonNota::class, FPromotionRulesdPayments::class,
    FPromotionRulesdValidCusts::class, FPromotionRulesdValidProducts::class, FPromotionRulesh::class,
    FRegion::class, FSalesman::class, FStock::class, FSubArea::class, FtApPaymentd::class, FtApPaymenth::class,
    FtArPaymentd::class, FtArPaymenth::class, FTax::class, FtOpnamedItems::class, FtOpnameh::class,
    FtPriceAltdItems::class, FtPriceAlth::class, FtPricedItems::class, FtPriceh::class, FtPurchasedItems::class, FtPurchaseh::class,
    FtSalesdItems::class, FtSalesh::class, FtStockTransferdItems::class, FtStockTransferh::class, FUangMuka::class,
    FVendor::class, FWarehouse::class, Sysvar::class, FExpedisi::class


                     ], version = 5, exportSchema = false)
@TypeConverters(RoomDateConverters::class) //Karena ada Native Date
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao

    abstract val userDao: FUserDao
    abstract val userRolesDao: FUserRolesDao

    abstract val areaDao: FAreaDao
    abstract val companyDao: FCompanyDao
    abstract val customerDao: FCustomerDao
    abstract val customerGroupDao: FCustomerGroupDao
    abstract val customerPicDao: FCustomerPicDao
    abstract val customerSalesmanDao: FCustomerSalesmanDao
    abstract val distributionChannelDao: FDistributionChannelDao
    abstract val divisionDao: FDivisionDao
    abstract val expedisiDao: FExpedisiDao
    abstract val giroDao: FGiroDao
    abstract val materialDao: FMaterialDao
    abstract val materialGroup1Dao: FMaterialGroup1Dao
    abstract val materialGroup2Dao: FMaterialGroup2Dao
    abstract val materialGroup3Dao: FMaterialGroup3Dao
    abstract val materialPicDao: FMaterialPicDao
    abstract val materialSalesBrandDao: FMaterialSalesBrandDao
    abstract val paramDiskonItemDao: FParamDiskonItemDao
    abstract val paramDiskonItemVendorDao: FParamDiskonItemVendorDao
    abstract val paramDiskonNotaDao: FParamDiskonNotaDao
    abstract val promotionRulesdPaymentsDao: FPromotionRulesdPaymentsDao
    abstract val promotionRulesdValidCustsDao: FPromotionRulesdValidCustsDao
    abstract val promotionRulesdValidProductsDao: FPromotionRulesdValidProductsDao
    abstract val promotionRuleshDao: FPromotionRuleshDao
    abstract val regionDao: FRegionDao
    abstract val salesmanDao: FSalesmanDao
    abstract val stockDao: FStockDao
    abstract val subAreaDao: FSubAreaDao
    abstract val apPaymentdDao: FtApPaymentdDao
    abstract val apPaymenthDao: FtApPaymenthDao
    abstract val arPaymentdDao: FtArPaymentdDao
    abstract val arPaymenthDao: FtArPaymenthDao
    abstract val taxDao: FTaxDao
    abstract val opnamedItemsDao: FtOpnamedItemsDao
    abstract val opnamehDao: FtOpnamehDao
    abstract val priceAltdItemsDao: FtPriceAltdItemsDao
    abstract val priceAlthDao: FtPriceAlthDao
    abstract val pricedItemsDao: FtPricedItemsDao
    abstract val pricehDao: FtPricehDao
    abstract val purchasedItemsDao: FtPurchasedItemsDao
    abstract val purchasehDao: FtPurchasehDao
    abstract val salesdItemsDao: FtSalesdItemsDao
    abstract val saleshDao: FtSaleshDao
    abstract val stockTransferdItemsDao: FtStockTransferdItemsDao
    abstract val stockTransferhDao: FtStockTransferhDao
    abstract val uangMukaDao: FUangMukaDao
    abstract val vendorDao: FVendorDao
    abstract val warehouseDao: FWarehouseDao
    abstract val sysvarDao: SysvarDao

    companion object {
        const val DB_NAME = "ArtGalleryDatabase.db"
    }
}
