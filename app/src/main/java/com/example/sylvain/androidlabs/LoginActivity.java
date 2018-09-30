package com.example.sylvain.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    protected static final String activityName = "LoginActivity";

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(activityName,"In OnCreate()");

        final Button loginButton = (Button)findViewById(R.id.loginButton);
        final EditText loginText = (EditText)findViewById(R.id.loginText);

        Context ctx = getActivity();
        final SharedPreferences saveEmail = ctx.getSharedPreferences(getString(R.string.defaultEmail) ,Context.MODE_PRIVATE);

        final String defaultValue = saveEmail.getString("defaultEmail", "email@domain.com");
        loginText.setText(defaultValue);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = loginText.getText().toString();

                SharedPreferences.Editor editor = saveEmail.edit();
                editor.putString("defaultEmail", newEmail);
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        Log.i(activityName, "In OnResume()");
    }

    protected void onStart(){
        super.onStart();
        Log.i(activityName, "In OnStart()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(activityName, "In OnStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(activityName, "In OnDestroy()");
    }
}
