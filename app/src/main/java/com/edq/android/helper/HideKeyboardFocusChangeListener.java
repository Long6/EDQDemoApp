package com.edq.android.helper;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.edq.android.edqdemoapp.R;

/**
 * Created by Long on 7/19/2015.
 */
public class HideKeyboardFocusChangeListener implements View.OnFocusChangeListener {

    private Context context;

    public HideKeyboardFocusChangeListener(Context context) {
        this.context = context;
    }

    public void onFocusChange(View v, boolean hasFocus){

        if(v.getId() == R.id.emailFragmentInput && !hasFocus) {

            InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        }
    }
}
