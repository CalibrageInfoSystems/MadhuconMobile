package com.trst01.locationtracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddD30Table;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundariesTrackingTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddGrowthMonitoringTable;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.BankTable;
import com.trst01.locationtracker.database.entity.BranchTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.D20DiseaseTable;
import com.trst01.locationtracker.database.entity.D20FertilizerTable;
import com.trst01.locationtracker.database.entity.D20PestTable;
import com.trst01.locationtracker.database.entity.D20WeedTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.DistrictTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.FertilizerTable;
import com.trst01.locationtracker.database.entity.MandalTable;
import com.trst01.locationtracker.database.entity.ParameterTable;
import com.trst01.locationtracker.database.entity.PestTable;
import com.trst01.locationtracker.database.entity.PlantSubTypeTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.SampleSlabTable;
import com.trst01.locationtracker.database.entity.SavingComplainImagesTable;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.StateTable;
import com.trst01.locationtracker.database.entity.UOMTable;
import com.trst01.locationtracker.database.entity.UserSectionTable;
import com.trst01.locationtracker.database.entity.UsersTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.WarehouseTable;
import com.trst01.locationtracker.database.entity.WeedTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionSyncResponseDTO implements Serializable {

        @SerializedName("Farmer")
        @Expose
        private List<AddFarmerTable> Farmer = null;
        @SerializedName("PlotOffer")
        @Expose
        private List<AddPlotOfferTable> PlotOffer = new ArrayList<>();
        @SerializedName("Doc10")
        @Expose
        private List<AddD10Table> Doc10 = new ArrayList<>();
        @SerializedName("Doc20")
        @Expose
        private List<AddD20Table> Doc20 = new ArrayList<>();
        @SerializedName("Doc30")
        @Expose
        private List<AddD30Table> Doc30 = new ArrayList<>();
        @SerializedName("Plot")
        @Expose
        private List<AddPlotTable> Plot = null;
        @SerializedName("GeoBoundaries")
        @Expose
        private List<AddGeoBoundriesTable> GeoBoundaries = new ArrayList<>();

        @SerializedName("UserTracking")
        @Expose
        private List<AddGeoBoundariesTrackingTable> UserTracking = new ArrayList<>();

        @SerializedName("UserSection")
        @Expose
        private List<UserSectionTable> UserSection = new ArrayList<>();

        @SerializedName("Doc20Fertilizer")
        @Expose
        private List<D20FertilizerTable> Doc20Fertilizer = new ArrayList<>();

        @SerializedName("Doc20Disease")
        @Expose
        private List<D20DiseaseTable> Doc20Disease = new ArrayList<>();

        @SerializedName("Doc20Weed")
        @Expose
        private List<D20WeedTable> Doc20Weed = new ArrayList<>();

        @SerializedName("Doc20Pest")
        @Expose
        private List<D20PestTable> Doc20Pest = new ArrayList<>();

        @SerializedName("ComplaintRepository")
        @Expose
        private List<SavingComplainImagesTable> ComplaintRepository = new ArrayList<>();

        @SerializedName("Complaints")
        @Expose
        private List<AddComplaintsDetailsTable> Complaints = new ArrayList<>();

        public List<AddGrowthMonitoringTable> getGrowthMonitoring() {
                return GrowthMonitoring;
        }

        public void setGrowthMonitoring(List<AddGrowthMonitoringTable> growthMonitoring) {
                GrowthMonitoring = growthMonitoring;
        }

        @SerializedName("GrowthMonitoring")
        @Expose
        private List<AddGrowthMonitoringTable> GrowthMonitoring = new ArrayList<>();

        public List<AddComplaintsDetailsTable> getComplaints() {
                return Complaints;
        }

        public void setComplaints(List<AddComplaintsDetailsTable> complaints) {
                Complaints = complaints;
        }

        public List<SavingComplainImagesTable> getComplaintRepository() {
                return ComplaintRepository;
        }

        public void setComplaintRepository(List<SavingComplainImagesTable> complaintRepository) {
                ComplaintRepository = complaintRepository;
        }


        public List<D20FertilizerTable> getDoc20Fertilizer() {
                return Doc20Fertilizer;
        }

        public void setDoc20Fertilizer(List<D20FertilizerTable> doc20Fertilizer) {
                Doc20Fertilizer = doc20Fertilizer;
        }

        public List<D20DiseaseTable> getDoc20Disease() {
                return Doc20Disease;
        }

        public void setDoc20Disease(List<D20DiseaseTable> doc20Disease) {
                Doc20Disease = doc20Disease;
        }

        public List<D20WeedTable> getDoc20Weed() {
                return Doc20Weed;
        }

        public void setDoc20Weed(List<D20WeedTable> doc20Weed) {
                Doc20Weed = doc20Weed;
        }

        public List<D20PestTable> getDoc20Pest() {
                return Doc20Pest;
        }

        public void setDoc20Pest(List<D20PestTable> doc20Pest) {
                Doc20Pest = doc20Pest;
        }

        public List<UserSectionTable> getUserSection() {
                return UserSection;
        }

        public void setUserSection(List<UserSectionTable> userSection) {
                UserSection = userSection;
        }

        public List<AddFarmerTable> getFarmer() {
                return Farmer;
        }

        public void setFarmer(List<AddFarmerTable> farmer) {
                Farmer = farmer;
        }

        public List<AddPlotTable> getPlot() {
                return Plot;
        }

        public void setPlot(List<AddPlotTable> plot) {
                Plot = plot;
        }

        public List<AddGeoBoundriesTable> getGeoBoundaries() {
                return GeoBoundaries;
        }

        public void setGeoBoundaries(List<AddGeoBoundriesTable> geoBoundaries) {
                GeoBoundaries = geoBoundaries;
        }

        public List<AddD10Table> getDoc10() {
                return Doc10;
        }

        public void setDoc10(List<AddD10Table> doc10) {
                Doc10 = doc10;
        }

        public List<AddD30Table> getDoc30() {
                return Doc30;
        }

        public void setDoc30(List<AddD30Table> doc30) {
                Doc30 = doc30;
        }

        public List<AddPlotOfferTable> getPlotOffer() {
                return PlotOffer;
        }

        public void setPlotOffer(List<AddPlotOfferTable> plotOffer) {
                PlotOffer = plotOffer;
        }

        public List<AddGeoBoundariesTrackingTable> getUserTracking() {
                return UserTracking;
        }

        public void setUserTracking(List<AddGeoBoundariesTrackingTable> userTracking) {
                UserTracking = userTracking;
        }

        public List<AddD20Table> getDoc20() {
                return Doc20;
        }

        public void setDoc20(List<AddD20Table> doc20) {
                Doc20 = doc20;
        }
}