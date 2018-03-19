package com.crud.singl.eyehealth.startup;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.crud.singl.eyehealth.R;

public class SplashActivity extends Activity implements Animation.AnimationListener{
    Animation animFadeIn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(Build.VERSION.SDK_INT < 22){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            View decorView = getWindow().getDecorView();
            //ซ่อน status bar
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            //โปรดจำไว้ว่าคุณไม่ควรแสดงแถบการทำงานถ้าแถบสถานะถูกซ่อนซ่อนไว้เพื่อซ่อนสิ่งนี้ด้วยหากจำเป็น
        }
        // โหลดภาพเคลื่อนไหว
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in);
        // ตั้งค่าการฟังภาพเคลื่อนไหว
        animFadeIn.setAnimationListener(this);
        // ภาพเคลื่อนไหวสำหรับภาพ
        linearLayout = (LinearLayout) findViewById(R.id.layout_linear);
        // เริ่มการแสดงภาพเคลื่อนไหว
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.startAnimation(animFadeIn);
    }

    @Override
    public  void onBackPressed(){
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // ภายใต้การใช้งาน
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // เริ่มต้นหน้าจอหลัก
        Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // ภายใต้การใช้งาน
    }
}
