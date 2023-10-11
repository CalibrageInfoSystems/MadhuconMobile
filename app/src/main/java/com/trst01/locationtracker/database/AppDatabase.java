package com.trst01.locationtracker.database;

import static com.trst01.locationtracker.constant.AppConstant.DB_VERSION;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.trst01.locationtracker.database.converter.ArrayListConverter;
import com.trst01.locationtracker.database.converter.DateConverter;
import com.trst01.locationtracker.database.dao.AppDAO;
import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddD30Table;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundariesTrackingTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddGrowthMonitoringTable;
import com.trst01.locationtracker.database.entity.AddPlantationTable;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.BankTable;
import com.trst01.locationtracker.database.entity.BranchTable;
import com.trst01.locationtracker.database.entity.CastTable;
import com.trst01.locationtracker.database.entity.CircleTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.D20DiseaseTable;
import com.trst01.locationtracker.database.entity.D20FertilizerTable;
import com.trst01.locationtracker.database.entity.D20PestTable;
import com.trst01.locationtracker.database.entity.D20WeedTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.DistrictTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.FertilizerTable;
import com.trst01.locationtracker.database.entity.KeyValue;
import com.trst01.locationtracker.database.entity.LookUpDropDownDataTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
import com.trst01.locationtracker.database.entity.MandalTable;
import com.trst01.locationtracker.database.entity.ParameterTable;
import com.trst01.locationtracker.database.entity.PestTable;
import com.trst01.locationtracker.database.entity.PlantSubTypeTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.PlotExistOnTable;
import com.trst01.locationtracker.database.entity.ResonForNotPlantingTable;
import com.trst01.locationtracker.database.entity.SampleSlabTable;
import com.trst01.locationtracker.database.entity.SavingComplainImagesTable;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.StateTable;
import com.trst01.locationtracker.database.entity.UOMTable;
import com.trst01.locationtracker.database.entity.UserSectionTable;
import com.trst01.locationtracker.database.entity.UsersTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.database.entity.WarehouseTable;
import com.trst01.locationtracker.database.entity.WeedTable;

@Database(entities = { AddGeoBoundriesTable.class, DivisionTable.class, SectionTable.class, VillageTable.class, CircleTable.class,
        CropTable.class, BankTable.class, BranchTable.class, DiseaseTable.class, DistrictTable.class, FertilizerTable.class,
        MandalTable.class, StateTable.class, UsersTable.class, ParameterTable.class, PestTable.class, PlantTypeTable.class,
        PlantSubTypeTable.class, SampleSlabTable.class, SeasonTable.class, UOMTable.class, VarietyTable.class, CastTable.class, LookupDtlTable.class,
        LookupHDRTable.class, PlotExistOnTable.class, AddPlotOfferTable.class, ResonForNotPlantingTable.class, AddPlantationTable.class, AddD20Table.class,
        AddD10Table.class, AddD30Table.class,WarehouseTable.class, WeedTable.class, AddFarmerTable.class, AddPlotTable.class, UserSectionTable.class,
        AddGeoBoundariesTrackingTable.class, D20FertilizerTable.class, D20DiseaseTable.class, D20WeedTable.class, D20PestTable.class,
        SavingComplainImagesTable.class, AddComplaintsDetailsTable.class, LookUpDropDownDataTable.class, AddGrowthMonitoringTable.class , KeyValue.class}, version = DB_VERSION, exportSchema = false )

@TypeConverters({DateConverter.class, ArrayListConverter.class})
public abstract class  AppDatabase extends RoomDatabase {
    // --- SINGLETON ---

    private static volatile AppDatabase INSTANCE;

    // --- DAO ---
    public abstract AppDAO bootStrapDAO();

}
// TODO: 2/16/2022 From Season Table we have to created 