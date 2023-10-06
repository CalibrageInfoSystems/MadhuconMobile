package com.trst01.locationtracker.models;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.trst01.locationtracker.database.entity.BankTable;
import com.trst01.locationtracker.database.entity.BranchTable;
import com.trst01.locationtracker.database.entity.CastTable;
import com.trst01.locationtracker.database.entity.CircleTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.DistrictTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.FertilizerTable;
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
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.StateTable;
import com.trst01.locationtracker.database.entity.UOMTable;
import com.trst01.locationtracker.database.entity.UsersTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.database.entity.WarehouseTable;
import com.trst01.locationtracker.database.entity.WeedTable;

import java.io.Serializable;
import java.util.List;

public class MastersResponseDTO implements Serializable {

        @SerializedName("Division")
        @Expose
        private List<DivisionTable> division = null;
        @SerializedName("Section")
        @Expose
        private List<SectionTable> section = null;
        @SerializedName("Circle")
        @Expose
        private List<CircleTable> circle = null;
        @SerializedName("ResonForNotPlanting")
        @Expose
        private List<ResonForNotPlantingTable> reson = null;
        @SerializedName("Village")
        @Expose
        private List<VillageTable> village = null;
        @SerializedName("Crop")
        @Expose
        private List<CropTable> crop = null;
        @SerializedName("Bank")
        @Expose
        private List<BankTable> bank = null;
        @SerializedName("Branch")
        @Expose
        private List<BranchTable> branch = null;
        @SerializedName("Disease")
        @Expose
        private List<DiseaseTable> disease = null;
        @SerializedName("District")
        @Expose
        private List<DistrictTable> district = null;
        @SerializedName("Fertilizer")
        @Expose
        private List<FertilizerTable> fertilizer = null;
        @SerializedName("Mandal")
        @Expose
        private List<MandalTable> mandal = null;
        @SerializedName("State")
        @Expose
        private List<StateTable> state = null;
        @SerializedName("Users")
        @Expose
        private List<UsersTable> users = null;
        @SerializedName("Parameter")
        @Expose
        private List<ParameterTable> parameter = null;
        @SerializedName("Pest")
        @Expose
        private List<PestTable> pest = null;
        @SerializedName("PlantType")
        @Expose
        private List<PlantTypeTable> plantType = null;
        @SerializedName("PlantSubType")
        @Expose
        private List<PlantSubTypeTable> plantSubType = null;
        @SerializedName("SampleSlab")
        @Expose
        private List<SampleSlabTable> sampleSlab = null;
        @SerializedName("Season")
        @Expose
        private List<SeasonTable> season = null;
        @SerializedName("UOM")
        @Expose
        private List<UOMTable> uom = null;
        @SerializedName("Variety")
        @Expose
        private List<VarietyTable> variety = null;
        @SerializedName("Warehouse")
        @Expose
        private List<WarehouseTable> warehouse = null;
        @SerializedName("Weed")
        @Expose
        private List<WeedTable> weed = null;
        @SerializedName("PlotExistOn")
        @Expose
        private List<PlotExistOnTable> plotExistOn = null;
        @SerializedName("Cast")
        @Expose
        private List<CastTable> cast = null;
        @SerializedName("LookupHDR")
        @Expose
        private List<LookupHDRTable> lookUpHDR = null;
        @SerializedName("LookupDtl")
        @Expose
        private List<LookupDtlTable> lookupDtl = null;

    public List<PlotExistOnTable> getPlotExistOn() {
        return plotExistOn;
    }

    public void setPlotExistOn(List<PlotExistOnTable> plotExistOn) {
        this.plotExistOn = plotExistOn;
    }

    public List<CastTable> getCast() {
        return cast;
    }

    public void setCast(List<CastTable> cast) {
        this.cast = cast;
    }

    public List<LookupHDRTable> getLookUpHDR() {
        return lookUpHDR;
    }

    public void setLookUpHDR(List<LookupHDRTable> lookUpHDR) {
        this.lookUpHDR = lookUpHDR;
    }

