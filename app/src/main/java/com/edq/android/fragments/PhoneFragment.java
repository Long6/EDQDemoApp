package com.edq.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edq.android.edqdemoapp.R;

/**
 * Created by Long on 7/18/2015.
 */
public class PhoneFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the view from fragment_email.xml
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        return view;
    }
}
