package com.conradhaupt.MenU;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

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

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		// Handle preference if restart clicked
		if (preference.getKey().equals("preference_about_restart")) {
			System.out.println("Recreating app");
			this.getActivity().recreate();
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
}
