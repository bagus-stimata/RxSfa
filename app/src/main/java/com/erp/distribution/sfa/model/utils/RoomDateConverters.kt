package com.erp.distribution.sfa.model.utils

import androidx.room.TypeConverter
import com.erp.distribution.sfa.model.modelenum.*
import java.util.*

object RoomDateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toEnum3Level(value: String?): Enum3Level? {
        return if (value == null) null else Enum3Level.valueOf(value)
    }

    @TypeConverter
    fun fromEnum3Level(value: Enum3Level?): Int? {
        return value?.intCode
    }

    @TypeConverter
    fun toEnumAccCoaType(value: String?): EnumAccCoaType? {
        return if (value == null) null else EnumAccCoaType.valueOf(value)
    }

    @TypeConverter
    fun fromEnumAccCoaType(value: EnumAccCoaType?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumAccTransactionType(value: String?): EnumAccTransactionType? {
        return if (value == null) null else EnumAccTransactionType.valueOf(value)
    }

    @TypeConverter
    fun fromEnumAccTransactionType(value: EnumAccTransactionType?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumCurrency(value: String?): EnumCurrency? {
        return if (value == null) null else EnumCurrency.valueOf(value)
    }

    @TypeConverter
    fun fromEnumCurrency(value: EnumCurrency?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumFormatFaktur(value: String?): EnumFormatFaktur? {
        return if (value == null) null else EnumFormatFaktur.valueOf(value)
    }

    @TypeConverter
    fun fromEnumFormatFaktur(value: EnumFormatFaktur?): String? {
        return value?.strCode
    }

    @TypeConverter
    fun toEnumJenisNomorUrut(value: String?): EnumJenisNomorUrut? {
        return if (value == null) null else EnumJenisNomorUrut.valueOf(value)
    }

    @TypeConverter
    fun fromEnumJenisNomorUrut(value: EnumJenisNomorUrut?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumKeyGoogleAPI(value: String?): EnumKeyGoogleAPI? {
        return if (value == null) null else EnumKeyGoogleAPI.valueOf(value)
    }

    @TypeConverter
    fun fromEnumKeyGoogleAPI(value: EnumKeyGoogleAPI?): String? {
        return value?.strCode
    }

    @TypeConverter
    fun toEnumLunasBelum(value: String?): EnumLunasBelum? {
        return if (value == null) null else EnumLunasBelum.valueOf(value)
    }

    @TypeConverter
    fun fromEnumLunasBelum(value: EnumLunasBelum?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumMaterialType(value: String?): EnumMaterialType? {
        return if (value == null) null else EnumMaterialType.valueOf(value)
    }

    @TypeConverter
    fun fromEnumMaterialType(value: EnumMaterialType?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumMaxRowsShow(value: String?): EnumMaxRowsShow? {
        return if (value == null) null else EnumMaxRowsShow.valueOf(value)
    }

    @TypeConverter
    fun fromEnumMaxRowsShow(value: EnumMaxRowsShow?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumMonth(value: String?): EnumMonth? {
        return if (value == null) null else EnumMonth.valueOf(value)
    }

    @TypeConverter
    fun fromEnumMonth(value: EnumMonth?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumOrganizationLevel(value: String?): EnumOrganizationLevel? {
        return if (value == null) null else EnumOrganizationLevel.valueOf(value)
    }

    @TypeConverter
    fun fromEnumOrganizationLevel(value: EnumOrganizationLevel?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumPromoDiscFgMethod(value: String?): EnumPromoDiscFgMethod? {
        return if (value == null) null else EnumPromoDiscFgMethod.valueOf(value)
    }

    @TypeConverter
    fun fromEnumPromoDiscFgMethod(value: EnumPromoDiscFgMethod?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumReligion(value: Int?): EnumReligion? {
        return if (value == null) null else EnumReligion.ISL
    }

    @TypeConverter
    fun fromEnumReligion(value: EnumReligion?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumRequestStatus(value: Int?): EnumRequestStatus? {
        return if (value == null) null else EnumRequestStatus.OPEN
    }

    @TypeConverter
    fun fromEnumRequestStatus(value: EnumRequestStatus?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumSalesType(value: String?): EnumSalesType? {
        return if (value == null) null else EnumSalesType.valueOf(value)
    }

    @TypeConverter
    fun fromEnumSalesType(value: EnumSalesType?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumStatusGiro(value: String?): EnumStatusGiro? {
        return if (value == null) null else EnumStatusGiro.valueOf(value)
    }

    @TypeConverter
    fun fromEnumStatusGiro(value: EnumStatusGiro?): String? {
        return value?.strCode
    }

    @TypeConverter
    fun toEnumStatusOperasiForm(value: String?): EnumStatusOperasiForm? {
        return if (value == null) null else EnumStatusOperasiForm.valueOf(value)
    }

    @TypeConverter
    fun fromEnumStatusOperasiForm(value: EnumStatusOperasiForm?): String? {
        return value?.strCode
    }

    @TypeConverter
    fun toEnumStatusPengiriman(value: Int?): EnumStatusPengiriman? {
        return if (value == null) null else EnumStatusPengiriman.NOTA_OPEN
    }

    @TypeConverter
    fun fromEnumStatusPengiriman(value: EnumStatusPengiriman?): Int? {
        return value?.intCode
    }

    @TypeConverter
    fun toEnumStatusProteksi(value: String?): EnumStatusProteksi? {
        return if (value == null) null else EnumStatusProteksi.valueOf(value)
    }

    @TypeConverter
    fun fromEnumStatusProteksi(value: EnumStatusProteksi?): Int? {
        return value?.intCode
    }

    @TypeConverter
    fun toEnumStatusService(value: String?): EnumStatusService? {
        return if (value == null) null else EnumStatusService.valueOf(value)
    }

    @TypeConverter
    fun fromEnumStatusService(value: EnumStatusService?): Int? {
        return value?.intCode
    }

    @TypeConverter
    fun toEnumStatusUpload(value: Int?): EnumStatusUpload? {
        return if (value == null) null else EnumStatusUpload.OPEN
    }

    @TypeConverter
    fun fromEnumStatusUpload(value: EnumStatusUpload?): Int? {
        return value?.intCode
    }

    @TypeConverter
    fun toEnumTipeFakturBeli(value: String?): EnumTipeFakturBeli? {
        return if (value == null) null else EnumTipeFakturBeli.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeFakturBeli(value: EnumTipeFakturBeli?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumTipeFakturJual(value: String?): EnumTipeFakturJual? {
        return if (value == null) null else EnumTipeFakturJual.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeFakturJual(value: EnumTipeFakturJual?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumTipeImporFromFile(value: String?): EnumTipeImporFromFile? {
        return if (value == null) null else EnumTipeImporFromFile.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeImporFromFile(value: EnumTipeImporFromFile?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumTipeJob(value: String?): EnumTipeJob? {
        return if (value == null) null else EnumTipeJob.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeJob(value: EnumTipeJob?): Int? {
        return value?.intCode
    }

    @TypeConverter
    fun toEnumTipePajakCustomer(value: Int?): EnumTipePajakCustomer? {
        return if (value == null) null else EnumTipePajakCustomer.REG_01
    }

    @TypeConverter
    fun fromEnumTipePajakCustomer(value: EnumTipePajakCustomer?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumTipeSettlement(value: String?): EnumTipeSettlement? {
        return if (value == null) null else EnumTipeSettlement.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeSettlement(value: EnumTipeSettlement?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumTipeStockOpname(value: String?): EnumTipeStockOpname? {
        return if (value == null) null else EnumTipeStockOpname.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeStockOpname(value: EnumTipeStockOpname?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumTipeStockTransfer(value: Int?): EnumTipeStockTransfer? {
        return if (value == null) null else EnumTipeStockTransfer.MUTASI_STD_TO_STD
    }

    @TypeConverter
    fun fromEnumTipeStockTransfer(value: EnumTipeStockTransfer?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumTipeSuratJalan(value: String?): EnumTipeSuratJalan? {
        return if (value == null) null else EnumTipeSuratJalan.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeSuratJalan(value: EnumTipeSuratJalan?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumTipeUserDetil(value: String?): EnumTipeUserDetil? {
        return if (value == null) null else EnumTipeUserDetil.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeUserDetil(value: EnumTipeUserDetil?): Int? {
        return value?.intId
    }

    @TypeConverter
    fun toEnumTipeWarehouse(value: String?): EnumTipeWarehouse? {
        return if (value == null) null else EnumTipeWarehouse.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTipeWarehouse(value: EnumTipeWarehouse?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumTunaiKredit(value: String?): EnumTunaiKredit? {
        return if (value == null) null else EnumTunaiKredit.valueOf(value)
    }

    @TypeConverter
    fun fromEnumTunaiKredit(value: EnumTunaiKredit?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumUom(value: String?): EnumUom? {
        return if (value == null) null else EnumUom.valueOf(value)
    }

    @TypeConverter
    fun fromEnumUom(value: EnumUom?): String? {
        return value?.stringId
    }

    @TypeConverter
    fun toEnumUserOtorizeType(value: String?): EnumUserOtorizeType? {
        return if (value == null) null else EnumUserOtorizeType.valueOf(value)
    }

    @TypeConverter
    fun fromEnumUserOtorizeType(value: EnumUserOtorizeType?): String? {
        return value?.strCode
    }
}