package com.ohchang.moonjoo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Switch switchHeadset;

    private boolean isRegisteredReceiver() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (ReceiverService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchHeadset = (Switch) findViewById(R.id.switch_headset);

        switchHeadset.setChecked(isRegisteredReceiver());

        switchHeadset.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(getApplicationContext(), ReceiverService.class);

                if (isChecked) {
                    startService(intent);
                } else {
                    stopService(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        switchHeadset.setChecked(isRegisteredReceiver());
    }
}
