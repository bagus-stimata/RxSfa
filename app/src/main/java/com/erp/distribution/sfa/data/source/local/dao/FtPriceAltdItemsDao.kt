package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPriceAltdItemsDao {
    /**
     * @param ftPriceAltdItemsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPriceAltdItemEntities: List<FtPriceAltdItemsEntity>)


    @Update
    fun update(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity)

    @Delete
    fun delete(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity)

    @Query("DELETE FROM ftPriceAltdItems")
    fun deleteAllFtPriceAltdItems()

    @get:Query("SELECT * FROM ftPriceAltdItems ")
    val getAllFtPriceAltdItemsLive: LiveData<List<FtPriceAltdItemsEntity>>

    @Query("SELECT * FROM ftPriceAltdItems WHERE ftPriceAlthBean = :ftPriceAlthBean ")
    fun getAllByParentLive(ftPriceAlthBean: Int): LiveData<List<FtPriceAltdItemsEntity>>

    @Query("SELECT * FROM ftPriceAltdItems WHERE ftPriceAlthBean = :ftPriceAlthBean AND fmaterialBean = :fMaterialBean ")
    fun getCacheAllFtPriceAltdItemsByFtPriceAlthAndFMaterial(ftPriceAlthBean: Int, fMaterialBean: Int): LiveData<List<FtPriceAltdItemsEntity>>

}