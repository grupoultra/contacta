package com.sur.ultra.contacta;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by alexis on 5/27/16.
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "SpinnerActivity";

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Log.d(TAG, "Item selected");
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
