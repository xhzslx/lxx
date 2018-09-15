package com.example.jpm.librarydemo.tools.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.jpm.librarydemo.tools.base.ResUtil;
import com.example.jpm.librarydemo.tools.widget.DivItemDecoration;
import com.example.jpm.librarydemo.tools.widget.RecyclerViewEmptySupport;
import com.zhy.adapter.recyclerview.CommonAdapter;


public class RecyclerViewUtil {
    private static LinearLayoutManager layoutManager;

    public static <T> void setRecyclerViewEmpty(Activity context, RecyclerViewEmptySupport recyclerView,
                                                int divheight, CommonAdapter<T> adapter,
                                                String orientation, boolean hasHeand, View view) {
        layoutManager = new LinearLayoutManager(context);
        if (!TextUtils.isEmpty(orientation)) {
            layoutManager.setOrientation(Integer.parseInt(orientation));
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivItemDecoration(divheight, hasHeand));
        recyclerView.setEmptyView(view);
        recyclerView.setAdapter(adapter);
    }

    public static <T> void setRecyclerViewEmpty(Context context, RecyclerViewEmptySupport recyclerView,
                                                int divheight, CommonAdapter<T> adapter,
                                                String orientation, boolean hasHeand, View view) {
        layoutManager = new LinearLayoutManager(context);
        if (!TextUtils.isEmpty(orientation)) {
            layoutManager.setOrientation(Integer.parseInt(orientation));
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivItemDecoration(divheight, hasHeand));
        recyclerView.setEmptyView(view);
        recyclerView.setAdapter(adapter);
    }


    public static <T> void setRecyclerView(Activity context, RecyclerView recyclerView,
                                                int divheight, CommonAdapter<T> adapter,
                                                String orientation, boolean hasHeand, View view) {
        layoutManager = new LinearLayoutManager(context);
        if (!TextUtils.isEmpty(orientation)) {
            layoutManager.setOrientation(Integer.parseInt(orientation));
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivItemDecoration(divheight, hasHeand));
        recyclerView.setAdapter(adapter);
    }

    public static <T> void setRecyclerView(Context context, RecyclerView recyclerView,
                                           int divheight, CommonAdapter<T> adapter,
                                           String orientation, boolean hasHeand, View view) {
        layoutManager = new LinearLayoutManager(context);
        if (!TextUtils.isEmpty(orientation)) {
            layoutManager.setOrientation(Integer.parseInt(orientation));
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivItemDecoration(divheight, hasHeand));
        recyclerView.setAdapter(adapter);
    }

}
