/**
 * SponsorPay Android SDK
 *
 * Copyright 2011 - 2013 SponsorPay. All rights reserved.
 */

package com.sponsorpay.sdk.android.publisher;

import java.util.Map;

import android.content.Context;

import com.sponsorpay.sdk.android.SponsorPay;
import com.sponsorpay.sdk.android.credentials.SPCredentials;
import com.sponsorpay.sdk.android.publisher.AsyncRequest.AsyncRequestResultListener;
import com.sponsorpay.sdk.android.utils.StringUtils;

/**
 * Abstract class defining some common functionality for contacting the SponsorPay's API.
 */
public abstract class AbstractConnector<V> implements AsyncRequestResultListener {
	/**
	 * Parameter key used to transmit the timestamp of the request.
	 */
	protected static final String URL_PARAM_KEY_TIMESTAMP = "timestamp";

	/**
	 * URL of the SponsorPay's API resource to contact.
	 */
	protected String remoteResourceUrl;
	
	/**
	 * Android application context.
	 */
	protected Context mContext;

	/**
	 * Map of custom key/values to add to the parameters on the requests.
	 */
	protected Map<String, String> mCustomParameters;

	/**
	 * Credentials holding AppID, UserId and Security Token 
	 */
	protected SPCredentials mCredentials;
	
	protected V mUserListener;
	
	protected AbstractConnector(Context context, String credentialsToken, V userListener) {
		mContext = context;
		mCredentials = SponsorPay.getCredentials(credentialsToken);
		mUserListener = userListener;
		if (StringUtils.nullOrEmpty(mCredentials.getSecurityToken())) {
			throw new IllegalArgumentException("Security token has not been set on the credentials");
		}
	}
	
	/**
	 * Sets a map of custom key/values to add to the parameters on the requests to the REST API.
	 */
	public void setCustomParameters(Map<String, String> customParams) {
		mCustomParameters = customParams;
	}
	
	/**
	 * Gets the current UNIX timestamp (in seconds) for the outbound requests.
	 * 
	 * @return
	 */
	protected String getCurrentUnixTimestampAsString() {
		final int MILLISECONDS_IN_SECOND = 1000;
		return String.valueOf(System.currentTimeMillis() / MILLISECONDS_IN_SECOND);
	}
}
