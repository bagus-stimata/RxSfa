package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FWarehouse

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FWarehouseDao {
    /**
     * @param fWarehouse
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

}