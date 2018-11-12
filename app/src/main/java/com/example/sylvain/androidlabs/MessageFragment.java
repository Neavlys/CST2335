package com.example.sylvain.androidlabs;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sylvain.androidlabs.R;

public class MessageFragment extends Fragment {

    Button deleteButton;

    ChatWindow parent = null;

    public boolean iAmTablet;

    public MessageFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Bundle infoToPass = getArguments();

        String passedMessage = infoToPass.getString("Message");
        final long idPassed = infoToPass.getLong("ID");

        View screen = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView message = screen.findViewById(R.id.messageHere);
        TextView id = screen.findViewById(R.id.idHere);

        message.setText("Message is: " + passedMessage);
        id.setText("ID= " + idPassed);

        deleteButton = (Button) screen.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iAmTablet) {
                    parent.deleteMessage(idPassed);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(parent.getFragment());
                    fragmentTransaction.commit();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("ID", idPassed);
                    getActivity().setResult(69, intent);
                    getActivity().finish();
                }
            }
        });

        return screen;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        if(iAmTablet)
            parent = (ChatWindow)  context;
    }
}
