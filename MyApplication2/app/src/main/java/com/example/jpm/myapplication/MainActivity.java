package com.example.jpm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private List<test> list=new ArrayList<>();
    private List<test> copylist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=0;i<5;i++){
            test test1=new test();
            test1.setBean(i);
            list.add(test1);
            copylist.add(test1);
        }
        textView=findViewById(R.id.lx);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<list.size();i++){
                    list.get(i).setBean(-1);
                }

                Toast.makeText(MainActivity.this,copylist.get(0).getBean()+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
