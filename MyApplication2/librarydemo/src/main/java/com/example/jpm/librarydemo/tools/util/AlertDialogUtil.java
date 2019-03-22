package com.example.jpm.librarydemo.tools.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mayn on 2019/3/21.
 */

public class AlertDialogUtil {

    public static AlertDialog buildAlertDialogByView2Show(Context context, View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.create();
        builder.setView(view);
        alertDialog.show();
        return alertDialog;
    }

//    public static AlertDialog buildAlertDialog2Show(Context context, String title, String message,
//                                                    String positiveString, final String positive,
//                                                    String negativeString, final String negative,
//                                                    String neutralString, String neutral,
//                                                    final String packageString) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setTitle(title);
//        alertDialog.setMessage(message);
//        builder.setNegativeButton(negativeString, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////获取字节码对象
//                Class clazz = null;
//                try {
//                    clazz = Class.forName(packageString + "." + negative);
//                    Constructor con = clazz.getConstructor();
//                    //获取Method对象
//                    Method method = clazz.getMethod(negative, Class[].class);
//                    //调用invoke方法来调用
//                    method.invoke(con.newInstance(), Object[].class);
//                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        builder.setPositiveButton(positiveString, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //获取字节码对象
//                Class clazz = null;
//                try {
//                    clazz = Class.forName(packageString + "." + positive);
//                    Constructor con = clazz.getConstructor();
//                    //获取Method对象
//                    Method method = clazz.getMethod(positive, Class[].class);
//                    //调用invoke方法来调用
//                    method.invoke(con.newInstance(), Object[].class);
//                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        builder.setNeutralButton(positiveString, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //获取字节码对象
//                Class clazz = null;
//                try {
//                    clazz = Class.forName(packageString + "." + neutral);
//                    Constructor con = clazz.getConstructor();
//                    //获取Method对象
//                    Method method = clazz.getMethod(neutralString, Class[].class);
//                    //调用invoke方法来调用
//                    method.invoke(con.newInstance(), Object[].class);
//                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        alertDialog.show();
//        return alertDialog;
//    }

    public static AlertDialog.Builder buildAlertDialog2Show(Context context, String title, String message,
                                                            String positiveString, String negativeString
            , String neutralString, final AlertDialogutilListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (!TextUtils.isEmpty(negativeString)) {
            builder.setNegativeButton(negativeString, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.negativeListener(dialog, which);
                }
            });
        }
        if (!TextUtils.isEmpty(positiveString)) {
            builder.setPositiveButton(positiveString, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.positiveListener(dialog, which);
                }
            });
        }
        if (!TextUtils.isEmpty(neutralString)) {
            builder.setNeutralButton(neutralString, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.neutralListener(dialog,which);
                }
            });
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
        return builder;
    }

    public interface AlertDialogutilListener {
        void negativeListener(DialogInterface dialog, int which);

        void neutralListener(DialogInterface dialog, int which);

        void positiveListener(DialogInterface dialog, int which);

    }
}
