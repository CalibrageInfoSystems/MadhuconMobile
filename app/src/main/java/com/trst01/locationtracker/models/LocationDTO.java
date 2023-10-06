package com.trst01.locationtracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class LocationDTO implements Serializable {
   public ArrayList<Doc> Doc= new ArrayList<Doc>();


    public ArrayList<Doc> getDoc() {
        return Doc;
    }

    public void setDoc(ArrayList<Doc> doc) {
        Doc = doc;
    }
}
