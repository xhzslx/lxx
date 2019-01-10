package com.example.jpm.librarydemo.tools.util;

import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;

import com.example.jpm.librarydemo.tools.base.FCallBack;
import com.example.jpm.librarydemo.tools.base.XCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author lx
 *         created at 2018/4/20 11:05
 *         作用：进行数据请求
 */
public class HttpUtil {

    /**
     * @author lx
     * created at 2018/4/20 11:05
     * 作用：code 10000 get请求 如有多个请加0，1，2，3
     * code 10001 post请求 如有多个请加0，1，2，3
     * code 10009 下载文件 如有多个请加0，1，2，3
     */
    public static void okHttpGetMethod(final String url, final XCallBack callback, final int code) {

        LoggUtil.i("url", url);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    LoggUtil.i("test", "error");
                    callback.onError(code);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback != null && response.isSuccessful()) {
                    LoggUtil.i("test", "success");
                    callback.onResponse(response.body().string(), code);
                } else {
                    LoggUtil.i("test", "error2");
                    callback.onError(code);
                }
            }
        });
    }

    public static void okHttpDownloadFile(final String url, final FCallBack fCallBack, final int code) {

        LoggUtil.i("url", url);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                LoggUtil.i("test", "error");
                fCallBack.onError(code);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    // 储存下载文件的目录
                    String savePath = FileUtil.isExistDir("jpm");
                    try {
                        is = response.body().byteStream();
                        long total = response.body().contentLength();
                        File file = new File(savePath, FileUtil
                                .getNameFromUrl(url));
                        fos = new FileOutputStream(file);
                        long sum = 0;
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                            sum += len;
                            int progress = (int) (sum * 1.0f / total * 100);
                            // 下载中
                            fCallBack.onLoading(total, progress);
                        }
                        fos.flush();
                        // 下载完成
                        LoggUtil.i("test", "success");
                        fCallBack.onsuccess(code);
                    } catch (Exception e) {
                        LoggUtil.i("test", "error2");
                        fCallBack.onError(code);
                    } finally {
                        try {
                            if (is != null)
                                is.close();
                        } catch (IOException e) {
                        }
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                        }
                    }
                }else{
                    fCallBack.onError(code);
                    LoggUtil.i("test", "error3");
                }

            }
        });

    }

    public static void okHttpDownloadFile(final String url, final FCallBack fCallBack,
                                          final int code, final String foldername) {

        LoggUtil.i("url", url);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                LoggUtil.i("test", "error1");
                fCallBack.onError(code);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    String savePath;
                    // 储存下载文件的目录
                    if (TextUtils.isEmpty(foldername)) {
                        savePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                    } else {
                        savePath = FileUtil.isExistDir(foldername);
                    }

                    try {
                        is = response.body().byteStream();
                        long total = response.body().contentLength();
                        File file = new File(savePath, FileUtil
                                .getNameFromUrl(url));
                        fos = new FileOutputStream(file);
                        long sum = 0;
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                            sum += len;
                            int progress = (int) (sum * 1.0f / total * 100);
                            // 下载中
                            LoggUtil.i("test", "loading");
                            fCallBack.onLoading(total, progress);
                        }
                        fos.flush();
                        // 下载完成
                        LoggUtil.i("test", "success");
                        fCallBack.onsuccess(code);
                    } catch (Exception e) {
                        LoggUtil.i("test", "error2");
                        fCallBack.onError(code);
                    } finally {
                        try {
                            if (is != null)
                                is.close();
                        } catch (IOException e) {
                        }
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                        }
                    }
                }else{
                    LoggUtil.i("test", "error3");
                    fCallBack.onError(code);
                }

            }
        });

    }

    public static void okHttpPostFormBodyMethod(String url, Map<String, String> map,
                                                final XCallBack callback, final int code) {

        LoggUtil.i("url", url);
        //1:创建OKHttpClient对象
        //设置超时时间
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBody.add(entry.getKey(), entry.getValue());
        }
        FormBody build = formBody.build();
        Request request = new Request.Builder()
                .post(build)
                .url(url)
                .build();
        //调用okHttpClient对象实现CallBack方法
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LoggUtil.i("test", "error");
                callback.onError(code);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    LoggUtil.i("test", "success");
                    String result = response.body().string();
                    callback.onResponse(result, code);
                }else{
                    LoggUtil.i("test", "error2");
                    callback.onError(code);
                }
            }
        });
    }

    public static void okHttpPostMethod(final String url, final String bytes, final XCallBack callback, final int code) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS).build();//设置读取超时时间
        // 创建请求参数
        Request request = new Request.Builder().url(url).
                post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8")
                        , bytes))
                .addHeader("content-type", "binary/octet-stream")
                .build();

        // 创建请求对象
        Call call = okHttpClient.newCall(request);
        // 发起请求
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LoggUtil.i("test", "error");
                callback.onError(code);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    LoggUtil.i("test", "successful");
                    String result = response.body().string();
                    callback.onResponse(result, code);
                }else{
                    LoggUtil.i("test", "error2");
                    callback.onError(code);
                }
            }
        });
    }

    public static void okHttpPostMethod(final String url, final byte[] bytes, final XCallBack callback, final int code) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS).build();//设置读取超时时间
        // 创建请求参数
        Request request = new Request.Builder().url(url).
                post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8")
                        , bytes))
                .addHeader("content-type", "binary/octet-stream")
                .build();

        // 创建请求对象
        Call call = okHttpClient.newCall(request);
        // 发起请求
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LoggUtil.i("test", "error");
                callback.onError(code);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    LoggUtil.i("test", "successful");
                    String result = response.body().string();
                    callback.onResponse(result, code);
                }else{
                    LoggUtil.i("test", "error2");
                    callback.onError(code);
                }
            }
        });
    }
}
