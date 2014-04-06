package com.conradhaupt.MenU;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {

	/* Variables */
	// Object Variables
	private DrawerLayout mDrawerLayout;
	private ListView mDrawer;
	private FrameLayout mFrame;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private DrawerAdapter mDrawerAdapater;
	private FragmentManager fManager;

	// Fragment Variables
	private FeaturedFragment fFeatureFragment = null;
	private OrdersFragment fOrdersFragment = null;
	private SettingsFragment fSettingsFragment = null;

	// Value Variables
	private String nDrawerOpenTitle;
	private String nDrawerClosedTitle;

	// Static Variables
	public static final int FRAGMENT_IN_BACKSTACK = 0;
	public static final int FRAGMENT_CURRENT = 1;
	public static final int FRAGMENT_NOT_IN_BACKSTACK = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActivityPre();
		setContentView(R.layout.activity_main);
		initActivityPost();
	}

	@Override
	protected void onStart() {
		super.onStart();
		openFeaturedFragment();
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

	@Override
	public void onBackPressed() {
		// If there is only one fragment left then close activity, if not then
		// handle normally
		if (fManager.getBackStackEntryCount() > 1) {
			super.onBackPressed();
		} else {
			this.finish();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> viewGroup, View view, int position,
			long id) {
		// Notify adapter of click event
		this.mDrawerAdapater.setCurrent(position);
		this.mDrawerAdapater.notifyDataSetChanged();

		// Process button actions
		switch (position) {
		case 0:
			// Navigate to Featured Fragment
			openFeaturedFragment();
			break;
		case 1:
			// Navigate to Orders Fragment
			openOrdersFragment();
			break;
		case 2:
			// Navigate to Settings Fragment
			openSettingsFragment();
			break;
		default:
			System.out.println("No action defined for list item of position "
					+ position);
			break;
		}

	}

	/* Initialisation Methods */
	private void initActivityPre() {

		// Process theme preference
		switch (Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(
				this).getString("preference_list_theme_colour", "-1"))) {
		case -1:
			System.out.println("Theme preference not set, defaulting to Red");
		case 0:
			System.out.println("Setting theme as Red");
			this.setTheme(R.style.AppTheme_Red);
			break;
		case 1:
			System.out.println("Setting theme as Green");
			this.setTheme(R.style.AppTheme_Green);
			break;
		case 2:
			System.out.println("Setting theme as Peach");
			this.setTheme(R.style.AppTheme_Peach);
			break;
		default:
			break;
		}

		// Process ActionBar Values
		this.getActionBar().setDisplayShowHomeEnabled(true);
		this.getActionBar().setHomeButtonEnabled(true);
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

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
		mDrawer.setOnItemClickListener(this);

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

		// Instantiate FragmentManager
		fManager = this.getFragmentManager();
	}

	private void openFeaturedFragment() {
		// Initialize fragment if necessary

		// Check if the fragment exists in the backstack
		switch (checkForFragment(FeaturedFragment.TAG)) {
		case FRAGMENT_CURRENT:
			// Fragment is the current one, do nothing
			System.out
					.println("FeaturedFragment is the current one, doing nothing");
			break;
		case FRAGMENT_IN_BACKSTACK:
			// Fragment exists, pop to it
			fManager.popBackStack(FeaturedFragment.TAG, 0);
			System.out
					.println("FeaturedFragment exists, popping to its position");
			break;
		case FRAGMENT_NOT_IN_BACKSTACK:
			// Fragment has not been opened
			// Instantiate fragment if necessary
			if (fFeatureFragment == null) {
				fFeatureFragment = FeaturedFragment.newInstance();
			}

			// Transition fragment into fragmentManager
			FragmentTransaction fTransaction = fManager.beginTransaction();
			fTransaction.replace(R.id.activity_main_frame, fFeatureFragment,
					FeaturedFragment.TAG);
			fTransaction.setCustomAnimations(android.R.animator.fade_in,
					android.R.animator.fade_out, android.R.animator.fade_in,
					android.R.animator.fade_out);
			fTransaction.addToBackStack(FeaturedFragment.TAG);
			fTransaction.commit();

			break;
		default:
			// Houston, we have a problem
			System.out.println("Received a result that isn't handled.");
			break;
		}

		// Close drawer
		mDrawerLayout.closeDrawers();
	}

	private void openOrdersFragment() {
		// Initialize fragment if necessary

		// Check if the fragment exists in the backstack
		switch (checkForFragment(OrdersFragment.TAG)) {
		case FRAGMENT_CURRENT:
			// Fragment is the current one, do nothing
			System.out
					.println("OrdersFragment is the current one, doing nothing");
			break;
		case FRAGMENT_IN_BACKSTACK:
			// Fragment exists, pop to it
			fManager.popBackStack(OrdersFragment.TAG, 0);
			System.out
					.println("OrdersFragment exists, popping to its position");
			break;
		case FRAGMENT_NOT_IN_BACKSTACK:
			// Fragment has not been opened
			// Instantiate fragment if necessary
			if (fOrdersFragment == null) {
				fOrdersFragment = OrdersFragment.newInstance();
			}

			// Transition fragment into fragmentManager
			FragmentTransaction fTransaction = fManager.beginTransaction();
			fTransaction.replace(R.id.activity_main_frame, fOrdersFragment,
					OrdersFragment.TAG);
			fTransaction.setCustomAnimations(android.R.animator.fade_in,
					android.R.animator.fade_out, android.R.animator.fade_in,
					android.R.animator.fade_out);
			fTransaction.addToBackStack(OrdersFragment.TAG);
			fTransaction.commit();
			break;
		default:
			// Houston, we have a problem
			System.out.println("Received a result that isn't handled.");
			break;
		}

		// Close drawer
		mDrawerLayout.closeDrawers();
	}

	private void openSettingsFragment() {
		// Initialize fragment if necessary

		// Check if the fragment exists in the backstack
		switch (checkForFragment(SettingsFragment.TAG)) {
		case FRAGMENT_CURRENT:
			// Fragment is the current one, do nothing
			System.out
					.println("SettingsFragment is the current one, doing nothing");
			break;
		case FRAGMENT_IN_BACKSTACK:
			// Fragment exists, pop to it
			fManager.popBackStack(SettingsFragment.TAG, 0);
			System.out
					.println("SettingsFragment exists, popping to its position");
			break;
		case FRAGMENT_NOT_IN_BACKSTACK:
			// Fragment has not been opened
			// Instantiate fragment if necessary
			if (fSettingsFragment == null) {
				fSettingsFragment = SettingsFragment.newInstance();
			}

			// Transition fragment into fragmentManager
			FragmentTransaction fTransaction = fManager.beginTransaction();
			fTransaction.replace(R.id.activity_main_frame, fSettingsFragment,
					SettingsFragment.TAG);
			fTransaction.setCustomAnimations(android.R.animator.fade_in,
					android.R.animator.fade_out, android.R.animator.fade_in,
					android.R.animator.fade_out);
			fTransaction.addToBackStack(SettingsFragment.TAG);
			fTransaction.commit();
			break;
		default:
			// Houston, we have a problem
			System.out.println("Received a result that isn't handled.");
			break;
		}

		// Close drawer
		mDrawerLayout.closeDrawers();
	}

	private int checkForFragment(String fragmentTag) {
		// Check if the current fragment is the chosen one
		try {
			// Check if fragment exists in the backstack
			if (fManager.findFragmentByTag(fragmentTag) != null) {
				System.out.println("step 1");
				// Fragment exists, check if it is the current one
				System.out.println("Fragment manager has "
						+ fManager.getBackStackEntryCount());
				if (fManager
						.getBackStackEntryAt(
								fManager.getBackStackEntryCount() - 1)
						.getName().equals(fragmentTag)) {
					// Fragment is the current one therefore do nothing
					System.out.println("step 2");
					return FRAGMENT_CURRENT;
				} else {
					// Fragment is in the backstack but not the current one
					System.out.println("step 3");
					return FRAGMENT_IN_BACKSTACK;
				}
			}
		} catch (Exception e) {
			System.out
					.println("Receieved following error while checking for fragment with tag "
							+ fragmentTag + ":\n" + e);
		}

		// Return output
		return FRAGMENT_NOT_IN_BACKSTACK;
	}

	/* Classes */
	public class DrawerAdapter extends ArrayAdapter<String> {

		public int current = 0;

		public DrawerAdapter(Context context, int resource,
				int textViewResourceId, String[] objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View outputView = super.getView(position, convertView, parent);

			// Process current
			// System.out.println("Processing position " + position
			// + " with current location being position " + current);

			// Process current styling
			TextView title = (TextView) outputView
					.findViewById(android.R.id.title);
			if (current == position) {
				title.setTypeface(null, Typeface.BOLD);
			} else {
				title.setTypeface(null, Typeface.NORMAL);
			}

			// Return view
			return outputView;
		}

		public void setCurrent(int currentPosition) {
			current = currentPosition;
		}
	}
}
