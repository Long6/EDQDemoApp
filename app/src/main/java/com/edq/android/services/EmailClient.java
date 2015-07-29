package com.edq.android.services;

import android.util.Log;

import com.edq.android.edqdemoapp.*;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Long on 7/10/2015.
 */
public class EmailClient {

    private static final String TAG = "longLogger";

    //private static final String ENDPOINT = "https://stgapi.qasemail.qas.com/v2/validity/foo?key=ahuE1VM9MZA3nkewqEvsqUsaIUXDgrAzCbhvccjewiM&timeout=5";
    //private static final String ENDPOINT = "https://www.google.com";
    private static final String ENDPOINT = "https://stgapi.qasemail.qas.com/v2";
    private static String KEY;
    private static String timeout;

    public EmailClient() {

        try {
            /*
            KEY = Resources.getSystem().getString(R.string.emailKey);
            timeout = Resources.getSystem().getString(R.string.emailTimeout);
            */
            KEY = "ahuE1VM9MZA3nkewqEvsqUsaIUXDgrAzCbhvccjewiM";
            timeout = "5";
        } catch (Exception e) {
            Log.i(TAG, "EmailService: constructor exception: " + e.getLocalizedMessage());
        }
    }

    public String validity(String email) throws Exception {

        StringBuffer response = new StringBuffer();

        String call = buildCall(email);
        URL validityUrl = new URL(ENDPOINT + "/validity/" + call.replaceAll("\\s", ""));

        //Logging
        Log.i(TAG, "EmailClient Validity Call=" + validityUrl);


        //Define trustAll manager
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers()
                    {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                }
        };

        //Set SSL TrustAll
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, trustAllCerts, null);

        HttpsURLConnection urlConnection = (HttpsURLConnection) validityUrl.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setSSLSocketFactory(context.getSocketFactory());

        int responseCode = urlConnection.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        Log.i(TAG, "EmailClient: response= " + response.toString());

        return response.toString();
    }


    //Build URI for GET call
    private String buildCall(String email) {

        String call = email + "?key=" +
                KEY + "&timeout=" +
                timeout;

        //call = "https://www.google.com/search?q=test";
        return call;
    }

    //Overload w/Timeout
    private String buildCall(String email, String timeout) {

        String call = email + "?key=" +
                KEY + "&timeout=" +
                timeout;

        //call = "https://www.google.com/search?q=test";
        return call;
    }

    //Overload w/timeout + key
    private String buildCall(String email, String KEY, String timeout) {

        String call = email + "?key=" +
                KEY + "&timeout=" +
                timeout;

        //call = "https://www.google.com/search?q=test";
        return call;
    }

}
