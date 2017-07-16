package com.akimchenko.antony.messengerautoreply;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by antony on 7/15/17.
 */

public class MainFragment extends Fragment {

    private static final String MESSAGE_BUNDLE_KEY = "autoMessage";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return view;
        EditText autoMessage = view.findViewById(R.id.auto_message_edit_text);
        ImageView skypeButton = view.findViewById(R.id.skype_button);
        ImageView viberbutton = view.findViewById(R.id.viber_button);
        RelativeLayout telegramButton = view.findViewById(R.id.telegram_link_add);
        ImageView telegramIcon = telegramButton.findViewById(R.id.telegram_button_icon);
        TextView telegramButtonText = view.findViewById(R.id.telegram_button_text);
        skypeButton.setImageDrawable(ContextCompat.getDrawable(activity, activity.isSkypeLoggedIn() ? R.drawable.ic_skype : R.drawable.ic_skype_disabled));
        viberbutton.setImageDrawable(ContextCompat.getDrawable(activity, activity.isViberLoggedIn() ? R.drawable.ic_viber : R.drawable.ic_viber_disabled));
        telegramIcon.setImageDrawable(ContextCompat.getDrawable(activity, activity.isTelegramAttached() ? R.drawable.ic_telegram : R.drawable.ic_telegram_disabled));
        telegramButtonText.setText(activity.getResources().getString(activity.isTelegramAttached() ? R.string.telegram_link_attached : R.string.telegram_link_not_attached));
        telegramButtonText.setTextColor(ContextCompat.getColor(activity, activity.isTelegramAttached() ? R.color.colorAccent : R.color.greyInactive));

        autoMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity activity1 = (MainActivity) getActivity();
                if (activity1 == null) return;
                activity1.setResponseString(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        autoMessage.setText(activity.getResponseString());
        return view;
    }
}
