package com.example.jpm.librarydemo.tools.util;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mayn on 2019/1/10.
 */

public class RetrofitManager {

    //设置缓存有效期
    private static final long CACHE_STATE_LONG = 24 * 60 * 60 * 7;

    private static final int CACHE_STATE_SHORT = 60;

    private static final String CACHE_COMTROL_AGE = "Cache-Control: public ,max-age=";
    //查询缓存的Cache_Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合
    //设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached,max-stale=" + CACHE_STATE_LONG;

    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    public static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=0";
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    private OkHttpClient mOkHttpClient;
    public static volatile RetrofitManager manager;
    private Retrofit retrofit;
    public static Context context;
    private String url;
    private static Class apiService;
    private Object myservice;


    private RetrofitManager() {
        initRetrofit();
    }


    private void initRetrofit() {
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(context.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(sLoggingInterceptor)
                .addInterceptor(sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl((String) SharePreferencesUtil.get(context, "baseurl", ""))
                .build();
        myservice = retrofit.create(apiService);
    }

    public Object getApiService() {
        return myservice;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isConnected(context)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                LoggUtil.e("RetrofitManager", "no network");
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isConnected(context)) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    /**
     * 打印返回的json数据拦截器
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                LoggUtil.d("LogTAG", "request.body() == null");
            }
            //打印url信息
            LoggUtil.i("RetrofitManager", request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);

            return response;
        }
    };

    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }


    public static RetrofitManager getInstance(Context context, Class myclass) {
        if (RetrofitManager.context == null) {
            RetrofitManager.context = context;
        }
        apiService = myclass;
        if (manager == null) {
            synchronized (RetrofitManager.class) {
                if (manager == null) {
                    manager = new RetrofitManager();
                }
            }
        }
        return manager;
    }

}
