package com.example.jpm.librarydemo.tools.util;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    /**
     * @author lx
     * created at 2018/4/20 11:05
     * 作用：code 10000 get请求 如有多个请加0，1，2，3
     * code 10001 post请求 如有多个请加0，1，2，3
     * code 10009 下载文件 如有多个请加0，1，2，3
     */
    public static void okHttpGetMethod(final String url, final XCallBack callback, final int code) {
        new Thread() {
            @Override
            public void run() {
                super.run();
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
                            callback.onError(code);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            callback.onResponse(response.body().string(), code);
                        }
                    }
                });
            }
        }.start();
    }

    public static void okHttpDownloadFile(final String url, final FCallBack fCallBack,
                                          final String path) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // 下载失败
                        fCallBack.onError();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len = 0;
                        FileOutputStream fos = null;
                        // 储存下载文件的目录
                        String savePath = isExistDir(path);
                        try {
                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            File file = new File(savePath, getNameFromUrl(url));
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
                            fCallBack.onsuccess();
                        } catch (Exception e) {
                            fCallBack.onError();
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
                    }
                });

            }
        }.start();

    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private static String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    @NonNull
    private static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public static void okHttpPostMethod(final String url, final String bytes, final XCallBack callback, final int code) {
        // 创建客户端
        new Thread() {
            @Override
            public void run() {
                super.run();
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
                        callback.onError(code);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String result = response.body().string();
                            callback.onResponse(result, code);
                        }
                    }
                });
            }
        }.start();

    }

    public interface XCallBack {
        void onResponse(String result, int code);

        void onError(int code);
    }

    public interface FCallBack {
        void onsuccess();

        void onError();

        void onLoading(long total, long current);
    }


}
