package com.trst01.locationtracker.database.entity;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class AddLogBookGeoBoundariesTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("LogBookGeoBoundariesId")
    @Expose(serialize = false, deserialize = false)
    private int LogBookGeoBoundariesId;

    public int getLogBookGeoBoundariesId() {
        return LogBookGeoBoundariesId;
    }

    public void setLogBookGeoBoundariesId(int logBookGeoBoundariesId) {
        LogBookGeoBoundariesId = logBookGeoBoundariesId;
    }

        @SerializedName("Id")
        @Expose
        private String Id ;

        @SerializedName("PlotId")
        @Expose
        private String PlotId ;

        @SerializedName("Latitude")
        @Expose
        private String Latitude ;

        @SerializedName("Longitude")
        @Expose
        private String Longitude ;

        @SerializedName("TotalPlotArea")
        @Expose
        private String TotalPlotArea ;

        @SerializedName("CultureArea")
        @Expose
        private String CultureArea ;

        @SerializedName("Sync")
        @Expose
        private String Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;


        public String getId() {
        return Id;
    }

        public void setId(String id) {
        Id = id;
    }

        public String getPlotId() {
        return PlotId;
    }

        public void setPlotId(String plotId) {
        PlotId = plotId;
    }

        public String getLatitude() {
        return Latitude;
    }

        public void setLatitude(String latitude) {
        Latitude = latitude;
    }

        public String getLongitude() {
        return Longitude;
    }

        public void setLongitude(String longitude) {
        Longitude = longitude;
    }

        public String getTotalPlotArea() {
        return TotalPlotArea;
    }

        public void setTotalPlotArea(String totalPlotArea) {
        TotalPlotArea = totalPlotArea;
    }

        public String getCultureArea() {
        return CultureArea;
    }

        public void setCultureArea(String cultureArea) {
        CultureArea = cultureArea;
    }

        public String getSync() {
        return Sync;
    }

        public void setSync(String sync) {
        Sync = sync;
    }

        public String getDate() {
        return Date;
    }

        public void setDate(String date) {
        Date = date;
    }

        public String getIsActive() {
        return IsActive;
    }

        public void setIsActive(String isActive) {
        IsActive = isActive;
    }

        public String getCreatedDate() {
        return CreatedDate;
    }

        public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

        public String getCreatedByUserId() {
        return CreatedByUserId;
    }

        public void setCreatedByUserId(String createdByUserId) {
        CreatedByUserId = createdByUserId;
    }

        public String getUpdatedDate() {
        return UpdatedDate;
    }

        public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

        public String getUpdatedByUserId() {
        return UpdatedByUserId;
    }

        public void setUpdatedByUserId(String updatedByUserId) {
        UpdatedByUserId = updatedByUserId;
    }

        public String getSQNo() {
        return SQNo;
    }

        public void setSQNo(String SQNo) {
        this.SQNo = SQNo;
    }


