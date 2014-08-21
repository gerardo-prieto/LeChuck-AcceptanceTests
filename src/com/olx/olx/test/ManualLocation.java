package com.olx.olx.test;

import com.olx.grog.GrogApplication;
import com.olx.grog.db.DBHelper;
import com.olx.olx.helpers.PreferencesHelper;
import com.olx.olx.test.repository.Repository;
import com.olx.olx.ui.activities.SplashActivity;
import com.robotium.solo.*;

import android.test.ActivityInstrumentationTestCase2;


public class ManualLocation extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;
  	private DBHelper dbHelper;
  	
  	public ManualLocation() {
		super(SplashActivity.class);
  	}

  	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
  		PreferencesHelper.clear();
		PreferencesHelper.setTutorialseen(true);
		PreferencesHelper.setShowListingTooltip(false);
		PreferencesHelper.setApiEndpoint(Repository.ENVIRONMENT);
		getActivity();
   		// Clear DB
		dbHelper = GrogApplication.getInstance().getDbhelper();
  		if (dbHelper != null){
			dbHelper.clearCache();
	  		dbHelper.purgeCache();
  		}
		solo.sleep(5000);
		Timeout.setSmallTimeout(40357);
		solo.takeScreenshot();
		if (solo.getCurrentActivity().findViewById(com.olx.olx.R.id.home_post_button) == null) {	
			solo.scrollUp();
			solo.clickOnText(Repository.LOCATION_COUNTRY);
			solo.takeScreenshot();
			solo.clickOnText(Repository.LOCATION_CITY);
			solo.takeScreenshot();
			solo.clickOnView(solo.getView(com.olx.olx.R.id.view_home_location_pin));
		}
	}
  	
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testManualLocation() {
		// Click on Capital Federal, Argentina
		solo.clickOnView(solo.getView(com.olx.olx.R.id.view_home_location_place));
		solo.takeScreenshot();

		if(!solo.getView(com.olx.olx.R.id.location_settings_country_container).isEnabled()){
			solo.clickOnView(solo.getView(com.olx.olx.R.id.check));
		}
		solo.takeScreenshot();
		
		// Click on País Argentina
		solo.clickOnView(solo.getView(com.olx.olx.R.id.location_settings_country_container));
		solo.takeScreenshot();
		solo.clickOnText(Repository.LOCATION_COUNTRY);
		
		solo.clickOnView(solo.getView(com.olx.olx.R.id.location_settings_city_container));
		solo.takeScreenshot();
		solo.clickOnText(Repository.LOCATION_CITY);
		solo.takeScreenshot();
		
		// Click on HomeView Ubicación 
		solo.goBack();
		assertTrue(solo.searchText(Repository.LOCATION_CITY + ", " + Repository.LOCATION_COUNTRY));
		solo.takeScreenshot();
	}
}
