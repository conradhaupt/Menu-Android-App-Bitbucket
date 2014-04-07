package com.conradhaupt.MenU;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OrdersFragment extends Fragment {
	/* Variables */
	// Static Variables
	public static final String TAG = "OrdersFragment";

	/* Methods */
	public static OrdersFragment newInstance() {
		OrdersFragment fragment = new OrdersFragment();
		// Bundle args = new Bundle();
		// args.putString(ARG_PARAM1, param1);
		// fragment.setArguments(args);
		return fragment;
	}

	public OrdersFragment() {
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
		return inflater.inflate(R.layout.fragment_orders, container, false);
	}

}
