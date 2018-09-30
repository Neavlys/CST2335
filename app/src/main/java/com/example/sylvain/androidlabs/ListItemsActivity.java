package com.example.sylvain.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {

    protected static final String activityName = "ListItemsActivity";

    public Activity getActivity() {
        return this;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    protected void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(activityName,"In OnCreate()");

        final ImageButton cameraButton = (ImageButton)findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        final Switch checkSwitch = (Switch)findViewById(R.id.switch2);
        checkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;
                Toast toast;

                if (isChecked) {
                    text = getResources().getString(R.string.switchOn);
                    duration = Toast.LENGTH_SHORT;
                    toast = Toast.makeText(getActivity(), text, duration);
                    toast.show();
                } else {
                    text = getResources().getString(R.string.switchOff);
                    duration = Toast.LENGTH_LONG;
                    toast = Toast.makeText(getActivity(), text, duration);
                    toast.show();
                }
            }
        });

        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                    builder.setMessage(R.string.dialogMessage);
                    builder.setTitle(R.string.dialogTitle);

                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("Response", getResources().getString(R.string.response));
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }
                    });

                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkBox.setChecked(false);
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageButton cameraButton = (ImageButton)findViewById(R.id.cameraButton);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            cameraButton.setImageBitmap(imageBitmap);
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