    public List<LookupDtlTable> getLookupDtl() {
        return lookupDtl;
    }

    public void setLookupDtl(List<LookupDtlTable> lookupDtl) {
        this.lookupDtl = lookupDtl;
    }

    public List<DivisionTable> getDivision() {
        return division;
    }

    public void setDivision(List<DivisionTable> division) {
        this.division = division;
    }

    public List<SectionTable> getSection() {
        return section;
    }

    public void setSection(List<SectionTable> section) {
        this.section = section;
    }

    public List<CircleTable> getCircle() {
        return circle;
    }

    public void setCircle(List<CircleTable> circle) {
        this.circle = circle;
    }

    public List<VillageTable> getVillage() {
        return village;
    }

    public void setVillage(List<VillageTable> village) {
        this.village = village;
    }

    public List<CropTable> getCrop() {
            return crop;
        }

        public void setCrop(List<CropTable> crop) {
            this.crop = crop;
        }

        public List<BankTable> getBank() {
            return bank;
        }

        public void setBank(List<BankTable> bank) {
            this.bank = bank;
        }

        public List<BranchTable> getBranch() {
            return branch;
        }

        public void setBranch(List<BranchTable> branch) {
            this.branch = branch;
        }

        public List<DiseaseTable> getDisease() {
            return disease;
        }

        public void setDisease(List<DiseaseTable> disease) {
            this.disease = disease;
        }

        public List<DistrictTable> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictTable> district) {
            this.district = district;
        }

        public List<FertilizerTable> getFertilizer() {
            return fertilizer;
        }

        public void setFertilizer(List<FertilizerTable> fertilizer) {
            this.fertilizer = fertilizer;
        }

        public List<MandalTable> getMandal() {
            return mandal;
        }

        public void setMandal(List<MandalTable> mandal) {
            this.mandal = mandal;
        }

        public List<StateTable> getState() {
            return state;
        }

        public void setState(List<StateTable> state) {
            this.state = state;
        }

        public List<UsersTable> getUsers() {
            return users;
        }

        public void setUsers(List<UsersTable> users) {
            this.users = users;
        }

        public List<ParameterTable> getParameter() {
            return parameter;
        }

        public void setParameter(List<ParameterTable> parameter) {
            this.parameter = parameter;
        }

        public List<PestTable> getPest() {
            return pest;
        }

        public void setPest(List<PestTable> pest) {
            this.pest = pest;
        }

        public List<PlantTypeTable> getPlantType() {
            return plantType;
        }

        public void setPlantType(List<PlantTypeTable> plantType) {
            this.plantType = plantType;
        }

        public List<PlantSubTypeTable> getPlantSubType() {
            return plantSubType;
        }

        public void setPlantSubType(List<PlantSubTypeTable> plantSubType) {
            this.plantSubType = plantSubType;
        }

        public List<SampleSlabTable> getSampleSlab() {
            return sampleSlab;
        }

        public void setSampleSlab(List<SampleSlabTable> sampleSlab) {
            this.sampleSlab = sampleSlab;
        }

        public List<SeasonTable> getSeason() {
            return season;
        }

        public void setSeason(List<SeasonTable> season) {
            this.season = season;
        }

        public List<UOMTable> getUom() {
            return uom;
        }

        public void setUom(List<UOMTable> uom) {
            this.uom = uom;
        }

        public List<VarietyTable> getVariety() {
            return variety;
        }

        public void setVariety(List<VarietyTable> variety) {
            this.variety = variety;
        }

        public List<WarehouseTable> getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(List<WarehouseTable> warehouse) {
            this.warehouse = warehouse;
        }

        public List<WeedTable> getWeed() {
            return weed;
        }

        public void setWeed(List<WeedTable> weed) {
            this.weed = weed;
        }

    public List<ResonForNotPlantingTable> getReson() {
        return reson;
    }

    public void setReson(List<ResonForNotPlantingTable> reson) {
        this.reson = reson;
    }
}