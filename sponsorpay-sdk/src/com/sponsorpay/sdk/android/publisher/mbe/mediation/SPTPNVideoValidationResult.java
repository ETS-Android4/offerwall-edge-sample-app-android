/**
 * SponsorPay Android SDK
 *
 * Copyright 2011 - 2013 SponsorPay. All rights reserved.
 */

package com.sponsorpay.sdk.android.publisher.mbe.mediation;

public enum SPTPNVideoValidationResult {

	SPTPNValidationAdapterNotIntegrated("no_sdk"),
	SPTPNValidationNoVideoAvailable("no_video"),
    SPTPNValidationTimeout("timeout"),
    SPTPNValidationNetworkError("network_error"),
    SPTPNValidationDiskError("disk_error"),
    SPTPNValidationError("error"),
    SPTPNValidationSuccess("success");

    private final String text;
	
	private SPTPNVideoValidationResult(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
    
}