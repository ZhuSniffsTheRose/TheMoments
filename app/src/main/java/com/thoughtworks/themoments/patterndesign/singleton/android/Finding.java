package com.thoughtworks.themoments.patterndesign.singleton.android;

import android.content.Context;

/**
 * Created by Zhu on 2019-12-11
 */
public class Finding {

    public static void main(String[] args) {
        Context context = null;

//        LayoutInflater.from(context).inflate();


        // context 具体实现是啥？
        // Activity 入口就是 ActivityThread 的 main 函数， 在 main 函数中 创建一个新的 ActivityThread 对象，并且
        // 启动 消息循环， 创建新的 Activity、新的 Context 对象，然后将 Context 对象传递给 Activity

        // 具体来看，  main 函数中，
        //      ActivityThread thread = new ActivityThread();
        //        thread.attach(false, startSeq);
        // attach 中 ， 非系统应用， 会通过binder 机制与 AMS 通信，并且最终调用 handleLaunchActivity
        //  然后调用 performLaunchActivity ===》  ContextImpl appContext = createBaseContextForActivity(r); 返回的 ContextImpl， 然后    activity.attach(appContext,...) 设置给Activity


    }

}
