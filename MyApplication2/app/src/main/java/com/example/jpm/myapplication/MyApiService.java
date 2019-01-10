package com.example.jpm.myapplication;

import com.example.jpm.librarydemo.tools.base.BaseApiService;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mayn on 2018/12/10.
 */

public interface MyApiService {

    //http://122.193.93.219:1002/Service.svc/?
    // login_name=tcfgw&&password=CAF1A3DFB505FFED0D024130F58C5CFA
    @GET("SignIn")
    Observable<SignInbean> SignIn(@Query("login_name") String login_name,
                                  @Query("password") String password);
}

