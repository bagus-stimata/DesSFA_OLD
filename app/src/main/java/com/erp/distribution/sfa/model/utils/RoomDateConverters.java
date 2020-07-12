package com.erp.distribution.sfa.model.utils;

import androidx.room.TypeConverter;

import com.erp.distribution.sfa.model.modelenum.Enum3Level;
import com.erp.distribution.sfa.model.modelenum.EnumAccCoaType;
import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType;
import com.erp.distribution.sfa.model.modelenum.EnumCurrency;
import com.erp.distribution.sfa.model.modelenum.EnumFormatFaktur;
import com.erp.distribution.sfa.model.modelenum.EnumJenisNomorUrut;
import com.erp.distribution.sfa.model.modelenum.EnumKeyGoogleAPI;
import com.erp.distribution.sfa.model.modelenum.EnumLunasBelum;
import com.erp.distribution.sfa.model.modelenum.EnumMaterialType;
import com.erp.distribution.sfa.model.modelenum.EnumMaxRowsShow;
import com.erp.distribution.sfa.model.modelenum.EnumMonth;
import com.erp.distribution.sfa.model.modelenum.EnumOrganizationLevel;
import com.erp.distribution.sfa.model.modelenum.EnumPromoDiscFgMethod;
import com.erp.distribution.sfa.model.modelenum.EnumReligion;
import com.erp.distribution.sfa.model.modelenum.EnumRequestStatus;
import com.erp.distribution.sfa.model.modelenum.EnumSalesType;
import com.erp.distribution.sfa.model.modelenum.EnumStatusGiro;
import com.erp.distribution.sfa.model.modelenum.EnumStatusOperasiForm;
import com.erp.distribution.sfa.model.modelenum.EnumStatusPengiriman;
import com.erp.distribution.sfa.model.modelenum.EnumStatusProteksi;
import com.erp.distribution.sfa.model.modelenum.EnumStatusService;
import com.erp.distribution.sfa.model.modelenum.EnumStatusUpload;
import com.erp.distribution.sfa.model.modelenum.EnumTipeFakturBeli;
import com.erp.distribution.sfa.model.modelenum.EnumTipeFakturJual;
import com.erp.distribution.sfa.model.modelenum.EnumTipeImporFromFile;
import com.erp.distribution.sfa.model.modelenum.EnumTipeJob;
import com.erp.distribution.sfa.model.modelenum.EnumTipePajakCustomer;
import com.erp.distribution.sfa.model.modelenum.EnumTipeSettlement;
import com.erp.distribution.sfa.model.modelenum.EnumTipeStockOpname;
import com.erp.distribution.sfa.model.modelenum.EnumTipeStockTransfer;
import com.erp.distribution.sfa.model.modelenum.EnumTipeSuratJalan;
import com.erp.distribution.sfa.model.modelenum.EnumTipeUserDetil;
import com.erp.distribution.sfa.model.modelenum.EnumTipeWarehouse;
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit;
import com.erp.distribution.sfa.model.modelenum.EnumUom;
import com.erp.distribution.sfa.model.modelenum.EnumUserOtorizeType;

import java.util.Date;

import static com.erp.distribution.sfa.model.modelenum.EnumCurrency.*;

public class RoomDateConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Enum3Level toEnum3Level(String value) {
        return value == null ? null : Enum3Level.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnum3Level(Enum3Level value) {
        return value == null ? null : value.getIntCode();
    }

