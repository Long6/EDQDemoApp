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
    ViewGroup cont;
    ScrollView scrollView;
    TextView statusVerified;
    TextView statusUnknown;
    TextView statusUndeliverable;
    TextView statusUnreachable;
    TextView statusIllegitimate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the view from fragment_email.xml
        cont = container;
        view = inflater.inflate(R.layout.fragment_email, cont, false);

/*
        scrollView = (ScrollView)cont.findViewById(R.id.scrollView);
        scrollView.setVisibility(View.INVISIBLE);

        //Set all verification levels to GONE
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            View v = scrollView.getChildAt(i);
            v.setVisibility(View.GONE);
        }
*/

        return view;
    }

    public void updateText(View view, String s) {

        ScrollView scrollV = (ScrollView)cont.findViewById(R.id.scrollView);
        scrollV.setVisibility(View.VISIBLE);

        TextView textV = (TextView)cont.findViewById(R.id.textView_Verified);
        textV.setVisibility(View.VISIBLE);
        textV.setText(s);
    }

}

