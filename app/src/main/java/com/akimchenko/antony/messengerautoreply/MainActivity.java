package com.akimchenko.antony.messengerautoreply;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by antony on 7/15/17.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pushFragment(new MainFragment());
    }

    public void pushFragment(Fragment fragment) {
        if (!isFinishing()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commitAllowingStateLoss();
        }
    }
}