    @TypeConverter
    public static EnumAccCoaType toEnumAccCoaType(String value) {
        return value == null ? null : EnumAccCoaType.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumAccCoaType(EnumAccCoaType value) {
        return value == null ? null : value.getStringId();
    }





    @TypeConverter
    public static EnumAccTransactionType toEnumAccTransactionType(String value) {
        return value == null ? null : EnumAccTransactionType.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumAccTransactionType(EnumAccTransactionType value) {
        return value == null ? null : value.getStringId();
    }

    @TypeConverter
    public static EnumCurrency toEnumCurrency(String value) {
        return value == null ? null : EnumCurrency.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumCurrency(EnumCurrency value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumFormatFaktur toEnumFormatFaktur(String value) {
        return value == null ? null : EnumFormatFaktur.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumFormatFaktur(EnumFormatFaktur value) {
        return value == null ? null : value.getStrCode();
    }
    @TypeConverter
    public static EnumJenisNomorUrut toEnumJenisNomorUrut(String value) {
        return value == null ? null : EnumJenisNomorUrut.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumJenisNomorUrut(EnumJenisNomorUrut value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumKeyGoogleAPI toEnumKeyGoogleAPI(String value) {
        return value == null ? null : EnumKeyGoogleAPI.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumKeyGoogleAPI(EnumKeyGoogleAPI value) {
        return value == null ? null : value.getStrCode();
    }
    @TypeConverter
    public static EnumLunasBelum toEnumLunasBelum(String value) {
        return value == null ? null : EnumLunasBelum.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumLunasBelum(EnumLunasBelum value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumMaterialType toEnumMaterialType(String value) {
        return value == null ? null : EnumMaterialType.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumMaterialType(EnumMaterialType value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumMaxRowsShow toEnumMaxRowsShow(String value) {
        return value == null ? null : EnumMaxRowsShow.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumMaxRowsShow(EnumMaxRowsShow value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumMonth toEnumMonth(String value) {
        return value == null ? null : EnumMonth.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnumMonth(EnumMonth value) {
        return value == null ? null : value.getIntId();
    }
    @TypeConverter
    public static EnumOrganizationLevel toEnumOrganizationLevel(String value) {
        return value == null ? null : EnumOrganizationLevel.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumOrganizationLevel(EnumOrganizationLevel value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumPromoDiscFgMethod toEnumPromoDiscFgMethod(String value) {
        return value == null ? null : EnumPromoDiscFgMethod.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumPromoDiscFgMethod(EnumPromoDiscFgMethod value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumReligion toEnumReligion(Integer value) {
        return value == null ? null : EnumReligion.ISL;
    }
    @TypeConverter
    public static Integer fromEnumReligion(EnumReligion value) {
        return value == null ? null : value.getIntId();
    }
    @TypeConverter
    public static EnumRequestStatus toEnumRequestStatus(Integer value) {
        return value == null ? null : EnumRequestStatus.OPEN;
    }
    @TypeConverter
    public static Integer fromEnumRequestStatus(EnumRequestStatus value) {
        return value == null ? null : value.getIntId();
    }
    @TypeConverter
    public static EnumSalesType toEnumSalesType(String value) {
        return value == null ? null : EnumSalesType.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumSalesType(EnumSalesType value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumStatusGiro toEnumStatusGiro(String value) {
        return value == null ? null : EnumStatusGiro.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumStatusGiro(EnumStatusGiro value) {
        return value == null ? null : value.getStrCode();
    }
    @TypeConverter
    public static EnumStatusOperasiForm toEnumStatusOperasiForm(String value) {
        return value == null ? null : EnumStatusOperasiForm.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumStatusOperasiForm(EnumStatusOperasiForm value) {
        return value == null ? null : value.getStrCode();
    }
    @TypeConverter
    public static EnumStatusPengiriman toEnumStatusPengiriman(Integer value) {
        return value == null ? null : EnumStatusPengiriman.NOTA_OPEN;
    }
    @TypeConverter
    public static Integer fromEnumStatusPengiriman(EnumStatusPengiriman value) {
        return value == null ? null : value.getIntCode();
    }
    @TypeConverter
    public static EnumStatusProteksi toEnumStatusProteksi(String value) {
        return value == null ? null : EnumStatusProteksi.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnumStatusProteksi(EnumStatusProteksi value) {
        return value == null ? null : value.getIntCode();
    }
    @TypeConverter
    public static EnumStatusService toEnumStatusService(String value) {
        return value == null ? null : EnumStatusService.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnumStatusService(EnumStatusService value) {
        return value == null ? null : value.getIntCode();
    }
    @TypeConverter
    public static EnumStatusUpload toEnumStatusUpload(Integer value) {
        return value == null ? null : EnumStatusUpload.OPEN;
    }
    @TypeConverter
    public static Integer fromEnumStatusUpload(EnumStatusUpload value) {
        return value == null ? null : value.getIntCode();
    }
    @TypeConverter
    public static EnumTipeFakturBeli toEnumTipeFakturBeli(String value) {
        return value == null ? null : EnumTipeFakturBeli.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumTipeFakturBeli(EnumTipeFakturBeli value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumTipeFakturJual toEnumTipeFakturJual(String value) {
        return value == null ? null : EnumTipeFakturJual.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumTipeFakturJual(EnumTipeFakturJual value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumTipeImporFromFile toEnumTipeImporFromFile(String value) {
        return value == null ? null : EnumTipeImporFromFile.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumTipeImporFromFile(EnumTipeImporFromFile value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumTipeJob toEnumTipeJob(String value) {
        return value == null ? null : EnumTipeJob.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnumTipeJob(EnumTipeJob value) {
        return value == null ? null : value.getIntCode();
    }

    @TypeConverter
    public static EnumTipePajakCustomer toEnumTipePajakCustomer(Integer value) {
        return value == null ? null : EnumTipePajakCustomer.REG_01;
    }
    @TypeConverter
    public static Integer fromEnumTipePajakCustomer(EnumTipePajakCustomer value) {
        return value == null ? null : value.getIntId();
    }

    @TypeConverter
    public static EnumTipeSettlement toEnumTipeSettlement(String value) {
        return value == null ? null : EnumTipeSettlement.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnumTipeSettlement(EnumTipeSettlement value) {
        return value == null ? null : value.getIntId();
    }
    @TypeConverter
    public static EnumTipeStockOpname toEnumTipeStockOpname(String value) {
        return value == null ? null : EnumTipeStockOpname.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumTipeStockOpname(EnumTipeStockOpname value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumTipeStockTransfer toEnumTipeStockTransfer(Integer value) {
        return value == null ? null : EnumTipeStockTransfer.MUTASI_STD_TO_STD;
    }
    @TypeConverter
    public static Integer fromEnumTipeStockTransfer(EnumTipeStockTransfer value) {
        return value == null ? null : value.getIntId();
    }
    @TypeConverter
    public static EnumTipeSuratJalan toEnumTipeSuratJalan(String value) {
        return value == null ? null : EnumTipeSuratJalan.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnumTipeSuratJalan(EnumTipeSuratJalan value) {
        return value == null ? null : value.getIntId();
    }
    @TypeConverter
    public static EnumTipeUserDetil toEnumTipeUserDetil(String value) {
        return value == null ? null : EnumTipeUserDetil.valueOf(value);
    }
    @TypeConverter
    public static Integer fromEnumTipeUserDetil(EnumTipeUserDetil value) {
        return value == null ? null : value.getIntId();
    }
    @TypeConverter
    public static EnumTipeWarehouse toEnumTipeWarehouse(String value) {
        return value == null ? null : EnumTipeWarehouse.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumTipeWarehouse(EnumTipeWarehouse value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumTunaiKredit toEnumTunaiKredit(String value) {
        return value == null ? null : EnumTunaiKredit.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumTunaiKredit(EnumTunaiKredit value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumUom toEnumUom(String value) {
        return value == null ? null : EnumUom.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumUom(EnumUom value) {
        return value == null ? null : value.getStringId();
    }
    @TypeConverter
    public static EnumUserOtorizeType toEnumUserOtorizeType(String value) {
        return value == null ? null : EnumUserOtorizeType.valueOf(value);
    }
    @TypeConverter
    public static String fromEnumUserOtorizeType(EnumUserOtorizeType value) {
        return value == null ? null : value.getStrCode();
    }





}
