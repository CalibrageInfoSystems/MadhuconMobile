package com.trst01.locationtracker.uiLibrary.widgets.customSpinner;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;

import com.trst01.locationtracker.R;

import java.util.ArrayList;
import java.util.List;

public class SearchableSpinner extends AppCompatSpinner implements View.OnTouchListener,
        SearchableListDialog.SearchableItem {

    public static final int NO_ITEM_SELECTED = -1;
    private Context _context;
    private List _items;
    private SearchableListDialog _searchableListDialog;

    private boolean _isDirty;
    private ArrayAdapter<String> _arrayAdapter;
    private String _strHintText;
    private boolean _isFromInit;

    private boolean hasValidInput;

    public SearchableSpinner(Context context) {
        super(context);
        this._context = context;
        init();
    }

//    public SearchableSpinner(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this._context = context;
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchableSpinner);
//        final int N = a.getIndexCount();
//        for (int i = 0; i < N; ++i) {
//            int attr = a.getIndex(i);
//            if (attr == R.styleable.SearchableSpinner_hintText) {
//                _strHintText = a.getString(attr);
//            }
//        }
//        a.recycle();
//        init();
//    }

    public SearchableSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this._context = context;
        init();
    }

    private void init() {
        try {
            _items = new ArrayList();
            _searchableListDialog = SearchableListDialog.newInstance(_items);
            _searchableListDialog.setOnSearchableItemClickListener(this);
            setOnTouchListener(this);

            _arrayAdapter = (ArrayAdapter) getAdapter();
            if (!TextUtils.isEmpty(_strHintText)) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(_context, android.R.layout
                        .simple_list_item_1, new String[]{_strHintText});
                _isFromInit = true;
                setAdapter(arrayAdapter);
            } else {
                setAdapter(_arrayAdapter);
            }

//            if (_arrayAdapter != null){
//                String string = "";
//                List<String> tempList = new ArrayList<>();
//                for (int i = 0; i < _arrayAdapter.getCount(); i++) {
//                    if (i == 0){
//                        string = _arrayAdapter.getItem(0);
//                    } else {
//                        tempList.add(_arrayAdapter.getItem(i));
//                    }
//                }
//                Collections.sort(tempList);
//                tempList.add(0, string);
//                _arrayAdapter = new ArrayAdapter(_context, R.layout.view_spinner_textheight, tempList);
//                if (!TextUtils.isEmpty(_strHintText)) {
//                    ArrayAdapter arrayAdapter = new ArrayAdapter(_context, android.R.layout
//                            .simple_list_item_1, new String[]{_strHintText});
//                    _isFromInit = true;
//                    setAdapter(arrayAdapter);
//                } else {
//                    setAdapter(_arrayAdapter);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                if (null != _arrayAdapter) {

                    // Refresh content #6
                    // Change Start
                    // Description: The items were only set initially, not reloading the data in the
                    // spinner every time it is loaded with items in the adapter.
                    _items.clear();
                    for (int i = 0; i < _arrayAdapter.getCount(); i++) {
                        _items.add(_arrayAdapter.getItem(i));
                    }
                    // Change end.
                    try {
                        if (!_searchableListDialog.isAdded()) {
                            _searchableListDialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        try {
            if (!_isFromInit) {
               _arrayAdapter = (ArrayAdapter) adapter;
                List<String> tempList = new ArrayList<>();
                if (_arrayAdapter != null){
                    String string = "";
                    for (int i = 0; i < _arrayAdapter.getCount(); i++) {
                        if (i == 0){
                            string = _arrayAdapter.getItem(0);
                        } else {
                            tempList.add(_arrayAdapter.getItem(i));
                        }
                    }
                    // TODO: NO NEED TO SORT IN ALPHABETICAL ORDER
//                    Collections.sort(tempList);
                    tempList.add(0, string);
                }
                if (_context != null){
                    _arrayAdapter = new ArrayAdapter(_context, R.layout.view_spinner_textheight, tempList);
                    if (!TextUtils.isEmpty(_strHintText)/* && !_isDirty*/) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(_context, android.R.layout
                                .simple_list_item_1, new String[]{_strHintText});
                        super.setAdapter(arrayAdapter);
                    } else {
                        super.setAdapter(_arrayAdapter);
                    }
                } else {
                    super.setAdapter(_arrayAdapter);
                }

//                if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
//                    ArrayAdapter arrayAdapter = new ArrayAdapter(_context, android.R.layout
//                            .simple_list_item_1, new String[]{_strHintText});
//                    super.setAdapter(arrayAdapter);
//                } else {
//                    super.setAdapter(adapter);
//                }
            } else {
                _isFromInit = false;
                super.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSearchableItemClicked(Object item, int position) {
        try {
            Log.i("SPINNER", _items + "");
            Log.i("SPINNER", item.toString());
            Log.i("SPINNER", position + "");
            int pos = _items.indexOf(item);
            Log.i("SPINNER", _arrayAdapter.getItem(pos).toString() + "");
            Log.i("SPINNER", pos + "");
            setSelection(pos, false);
//            if (!_isDirty) {
//                _isDirty = true;
//                setAdapter(_arrayAdapter);
//                setSelection(pos + 1, false);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        try {
            setSelection(position, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSelection(int position, boolean animate) {
        super.setSelection(position, animate);
        try {
            if (position > 0) {
                setHasValidInput(true);
            } else {
                if (getTag() != null){
                    setHasValidInput(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String strTitle) {
        try {
            _searchableListDialog.setTitle(strTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPositiveButton(String strPositiveButtonText) {
        try {
            _searchableListDialog.setPositiveButton(strPositiveButtonText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPositiveButton(String strPositiveButtonText, DialogInterface.OnClickListener onClickListener) {
        try {
            _searchableListDialog.setPositiveButton(strPositiveButtonText, onClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnSearchTextChangedListener(SearchableListDialog.OnSearchTextChanged onSearchTextChanged) {
        try {
            _searchableListDialog.setOnSearchTextChangedListener(onSearchTextChanged);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Activity scanForActivity(Context cont) {
        try {
            if (cont == null)
                return null;
            else if (cont instanceof Activity)
                return (Activity) cont;
            else if (cont instanceof ContextWrapper)
                return scanForActivity(((ContextWrapper) cont).getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getSelectedItemPosition() {
        try {
            if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
                return NO_ITEM_SELECTED;
            } else {
                return super.getSelectedItemPosition();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getSelectedItem() {
        try {
            if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
                return null;
            } else {
                return super.getSelectedItem();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isHasValidInput() {
        return hasValidInput;
    }

    public void setHasValidInput(boolean hasValidInput) {
        this.hasValidInput = hasValidInput;
        try {
            TextView textView = (TextView) getSelectedView();
            if (!hasValidInput) {
                textView.setError("Please Select");
            } else {
                textView.setError(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}