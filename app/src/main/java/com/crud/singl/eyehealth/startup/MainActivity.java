package com.crud.singl.eyehealth.startup;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.crud.singl.eyehealth.R;
import com.crud.singl.eyehealth.adapter.MyFragmentPagerAdapter;
import com.crud.singl.eyehealth.fragments.InstalledDialogFragment;
import com.crud.singl.eyehealth.introHealth.IntroHealthActivity;
import com.crud.singl.eyehealth.model.StatEntry;
import com.crud.singl.eyehealth.receiver.PhoneBootReceiver;
import com.crud.singl.eyehealth.service.UsageService;
import com.crud.singl.eyehealth.util.Utils;
import com.crud.singl.eyehealth.view.SlidingTabLayout;

public class MainActivity extends AppCompatActivity implements InstalledDialogFragment.ChooserFragmentInterface{
    private BottomNavigationView bottomNave;

    private static final int OFFSCREEN_PAGE_LIMIT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNave = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        viewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // disable PhoneBootReceiver on Lollipop+
            ComponentName component = new ComponentName(this, PhoneBootReceiver.class);
            getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED , PackageManager.DONT_KILL_APP);
        }


        //set bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNave.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.screen_id:
                        bottomNave.setItemBackgroundResource(R.color.colorPrimary);

                        break;

                    case R.id.eye_id:
                        bottomNave.setItemBackgroundResource(R.color.colorAccent);
                        Intent intent1 = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.intro_id:
                        bottomNave.setItemBackgroundResource(R.color.colorPrimary);
                        Intent intent2 = new Intent(MainActivity.this, IntroHealthActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(Utils.LOG_TAG, "MainActivity.onStart()");

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.d(Utils.LOG_TAG, "pre-Lollipop");
            startService(new Intent(this, UsageService.class));
        }
    }

    @Override
    public void onChooserFragmentResult(int choice, Object dataObject) {
        StatEntry entry = (StatEntry)dataObject;
        String packageName = entry.getPackageName();

        switch (choice) {
            case Utils.DIALOG_LAUNCH:
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.installed_cannot_be_launched), Toast.LENGTH_SHORT).show();
                }
                break;

            case Utils.DIALOG_DETAILS:
                Intent intent = new Intent(this, DetailsActivity.class);
                intent.putExtra(Utils.INTENT_PACKAGE_NAME, packageName);
                startActivity(intent);
                break;

            case Utils.DIALOG_SYSTEM_MENU:
                openSystemMenu(packageName);
                break;

            case Utils.DIALOG_UNINSTALL:
                Uri packageURI = Uri.parse("package:" + packageName);
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                startActivity(uninstallIntent);
                break;

            default:
                break;
        }
    }

    private void openSystemMenu(String packageName) {
        try {
            // เปิดหน้าข้อมูลแอ็พพลิเคชันเฉพาะ:
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + packageName));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // เปิดหน้า Apps ทั่วไป:
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            startActivity(intent);
        }
    }
}
