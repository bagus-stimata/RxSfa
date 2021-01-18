package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialPic

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialPicDao {
    /**
     * @param fMaterialPic
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

}