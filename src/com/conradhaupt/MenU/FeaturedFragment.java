package com.conradhaupt.MenU;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FeaturedFragment extends Fragment {
	/* Variables */
	// Static Variables
	public static final String TAG = "FeaturedFragment";

	/* Methods */
	public static FeaturedFragment newInstance() {
		FeaturedFragment fragment = new FeaturedFragment();
		// Bundle args = new Bundle();
		// args.putString(ARG_PARAM1, param1);
		// fragment.setArguments(args);
		return fragment;
	}

	public FeaturedFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// if (getArguments() != null) {
		// mParam1 = getArguments().getString(ARG_PARAM1);
		// }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_featured, container, false);
	}

}
