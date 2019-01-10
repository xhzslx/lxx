package com.example.jpm.myapplication;

import android.content.Context;

import com.example.jpm.librarydemo.tools.base.MyApplication;
import com.example.jpm.librarydemo.tools.util.SharePreferencesUtil;

/**
 * Created by mayn on 2018/12/10.
 */

public class MyApplicationTest extends MyApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        SharePreferencesUtil.put(base,"baseurl",
                "http://122.193.93.219:1002/Service.svc/");
    }
}
