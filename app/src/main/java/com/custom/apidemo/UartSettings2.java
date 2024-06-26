package com.custom.apidemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.custom.customapi.serialport.SerialPortDataCallBack;
import com.custom.customapi.serialport.SerialPortUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UartSettings2 extends Activity implements SerialPortDataCallBack  {
	private static final String TAG = "UartSettings";
	private boolean isOpen;
	private static final String SEND_CMD="112233445566";
	private SerialPortUtil serialPortUtil;
	Button mOpenBtn;
	Button mCloseBtn;
	Button mSendMsgBtn;
	TextView mInfoView;
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uart_main);

		mOpenBtn = (Button)findViewById(R.id.open_serial_port);
		mCloseBtn = (Button)findViewById(R.id.close_serial_port);
		mSendMsgBtn = (Button)findViewById(R.id.send_msg);
		mInfoView = (TextView)findViewById(R.id.testinfo);

		mOpenBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mInfoView.setText("");
				serialPortUtil = new SerialPortUtil();
				serialPortUtil.setSerialPortDataCallBack(UartSettings2.this);
				serialPortUtil.initSerialPort("/dev/ttyWCH0", 9600, 0);
			}
		});

		mCloseBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(serialPortUtil != null) {
					serialPortUtil.closeSerialPort();
					mInfoView.append("close");
				}
				serialPortUtil = null;
			}
		});

		mSendMsgBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(serialPortUtil != null) {
					mInfoView.append("\nsend CMD=0123456789ABCDEF\n");
					String sendTxt = "0123456789ABCDEF";
					serialPortUtil.sendOrder(hexStringToBytes(sendTxt));
				}
			}
		});
	}

	public static String bytesToHexString(byte[] src) {
		return bytesToHexString(src,null);
	}

	public static String bytesToHexString(byte[] src,String sp) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv.toUpperCase());
			if(!TextUtils.isEmpty(sp)){
				stringBuilder.append(sp);
			}
		}
		return stringBuilder.toString();
	}

	private byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	private byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return new byte[]{};
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	@Override
	public void onDataReceived(byte[] bytes, int len) {
		Log.e(TAG, "len " + len);
		if(len > 0) {
			String info = bytesToHexString(bytes);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			mInfoView.append("TIME: " + dateFormat.format(date) + "  " + info);
		}

	}

	@Override
	public void onSerialPortInitFinish(boolean b) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mInfoView.append(b?"open success\n":"open fail\n");
			}
		});
	}
}
