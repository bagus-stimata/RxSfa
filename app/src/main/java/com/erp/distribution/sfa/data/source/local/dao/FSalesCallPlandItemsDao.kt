package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsWithHeader
import com.erp.distribution.sfa.domain.model.FSalesCallPlandItems

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSalesCallPlandItemsDao {
    /**
     * @param fSalesCallPlandItemsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFSalesCallPlandItemEntities: List<FSalesCallPlandItemsEntity>)


    @Update
    fun update(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity)

    @Delete
    fun delete(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity)

    @Query("DELETE FROM fSalesCallPlandItems")
    fun deleteAllFSalesCallPlandItems()

//    @get:Query("SELECT * FROM fSalesCallPlandItems ")
//    val getAllFSalesCallPlandItemsLive: LiveData<List<FSalesCallPlandItemsEntity>>

    @Transaction
    @Query("SELECT * FROM fSalesCallPlandItems ")
    fun getAllFSalesCallPlandItemsLive(): LiveData<List<FSalesCallPlandItemsWithHeader>>
    @Transaction
    @Query("SELECT * FROM fSalesCallPlandItems ")
    fun getAllFSalesCallPlandItems(): List<FSalesCallPlandItemsWithHeader>

    @Transaction
    @Query("SELECT * FROM fSalesCallPlandItems WHERE fsalesCallPlanhBean = :fsalesCallPlanhBean ")
    fun getAllByParentLive(fsalesCallPlanhBean: Long): LiveData<List<FSalesCallPlandItemsWithHeader>>
    @Transaction
    @Query("SELECT * FROM fSalesCallPlandItems WHERE fsalesCallPlanhBean = :fsalesCallPlanhBean ")
    fun getAllByParent(fsalesCallPlanhBean: Long): List<FSalesCallPlandItemsWithHeader>


    @Query("SELECT * FROM fSalesCallPlandItems WHERE fsalesCallPlanhBean = :fsalesCallPlanhBean AND fcustomerBean = :fcustomerBean ")
    fun getCacheAllFSalesCallPlandItemsByFtPriceAlthAndFMaterial(fsalesCallPlanhBean: Long, fcustomerBean: Int): LiveData<List<FSalesCallPlandItemsEntity>>


}