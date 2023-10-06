package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class ParameterTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ParameterId")
    @Expose(serialize = false, deserialize = false)
    private int parameterId;

    @SerializedName("Id")
    @Expose
    private Integer id;


    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Name")
    @Expose
    private String name;


    @SerializedName("DataType")
    @Expose
    private String dataType;


    @SerializedName("UIControl")
    @Expose
    private String UIControl;


    @SerializedName("DefaultQuery")
    @Expose
    private String defaultQuery;


    @SerializedName("UIControlQuery")
    @Expose
    private String UIControlQuery;


    @SerializedName("Order")
    @Expose
    private Integer order;

    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId ;

    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String updatedByUserId ;

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUIControl() {
        return UIControl;
    }

    public void setUIControl(String UIControl) {
        this.UIControl = UIControl;
    }

    public String getDefaultQuery() {
        return defaultQuery;
    }

    public void setDefaultQuery(String defaultQuery) {
        this.defaultQuery = defaultQuery;
    }

    public String getUIControlQuery() {
        return UIControlQuery;
    }

    public void setUIControlQuery(String UIControlQuery) {
        this.UIControlQuery = UIControlQuery;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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