package com.edq.android.edqdemoapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.app.ActionBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.support.v4.app.FragmentActivity;


public class MainActivity extends FragmentActivity {

    private static final String TAG = "longLogger";
    private static RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);

        //Permissions to make calls to email web service
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Enable app icon as the Up button
        if(getActionBar() != null) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Logger
        Log.i(TAG, "MainActivity: onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Click button_show_magic
    public void switchMagicView(View magicView) {

        Intent intentMagic = new Intent(this, MagicActivity.class);
        startActivity(intentMagic);

/*
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MagicActivity fragment = new MagicActivity();
        transaction.replace(R.id.sample_content_fragment, fragment);
        transaction.commit();
*/

        //Logger
        Log.i(TAG, "switchMagicView");
    }

    //Click Email Validation
    public void switchEmailView(View emailView) {
        Intent intentEmail = new Intent(this, EmailActivity.class);
        startActivity(intentEmail);

        //Logger
        Log.i(TAG, "switchEmailView");
    }
}