//    @SerializedName("PlotId")
//    @Expose
//    private String PlotNo;
//
//    @SerializedName("LogBookNo")
//    @Expose
//    private String LogBookNo;
//
//    //        @SerializedName("Season")
//    @SerializedName("SeasonId")
//    @Expose
//    private Integer Season;
//
//    @SerializedName("DateOfSowing")
//    @Expose
//    private String  DateOfSowing = "";
//
//
//    //        @SerializedName("CropVariety")
//    @SerializedName("CropVarietyId")
//    @Expose
//    private String CropVariety;
//
//    public String getCropVariety() {
//        return CropVariety;
//    }
//
//    public void setCropVariety(String cropVariety) {
//        CropVariety = cropVariety;
//    }
//
//    //        @SerializedName("Crop")
//    @SerializedName("CropId")
//    @Expose
//    private String Crop;
//
//
//    @SerializedName("Sync")
//    @Expose
//    private Boolean Sync ;
//
//    public Boolean getSync() {
//        return Sync;
//    }
//
//    public void setSync(Boolean sync) {
//        Sync = sync;
//    }
////        @SerializedName("Harvest")
////        @Expose
////        private String Harvest;
////
////        @SerializedName("CultivationPractice")
////        @Expose
////        private Integer CultivationPractice;
//
//    public String getPlotNo() {
//        return PlotNo;
//    }
//
//    public void setPlotNo(String plotNo) {
//        PlotNo = plotNo;
//    }
//
//    public String getLogBookNo() {
//        return LogBookNo;
//    }
//
//    public void setLogBookNo(String logBookNo) {
//        LogBookNo = logBookNo;
//    }
//
//    public Integer getSeason() {
//        return Season;
//    }
//
//    public void setSeason(Integer season) {
//        Season = season;
//    }
//
//
//    public String getDateOfSowing() {
//        return DateOfSowing;
//    }
//
//    public void setDateOfSowing(String dateOfSowing) {
//        DateOfSowing = dateOfSowing;
//    }
//
//    public String getCrop() {
//        return Crop;
//    }
//
//    public void setCrop(String crop) {
//        Crop = crop;
//    }
//
////        public String getHarvest() {
////            return Harvest;
////        }
////
////        public void setHarvest(String harvest) {
////            Harvest = harvest;
////        }
////
////        public Integer getCultivationPractice() {
////            return CultivationPractice;
////        }
////
////        public void setCultivationPractice(Integer cultivationPractice) {
////            CultivationPractice = cultivationPractice;
////        }
//
//    @SerializedName("IsActive")
//    @Expose
//    private String IsActive = "1";
//
//    @SerializedName("CreatedDate")
//    @Expose
//    private String CreatedDate;
//
//    @SerializedName("CreatedByUserId")
//    @Expose
//    private String CreatedByUserId;
//
//    @SerializedName("UpdatedDate")
//    @Expose
//    private String UpdatedDate;
//
//    @SerializedName("UpdatedByUserId")
//    @Expose
//    private String UpdatedByUserId ;
//
//
//
//
//    public String getIsActive() {
//        return IsActive;
//    }
//
//    public void setIsActive(String isActive) {
//        IsActive = isActive;
//    }
//
//    public String getCreatedDate() {
//        return CreatedDate;
//    }
//
//    public void setCreatedDate(String createdDate) {
//        CreatedDate = createdDate;
//    }
//
//    public String getCreatedByUserId() {
//        return CreatedByUserId;
//    }
//
//    public void setCreatedByUserId(String createdByUserId) {
//        CreatedByUserId = createdByUserId;
//    }
//
//    public String getUpdatedDate() {
//        return UpdatedDate;
//    }
//
//    public void setUpdatedDate(String updatedDate) {
//        UpdatedDate = updatedDate;
//    }
//
//    public String getUpdatedByUserId() {
//        return UpdatedByUserId;
//    }
//
//    public void setUpdatedByUserId(String updatedByUserId) {
//        UpdatedByUserId = updatedByUserId;
//    }
//
////    @SerializedName("PlotNo")
////    @Expose
////    private String PlotNo;
////
////    @SerializedName("LogBookNo")
////    @Expose
////    private String LogBookNo;
////
////    @SerializedName("Season")
////    @Expose
////    private Integer Season;
////
////    @SerializedName("DateOfSowing")
////    @Expose
////    private String  DateOfSowing = "";
////
////    @SerializedName("DateCheck")
////    @Expose
////    private String  DateCheck = "";
////
////
////    @SerializedName("Crop")
////    @Expose
////    private String Crop;
////
////    @SerializedName("Harvest")
////    @Expose
////    private String Harvest;
////
////    @SerializedName("CultivationPractice")
////    @Expose
////    private Integer CultivationPractice;
////
////
////    public String getCropVariety() {
////        return CropVariety;
////    }
////
////    public void setCropVariety(String cropVariety) {
////        CropVariety = cropVariety;
////    }
////
////    @SerializedName("CropVariety")
////    @Expose
////    private String CropVariety;
////
////
//////    @SerializedName("SeasonName")
//////    @Expose
//////    private String SeasonName;
////
////    @SerializedName("sync")
////    @Expose
////    private boolean sync;
////
////
////    @SerializedName("serverStatus")
////    @Expose
////    private String serverStatus;
////
////
////    @SerializedName("IsActive")
////    @Expose
////    private String IsActive = "1";
////
////    @SerializedName("CreatedDate")
////    @Expose
////    private String CreatedDate;
////
////    @SerializedName("CreatedByUserId")
////    @Expose
////    private String CreatedByUserId ;
////
////    @SerializedName("UpdatedDate")
////    @Expose
////    private String UpdatedDate;
////
////    @SerializedName("UpdatedByUserId")
////    @Expose
////    private String UpdatedByUserId ;
////
////    public int getLogBookId() {
////        return LogBookId;
////    }
////
////    public void setLogBookId(int logBookId) {
////        LogBookId = logBookId;
////    }
////
////    public String getPlotNo() {
////        return PlotNo;
////    }
////
////    public void setPlotNo(String plotNo) {
////        PlotNo = plotNo;
////    }
////
////    public String getLogBookNo() {
////        return LogBookNo;
////    }
////
////    public void setLogBookNo(String logBookNo) {
////        LogBookNo = logBookNo;
////    }
////
////
////    public String getDateCheck() {
////        return DateCheck;
////    }
////
////    public void setDateCheck(String dateCheck) {
////        DateCheck = dateCheck;
////    }
////
//////    public String getCultivationPracticeName() {
//////        return CultivationPracticeName;
//////    }
//////
//////    public void setCultivationPracticeName(String cultivationPracticeName) {
//////        CultivationPracticeName = cultivationPracticeName;
//////    }
//////
//////    public String getSeasonName() {
//////        return SeasonName;
//////    }
//////
//////    public void setSeasonName(String seasonName) {
//////        SeasonName = seasonName;
//////    }
////
////    public String getDateOfSowing() {
////        return DateOfSowing;
////    }
////
////    public void setDateOfSowing(String dateOfSowing) {
////        DateOfSowing = dateOfSowing;
////    }
////
////    public Integer getSeason() {
////        return Season;
////    }
////
////    public void setSeason(Integer season) {
////        Season = season;
////    }
////
////    public Integer getCultivationPractice() {
////        return CultivationPractice;
////    }
////
////    public void setCultivationPractice(Integer cultivationPractice) {
////        CultivationPractice = cultivationPractice;
////    }
////
////    public boolean isSync() {
////        return sync;
////    }
////
////    public void setSync(boolean sync) {
////        this.sync = sync;
////    }
////
////    public String getServerStatus() {
////        return serverStatus;
////    }
////
////    public void setServerStatus(String serverStatus) {
////        this.serverStatus = serverStatus;
////    }
////
////    public String getIsActive() {
////        return IsActive;
////    }
////
////    public void setIsActive(String isActive) {
////        IsActive = isActive;
////    }
////
////    public String getCreatedDate() {
////        return CreatedDate;
////    }
////
////    public void setCreatedDate(String createdDate) {
////        CreatedDate = createdDate;
////    }
////
////    public String getCreatedByUserId() {
////        return CreatedByUserId;
////    }
////
////    public void setCreatedByUserId(String createdByUserId) {
////        CreatedByUserId = createdByUserId;
////    }
////
////    public String getUpdatedDate() {
////        return UpdatedDate;
////    }
////
////    public void setUpdatedDate(String updatedDate) {
////        UpdatedDate = updatedDate;
////    }
////
////    public String getUpdatedByUserId() {
////        return UpdatedByUserId;
////    }
////
////    public void setUpdatedByUserId(String updatedByUserId) {
////        UpdatedByUserId = updatedByUserId;
////    }
////
////    public String getCrop() {
////        return Crop;
////    }
////
////    public void setCrop(String crop) {
////        Crop = crop;
////    }
////
////    public String getHarvest() {
////        return Harvest;
////    }
////
////    public void setHarvest(String harvest) {
////        Harvest = harvest;
////    }
}