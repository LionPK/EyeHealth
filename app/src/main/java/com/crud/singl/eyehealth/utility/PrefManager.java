package com.crud.singl.eyehealth.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by singl on 3/18/2018.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // โหมด pref ที่ใช้ร่วมกัน
    int PRIVATE_MODE = 0;

    // ค่าที่ตั้งร่วมกันที่มีการแชร์
    private  static final String PREF_NAME = "MyPreference";
    private  static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setIsFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public  boolean isFirstTimeLaunch(){
        return  pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
