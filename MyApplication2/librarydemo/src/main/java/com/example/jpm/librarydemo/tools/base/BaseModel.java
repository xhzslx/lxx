package com.example.jpm.librarydemo.tools.base;

import android.content.Context;

import com.example.jpm.librarydemo.tools.util.RetrofitManager;

import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;


/**
 * Created by mayn on 2018/12/10.
 */

public class BaseModel {
    public RetrofitManager manager;

    public BaseModel(Context context,Class myclass) {
        if (manager == null) {
            manager = RetrofitManager.getInstance(context,myclass);
        }
    }

    public BaseModel() {
    }
}
