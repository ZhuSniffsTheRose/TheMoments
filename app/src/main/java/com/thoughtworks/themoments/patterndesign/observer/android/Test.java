package com.thoughtworks.themoments.patterndesign.observer.android;

import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Zhu on 2019-12-16
 */
public class Test {

    public static void main(String[] args) {
        ListView listView = null;
        BaseAdapter adapter = null;
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
