package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class AddPlantationTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("plantation")
    @Expose(serialize = false, deserialize = false)
    private int plantationId;

    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("sync")
    @Expose
    private String sync;

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public int getPlantationId() {
        return plantationId;
    }

    public void setPlantationId(int plantationId) {
        this.plantationId = plantationId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
