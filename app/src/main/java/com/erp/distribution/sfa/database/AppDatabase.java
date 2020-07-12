package com.erp.distribution.sfa.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.erp.distribution.sfa.dao.FAreaDao;
import com.erp.distribution.sfa.dao.FCompanyDao;
import com.erp.distribution.sfa.dao.FCustomerDao;
import com.erp.distribution.sfa.dao.FCustomerGroupDao;
import com.erp.distribution.sfa.dao.FCustomerPicDao;
import com.erp.distribution.sfa.dao.FCustomerSalesmanDao;
import com.erp.distribution.sfa.dao.FDistributionChannelDao;
import com.erp.distribution.sfa.dao.FDivisionDao;
import com.erp.distribution.sfa.dao.FExpedisiDao;
import com.erp.distribution.sfa.dao.FGiroDao;
import com.erp.distribution.sfa.dao.FMaterialDao;
import com.erp.distribution.sfa.dao.FMaterialGroup1Dao;
import com.erp.distribution.sfa.dao.FMaterialGroup2Dao;
import com.erp.distribution.sfa.dao.FMaterialGroup3Dao;
import com.erp.distribution.sfa.dao.FMaterialPicDao;
import com.erp.distribution.sfa.dao.FMaterialSalesBrandDao;
import com.erp.distribution.sfa.dao.FParamDiskonItemDao;
import com.erp.distribution.sfa.dao.FParamDiskonItemVendorDao;
import com.erp.distribution.sfa.dao.FParamDiskonNotaDao;
import com.erp.distribution.sfa.dao.FPromotionRulesdPaymentsDao;
import com.erp.distribution.sfa.dao.FPromotionRulesdValidCustsDao;
import com.erp.distribution.sfa.dao.FPromotionRulesdValidProductsDao;
import com.erp.distribution.sfa.dao.FPromotionRuleshDao;
import com.erp.distribution.sfa.dao.FRegionDao;
import com.erp.distribution.sfa.dao.FSalesmanDao;
import com.erp.distribution.sfa.dao.FStockDao;
import com.erp.distribution.sfa.dao.FUangMukaDao;
import com.erp.distribution.sfa.dao.FVendorDao;
import com.erp.distribution.sfa.dao.FWarehouseDao;
import com.erp.distribution.sfa.dao.FtStockTransferdItemsDao;
import com.erp.distribution.sfa.dao.FSubAreaDao;
import com.erp.distribution.sfa.dao.FTaxDao;
import com.erp.distribution.sfa.dao.FtApPaymentdDao;
import com.erp.distribution.sfa.dao.FtApPaymenthDao;
import com.erp.distribution.sfa.dao.FtArPaymentdDao;
import com.erp.distribution.sfa.dao.FtArPaymenthDao;
import com.erp.distribution.sfa.dao.FtOpnamedItemsDao;
import com.erp.distribution.sfa.dao.FtOpnamehDao;
import com.erp.distribution.sfa.dao.FtPriceAltdItemsDao;
import com.erp.distribution.sfa.dao.FtPriceAlthDao;
import com.erp.distribution.sfa.dao.FtPricedItemsDao;
import com.erp.distribution.sfa.dao.FtPricehDao;
import com.erp.distribution.sfa.dao.FtPurchasedItemsDao;
import com.erp.distribution.sfa.dao.FtPurchasehDao;
import com.erp.distribution.sfa.dao.FtSalesdItemsDao;
import com.erp.distribution.sfa.dao.FtSaleshDao;
import com.erp.distribution.sfa.dao.FtStockTransferhDao;
import com.erp.distribution.sfa.dao.SysvarDao;
import com.erp.distribution.sfa.model.FArea;
import com.erp.distribution.sfa.model.FCompany;
import com.erp.distribution.sfa.model.FCustomer;
import com.erp.distribution.sfa.model.FCustomerGroup;
import com.erp.distribution.sfa.model.FCustomerPic;
import com.erp.distribution.sfa.model.FCustomerSalesman;
import com.erp.distribution.sfa.model.FDistributionChannel;
import com.erp.distribution.sfa.model.FDivision;
import com.erp.distribution.sfa.model.FExpedisi;
import com.erp.distribution.sfa.model.FGiro;
import com.erp.distribution.sfa.model.FMaterial;
import com.erp.distribution.sfa.model.FMaterialGroup1;
import com.erp.distribution.sfa.model.FMaterialGroup2;
import com.erp.distribution.sfa.model.FMaterialGroup3;
import com.erp.distribution.sfa.model.FMaterialPic;
import com.erp.distribution.sfa.model.FMaterialSalesBrand;
import com.erp.distribution.sfa.model.FParamDiskonItem;
import com.erp.distribution.sfa.model.FParamDiskonItemVendor;
import com.erp.distribution.sfa.model.FParamDiskonNota;
import com.erp.distribution.sfa.model.FPromotionRulesdPayments;
import com.erp.distribution.sfa.model.FPromotionRulesdValidCusts;
import com.erp.distribution.sfa.model.FPromotionRulesdValidProducts;
import com.erp.distribution.sfa.model.FPromotionRulesh;
import com.erp.distribution.sfa.model.FRegion;
import com.erp.distribution.sfa.model.FSalesman;
import com.erp.distribution.sfa.model.FStock;
import com.erp.distribution.sfa.model.FSubArea;
import com.erp.distribution.sfa.model.FTax;
import com.erp.distribution.sfa.model.FUangMuka;
import com.erp.distribution.sfa.model.FVendor;
import com.erp.distribution.sfa.model.FWarehouse;
import com.erp.distribution.sfa.model.FtApPaymentd;
import com.erp.distribution.sfa.model.FtApPaymenth;
import com.erp.distribution.sfa.model.FtArPaymentd;
import com.erp.distribution.sfa.model.FtArPaymenth;
import com.erp.distribution.sfa.model.FtOpnamedItems;
import com.erp.distribution.sfa.model.FtOpnameh;
import com.erp.distribution.sfa.model.FtPriceAltdItems;
import com.erp.distribution.sfa.model.FtPriceAlth;
import com.erp.distribution.sfa.model.FtPricedItems;
import com.erp.distribution.sfa.model.FtPriceh;
import com.erp.distribution.sfa.model.FtPurchasedItems;
import com.erp.distribution.sfa.model.FtPurchaseh;
import com.erp.distribution.sfa.model.FtSalesdItems;
import com.erp.distribution.sfa.model.FtSalesh;
import com.erp.distribution.sfa.model.FtStockTransferdItems;
import com.erp.distribution.sfa.model.FtStockTransferh;
import com.erp.distribution.sfa.model.Sysvar;
import com.erp.distribution.sfa.model.utils.RoomDateConverters;
import com.erp.distribution.sfa.security_model.FUser;
import com.erp.distribution.sfa.security_model.FUserRoles;
import com.erp.distribution.sfa.security_repository.FUserDao;
import com.erp.distribution.sfa.security_repository.FUserRolesDao;


