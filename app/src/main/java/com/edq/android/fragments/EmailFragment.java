package com.edq.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.edq.android.edqdemoapp.MagicActivity;
import com.edq.android.edqdemoapp.R;
import com.edq.android.helper.HideKeyboardFocusChangeListener;
import com.edq.android.services.EmailClient;


/**
 * Created by Long on 7/18/2015.
 */
public class EmailFragment extends Fragment {


    View view;
    ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the view from fragment_email.xml
        this.view = inflater.inflate(R.layout.fragment_email, container, false);
        this.container = container;

        return view;
    }

    public void updateText(View view) {

        TextView textView = (TextView)container.findViewById(R.id.textView);

        textView.setText("I changed this stuff!! ^_^");
    }

}

