package com.erp.distribution.sfa.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.erp.distribution.sfa.data.source.local.dao.PhotoDao
import com.erp.distribution.sfa.domain.model.Photo

import com.erp.distribution.sfa.model.*
import com.erp.distribution.sfa.model.utils.RoomDateConverters
import com.erp.distribution.sfa.security_model.FUser
import com.erp.distribution.sfa.security_model.FUserRoles


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

    companion object {
        const val DB_NAME = "ArtGalleryDatabase.db"
    }
}
