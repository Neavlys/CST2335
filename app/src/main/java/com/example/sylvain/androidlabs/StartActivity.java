package com.example.sylvain.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    protected static final String activityName = "StartActivity";

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(activityName,"In OnCreate()");

        Button startButton = (Button)findViewById(R.id.button5);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 50);
            }
        });

        Button startChatBtn = (Button)findViewById(R.id.startChatBtn);
        startChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(activityName, "User clicked Start Chart");

                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == 50) {
            Log.i(activityName, "Returned to StartActivity.onActivityResult");
        }

        if (responseCode == RESULT_OK) {
            String messagePassed = data.getStringExtra("Response");
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(getActivity(), messagePassed, duration);
            toast.show();
        }
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
