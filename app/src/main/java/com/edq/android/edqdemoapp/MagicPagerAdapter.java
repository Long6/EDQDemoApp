package com.edq.android.edqdemoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.edq.android.fragments.*;

/**
 * Created by Long on 7/18/2015.
 */
public class MagicPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 3;
    private FragmentManager fm;
    private String emailTag;
    private EmailFragment emailFragment;
    private String phoneTag;
    private PhoneFragment phoneFragment;

    //Tab Titles
    private String tabTitles[] = new String[] {
            "EDQ Email",
            "EDQ Phone",
            "EDQ Address"
    };

    public MagicPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open EmailFragment.java
            case 0:
                EmailFragment emailFragment = new EmailFragment();
                return emailFragment;

            // Open PhoneFragment.java
            case 1:
                PhoneFragment phoneFragment = new PhoneFragment();
                return phoneFragment;

            // Open AddressFragment.java
            case 2:
                AddressFragment addressFragment = new AddressFragment();
                return addressFragment;
        }
        return null;
    }

    // Here we can finally safely save a reference to the created
    // Fragment, no matter where it came from (either getItem() or
    // FragmentManger). Simply save the returned Fragment from
    // super.instantiateItem() into an appropriate reference depending
    // on the ViewPager position.
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        switch (position) {
            case 0:
                emailFragment = (EmailFragment) createdFragment;
                emailTag = createdFragment.getTag();
                break;
            case 1:
                phoneTag = createdFragment.getTag();
                phoneFragment = (PhoneFragment)createdFragment;
                break;
        }
        return createdFragment;
    }

    @Override
    public String getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //Helper function to get fragmentId
    private static String makeFragmentName(int viewId, int index)
    {
        return "android:switcher:" + viewId + ":" + index;
    }

    public String getEmailTag() {
        return emailTag;
    }

}
