package com.trst01.locationtracker.view_models;

import com.trst01.locationtracker.database.entity.AddGeoBoundariesTrackingTable;

import java.util.List;

public interface OnInsertCallback {
    void onInsertComplete(List<AddGeoBoundariesTrackingTable> insertedItems);
}