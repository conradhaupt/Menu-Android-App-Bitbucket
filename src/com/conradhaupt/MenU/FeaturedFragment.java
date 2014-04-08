package com.conradhaupt.MenU;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.conradhaupt.MenU.Models.Restaurant;

public class FeaturedFragment extends Fragment {
	/* Variables */
	// Object Variables
	private FeaturedGridViewAdapter fFeaturedGridViewAdapter;

	// View Variables
	private GridView mGridView;

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
	public void onResume() {
		super.onResume();
		initFragmentPost();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_featured, container, false);
	}

	/* Initialization Methods */
	private void initFragmentPost() {
		// Initialize GridView
		mGridView = (GridView) this.getActivity().findViewById(
				R.id.fragment_featured_gridview);

		// Initialize GridView Adapter
		Restaurant[] restaurants = new Restaurant[2];
		restaurants[0] = new Restaurant();
		restaurants[1] = new Restaurant();
		fFeaturedGridViewAdapter = new FeaturedGridViewAdapter(
				this.getActivity(), R.layout.simple_item_image_1, 0,
				restaurants);

		// Assign GridView Adapter
		mGridView.setAdapter(fFeaturedGridViewAdapter);
	}

	/* Classes */
	private class FeaturedGridViewAdapter extends ArrayAdapter<Restaurant> {

		public FeaturedGridViewAdapter(Context context, int resource,
				int textViewResourceId, Restaurant[] objects) {
			super(context, resource, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater lLayoutInflater = LayoutInflater.from(getContext());
			// Inflate layout file if needed
			if (convertView == null) {
				convertView = lLayoutInflater.inflate(
						R.layout.simple_item_image_1, null, false);
			}

			// TODO change view values

			// Return view
			return convertView;
		}

	}

}
