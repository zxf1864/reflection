package com.example.administrator.reflection.reflection;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class FunctionModuleAdapter extends BaseAdapter {
	private List<FunctionModule> data;
	private int layoutId = -1;

	/**
	 * 使用默认的item布局，调用该构造方法
	 * @param modules
	 */
	public FunctionModuleAdapter(List<FunctionModule> modules) {
		this.data = modules;
	}

	/**
	 * 自定义gridview item时，使用该构造方法，传入自定义布局的资源id </br>
	 * 注:自定义布局暂时只支持一个ViewGroup 里面可以有一个ImageView和TextView 且id要与默认的item布局一致</br>
	 * 后续有需求可在修改
	 * @param data
	 * @param layoutId
	 */
	public FunctionModuleAdapter(List<FunctionModule> data, int layoutId) {
		this.data = data;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public FunctionModule getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return data.get(position).getView(convertView, layoutId);
	}

}
