package com.thoughtworks.themoments.patterndesign.builder.android;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Zhu on 2019-12-11
 *
 * 将 Dialog 的构造和表示进行分离
 *
 * 什么是构造？ 什么是表示？
 *
 *
 * 在 dialog#show,我们可以发现， 最终 mDecor(decor初始化在 setContentView中) 被添加到了 WindowManger 中
 * 那 WindowManager  做了啥
 *
 *  定义 windowManager 是一个非常重要的子系统， 就是 WMS。
 *
 *
 *
 *
 */
public class Finding {
    public static void main(String[] args) {
        Context context  = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("");
        builder.setTitle("");
        AlertDialog alertDialog = builder.create();

    }
}
