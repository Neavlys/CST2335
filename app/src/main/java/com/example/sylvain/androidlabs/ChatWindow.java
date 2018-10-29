package com.example.sylvain.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.sylvain.androidlabs.ChatDatabaseHelper.KEY_MESSAGE;
import static com.example.sylvain.androidlabs.ChatDatabaseHelper.TABLE_NAME;


public class ChatWindow extends Activity {

    protected static final String ACTIVITY_NAME = "ChatWindow";

    ListView chatView;
    EditText sendText;
    Button sendButton;
    ArrayList<String> chatArray;

    Context ctx;
    ChatDatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        chatView = (ListView)findViewById(R.id.chatView);
        sendText = (EditText)findViewById(R.id.sendText);
        sendButton = (Button)findViewById(R.id.sendButton);
        chatArray = new ArrayList<String>();

        final ChatAdaptor messageAdaptor = new ChatAdaptor(this);
        chatView.setAdapter(messageAdaptor);

        ctx = this;
        dbHelper = new ChatDatabaseHelper(ctx);
        db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME, null);

        cursor.moveToFirst();

        Log.i(ACTIVITY_NAME, "Cursor's column count=" + cursor.getColumnCount());
        for(int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "Cursor's column name:" + cursor.getColumnName(i));
        }

        while(!cursor.isAfterLast()) {
            chatArray.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(
                    cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatArray.add(sendText.getText().toString());
                messageAdaptor.notifyDataSetChanged();

                ContentValues cv = new ContentValues();
                cv.put(ChatDatabaseHelper.KEY_MESSAGE, sendText.getText().toString());
                db.insert(ChatDatabaseHelper.TABLE_NAME, "", cv);

                sendText.setText("");
            }
        });
    }

    private class ChatAdaptor extends ArrayAdapter<String> {
        public ChatAdaptor(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return chatArray.size();
        }

        public String getItem(int position) {
            return chatArray.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result = null;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView)result.findViewById(R.id.messageText);
            message.setText(getItem(position));
            return result;
        }

        public long getItemId(int position) {
            return position;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
