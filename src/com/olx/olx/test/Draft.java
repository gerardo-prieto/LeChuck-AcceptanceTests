package com.olx.olx.test;

import android.test.ActivityInstrumentationTestCase2;

import com.olx.grog.GrogApplication;
import com.olx.grog.db.DBHelper;
import com.olx.olx.helpers.PreferencesHelper;
import com.olx.olx.test.repository.Repository;
import com.olx.olx.ui.activities.SplashActivity;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;


public class Draft extends ActivityInstrumentationTestCase2<SplashActivity> {
	private Solo solo;
  	private DBHelper dbHelper;
  	
  	public Draft() {
		super(SplashActivity.class);
  	}

  	public void setUp() throws Exception {
  		PreferencesHelper.clear();
		PreferencesHelper.setTutorialseen(true);
		PreferencesHelper.setApiEndpoint(Repository.ENVIRONMENT);
		solo = new Solo(getInstrumentation());
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
			solo.takeScreenshot();
			solo.clickOnText(Repository.LOCATION_COUNTRY);
			solo.takeScreenshot();
			solo.clickOnText(Repository.LOCATION_CITY);
		}
		solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class, 2000);
		assertTrue("com.olx.olx.ui.activities.HomeActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.HomeActivity.class));
		Timeout.setSmallTimeout(15843);
		solo.takeScreenshot();
	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testCreateAndView() {

		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		
		// Go to My Ads + Check empty state
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_my_ads));
		assertTrue("com.olx.olx.ui.activities.MyAdsActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyAdsActivity.class));
        assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.myads_no_results)));
        solo.takeScreenshot();
        // Create a Draft
		solo.goBack();
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.home_post_button));
		solo.takeScreenshot();
		assertTrue("com.olx.olx.ui.activities.PublishActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.PublishActivity.class));
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_category));
		assertTrue("com.olx.olx.ui.activities.CategoriesActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.CategoriesActivity.class));
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		solo.takeScreenshot();
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), Repository.DRAFT_TITLE);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		solo.goBack();
		solo.takeScreenshot();
		solo.sleep(2000);
		assertTrue(solo.getString(com.olx.olx.R.string.publish_save_draft), solo.waitForView(solo.getView(android.R.id.message)));
		solo.takeScreenshot();
		
		// View Draft
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_my_ads));
		assertTrue("com.olx.olx.ui.activities.MyAdsActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyAdsActivity.class));
		assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.draft)));
		solo.takeScreenshot();
		solo.clickInList(1,0);
		assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.publish)) && solo.searchText(solo.getString(com.olx.olx.R.string.publish_add_photos_tip)));
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		assertTrue(solo.searchText(Repository.DRAFT_TITLE));
		solo.takeScreenshot();
	}
	
	public void testEditDraft() {
	
		// Create a Draft
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_my_ads));
		assertTrue("com.olx.olx.ui.activities.MyAdsActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyAdsActivity.class));
        assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.myads_no_results)));
        solo.takeScreenshot();
        solo.goBack();
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.home_post_button));
		assertTrue("com.olx.olx.ui.activities.PublishActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.PublishActivity.class));
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_category));
		assertTrue("com.olx.olx.ui.activities.CategoriesActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.CategoriesActivity.class));
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), Repository.DRAFT_TITLE);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		solo.takeScreenshot();
		solo.goBack();
		solo.sleep(2000);
		assertTrue(solo.getString(com.olx.olx.R.string.publish_save_draft), solo.waitForView(solo.getView(android.R.id.message)));
		solo.takeScreenshot();
			
		// Edit Draft
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_my_ads));
		solo.clickInList(1,0);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), Repository.DRAFT_TITLE_EDITED);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		solo.takeScreenshot();
		solo.goBack();
		solo.sleep(2000);
		assertTrue(solo.getString(com.olx.olx.R.string.publish_save_draft), solo.waitForView(solo.getView(android.R.id.message)));
		solo.takeScreenshot();
		solo.clickInList(1,0);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		assertTrue(solo.searchText(Repository.DRAFT_TITLE_EDITED));
		solo.takeScreenshot();
	}
	
	
	public void testDeleteDraft() {
		
		// Create a Draft
		solo.setNavigationDrawer(Solo.OPENED);
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_my_ads));
		assertTrue("com.olx.olx.ui.activities.MyAdsActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyAdsActivity.class));
        assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.myads_no_results)));
        solo.takeScreenshot();
		solo.goBack();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.home_post_button));
		assertTrue("com.olx.olx.ui.activities.PublishActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.PublishActivity.class));
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_category));
		assertTrue("com.olx.olx.ui.activities.CategoriesActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.CategoriesActivity.class));
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		solo.takeScreenshot();
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), Repository.DRAFT_TITLE);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		solo.takeScreenshot();
		solo.goBack();
		solo.takeScreenshot();
		solo.waitForText(solo.getString(com.olx.olx.R.string.publish_save_draft));
		assertTrue(solo.getString(com.olx.olx.R.string.publish_save_draft), solo.waitForView(solo.getView(android.R.id.message)));
		solo.takeScreenshot();
			
		// Delete Draft
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_my_ads));
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.myads_item_menu));
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
		solo.waitForDialogToOpen(5000);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(android.R.id.button1));
		assertTrue(solo.searchText(solo.getString(com.olx.olx.R.string.myads_no_results)));
		solo.takeScreenshot();
	}
}
