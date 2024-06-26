package com.custom.apidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class WolSettings extends Activity {
	private static final String TAG = WolSettings.class.getSimpleName();
	private RadioGroup wol_group;
	private RadioButton on_Button,  off_Button;
	//private TextView textView_wol;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wolsettings_main);

		wol_group =(RadioGroup)findViewById(R.id.wol_GroupId);
		
        on_Button =(RadioButton)findViewById(R.id.on_ButtonId);
		off_Button=(RadioButton)findViewById(R.id.off_ButtonId);
		
		if(true == MainApp.getCustomApi().getWolMode())
			on_Button.setChecked(true);
		else if(false == MainApp.getCustomApi().getWolMode())
			off_Button.setChecked(true);
		
        RadioGroupListener listener =new RadioGroupListener();
        wol_group.setOnCheckedChangeListener(listener);
    }
    class RadioGroupListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			
			if(checkedId == on_Button.getId()){
				MainApp.getCustomApi().setWolMode(true);
			}else if(checkedId == off_Button.getId()){
				MainApp.getCustomApi().setWolMode(false);
			}

		}
    }
}
