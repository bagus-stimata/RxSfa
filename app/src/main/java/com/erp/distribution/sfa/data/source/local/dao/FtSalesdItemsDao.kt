package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FtSalesdItems
import com.erp.distribution.sfa.data.source.entity.FtSalesh
import kotlinx.coroutines.flow.Flow

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtSalesdItemsDao {
    /**
     * @param ftSalesdItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ftSalesdItems: FtSalesdItems)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtSalesdItems: List<FtSalesdItems>)


    @Update
    fun update(ftSalesdItems: FtSalesdItems)

    @Delete
    fun delete(ftSalesdItems: FtSalesdItems)

    @Query("DELETE FROM ftSalesdItems")
    fun deleteAllFtSalesdItems()

    @Query("SELECT * FROM ftSalesdItems WHERE id = :id ")
    fun getAllById(id: Long): FtSalesdItems
    @Query("SELECT * FROM ftSalesdItems WHERE id = :id ")
    fun getAllByIdLive(id: Long): LiveData<FtSalesdItems>



    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSalesBean AND  fmaterialBean = :fmaterialBean ")
    fun getAllByFtSaleshAndMaterialFlow(ftSalesBean: Long, fmaterialBean: Int): Flow<List<FtSalesdItems>>

    @Query("SELECT * FROM ftSalesdItems ")
    fun getAllFtSaleshFLow(): Flow<List<FtSalesdItems>>


    @get:Query("SELECT * FROM ftSalesdItems ")
    val getAllFtSalesdItems: List<FtSalesdItems>
    @get:Query("SELECT * FROM ftSalesdItems ")
    val getAllFtSalesdItemsLive: LiveData<List<FtSalesdItems>>

    @Query("SELECT * FROM ftSalesdItems WHERE fmaterialBean = :materialId ")
    fun getAllFtSalesdItemsByMaterial(materialId: Int): List<FtSalesdItems>
    @Query("SELECT * FROM ftSalesdItems WHERE fmaterialBean = :materialId ")
    fun getAllFtSalesdItemsByMaterialLive(materialId: Int): LiveData<List<FtSalesdItems>>


    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshId ")
    fun getAllByFtSalesh(ftSaleshId: Long): List<FtSalesdItems>
    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshId ")
    fun getAllByFtSaleshLive(ftSaleshId: Long): LiveData<List<FtSalesdItems>>

    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshBean AND  fmaterialBean = :fmaterialBean ")
    fun getAllByFtSaleshAndMaterial(ftSaleshBean: Long, fmaterialBean: Int): List<FtSalesdItems>
    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :ftSaleshBean AND  fmaterialBean = :fmaterialBean ")
    fun getAllByFtSaleshAndMaterialLive(ftSaleshBean: Long, fmaterialBean: Int): LiveData<List<FtSalesdItems>>

}