package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FSalesman

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSalesmanDao {
    /**
     * @param fSalesman
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fSalesman: FSalesman)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFSalesman: List<FSalesman>)


    @Update
    fun update(fSalesman: FSalesman)

    @Delete
    fun delete(fSalesman: FSalesman)

    @Query("DELETE FROM fSalesman")
    fun deleteAllFSalesman()

    @Query("SELECT * FROM fSalesman WHERE id = :id ")
    fun getAllById(id: Int): FSalesman
    @Query("SELECT * FROM fSalesman WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FSalesman>


    @get:Query("SELECT * FROM fSalesman ")
    val getAllFSalesman: List<FSalesman>
    @get:Query("SELECT * FROM fSalesman ")
    val getAllFSalesmanLive: LiveData<List<FSalesman>>

    @Query("SELECT * FROM fSalesman WHERE spcode LIKE :spcode ")
    fun getAllFSalesmanByKode(spcode: String): List<FSalesman>
    @Query("SELECT * FROM fSalesman WHERE spcode LIKE :spcode ")
    fun getAllFSalesmanByKodeLive(spcode: String): LiveData<List<FSalesman>>


    @Query("SELECT * FROM fSalesman WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FSalesman>

    @Query("SELECT * FROM fSalesman WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FSalesman>>
}