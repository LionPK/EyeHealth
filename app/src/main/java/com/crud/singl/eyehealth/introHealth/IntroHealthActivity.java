package com.crud.singl.eyehealth.introHealth;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crud.singl.eyehealth.R;
import com.crud.singl.eyehealth.startup.BottomNavigationViewHelper;
import com.crud.singl.eyehealth.startup.MainActivity;
import com.crud.singl.eyehealth.startup.MenuActivity;

public class IntroHealthActivity extends AppCompatActivity {
    private BottomNavigationView bottomNave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_health);

        bottomNave = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);

        //set bottom action
        final Activity a = IntroHealthActivity.this;

        findViewById(R.id.bottom_eyeDis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(a, IntroOneActivity.class));
            }
        });

        findViewById(R.id.bottom_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(a, IntroTwoActivity.class));
            }

        });

        findViewById(R.id.button_sight).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(a, IntroThreeActivity.class));
            }
        });



        //set bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNave.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.screen_id:
                        bottomNave.setItemBackgroundResource(R.color.colorPrimary);
                        Intent intent0 = new Intent(IntroHealthActivity.this, MainActivity.class);
                        startActivity(intent0);

                        break;

                    case R.id.eye_id:
                        bottomNave.setItemBackgroundResource(R.color.colorAccent);
                        Intent intent1 = new Intent(IntroHealthActivity.this, MenuActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.intro_id:
                        bottomNave.setItemBackgroundResource(R.color.colorPrimary);

                        break;
                }

                return false;
            }
        });
    }
}
