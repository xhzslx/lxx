package com.example.jpm.librarydemo.tools.base;

/**
 * EventBus返回刷新数据实体类
 * Created by lx464 on 2018/3/23.
 */

public class ActivityNumberEventBus {
    private int number;

    public ActivityNumberEventBus(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
