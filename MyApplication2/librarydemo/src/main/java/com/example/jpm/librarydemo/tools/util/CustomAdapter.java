package com.example.jpm.librarydemo.tools.util;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public abstract class CustomAdapter<T> extends CommonAdapter<T> {


    public CustomAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    //加入新的数据
    public void setbeanList(List<T> list) {
        mDatas.clear();
        if (!list.isEmpty()) {
            this.mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    //添加数据
    public void refreshData(List<T> newList) {

        mDatas.addAll(newList);
        notifyDataSetChanged();

    }
}
