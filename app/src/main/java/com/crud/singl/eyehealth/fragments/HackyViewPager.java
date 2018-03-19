package com.crud.singl.eyehealth.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.crud.singl.eyehealth.R;

import java.util.jar.Attributes;

public class HackyViewPager extends ViewPager {

    private  boolean isLocked;

    public HackyViewPager(Context context){
        super(context);
        isLocked = false;
    }

    public  HackyViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        isLocked = false;
    }

    @Override
    public  boolean onInterceptTouchEvent(MotionEvent ev){
        if(!isLocked){
            try{
                return  super.onInterceptTouchEvent(ev);
            }catch (IllegalArgumentException e){
                return  false;
            }
        }
        return  false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return  !isLocked && super.onTouchEvent(event);
    }

    public  void  toggleLock(){
        isLocked = !isLocked;
    }

    public void setLocked(boolean isLocked){
        this.isLocked = isLocked;
    }

    public boolean isLocked(){
        return isLocked;
    }
}
