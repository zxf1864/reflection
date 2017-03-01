package com.example.administrator.reflection.module;

import android.content.Context;

import com.example.administrator.reflection.R;
import com.example.administrator.reflection.activity.Activity2;
import com.example.administrator.reflection.reflection.ActivityModule;

/**
 * Created by Administrator on 2017/2/28 0028.
 */
public class ActivityModule2 extends ActivityModule {
    public ActivityModule2(Context context) {
        super(context);
        setModuleImgRes(R.mipmap.ic_launcher);
        setModuleNameRes(R.string.activity_module2);
        setTargetClass(Activity2.class);
    }
}
