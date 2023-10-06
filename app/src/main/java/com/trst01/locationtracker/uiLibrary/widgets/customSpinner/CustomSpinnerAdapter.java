package com.trst01.locationtracker.uiLibrary.widgets.customSpinner;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CustomSpinnerAdapter extends ArrayAdapter implements Filterable {

    // declaring our ArrayList of items
    private List _list;
    private List _allList;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> _list,
    * because it is the list of _list we want to display.
    */
    public CustomSpinnerAdapter(Context context, int textViewResourceId, List _list) {
        super(context, textViewResourceId, _list);
        this._list = _list;
        this._allList = _list;
    }

    /*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
		try {
            Object string = _list.get(position);

            if (!TextUtils.isEmpty((String)string)) {
                TextView tv = (TextView) v.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setText((String)string);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // the view must be returned to our activity
        return v;

    }

    @Override
    @NonNull
    public Filter getFilter() {
        // return a filter that filters data based on a constraint

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                // Create a FilterResults object
                FilterResults results = new FilterResults();
                try {

                    // If the constraint (search string/pattern) is null
                    // or its length is 0, i.e., its empty then
                    // we just set the `values` property to the
                    // original contacts list which contains all of them
                    if (constraint == null || constraint.length() == 0) {
                        results.values = _allList;
                        results.count = _allList.size();
                    }
                    else {
                        // Some search copnstraint has been passed
                        // so let's filter accordingly
                        List filteredContacts = new ArrayList<>();

                        // We'll go through all the contacts and see
                        // if they contain the supplied string
                        for (Object s : _allList) {
                            if (s instanceof String){
                                String string = (String) s;
                                if (string.toUpperCase().contains( constraint.toString().toUpperCase())) {
                                    // if `contains` == true then add it
                                    // to our filtered list
                                    filteredContacts.add(string);
                                }
                            }
                        }

                        // Finally set the filtered values and size/count
                        results.values = filteredContacts;
                        results.count = filteredContacts.size();
                    }

                    // Return our FilterResults object
                    return results;
                } catch (Exception e){
                    e.printStackTrace();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                try {
                    if (results.count <= 0){
                        _list = new ArrayList();
                    } else {
                        _list = (ArrayList) results.values;
                    }
                    notifyDataSetChanged();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    public void clearItems(){
        try {
            _list.clear();
            _allList.clear();
            notifyDataSetChanged();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return _list.get(position);
    }
}
