package com.custom.apidemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetDisplayPos extends ListActivity {

    private static final String TAG = "SetDisplayPos";
    private final static int KEY_POS_0 = 0;
    private final static int KEY_POS_90 = 90;
    private final static int KEY_POS_180 = 180;
    private final static int KEY_POS_240 = 240;


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
        addItem(myData, "setdispplay pos  0", KEY_POS_0);
        addItem(myData, "setdispplay pos 90", KEY_POS_90);
        addItem(myData, "setdispplay pos 180", KEY_POS_180);
        addItem(myData, "setdispplay pos 240", KEY_POS_240);
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
            case KEY_POS_0:
                MainApp.getCustomApi().setDisplayPosition(0);
                break;
            case KEY_POS_90:
                MainApp.getCustomApi().setDisplayPosition(1);
                break;
            case KEY_POS_180:
                MainApp.getCustomApi().setDisplayPosition(2);
                break;
            case KEY_POS_240:
                MainApp.getCustomApi().setDisplayPosition(3);
                break;
            default:
                break;
        }

    }

}
