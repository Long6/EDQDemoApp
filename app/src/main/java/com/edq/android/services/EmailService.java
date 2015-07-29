package com.edq.android.services;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request.Method;
import com.edq.android.edqdemoapp.*;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.android.*;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.*;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Long on 6/5/2015.
 */
public class EmailService {

    private static final String TAG = "longLogger";

    private static final String ENDPOINT = "https://stgapi.qasemail.qas.com/v2/validity/";
    private static String KEY;
    private static String timeout;

    public EmailService() {

        try {

            KEY = Resources.getSystem().getString(R.string.emailKey);
            timeout = Resources.getSystem().getString(R.string.emailTimeout);

        } catch (Exception e) {
            Log.i(TAG, "EmailService: constructor exception: " + e.getLocalizedMessage());
        }
    }


    public String validateEmail(String email, final Context context) {

        //Using Volley
        String url = buildUrl(email);
        final String response = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        Log.i(TAG, "EmailService: callResponse: " + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "EmailService: volleyResponse: " + error.getLocalizedMessage());
                    }
        });
        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

        return response;
    }

    //Build URI for GET call
    private String buildUrl(String email) {

        String call = ENDPOINT +
                email + "?key=" +
                KEY + "&timeout=" +
                timeout;

        //call = "https://www.google.com/search?q=test";
        return call;
    }

}
