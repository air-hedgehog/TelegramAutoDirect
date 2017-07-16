package com.akimchenko.antony.messengerautoreply;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by antony on 7/15/17.
 */

public class MainActivity extends AppCompatActivity {

    private static final String RESPONSE_STRING_SP_KEY = "responseStringKey";
    private static final String SKYPE_LOGIN_SP_KEY = "SkypeLoginKey";
    private static final String VIBER_LOGIN_SP_KEY = "ViberLoginKey";
    private static final String TELEGRAM_ATTACHED_SP_KEY = "TelegramAttachedKey";
    private static final String TELEGRAM_LINK_SP_KEY = "TelegramLinkKey";

    private SharedPreferences sharedPreferences;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        mainFragment = new MainFragment();
        pushFragment(mainFragment);
    }

    public void pushFragment(Fragment fragment) {
        if (!isFinishing()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commitAllowingStateLoss();
        }
    }

    public void setSkypeLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(SKYPE_LOGIN_SP_KEY, isLoggedIn).apply();
    }

    public void setViberLogedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(VIBER_LOGIN_SP_KEY, isLoggedIn).apply();
    }

    public void setTelegramAttached(boolean isAttached) {
        sharedPreferences.edit().putBoolean(TELEGRAM_ATTACHED_SP_KEY, isAttached).apply();
    }

    public boolean isSkypeLoggedIn() {
        return sharedPreferences.getBoolean(SKYPE_LOGIN_SP_KEY, false);
    }

    public boolean isViberLoggedIn() {
        return sharedPreferences.getBoolean(VIBER_LOGIN_SP_KEY, false);
    }

    public boolean isTelegramAttached() {
        return sharedPreferences.getBoolean(TELEGRAM_ATTACHED_SP_KEY, false);
    }

    public void setTelegramLink(String link) {
        sharedPreferences.edit().putString(TELEGRAM_LINK_SP_KEY, link).apply();
    }

    public String getTelegramLink() {
        return sharedPreferences.getString(TELEGRAM_LINK_SP_KEY, "");
    }

    public void setResponseString(String string) {
        if (!isTelegramAttached() && string.endsWith(getTelegramLink())) {
            string.replaceAll(getTelegramLink(), "");
        }
        sharedPreferences.edit().putString(RESPONSE_STRING_SP_KEY, string).apply();
    }

    public String getResponseString() {
        return sharedPreferences.getString(RESPONSE_STRING_SP_KEY, "");
    }
}
