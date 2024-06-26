package com.custom.apidemo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomBroadcast extends ListActivity {

    private static final String TAG = "CustomBroadcast";
    private final static int KEY_UPDATE_TIME = 0;
    private final static int KEY_SILENT_INSTALL = 1;
    private final static int KEY_SILENT_UNINSTALL = 2;
    private final static int KEY_REBOOT = 3;
    private final static int KEY_SHUTDOWN = 4;
    private final static int KEY_SLEEP = 5;
    private final static int KEY_WAKEUP = 6;
    private final static int KEY_SHOW_STATUSBAR = 7;
    private final static int KEY_HIDE_STATUSBAR = 8;
    private final static int KEY_SHOW_NAVIBAR = 9;
    private final static int KEY_HIDE_NAVIBAR = 10;
    private final static int KEY_OTA_UPDATE = 11;

    private final static int KEY_DISPLAY_POS = 12;
    private final static int KEY_SET_SYSPROP = 13;


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
        addItem(myData, "Update time", KEY_UPDATE_TIME);
        addItem(myData, "Silent install", KEY_SILENT_INSTALL);
        addItem(myData, "Silent uninstall", KEY_SILENT_UNINSTALL);
        addItem(myData, "Reboot", KEY_REBOOT);
        addItem(myData, "Shutdown", KEY_SHUTDOWN);
        addItem(myData, "Sleep", KEY_SLEEP);
        addItem(myData, "Wakeup", KEY_WAKEUP);
        addItem(myData, "Show statusbar", KEY_SHOW_STATUSBAR);
        addItem(myData, "Hide statusbar", KEY_HIDE_STATUSBAR);
        addItem(myData, "Show navibar", KEY_SHOW_NAVIBAR);
        addItem(myData, "Hide navibar", KEY_HIDE_NAVIBAR);
        addItem(myData, "OTA update", KEY_OTA_UPDATE);
        addItem(myData, "Display pos", KEY_DISPLAY_POS);
        addItem(myData, "Set systemprop", KEY_SET_SYSPROP);
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
            case KEY_UPDATE_TIME:
                Intent timeIntent = new Intent("com.custom.action.UPDATE_TIME");
                timeIntent.putExtra("time", "2024-06-01 12:00:00");
                sendBroadcast(timeIntent);
                break;
            case KEY_SILENT_INSTALL:
                Intent silentInstallIntent = new Intent("com.custom.action.SILENT_INSTALL");
                silentInstallIntent.putExtra("apkPath", "/sdcard/test.apk");
                silentInstallIntent.putExtra("isLaunch", true);
                sendBroadcast(silentInstallIntent);
                break;
            case KEY_SILENT_UNINSTALL:
                Intent silentUninstallIntent = new Intent("com.custom.action.SILENT_UNINSTALL");
                silentUninstallIntent.putExtra("pkgName", "com.androits.gps.test.pro");
                sendBroadcast(silentUninstallIntent);
                break;
            case KEY_REBOOT:
                Intent rebootIntent = new Intent("com.custom.action.REBOOT");
                sendBroadcast(rebootIntent);
                break;
            case KEY_SHUTDOWN:
                Intent shutdownIntent = new Intent("com.custom.action.SHUTDOWN");
                sendBroadcast(shutdownIntent);
                break;
            case KEY_SLEEP:
                Intent sleepIntent = new Intent("com.custom.action.SLEEP");
                sendBroadcast(sleepIntent);
                break;
            case KEY_WAKEUP:
                Intent wakeupIntent = new Intent("com.custom.action.WAKEUP");
                sendBroadcast(wakeupIntent);
                break;
            case KEY_SHOW_STATUSBAR:
                Intent showStatusBarIntent = new Intent("com.custom.action.SHOW_STATUSBAR");
                sendBroadcast(showStatusBarIntent);
                break;
            case KEY_HIDE_STATUSBAR:
                Intent hideStatusBarIntent = new Intent("com.custom.action.HIDE_STATUSBAR");
                sendBroadcast(hideStatusBarIntent);
                break;
            case KEY_SHOW_NAVIBAR:
                Intent showNaviBarIntent = new Intent("com.custom.action.SHOW_NAVIBAR");
                sendBroadcast(showNaviBarIntent);
                break;
            case KEY_HIDE_NAVIBAR:
                Intent hideNaviBarIntent = new Intent("com.custom.action.HIDE_NAVIBAR");
                sendBroadcast(hideNaviBarIntent);
                break;
            case KEY_OTA_UPDATE:
                Intent otaUpdateIntent = new Intent("com.custom.action.OTA_UPDATE");
                otaUpdateIntent.putExtra("path", "/sdcard/a10_3588-android-13-v20240607-ota.zip");
                sendBroadcast(otaUpdateIntent);
                break;
            case KEY_DISPLAY_POS:
                Intent disPlayPosIntent = new Intent("com.custom.action.SET_DISPOS");
                disPlayPosIntent.putExtra("pos", 0);
                sendBroadcast(disPlayPosIntent);
                break;
            case KEY_SET_SYSPROP:
                Intent setSysPropIntent = new Intent("com.custom.action.SET_SYSPROPS");
                setSysPropIntent.putExtra("name", "persist.sys.test");
                setSysPropIntent.putExtra("value", "setprop_test");
                sendBroadcast(setSysPropIntent);
                break;
            default:
                break;
        }

    }

}