@Database(entities =
            { FUser.class, FUserRoles.class,
                    FArea.class, FCompany.class, FCustomer.class, FCustomerGroup.class, FCustomerPic.class, FCustomerSalesman.class,
                    FDistributionChannel.class, FDivision.class, FGiro.class, FMaterial.class, FMaterialGroup1.class,
                    FMaterialGroup2.class, FMaterialGroup3.class, FMaterialPic.class, FMaterialSalesBrand.class,
                    FParamDiskonItem.class, FParamDiskonItemVendor.class, FParamDiskonNota.class, FPromotionRulesdPayments.class,
                    FPromotionRulesdValidCusts.class, FPromotionRulesdValidProducts.class, FPromotionRulesh.class,
                    FRegion.class, FSalesman.class, FStock.class, FSubArea.class, FtApPaymentd.class, FtApPaymenth.class,
                    FtArPaymentd.class, FtArPaymenth.class, FTax.class, FtOpnamedItems.class, FtOpnameh.class,
                    FtPriceAltdItems.class, FtPriceAlth.class, FtPricedItems.class, FtPriceh.class, FtPurchasedItems.class, FtPurchaseh.class,
                    FtSalesdItems.class, FtSalesh.class, FtStockTransferdItems.class, FtStockTransferh.class, FUangMuka.class,
                    FVendor.class, FWarehouse.class, Sysvar.class, FExpedisi.class},
            version = 8)
