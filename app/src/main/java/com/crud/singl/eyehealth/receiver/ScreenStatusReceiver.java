package com.crud.singl.eyehealth.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.crud.singl.eyehealth.service.UsageService;
import com.crud.singl.eyehealth.util.Utils;

/**
 * Created by singl on 3/19/2018.
 */

public class ScreenStatusReceiver extends BroadcastReceiver {

    private boolean userPresent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d(Utils.LOG_TAG, "received ACTION_SCREEN_OFF");
            userPresent = false;
        } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Log.d(Utils.LOG_TAG, "received ACTION_USER_PRESENT");
            userPresent = true;
        }

        Intent i = new Intent(context, UsageService.class);
        i.putExtra("user_present", userPresent);
        context.startService(i);
    }
}
