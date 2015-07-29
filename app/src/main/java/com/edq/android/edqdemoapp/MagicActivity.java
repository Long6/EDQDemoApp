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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.edq.android.fragments.AddressFragment;
import com.edq.android.fragments.EmailFragment;
import com.edq.android.fragments.PhoneFragment;
import com.edq.android.helper.HideKeyboardFocusChangeListener;
import com.edq.android.services.EmailClient;



public class MagicActivity extends FragmentActivity {

    private static final String TAG = "longLogger";



/*None functioning implementation of TabHost
    private TabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_magic);
        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        //tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabSpec tab3 = tabHost.newTabSpec("Third Tab");

        tab1.setIndicator("EDQ Email");
        tab1.setContent(new Intent(this,EmailFragment.class));

        tab2.setIndicator("EDQ Phone");
        tab2.setContent(new Intent(this,PhoneFragment.class));

        tab3.setIndicator("EDQ Address");
        tab3.setContent(new Intent(this,AddressFragment.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
*/

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

        /*MagicPagerAdapter testing = (MagicPagerAdapter) viewPager.getAdapter();
        EmailFragment emailFragment = (EmailFragment)testing.getItem(0);
        emailFragment.setViewText();
        */
        /*String test = viewPager.getAdapter().getPageTitle(0).toString();
        String blah = "tasdf";*/
    }


}
