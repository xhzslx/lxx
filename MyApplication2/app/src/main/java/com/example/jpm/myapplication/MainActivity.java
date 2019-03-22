package com.example.jpm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jpm.librarydemo.tools.base.IPlusView;
import com.example.jpm.librarydemo.tools.util.RetrofitManager;
import com.example.jpm.librarydemo.tools.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IPlusView {

    private TextView textView;
    private Presenter presenter;
    private SignInbean signInbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        textView = findViewById(R.id.lx);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData("tcfgw",
                        "CAF1A3DFB505FFED0D024130F58C5CFA");
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void disimissProgress() {

    }

    @Override
    public void setSuccessData2View(Object o, int code) {
        switch (code){
            case 10000:
                signInbean= (SignInbean) o;
                ToastUtil.showShort(MainActivity.this,signInbean
                        .getBody().get(0).getM_Name());
                break;
        }

    }

    @Override
    public void loadDataError(int code) {
        ToastUtil.showShort(MainActivity.this,"网络错误");
    }
}
