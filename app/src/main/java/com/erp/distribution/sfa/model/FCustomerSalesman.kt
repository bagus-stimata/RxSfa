package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "fcustomer_salesman")
@Entity(tableName = "FCustomerSalesman")
class FCustomerSalesman : Serializable {
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean")
    //	private FCustomer fcustomerBean;
    var fcustomerBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fsalesmanBean")
    //	private FSalesman fsalesmanBean;
    var fsalesmanBean = 0

    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0
    var noUrut = 0
    var harikunjungan = 0
    var pekankunjungan = 0

    companion object {
        /**
         *
         */
        const val serialVersionUID = 1L
    }
}