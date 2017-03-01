package com.example.administrator.reflection.reflection;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.reflection.reflection.FunctionModule;

/**
 * 点击后跳转界面的功能模块
 * @author way
 *
 */
public abstract class ActivityModule extends FunctionModule {


	public ActivityModule(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	protected Class<?> targetClass;


	@Override
	public void onClick(AdapterView<?> adapterView, View view, int position,
			long arg3) {
		if (targetClass == null) {
			return;
		}
		Intent intent =new Intent();
		intent.setClass(mContext, targetClass);
		mContext.startActivity(intent);
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<?> targetCLass) {
		this.targetClass = targetCLass;
	}
	

}
