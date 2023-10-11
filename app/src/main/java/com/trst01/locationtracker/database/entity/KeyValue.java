package com.trst01.locationtracker.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class KeyValue {
    @PrimaryKey(autoGenerate = true)
    private int keyValueId;

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Key")
    @Expose
    private String key;

    @SerializedName("Value")
    @Expose
    private String value;

    @SerializedName("Value1")
    @Expose
    private String value1;

    @SerializedName("Value2")
    @Expose
    private String value2;

    @SerializedName("Value3")
    @Expose
    private String value3;

    @SerializedName("Value4")
    @Expose
    private String value4;

    @SerializedName("Value5")
    @Expose
    private String value5;

    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId;

    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String updatedByUserId;

    public int getKeyValueId() {
        return keyValueId;
    }

    public void setKeyValueId(int keyValueId) {
        this.keyValueId = keyValueId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }
}