@TypeConverters({RoomDateConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    /**
     * abstract
     * Mekanisme untuk mengkoneksikan antara Kelas Database dengan Kelas Dao
     */
    public abstract FUserDao fUserDao();
    public abstract FUserRolesDao fUserRolesDao();

    public abstract FAreaDao fAreaDao();
    public abstract FCompanyDao fCompanyDao();
    public abstract FCustomerDao fCustomerDao();
    public abstract FCustomerGroupDao fCustomerGroupDao();
    public abstract FCustomerPicDao fCustomerPicDao();
    public abstract FCustomerSalesmanDao fCustomerSalesmanDao();
    public abstract FDistributionChannelDao fDistributionChannelDao();
    public  abstract FDivisionDao fDivisionDao();
    public abstract FExpedisiDao fExpedisiDao();
    public abstract FGiroDao fGiroDao();
    public abstract FMaterialDao fMaterialDao();
    public abstract FMaterialGroup1Dao fMaterialGroup1Dao();
    public abstract FMaterialGroup2Dao fMaterialGroup2Dao();
    public abstract FMaterialGroup3Dao fMaterialGroup3Dao();
    public abstract FMaterialPicDao fMaterialPicDao();
    public abstract FMaterialSalesBrandDao fMaterialSalesBrandDao();
    public abstract FParamDiskonItemDao fParamDiskonItemDao();
    public abstract FParamDiskonItemVendorDao fParamDiskonItemVendorDao();
    public abstract FParamDiskonNotaDao fParamDiskonNotaDao();
    public abstract FPromotionRulesdPaymentsDao fPromotionRulesdPaymentsDao();
    public abstract FPromotionRulesdValidCustsDao fPromotionRulesdValidCustsDao();
    public abstract FPromotionRulesdValidProductsDao fPromotionRulesdValidProductsDao();
    public abstract FPromotionRuleshDao fPromotionRuleshDao();
    public abstract FRegionDao fRegionDao();
    public abstract FSalesmanDao fSalesmanDao();
    public abstract FStockDao fStockDao();
    public abstract FSubAreaDao fSubAreaDao();
    public abstract FtApPaymentdDao ftApPaymentdDao();
    public abstract FtApPaymenthDao ftApPaymenthDao();
    public abstract FtArPaymentdDao ftArPaymentdDao();
    public abstract FtArPaymenthDao ftArPaymenthDao();
    public abstract FTaxDao fTaxDao();
    public abstract FtOpnamedItemsDao ftOpnamedItemsDao();
    public abstract FtOpnamehDao ftOpnamehDao();
    public abstract FtPriceAltdItemsDao ftPriceAltdItemsDao();
    public abstract FtPriceAlthDao ftPriceAlthDao();
    public abstract FtPricedItemsDao ftPricedItemsDao();
    public abstract FtPricehDao ftPricehDao();
    public abstract FtPurchasedItemsDao ftPurchasedItemsDao();
    public abstract FtPurchasehDao ftPurchasehDao();
    public abstract FtSalesdItemsDao ftSalesdItemsDao();
    public abstract FtSaleshDao ftSaleshDao();
    public abstract FtStockTransferdItemsDao ftStockTransferdItemsDao();
    public abstract FtStockTransferhDao ftStockTransferhDao();
    public abstract FUangMukaDao fUangMukaDao();
    public abstract FVendorDao fVendorDao();
    public abstract FWarehouseDao fWarehouseDao();
    public abstract SysvarDao sysvarDao();



    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "des_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)

                    .allowMainThreadQueries() //this is buat bisa query

                    .build();
        }
        return instance;
    }
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };



    // Membuat method return untuk membuat database
    public static AppDatabase createDatabase(Context context){
        synchronized (AppDatabase.class){
            if (instance == null){
                instance = Room.databaseBuilder(context, AppDatabase.class, "des_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }




    /**
     * Optional
     * Untuk pertama kali jika database belum ada isinya
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FAreaDao fAreaDao;
        private PopulateDbAsyncTask(AppDatabase db) {
            fAreaDao = db.fAreaDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
//            noteDao.insert(new Note("Title 1", "Description 1", 1));
//            noteDao.insert(new Note("Title 2", "Description 2", 2));
//            noteDao.insert(new Note("Title 3", "Description 3", 3));
//            noteDao.insert(new Note("Title 4", "Description 4", 7));
//            noteDao.insert(new Note("Title 5", "Description 5", 4));
//            noteDao.insert(new Note("Title 6", "Description 6", 6));
//            noteDao.insert(new Note("Title 7", "Description 7", 3));
//            noteDao.insert(new Note("Title 8", "Description 8", 9));
//            noteDao.insert(new Note("Title 9", "Description 9", 2));
            return null;
        }
    }
}