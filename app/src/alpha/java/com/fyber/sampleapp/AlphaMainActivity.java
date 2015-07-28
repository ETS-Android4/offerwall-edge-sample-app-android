package com.fyber.sampleapp; /**
 * Fyber Android SDK
 * <p/>
 * Copyright (c) 2015 Fyber. All rights reserved.
 */

import android.os.Bundle;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class AlphaMainActivity extends MainActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());
	}
}
