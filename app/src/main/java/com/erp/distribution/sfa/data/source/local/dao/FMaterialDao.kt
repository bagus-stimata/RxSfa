package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialWithFDivisionAndVendorAndGroup
import kotlinx.coroutines.flow.Flow

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialDao {
    /**
     * @param fMaterialEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialEntity: FMaterialEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialEntity: List<FMaterialEntity>)
    
    @Update
    fun update(fMaterialEntity: FMaterialEntity)

    @Delete
    fun delete(fMaterialEntity: FMaterialEntity)

    @Query("DELETE FROM fMaterial")
    fun deleteAllFMaterial()


    fun getAllFMaterialFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean?): Flow<List<FMaterialWithFDivisionAndVendorAndGroup>> =
            when (sortOrder) {
                SortOrder.BY_KODE -> {
                    getAllFMaterialSortedByIDFLow(query)
                }
                SortOrder.BY_NAME -> {
                    getAllFMaterialSortedByNameFLow(query)
                }
                else -> getAllFMaterialFLow()
            }

    @Query("SELECT * FROM fMaterial WHERE (selected = :hideSelected OR selected = 0 OR selected = NULL) AND pname LIKE '%' || :searchQuery || '%' ORDER BY pname ")
    fun getAllFMaterialSortedByName(searchQuery: String, hideSelected: Boolean): Flow<List<FMaterialEntity>>

    @Query("SELECT * FROM fMaterial WHERE (selected = :hideSelected OR selected = 0 OR selected = NULL) AND pname LIKE '%' || :searchQuery || '%'  ORDER BY created ")
    fun getAllFMaterialSortedByDateCreated(searchQuery: String, hideSelected: Boolean): Flow<List<FMaterialEntity>>

//    @Query("SELECT * FROM fMaterial WHERE  pname LIKE '%' || :searchQuery || '%'  OR pcode LIKE '%' || :searchQuery || '%' ORDER BY pname ")
//    fun getAllFMaterialSortedByNameFLow(searchQuery: String): Flow<List<FMaterialEntity>>
//    @Query("SELECT * FROM fMaterial WHERE  pname LIKE '%' || :searchQuery || '%'  OR pcode LIKE '%' || :searchQuery || '%'  ORDER BY pcode ")
//    fun getAllFMaterialSortedByIDFLow(searchQuery: String): Flow<List<FMaterialEntity>>
//    @Query("SELECT * FROM fMaterial ")
//    fun getAllFMaterialFLow(): Flow<List<FMaterialEntity>>

    @Transaction
    @Query("SELECT * FROM fMaterial WHERE  pname LIKE '%' || :searchQuery || '%'  OR pcode LIKE '%' || :searchQuery || '%' ORDER BY pname ")
    fun getAllFMaterialSortedByNameFLow(searchQuery: String): Flow<List<FMaterialWithFDivisionAndVendorAndGroup>>
    @Transaction
    @Query("SELECT * FROM fMaterial WHERE  pname LIKE '%' || :searchQuery || '%'  OR pcode LIKE '%' || :searchQuery || '%'  ORDER BY pcode ")
    fun getAllFMaterialSortedByIDFLow(searchQuery: String): Flow<List<FMaterialWithFDivisionAndVendorAndGroup>>
    @Transaction
    @Query("SELECT * FROM fMaterial ")
    fun getAllFMaterialFLow(): Flow<List<FMaterialWithFDivisionAndVendorAndGroup>>


    @Query("SELECT * FROM fMaterial WHERE id = :id ")
    fun getAllById(id: Int): FMaterialEntity
    @Query("SELECT * FROM fMaterial WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialEntity>


    @get:Query("SELECT * FROM fMaterial ")
    val getAllFMaterialEntity: List<FMaterialEntity>
    @get:Query("SELECT * FROM fMaterial ")
    val getAllFMaterialEntityLive: LiveData<List<FMaterialEntity>>

    @Transaction
    @Query("SELECT * FROM fMaterial ")
    fun getAllFMateriaWithDivisionAndVendorAndGroupLive(): LiveData<List<FMaterialWithFDivisionAndVendorAndGroup>>

    @Query("SELECT * FROM fMaterial WHERE pcode LIKE :pcode ")
    fun getAllFMaterialByKode(pcode: String): List<FMaterialEntity>
    @Query("SELECT * FROM fMaterial WHERE pcode LIKE :pcode ")
    fun getAllFMaterialByKodeLive(pcode: String): LiveData<List<FMaterialEntity>>


    @Query("SELECT * FROM fMaterial WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterialEntity>

    @Query("SELECT * FROM fMaterial WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FMaterialEntity>>
    
}