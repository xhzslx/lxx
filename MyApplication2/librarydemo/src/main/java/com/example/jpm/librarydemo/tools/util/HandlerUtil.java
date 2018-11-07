package com.example.jpm.librarydemo.tools.util;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
/**
 * @author lx
 * created at 2018/4/17 11:03
 * 作用：Handler工具类
 */
public class HandlerUtil {

    public static void sendMessage(Handler handler, int what) {
        Message message = Message.obtain();
        if (what != -1) {
            message.what = what;
        }
        handler.sendMessage(message);
    }

    public static void sendMessage(Handler handler) {
        Message message = Message.obtain();
        handler.sendMessage(message);
    }

    public static void sendMessage(Handler handler, int what, String result) {
        Message message = Message.obtain();
        if (what != -1) {
            message.what = what;
        }
        message.obj = result;
        handler.sendMessage(message);
    }

//    public static void sendMessage(Handler handler, int what, String result) {
//        Message message = Message.obtain();
//        if (what != -1) {
//            message.what = what;
//        }
//        message.obj = result;
//        handler.sendMessage(message);
//    }

    public static void sendMessage(Handler handler, int what, Object result) {
        Message message = Message.obtain();
        if (what != -1) {
            message.what = what;
        }
        message.obj = result;
        handler.sendMessage(message);
    }
}
