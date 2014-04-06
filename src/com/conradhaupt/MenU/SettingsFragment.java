package com.conradhaupt.MenU;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
	/* Variables */
	// Static Variables
	public static final String TAG = "SettingsFragment";

	/* Methods */
	public static SettingsFragment newInstance() {
		SettingsFragment fragment = new SettingsFragment();
		return fragment;
	}

	public SettingsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load preference file
		addPreferencesFromResource(R.xml.preferences);
	}
}
