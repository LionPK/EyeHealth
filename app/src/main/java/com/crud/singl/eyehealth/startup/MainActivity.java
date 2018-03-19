package com.crud.singl.eyehealth.startup;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.crud.singl.eyehealth.R;
import com.crud.singl.eyehealth.fragments.InstalledDialogFragment;
import com.crud.singl.eyehealth.view.SlidingTabLayout;

public class MainActivity extends AppCompatActivity implements InstalledDialogFragment.ChooserFragmentInterface{

    private static final int OFFSCREEN_PAGE_LIMIT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setLogo(R.mipmap.ic_launcher);
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
    }
}
