package com.custom.apidemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusNaviBarSettings extends ListActivity {

    private static final String TAG = "StatusNaviBarSettings";
    private final static int KEY_SHOW_STATUSBAR = 0;
    private final static int KEY_HIDE_STATUSBAR = 1;
    private final static int KEY_SHOW_NAVIBAR = 2;
    private final static int KEY_HIDE_NAVIBAR = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new SimpleAdapter(this,
                getData(), android.R.layout.simple_list_item_1,
                new String[] { "title" },
                new int[] { android.R.id.text1 }));
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
        addItem(myData, "Show statusBar", KEY_SHOW_STATUSBAR);
        addItem(myData, "Hide statusBar", KEY_HIDE_STATUSBAR);
        addItem(myData, "Show NaviBar", KEY_SHOW_NAVIBAR);
        addItem(myData, "Hide NaviBar", KEY_HIDE_NAVIBAR);
        return myData;
    }

    protected void addItem(List<Map<String, Object>> data, String name,int key) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("key", key);
        data.add(temp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
        int key = (int) map.get("key");
        switch (key) {
            case KEY_SHOW_STATUSBAR:
                MainApp.getCustomApi().setStatusBarVisibility(true);
                break;
            case KEY_HIDE_STATUSBAR:
                MainApp.getCustomApi().setStatusBarVisibility(false);
                break;
            case KEY_SHOW_NAVIBAR:
                MainApp.getCustomApi().setNavigationbarVisibility(true);
                break;
            case KEY_HIDE_NAVIBAR:
                MainApp.getCustomApi().setNavigationbarVisibility(false);
                break;
            default:
                break;
        }

    }

}
