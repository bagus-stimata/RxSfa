package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtSalesdItemsDao {
    /**
     * @param ftSalesdItemsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ftSalesdItemsEntity: FtSalesdItemsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtSalesdItemEntities: List<FtSalesdItemsEntity>)


    @Update
    fun update(ftSalesdItemsEntity: FtSalesdItemsEntity)

    @Delete
    fun delete(ftSalesdItemsEntity: FtSalesdItemsEntity)

    @Query("DELETE FROM ftSalesdItems")
    fun deleteAllFtSalesdItems()

    @Query("SELECT * FROM ftSalesdItems WHERE id = :id ")
    fun getAllById(id: Long): FtSalesdItemsEntity
    @Query("SELECT * FROM ftSalesdItems WHERE id = :id ")
    fun getAllByIdLive(id: Long): LiveData<FtSalesdItemsEntity>



    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSalesBean AND  fmaterialBean = :fmaterialBean ")
    fun getAllByFtSaleshAndMaterialFlow(ftSalesBean: Long, fmaterialBean: Int): Flow<List<FtSalesdItemsEntity>>

    @Query("SELECT * FROM ftSalesdItems ")
    fun getAllFtSaleshFLow(): Flow<List<FtSalesdItemsEntity>>


    @get:Query("SELECT * FROM ftSalesdItems ")
    val getAllFtSalesdItemEntities: List<FtSalesdItemsEntity>
    @get:Query("SELECT * FROM ftSalesdItems ")
    val getAllFtSalesdItemsEntityLive: LiveData<List<FtSalesdItemsEntity>>

    @Query("SELECT * FROM ftSalesdItems WHERE fmaterialBean = :materialId ")
    fun getAllFtSalesdItemsByMaterial(materialId: Int): List<FtSalesdItemsEntity>
    @Query("SELECT * FROM ftSalesdItems WHERE fmaterialBean = :materialId ")
    fun getAllFtSalesdItemsByMaterialLive(materialId: Int): LiveData<List<FtSalesdItemsEntity>>


    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshId ")
    fun getAllByFtSalesh(ftSaleshId: Long): List<FtSalesdItemsEntity>
    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshId ")
    fun getAllByFtSaleshLive(ftSaleshId: Long): LiveData<List<FtSalesdItemsEntity>>

    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshBean AND  fmaterialBean = :fmaterialBean ")
    fun getAllByFtSaleshAndMaterial(ftSaleshBean: Long, fmaterialBean: Int): List<FtSalesdItemsEntity>
    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshBean AND  fmaterialBean = :fmaterialBean ")
    fun getAllByFtSaleshAndMaterialLive(ftSaleshBean: Long, fmaterialBean: Int): LiveData<List<FtSalesdItemsEntity>>

}