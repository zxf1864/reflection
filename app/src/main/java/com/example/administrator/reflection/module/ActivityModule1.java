package com.example.administrator.reflection.module;

import android.content.Context;

import com.example.administrator.reflection.MainActivity;
import com.example.administrator.reflection.R;
import com.example.administrator.reflection.activity.Activity1;
import com.example.administrator.reflection.reflection.ActivityModule;

/**
 * Created by Administrator on 2017/2/28 0028.
 */
public class ActivityModule1 extends ActivityModule {
    public ActivityModule1(Context context) {
        super(context);
        setModuleImgRes(R.mipmap.ic_launcher);
        setModuleNameRes(R.string.activity_module1);
        setTargetClass(Activity1.class);
    }
}
