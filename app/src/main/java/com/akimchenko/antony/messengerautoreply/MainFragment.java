package com.akimchenko.antony.messengerautoreply;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by antony on 7/15/17.
 */

public class MainFragment extends Fragment {

    private static final String MESSAGE_BUNDLE_KEY = "autoMessage";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        EditText autoMessage = (EditText) view.findViewById(R.id.auto_message_edit_text);
        savedInstanceState.putString(MESSAGE_BUNDLE_KEY, autoMessage.getText().toString());
        autoMessage.setText(savedInstanceState.getString(MESSAGE_BUNDLE_KEY));
        return view;
    }
}
