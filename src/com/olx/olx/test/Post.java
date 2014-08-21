package com.olx.olx.test;

import com.olx.grog.GrogApplication;
import com.olx.grog.db.DBHelper;
import com.olx.olx.helpers.PreferencesHelper;
import com.olx.olx.test.repository.Repository;
import com.olx.olx.ui.activities.SplashActivity;
import com.robotium.solo.*;

import android.test.ActivityInstrumentationTestCase2;


public class Post extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;
  	private int random_number = (int )(Math.random() * 5000 + 1);
	String POST_TITLE = Repository.POST_TITLE + random_number;
  	private DBHelper dbHelper;
  	
  	public Post() {
		super(SplashActivity.class);
  	}

  	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
// 		PreferencesHelper.clear();
		PreferencesHelper.setTutorialseen(true);
		PreferencesHelper.setShowListingTooltip(false);
		PreferencesHelper.setApiEndpoint(Repository.ENVIRONMENT);
   		// Clear DB
		dbHelper = GrogApplication.getInstance().getDbhelper();
  		if (dbHelper != null){
			dbHelper.clearCache();
	  		dbHelper.purgeCache();
  		}
		getActivity();
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

  	}
  
   	@Override
   	public void tearDown() throws Exception {
   		solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testPostLoggedIn() {
		//Login
		solo.waitForActivity(com.olx.olx.ui.activities.HomeActivity.class,4000);
		solo.sleep(4000);
		Timeout.setSmallTimeout(40357);
		solo.setNavigationDrawer(Solo.OPENED);
	
		//Check if User was logged before
		if (PreferencesHelper.getUser() != null) {
			solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_logout));
			solo.waitForDialogToOpen(5000);
			solo.clickOnView(solo.getView(android.R.id.button1));
			solo.setNavigationDrawer(Solo.OPENED);
		}
		
		solo.sleep(2000);
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_login));		
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email), Repository.LOGIN_EMAIL);
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password), Repository.LOGIN_PASSWORD);
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_btn_username));
		
		
		// Click on Post An Ad
		solo.waitForActivity(com.olx.olx.ui.activities.HomeActivity.class, 2000);		
	  	solo.clickOnView(solo.getView(com.olx.olx.R.id.home_post_button));
		solo.takeScreenshot();

		// Step 1
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_category));
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickInList(1, 0);


		
		// Step 2
		
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		solo.takeScreenshot();
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), POST_TITLE);
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1), Repository.POST_DESCRIPTION);
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.price_field_text_price));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.price_field_text_price), Repository.POST_PRICE);
		solo.takeScreenshot();
		// Click on OK
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		

		
		// Step 3
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_contact));
		solo.takeScreenshot();
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text,0));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), Repository.POST_CONTACTNAME);
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 2));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 2), Repository.POST_PHONE);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		solo.takeScreenshot();
		
		// Click on Publicar ahora
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_post));
		solo.waitForDialogToOpen(5000);
		solo.clickOnView(solo.getView(android.R.id.button2));
		
		//Verify Posted Item
		solo.takeScreenshot();
		solo.sleep(4000);
		solo.goBack();
		solo.setNavigationDrawer(Solo.OPENED);
		solo.clickInList(3, 0);
		assertTrue("com.olx.olx.ui.activities.MyAdsActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyAdsActivity.class));
		while(!solo.searchText(POST_TITLE)){
			solo.scrollListToTop(0);
		}
			
		solo.clickOnText(POST_TITLE);
		assertTrue(solo.searchText(POST_TITLE) && solo.searchText(Repository.POST_DESCRIPTION) && solo.searchText(Repository.POST_PRICE));
		solo.takeScreenshot();

	}
   	
   	
	public void testPostAnonymous() {
		//Check if User was logged before
		if (PreferencesHelper.getUser() != null) {
			solo.setNavigationDrawer(Solo.OPENED);
			solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_logout));
			solo.waitForDialogToOpen(5000);
			solo.clickOnView(solo.getView(android.R.id.button1));
		}
		
		solo.waitForActivity(com.olx.olx.ui.activities.HomeActivity.class,4000);
		solo.sleep(4000);
		
		// Click on Post An Ad
		solo.clickOnView(solo.getView(com.olx.olx.R.id.home_post_button));
		solo.takeScreenshot();

		// Step 1
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_category));
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		solo.takeScreenshot();
		solo.clickInList(1, 0);

		
		// Step 2
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
		solo.takeScreenshot();
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), POST_TITLE);
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1), Repository.POST_DESCRIPTION);
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.price_field_text_price));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.price_field_text_price), Repository.POST_PRICE);
		solo.takeScreenshot();
		// Click on OK
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		
		// Step 3
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_contact));
		solo.takeScreenshot();
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text,0));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), Repository.POST_CONTACTNAME);
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1), Repository.POST_EMAIL);
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 2));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 2), Repository.POST_PHONE);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
		solo.takeScreenshot();
		
		// Click on Publicar ahora
		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_post));
		solo.waitForDialogToOpen(5000);
		solo.clickOnView(solo.getView(android.R.id.button2));
		
		solo.takeScreenshot();
	
		//Verify Posted Item
		solo.takeScreenshot();
		solo.sleep(4000);
		solo.goBack();
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		solo.clickInList(3, 0);
		assertTrue("com.olx.olx.ui.activities.MyAdsActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyAdsActivity.class));
		solo.takeScreenshot();
		while(!solo.searchText(POST_TITLE)){
			solo.scrollListToTop(0);
		}
		solo.clickOnText(POST_TITLE);
		solo.takeScreenshot();
		solo.waitForActivity(solo.getCurrentActivity().toString());
		assertTrue(solo.searchText(POST_TITLE) && solo.searchText(Repository.POST_DESCRIPTION) && solo.searchText(Repository.POST_PRICE));
		solo.takeScreenshot();
				
	}
}
