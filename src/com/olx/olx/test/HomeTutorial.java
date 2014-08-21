package com.olx.olx.test;

import com.olx.grog.GrogApplication;
import com.olx.grog.db.DBHelper;
import com.olx.olx.helpers.PreferencesHelper;
import com.olx.olx.test.repository.Repository;
import com.olx.olx.ui.activities.SplashActivity;
import com.robotium.solo.*;

import android.test.ActivityInstrumentationTestCase2;

public class HomeTutorial extends
		ActivityInstrumentationTestCase2<SplashActivity> {
	private Solo solo;
  	private DBHelper dbHelper;

	public HomeTutorial() {
		super(SplashActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		PreferencesHelper.clear();
		PreferencesHelper.setApiEndpoint(Repository.ENVIRONMENT);
		PreferencesHelper.setTutorialseen(false);
   		// Clear DB
		dbHelper = GrogApplication.getInstance().getDbhelper();
  		if (dbHelper != null){
			dbHelper.clearCache();
	  		dbHelper.purgeCache();
  		}
		getActivity();
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public void testHomeTutorial() {
		solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class,2000);
		Timeout.setSmallTimeout(15645);
		solo.takeScreenshot();
		assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.tutorial_title)));
		assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.tutorial_screen_one)));
		solo.takeScreenshot();
		solo.scrollViewToSide(solo.getView(com.olx.olx.R.id.mainViewPager), Solo.RIGHT);
		assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.tutorial_screen_two)));
		solo.takeScreenshot();
		solo.scrollViewToSide(solo.getView(com.olx.olx.R.id.mainViewPager),	Solo.RIGHT);
		assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.tutorial_screen_three)));
		solo.takeScreenshot();
		solo.clickOnText(java.util.regex.Pattern.quote(solo.getString(com.olx.olx.R.string.tutorial_start)));
		solo.takeScreenshot();

		solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class, 3000);
		solo.sleep(4000);
		solo.waitForActivity(solo.getCurrentActivity().toString());
		solo.takeScreenshot();
	
		if (solo.getCurrentActivity().findViewById(com.olx.olx.R.id.home_post_button) == null) {
			solo.scrollUp();
			solo.takeScreenshot();
			solo.clickOnText(Repository.LOCATION_COUNTRY);
			solo.takeScreenshot();
			solo.clickOnText(Repository.LOCATION_CITY);
		}

		assertTrue("com.olx.olx.ui.activities.HomeActivity is not found!",
				solo.waitForActivity(com.olx.olx.ui.activities.HomeActivity.class));
		solo.takeScreenshot();
	}
}
