package com.ohchang.moonjoo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by Naver on 2016-06-16.
 */
public class HeadsetIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);

            switch(state) {
                case 0:
                    Log.d(getClass().getSimpleName(), "Headset is unplugged");
                    Toast.makeText(context, "이어폰이 빠졌습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Log.d(getClass().getSimpleName(), "Headset is plugged");
                    Toast.makeText(context, "이어폰이 끼워졌습니다.", Toast.LENGTH_SHORT).show();



                    long eventTime = SystemClock.uptimeMillis();

                    Intent downIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
                    downIntent.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(eventTime, eventTime,
                            KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0));
                    context.sendOrderedBroadcast(downIntent, null);

                    Intent upIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
                    upIntent.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(eventTime, eventTime,
                            KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0));
                    context.sendOrderedBroadcast(upIntent, null);

                    break;
                default:
                    Log.e(getClass().getSimpleName(), "some error");
            }
        }
    }
}
