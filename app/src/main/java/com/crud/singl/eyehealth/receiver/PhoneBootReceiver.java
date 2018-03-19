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

public class PhoneBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Utils.LOG_TAG, "received android.intent.action.BOOT_COMPLETED in "
                + PhoneBootReceiver.class.getSimpleName());

        Intent startServiceIntent = new Intent(context, UsageService.class);
        context.startService(startServiceIntent);
    }
}
