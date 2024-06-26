package com.custom.apidemo;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Surface;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "ExecSuCmd", new Intent(this, ExecSuCmdSettings.class));
		addItem(myData, "Gpio", new Intent(this, GpioSettings.class));
		addItem(myData, "I2c", new Intent(this, I2cSettings.class));
		addItem(myData, "Led", new Intent(this, LedSettings.class));
		addItem(myData, "Fan", new Intent(this, FanSettings.class));
		addItem(myData, "Wol", new Intent(this, WolSettings.class));
		addItem(myData, "Uart", new Intent(this, UartSettings2.class));
		addItem(myData, "Can", new Intent(this, CanSettings.class));
		addItem(myData, "Reboot", new Intent(this, RebootSettings.class));
		addItem(myData, "ShutDown", new Intent(this, ShutDownSettings.class));
		addItem(myData, "Sleep", new Intent(this, SleepSettings.class));
		addItem(myData, "SetTime", new Intent(this, SetTimeSettings.class));
		addItem(myData, "Silent Install apk & Uninstall apk", new Intent(this, SilentInstallSettings.class));
		addItem(myData, "SystemProperties", new Intent(this, SystemPropertiesSettings.class));
		addItem(myData, "Screenrecord", new Intent(this, ScreenrecordSettings.class));
		addItem(myData, "InstallPackage", new Intent(this, InstallPackageSettings.class));
		addItem(myData, "SetDisplayPos", new Intent(this, SetDisplayPos.class));
		addItem(myData, "StatusBar & NaviBar", new Intent(this, StatusNaviBarSettings.class));
		addItem(myData, "Broadcast", new Intent(this, CustomBroadcast.class));
		return myData;
	} 

	protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
	}
}
