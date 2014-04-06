package com.conradhaupt.MenU;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends Activity {

	/* Variables */
	// Object Variables
	private DrawerLayout mDrawerLayout;
	private ListView mDrawer;
	private FrameLayout mFrame;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private DrawerAdapter mDrawerAdapater;

	// Fragment Variables
	private FeaturedFragment fFeatureFragment;

	// Value Variables
	private String nDrawerOpenTitle;
	private String nDrawerClosedTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActivityPre();
		setContentView(R.layout.activity_main);
		initActivityPost();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mActionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mActionBarDrawerToggle.syncState();
	}

	/* Initialisation Methods */
	private void initActivityPre() {
		// Process ActionBar Values
		this.getActionBar().setDisplayShowHomeEnabled(true);
		this.getActionBar().setHomeButtonEnabled(true);
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		// Process theme preference
		switch (this.getSharedPreferences("preferences", Context.MODE_PRIVATE)
				.getInt("preference_list_theme_colour", 0)) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		default:
			break;
		}
	}

	private void initActivityPost() {
		// Init Drawer variables
		mDrawerLayout = (DrawerLayout) this
				.findViewById(R.id.activity_main_drawer_layout);
		mDrawer = (ListView) this.findViewById(R.id.activity_main_drawer);
		mFrame = (FrameLayout) this.findViewById(R.id.activity_main_frame);

		// Initialise drawer adapter
		mDrawerAdapater = new DrawerAdapter(this,
				R.layout.activity_main_drawer_item, android.R.id.title, this
						.getResources().getStringArray(
								R.array.activity_main_drawer_titles));
		mDrawer.setAdapter(mDrawerAdapater);

		// Set default starting titles
		nDrawerClosedTitle = this.getResources().getString(
				R.string.activity_main_drawer_closed_title);
		nDrawerOpenTitle = this.getResources().getString(
				R.string.activity_main_drawer_open_title);

		// Assign actionbardrawertoggle values
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_navigation_drawer,
				R.string.activity_main_drawer_open_title,
				R.string.activity_main_drawer_closed_title) {

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle(nDrawerClosedTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(nDrawerOpenTitle);
				invalidateOptionsMenu();
			}
		};
		// Assign actionBarDrawerToggle listener
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
	}

	private void initFeaturedFragment() {
		// TODO add in initialization code
	}

	private void initOrdersFragment() {
		// TODO add in initialization code
	}

	private void initSettingsFragment() {
		// TODO add in initialization code
	}

	/* Classes */
	public class DrawerAdapter extends ArrayAdapter<String> {

		public DrawerAdapter(Context context, int resource,
				int textViewResourceId, String[] objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			System.out.println("GetView run");
			return super.getView(position, convertView, parent);
		}
	}
}
