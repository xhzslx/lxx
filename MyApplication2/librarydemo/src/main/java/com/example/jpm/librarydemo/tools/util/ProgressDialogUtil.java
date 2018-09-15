package com.example.jpm.librarydemo.tools.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.jpm.librarydemo.tools.base.ResUtil;

public class ProgressDialogUtil {
    private static ProgressDialog pd = null;

    public static void uploadProgressDialogShow(Context context, String title) {
        if (pd == null) {
            pd = new ProgressDialog(context);/* 显示ProgressDialog */
            pd.setCanceledOnTouchOutside(false);
            Resources resources = context.getResources();
            Drawable drawable = resources.getDrawable(ResUtil.getDrawableId(context, "progressbar"));
            pd.setIndeterminateDrawable(drawable);
            pd.setMessage(title);/* 显示ProgressDialog */
            pd.show();/* 显示ProgressDialog */
        }
    }

    public static void uploadProgressDialogShow(ProgressDialog pd, Context context) {
        if (pd == null) {
            pd = new ProgressDialog(context);/* 显示ProgressDialog */
            pd.setCanceledOnTouchOutside(false);
            Resources resources = context.getResources();
            Drawable drawable = resources.getDrawable(ResUtil.getDrawableId(context, "progressbar"));
            pd.setIndeterminateDrawable(drawable);
            pd.setMessage("数据正在加载，请稍后");/* 显示ProgressDialog */
            pd.show();/* 显示ProgressDialog */
        }
    }

    public static void uploadProgressDialogDismiss() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }

    }

    public static void uploadProgressDialogDismiss(ProgressDialog pd) {
        if (pd != null) {
            pd.dismiss();
        }
    }

    public static void uploadProgressDialogShow(ProgressDialog pd) {
        pd.show();
    }
}
