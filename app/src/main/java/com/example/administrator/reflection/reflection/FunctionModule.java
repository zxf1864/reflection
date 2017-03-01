package com.example.administrator.reflection.reflection;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.reflection.R;

/**
 * 功能模块
 * 
 * @author way
 * 
 */
public abstract class FunctionModule {
	private Integer moduleNameRes;
	private String moduleName;
	private Integer moduleImgRes;
	protected Context mContext;
	private String titleColor;

	public FunctionModule(Context context) {
		this.mContext = context;
	}

	public abstract void onClick(AdapterView<?> adapterView, View view, int position, long arg3);

	public View getView(View convertView, int layoutId) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = initView(layoutId);
			viewHolder = new ViewHolder();
			viewHolder.titleTv = (TextView) convertView.findViewById(R.id.title_name);
			viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		putDataOnView(viewHolder);

		return convertView;
	}

	protected void putDataOnView(ViewHolder viewHolder) {
		if (viewHolder.titleTv != null && getModuleNameRes() != null)
			viewHolder.titleTv.setText(getModuleNameRes());
		if (getTitleColor() != null)
			viewHolder.linearLayout.setBackgroundColor(Color.parseColor(getTitleColor()));
		;
		if (viewHolder.imageView != null && getModuleImgRes() != null)
			viewHolder.imageView
					.setImageDrawable(mContext.getResources().getDrawable(getModuleImgRes()));
	}

	protected View initView(int layoutId) {
		if (layoutId != -1) {
			return LayoutInflater.from(mContext).inflate(layoutId, null);
		} else {
			return LayoutInflater.from(mContext).inflate(R.layout.item_home_gridview, null);
		}
	}

	public Integer getModuleNameRes() {
		return moduleNameRes;
	}

	public void setModuleNameRes(Integer moduleNameRes) {
		this.moduleNameRes = moduleNameRes;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getModuleImgRes() {
		return moduleImgRes;
	}

	public void setModuleImgRes(Integer moduleImgRes) {
		this.moduleImgRes = moduleImgRes;
	}

	public class ViewHolder {
		public TextView titleTv;
		public LinearLayout linearLayout;
		public ImageView imageView;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

}
