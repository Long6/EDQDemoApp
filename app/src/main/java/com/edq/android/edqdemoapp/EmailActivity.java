package com.edq.android.edqdemoapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edq.android.services.EmailClient;

import java.util.ArrayList;


public class EmailActivity extends Activity {

    private static final String TAG = "longLogger";
    private static RelativeLayout emailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(android.R.style.Theme_Holo_Light_DarkActionBar);
        super.onCreate(savedInstanceState);

        emailLayout = createView();

        //Set layout we just created
        setContentView(emailLayout);

        //Logger
        Log.i(TAG, "EmailActivity: onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_email, menu);
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

    private RelativeLayout createView() {
        //Layout
        RelativeLayout emailLayout = new RelativeLayout(this);
        emailLayout.setClickable(true);
        emailLayout.setPadding(32, 32, 32, 32);

        //Email Button
        final Button emailButton = new Button(this);
        emailButton.setText("Validate!");

        //Email field
        final EditText emailField = new EditText(this);
        emailField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        emailField.setHint("Email");

        //TextView - Validation status
        final TextView textView = new TextView(this);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        textView.setGravity(Gravity.TOP);

        //TextView2 - JSON Results
        final TextView textView2 = new TextView(this);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);


        //Set IDs
        emailButton.setId(1);
        emailField.setId(2);
        textView.setId(3);
        textView2.setId(4);

        //Button container Height/Width
        RelativeLayout.LayoutParams emailButtonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //Button Position
        emailButtonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        emailButtonParams.addRule(RelativeLayout.BELOW, textView.getId());
        emailButton.setBackgroundColor(getResources().getColor(R.color.buttons_bg));
        emailButton.setTextColor(getResources().getColor(R.color.main_button_text));

        //Button listener
        View.OnClickListener emailButtonListener = new View.OnClickListener() {
            public void onClick(View v) {

                String email = emailField.getText().toString();
                String emailResponse = "";
                Log.i(TAG, "EmailAcitivity: email=" + email);

                EmailClient emailClient = new EmailClient();
                try {
                    emailResponse = emailClient.validity(email);
                } catch (Exception e) {
                    Log.i(TAG, "EmailClient Exception: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }

                displayResponse(emailResponse);
            }
        };
        emailButton.setOnClickListener(emailButtonListener);

        //emailField container Width/Height
        RelativeLayout.LayoutParams emailFieldParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //emailField width
        int emailFieldWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
        //emailField.setWidth(emailFieldWidth);

        //emailField position
        emailFieldParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        emailFieldParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        emailFieldParams.setMargins(0, 120, 0, 0);

        //TextView container Height/Width
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //TextView2 container Height/Width
        RelativeLayout.LayoutParams textView2Params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //TextView position
        textViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewParams.addRule(RelativeLayout.BELOW, emailField.getId());
        textViewParams.setMargins(120, 20, 120, 20);

        //TextView2 position
        textView2Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textView2Params.addRule(RelativeLayout.BELOW, emailButton.getId());
        textView2Params.setMargins(40, 120, 40, 20);

        //add Views to layout
        emailLayout.addView(emailButton, emailButtonParams);
        emailLayout.addView(emailField, emailFieldParams);
        emailLayout.addView(textView, textViewParams);
        emailLayout.addView(textView2, textView2Params);

        return emailLayout;
    }

    public static void displayResponse(String response) {
        TextView textView = (TextView)emailLayout.findViewById(3);
        TextView textView2 = (TextView)emailLayout.findViewById(4);

        ArrayList<String> parsedResponse = parseResponse(response);
        textView.setText(parsedResponse.get(0));

        textView2.setText(response);

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static ArrayList<String> parseResponse(String json) {

        String result;
        ArrayList<String> parsedResponse = new ArrayList<String>();

        int begin = json.indexOf("result");
        int end = json.indexOf(",", begin);
        // + 3 as it considers “:” ; - 1 to remove the closing double quote
        result = json.substring(begin + "result".length() + 3, end - 1);
        parsedResponse.add(result);

        return parsedResponse;
    }
}
