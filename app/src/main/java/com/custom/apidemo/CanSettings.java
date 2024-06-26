package com.custom.apidemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.custom.customapi.can.CustomCan;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CanSettings extends Activity {
	private static final String TAG = "CanSettings";
	private Button mOpenBtn;
	private Button mCloseBtn;
	private Button mSendMsgBtn;
	private TextView mInfoView;
	private CustomCan customCan0 = new CustomCan(this);
	private CustomCan customCan1 = new CustomCan(this);
	private MyThread myThread;
	private String CanStr;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.can_main);
		customCan1.openCan("can1", "25000");

		mOpenBtn = (Button)findViewById(R.id.open_can);
		mCloseBtn = (Button)findViewById(R.id.close_can);
		mSendMsgBtn = (Button)findViewById(R.id.send_msg);
		mInfoView = (TextView)findViewById(R.id.testinfo);

		mOpenBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				customCan0.openCan("can0", "25000");
				myThread = new MyThread();
				myThread.start();
			}
		});

		mCloseBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				customCan0.closeCan();
				if(myThread != null) {
					myThread.stopMyThread();
					myThread = null;
				}
			}
		});

		mSendMsgBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int[] buf = new int[8];
				buf[0] = (byte)0x00;
				buf[1] = (byte)0x11;
				buf[2] = (byte)0x22;
				buf[3] = (byte)0x33;
				buf[4] = (byte)0x44;
				buf[5] = (byte)0x55;
				buf[6] = (byte)0x66;
				buf[7] = (byte)0x77;
				int ret = customCan0.writeCan(0x123, 0, 0, 8, buf);
			}
		});
	}

	public class MyThread extends Thread{
		private boolean isStart = true;
		@Override
		public void run() {
			while (isStart) {
				if(customCan0 != null) {
					long[] data = customCan0.readCan();
					if(data != null) {
						for (int i = 0; i < data.length; i++) {
							Log.e(TAG, "data[" + i + "] " + Long.toHexString(data[i]));
						}
						long canId = data[0];
						long canEff = data[1];
						long canRtr = data[2];
						long canLen = data[3];
						Log.e(TAG, "canId " + Long.toHexString(canId));
						Log.e(TAG, "canEff " + Long.toHexString(canEff));
						Log.e(TAG, "canRtr " + Long.toHexString(canRtr));
						Log.e(TAG, "canLen " + Long.toHexString(canLen));
						long[] canData = Arrays.copyOfRange(data, 4, (int) (4 + canLen));
						CanStr = "can RX ";
						CanStr += (canEff == 0) ? "S " : "E ";
						CanStr += (canRtr == 0) ? "-  " : "R  ";
						String canIdStr = Long.toHexString(canId);
						if (canEff == 0) {
							for (int i = 0; i < 3 - canIdStr.length(); i++) {
								canIdStr = '0' + canIdStr;
							}
						} else {
							for (int i = 0; i < 8 - canIdStr.length(); i++) {
								canIdStr = '0' + canIdStr;
							}
						}
						CanStr = CanStr + canIdStr + "   [" + Long.toString(canLen) + "]  ";
						for (int i = 0; i < canLen; i++) {
							String hex = Long.toHexString(canData[i]);
							hex = (hex.length() == 1) ? ('0' + hex) : hex;
							CanStr = CanStr + ' ' + hex;
						}
						CanStr = CanStr.toUpperCase();
						CanStr += '\n';
						Log.e(TAG, "Can RX:"  + CanStr);

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date date = new Date();
								mInfoView.setText(dateFormat.format(date) + " " + CanStr);
							}
						});
					}
				}
			}
		}
		public void stopMyThread() {
			isStart = false;
		}
	}

	protected void onDestroy() {
		super.onDestroy();
		if (myThread != null) {
			myThread.stopMyThread();
			myThread = null;
		}
		customCan1.closeCan();
	}

}
