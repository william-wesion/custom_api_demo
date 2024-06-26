package com.custom.apidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class FanSettings extends Activity {
	private static final String TAG = FanSettings.class.getSimpleName();
	private RadioGroup fan_group;
	private RadioButton auto_Button,Level_1_Button,Level_2_Button,Level_3_Button,Level_4_Button,Level_5_Button,off_Button;
	private TextView textView_fan;

	private static final int INDEX_LEVEL_OFF = 0;
    private static final int INDEX_LEVEL_AUTO = 1;
    private static final int INDEX_LEVEL_1 = 2;
    private static final int INDEX_LEVEL_2 = 3;
    private static final int INDEX_LEVEL_3 = 4;
	private static final int INDEX_LEVEL_4 = 5;
	private static final int INDEX_LEVEL_5 = 6;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fansettings_main);
		
        textView_fan =(TextView)findViewById(R.id.textView_fanId);    
		fan_group =(RadioGroup)findViewById(R.id.fan_GroupId);

		off_Button=(RadioButton)findViewById(R.id.off_ButtonId);
        auto_Button =(RadioButton)findViewById(R.id.auto_ButtonId);
        Level_1_Button =(RadioButton)findViewById(R.id.Level_1_ButtonId);
        Level_2_Button=(RadioButton)findViewById(R.id.Level_2_ButtonId);
		Level_3_Button=(RadioButton)findViewById(R.id.Level_3_ButtonId);
        Level_4_Button=(RadioButton)findViewById(R.id.Level_4_ButtonId);
		Level_5_Button=(RadioButton)findViewById(R.id.Level_5_ButtonId);


		if(INDEX_LEVEL_OFF == MainApp.getCustomApi().getFanMode())
			off_Button.setChecked(true);
		else if(INDEX_LEVEL_AUTO == MainApp.getCustomApi().getFanMode())
			auto_Button.setChecked(true);
		else if(INDEX_LEVEL_1 == MainApp.getCustomApi().getFanMode())
			Level_1_Button.setChecked(true);
		else if(INDEX_LEVEL_2 == MainApp.getCustomApi().getFanMode())
			Level_2_Button.setChecked(true);
 		else if(INDEX_LEVEL_3 == MainApp.getCustomApi().getFanMode())
			Level_3_Button.setChecked(true);
		else if(INDEX_LEVEL_4 == MainApp.getCustomApi().getFanMode())
			Level_4_Button.setChecked(true);
		else if(INDEX_LEVEL_5 == MainApp.getCustomApi().getFanMode())
			Level_5_Button.setChecked(true);
		
        RadioGroupListener listener =new RadioGroupListener();
        fan_group.setOnCheckedChangeListener(listener);
    }
    class RadioGroupListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			if(checkedId == off_Button.getId()){
				MainApp.getCustomApi().setFanMode(INDEX_LEVEL_OFF);
			} else if(checkedId == auto_Button.getId()){
				MainApp.getCustomApi().setFanMode(INDEX_LEVEL_AUTO);
			}else if(checkedId == Level_1_Button.getId()){
				MainApp.getCustomApi().setFanMode(INDEX_LEVEL_1);
			}else if(checkedId == Level_2_Button.getId()){
				MainApp.getCustomApi().setFanMode(INDEX_LEVEL_2);
			}else if(checkedId == Level_3_Button.getId()){
				MainApp.getCustomApi().setFanMode(INDEX_LEVEL_3);
			}else if(checkedId == Level_4_Button.getId()){
				MainApp.getCustomApi().setFanMode(INDEX_LEVEL_4);
			} else if(checkedId == Level_5_Button.getId()) {
				MainApp.getCustomApi().setFanMode(INDEX_LEVEL_5);
			}

		}
    }
}
