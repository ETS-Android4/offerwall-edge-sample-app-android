/**
 * SponsorPay Android SDK
 *
 * Copyright 2011 - 2013 SponsorPay. All rights reserved.
 */

package com.sponsorpay.mediation.mbe;

import java.util.Map;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sponsorpay.mediation.MockMediatedAdapter;
import com.sponsorpay.mediation.SPMediationConfigurator;

public class VideoMediationConfigurationActivity extends Activity {
	
	
    private OnClickListener listener = new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	    	  MockVideoSetting setting = (MockVideoSetting) v.getTag();
	    	  Map<String, Object> config = SPMediationConfigurator.INSTANCE.getConfigurationForAdapter(MockMediatedAdapter.ADAPTER_NAME);
//	    	  config.put(MockMediatedVideoAdapter.VIDEO_MOCK_BEHAVIOUR, setting.getBehaviour());
//	    	  config.put(MockMediatedVideoAdapter.VIDEO_EVENT_RESULT, setting.getVideoEvent());
//	    	  config.put(MockMediatedVideoAdapter.VIDEO_VALIDATION_RESULT, setting.getValidationResult());
//	    	  ConfigHolder.INSTANCE.setCurrentVideoConfig(setting);
	    	  config.put(MockMediatedVideoAdapter.VIDEO_MOCK_SETTING, setting);
	    	  finish();
	      }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		
		TextView text = new TextView(this);
		text.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		text.setTextSize(25f);
		text.setTypeface(Typeface.DEFAULT_BOLD);
//		text.setText(ConfigHolder.INSTANCE.getCurrentVideoConfig().toString());
		text.setText(SPMediationConfigurator.getConfiguration(
				MockMediatedAdapter.ADAPTER_NAME,
				MockMediatedVideoAdapter.VIDEO_MOCK_SETTING, MockVideoSetting.class)
				.toString());
		
		ExpandableListView lv = new ExpandableListView(this);
		lv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		lv.setAdapter(new MockVideoMediationListAdapter(this, listener));
		
		layout.addView(text);
		layout.addView(lv);
		
		setContentView(layout);
	}
	
	
	
}