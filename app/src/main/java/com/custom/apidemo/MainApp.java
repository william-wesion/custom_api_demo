package com.custom.apidemo;

import android.app.Application;
import com.custom.CustomApi;

public class MainApp extends Application {

	private static CustomApi mCstomApi;

	public static CustomApi getCustomApi() {
		return mCstomApi;
	}

 	@Override
	public void onCreate() {
		super.onCreate();
		mCstomApi = new CustomApi(this);
	}
}
