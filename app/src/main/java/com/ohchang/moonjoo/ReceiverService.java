package com.ohchang.moonjoo;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ReceiverService extends Service {
    private HeadsetIntentReceiver receiver;

    public void startReceiver() {
        if (receiver == null) {
            receiver = new HeadsetIntentReceiver();
        }

        registerReceiver(receiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
    }

    public void stopReceiver() {
        unregisterReceiver(receiver);
        receiver = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(getClass().getSimpleName(), "start ReceiverService");
        super.onStartCommand(intent, flags, startId);

        startReceiver();
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        stopReceiver();
    }
}
