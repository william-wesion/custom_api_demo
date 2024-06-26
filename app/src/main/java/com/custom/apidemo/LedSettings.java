package com.custom.apidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class LedSettings extends Activity {
	private static final String TAG = LedSettings.class.getSimpleName();
	private RadioGroup whiteLed_group,redLed_Group;
	private RadioButton w_heartbeatButton,w_onButton,w_offButton, w_timerButton, r_heartbeatButton,r_onButton,r_offButton, r_timerButton;
	private TextView textView_whiteLed,textView_redLed;
	private static final int LED_WHITE = 0;
    private static final int LED_RED   = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledsettings_main);
		
        textView_whiteLed =(TextView)findViewById(R.id.textView_whiteLedId);      
        whiteLed_group =(RadioGroup)findViewById(R.id.whiteLed_GroupId);
        w_heartbeatButton =(RadioButton)findViewById(R.id.w_heartbeatButtonId);
        w_onButton=(RadioButton)findViewById(R.id.w_onButtonId);
		w_offButton=(RadioButton)findViewById(R.id.w_offButtonId);
		w_timerButton=(RadioButton)findViewById(R.id.w_timerButtonId);

        textView_redLed =(TextView)findViewById(R.id.textView_redLedId);        
        redLed_Group=(RadioGroup)findViewById(R.id.redLed_GroupId);
		r_offButton=(RadioButton)findViewById(R.id.r_offButtonId);
		r_onButton=(RadioButton)findViewById(R.id.r_onButtonId);
		r_heartbeatButton =(RadioButton)findViewById(R.id.r_heartbeatButtonId);
		r_timerButton =(RadioButton)findViewById(R.id.r_timerButtonId);

		int whiteMode = MainApp.getCustomApi().getLedMode(LED_WHITE);
		if(0 == whiteMode)
			w_offButton.setChecked(true);
		else if(1 == whiteMode)
			w_onButton.setChecked(true);
		else if(2 == whiteMode)
			w_timerButton.setChecked(true);
		else if(3 == whiteMode)
			w_heartbeatButton.setChecked(true);

		int ledMode = MainApp.getCustomApi().getLedMode(LED_RED);
		if(0 == ledMode)
			r_offButton.setChecked(true);
		else if(1 == ledMode)
			r_onButton.setChecked(true);
		else if(2 == ledMode)
			r_timerButton.setChecked(true);
		else if(3 == ledMode)
			r_heartbeatButton.setChecked(true);

        RadioGroupListener listener =new RadioGroupListener();
        whiteLed_group.setOnCheckedChangeListener(listener);
        redLed_Group.setOnCheckedChangeListener(listener);
    }
    class RadioGroupListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if(group == whiteLed_group){
				if (checkedId == w_offButton.getId()) {
					MainApp.getCustomApi().setLedMode(LED_WHITE,0);
				} else if (checkedId == w_onButton.getId()) {
					MainApp.getCustomApi().setLedMode(LED_WHITE,1);
				} else if (checkedId == w_timerButton.getId()) {
					MainApp.getCustomApi().setLedMode(LED_WHITE,2);
				} else if (checkedId == w_heartbeatButton.getId()) {
					MainApp.getCustomApi().setLedMode(LED_WHITE,3);
				}
			}else if(group == redLed_Group){
 				if (checkedId == r_offButton.getId()) {
					MainApp.getCustomApi().setLedMode(LED_RED,0);
				} else if (checkedId == r_onButton.getId()) {
					MainApp.getCustomApi().setLedMode(LED_RED,1);
				} else if (checkedId == r_timerButton.getId()) {
					MainApp.getCustomApi().setLedMode(LED_RED,2);
				} else if (checkedId == r_heartbeatButton.getId()) {
				    MainApp.getCustomApi().setLedMode(LED_RED,3);
				}

			}
		}
    }
}
