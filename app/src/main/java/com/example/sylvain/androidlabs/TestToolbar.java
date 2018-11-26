package com.example.sylvain.androidlabs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TestToolbar extends AppCompatActivity {

    public AppCompatActivity getActivity() {return this;}

    Button toolbarBtn;
    String currentMessage;
    EditText toolBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar lab8_toolbar = (Toolbar)findViewById(R.id.lab8_toolbar);
        setSupportActionBar(lab8_toolbar) ;

        currentMessage = "You selected item 1";

        toolbarBtn = (Button)findViewById(R.id.toolBarButton);
        toolbarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.toolBarButton), "This is a Snackbar", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {
        switch(mi.getItemId()) {
            case R.id.action_one:
                Log.d("Toolbar", "Option 1 selected");
                Snackbar.make(findViewById(R.id.action_one), currentMessage, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_two:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_Title);
// Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.action_three:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                Context ctx = builder2.getContext();
                LayoutInflater inflater = LayoutInflater.from(ctx);
                View view = inflater.inflate(R.layout.item_three_layout, null, false);
                toolBarText = (EditText)view.findViewById(R.id.toolBarText);
                builder2.setView(view);
// Add the buttons
                builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        currentMessage = toolBarText.getText().toString();
                    }
                });
                builder2.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
// Create the AlertDialog
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
                break;
            case R.id.settings:
                Toast toast = Toast.makeText(getActivity(), "Version 1.0 by Sylvain Cashman", Toast.LENGTH_SHORT);
                toast.show();
        }
        return true;
    }
}
