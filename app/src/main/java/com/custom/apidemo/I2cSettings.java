package com.custom.apidemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class I2cSettings extends Activity implements OnClickListener {
	private static final String TAG = "I2cSettings";
	private Context mContext;

	Button i2c_read_btn,i2c_write_btn;
	TextView i2c_read_tv,i2c_write_tv;

	EditText i2c_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.i2c_main);
		mContext = this;
		i2c_text = (EditText)findViewById(R.id.i2c_text);

		i2c_read_btn = (Button)findViewById(R.id.i2c_read_btn);
		i2c_read_tv = (TextView)findViewById(R.id.i2c_read_tv);
		i2c_write_btn = (Button)findViewById(R.id.i2c_write_btn);
		i2c_write_tv = (TextView)findViewById(R.id.i2c_write_tv);
		
		i2c_read_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isInteger(i2c_text.getText().toString())) {
					i2c_read_tv.setText(String.valueOf(MainApp.getCustomApi().i2cReadByteData(4,0x0e, Integer.valueOf(i2c_text.getText().toString()))));
				}else{
					i2c_read_tv.setText("not int");
				}
			}
		});

		i2c_write_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isInteger(i2c_text.getText().toString())) {
					MainApp.getCustomApi().i2cWriteByteData(4,0x0e, 0x60, Integer.valueOf(i2c_text.getText().toString()));
					i2c_write_tv.setText(String.valueOf(MainApp.getCustomApi().i2cReadByteData(4,0x0e, 0x60)));
				}else{
					i2c_write_tv.setText("not int");
				}
			}
		});

	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	@Override
	public void onClick(View v) {
	}
}
