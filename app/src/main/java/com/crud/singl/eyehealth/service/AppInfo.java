package com.crud.singl.eyehealth.service;

/**
 * Created by singl on 3/19/2018.
 */

public class AppInfo {

    private String packageName;
    private boolean userApp;

    public AppInfo(String packageName, boolean userApp) {
        this.packageName = packageName;
        this.userApp = userApp;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isUserApp() {
        return userApp;
    }

    public void setUserApp(boolean userApp) {
        this.userApp = userApp;
    }
}
