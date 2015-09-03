package com.edq.android.edqdemoapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.edq.android.fragments.AddressFragment;
import com.edq.android.fragments.EmailFragment;
import com.edq.android.fragments.PhoneFragment;
import com.edq.android.helper.HideKeyboardFocusChangeListener;
import com.edq.android.services.EmailClient;


public class MagicActivity extends FragmentActivity {

    private static final String TAG = "longLogger";

    private ViewPager viewPager;
    private MagicPagerAdapter magicPagerAdapter;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get view from layout
        setContentView(R.layout.activity_magic);

        //Get teh viewPager on the activity
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Set ViewPagerAdapter into ViewPager
        magicPagerAdapter = new MagicPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(magicPagerAdapter);

        //Try to reference fragment and change dynamically
        /*MagicPagerAdapter testing = (MagicPagerAdapter) viewPager.getAdapter();
        EmailFragment emailFragment = (EmailFragment)testing.getItem(0);
        emailFragment.setViewText();
        */
        /*String test = viewPager.getAdapter().getPageTitle(0).toString();
        String blah = "tasdf";*/

    }

    public void alertBox(View view) {
        Toast.makeText(getApplicationContext(), "Magic activity is working!! rawr >:O",
                Toast.LENGTH_SHORT).show();

        EmailFragment emailFragment = (EmailFragment) magicPagerAdapter.getItem(0);
        emailFragment.updateText(view);
    }



}
