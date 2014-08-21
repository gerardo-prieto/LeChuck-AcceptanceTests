package com.olx.olx.test;

import com.olx.grog.GrogApplication;
import com.olx.grog.db.DBHelper;
import com.olx.olx.helpers.PreferencesHelper;
import com.olx.olx.test.repository.Repository;
import com.olx.olx.ui.activities.SplashActivity;
import com.robotium.solo.*;

import android.test.ActivityInstrumentationTestCase2;


public class MyFavs extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;
  	private DBHelper dbHelper;
  	
  	public MyFavs() {
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
  		solo.waitForActivity(solo.getCurrentActivity().toString());
		solo.sleep(5000);
		Timeout.setSmallTimeout(40357);
		solo.takeScreenshot();
		if (solo.getCurrentActivity().findViewById(com.olx.olx.R.id.home_post_button) == null) {	
			solo.scrollUp();
			solo.takeScreenshot();
			solo.clickOnText(Repository.LOCATION_COUNTRY);
			solo.clickOnText(Repository.LOCATION_CITY);
		}
		Timeout.setSmallTimeout(61221);
	}
  	
  
   	@Override
   	public void tearDown() throws Exception {
   		solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testAddItemsToMyFavsAnonymous() {
		solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class, 2000);
		solo.waitForActivity(com.olx.olx.ui.activities.HomeActivity.class, 2000);
		solo.sleep(3000);
		solo.takeScreenshot();
		// Click on Drawer
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		// Click on My Favorites
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_favorites));
		assertTrue("com.olx.olx.ui.activities.MyFavoritesActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyFavoritesActivity.class));
		assertTrue(solo.getString(com.olx.olx.R.string.myfavorites_no_results), true);
		solo.takeScreenshot();
		
		// Press menu back key
		solo.goBack();
		solo.takeScreenshot();
		// Click on FrameLayout Cell Phones - Tablets
		solo.clickOnView(solo.getView(android.widget.FrameLayout.class, 7));
			
		// Wait for activity: 'com.olx.olx.ui.activities.ListingActivity'
		assertTrue("com.olx.olx.ui.activities.ListingActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.ListingActivity.class));
		solo.takeScreenshot();
		
		// Add item to Favs
		solo.sleep(3000);
		solo.takeScreenshot();
		solo.clickInList(1, 0);
		assertTrue("com.olx.olx.ui.activities.ItemActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.ItemActivity.class));
		solo.takeScreenshot();
		solo.sleep(3000);
		solo.clickOnView(solo.getView(com.olx.olx.R.id.menu_favorite));
		solo.takeScreenshot();
		
		// Press menu back key
		solo.goBack();
		
		// Click on HomeView
		solo.setNavigationDrawer(Solo.OPENED);
		// Click on FrameLayout Favorite Ads CATEGORIES
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_favorites));
		// Wait for activity: 'com.olx.olx.ui.activities.MyFavoritesActivity'
		assertTrue("com.olx.olx.ui.activities.MyFavoritesActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyFavoritesActivity.class));
		assertTrue(solo.getView(com.olx.olx.R.id.myfavorites_item_favorite) != null);
		solo.takeScreenshot();	
	}
	
	public void testAddItemsToMyFavsLoggedIn() {
		solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class, 2000);
		solo.waitForActivity(com.olx.olx.ui.activities.HomeActivity.class, 2000);
		solo.sleep(3000);
		solo.takeScreenshot();
		// Login
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_login));
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email), Repository.LOGIN_EMAIL);
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password), Repository.LOGIN_PASSWORD);
		solo.takeScreenshot();
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_btn_username));
		solo.waitForActivity(solo.getCurrentActivity().toString());
		assertTrue(solo.searchText(Repository.LOGIN_USERNAME));
		solo.takeScreenshot();
		
		// Click on Drawer and Go to My Favs		
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_favorites));
		solo.takeScreenshot();
		solo.waitForActivity(com.olx.olx.ui.activities.MyFavoritesActivity.class);
		solo.sleep(4000);
		solo.takeScreenshot();
		boolean result;
		if (solo.getCurrentActivity().findViewById(com.olx.olx.R.id.myfavorites_item_favorite) != null){
			result = solo.getCurrentActivity().findViewById(com.olx.olx.R.id.myfavorites_item_favorite).isShown();
		}
			else
				result = false;
		while(result == true){
			// solo.getCurrentActivity().findViewById(com.olx.olx.R.id.myfavorites_item_favorite) != null
			solo.clickOnView(solo.getView(com.olx.olx.R.id.myfavorites_item_favorite));
			solo.waitForDialogToOpen(5000);
			solo.clickOnView(solo.getView(android.R.id.button1));
			solo.sleep(2000);
			solo.waitForActivity(solo.getCurrentActivity().toString());
			result = solo.getCurrentActivity().findViewById(com.olx.olx.R.id.myfavorites_item_favorite).isShown();
			solo.takeScreenshot();
		}
		

		assertTrue("com.olx.olx.ui.activities.MyFavoritesActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyFavoritesActivity.class));
		assertTrue(solo.getString(com.olx.olx.R.string.myfavorites_no_results), true);
		solo.takeScreenshot();
		
		// Press menu back key
		solo.goBack();
		solo.takeScreenshot();
		
		// Click on FrameLayout Cell Phones - Tablets
		solo.clickOnView(solo.getView(android.widget.FrameLayout.class, 7));
		solo.waitForActivity(com.olx.olx.ui.activities.ListingActivity.class);
		solo.takeScreenshot();
		
		
		// Add item to Favs
		solo.sleep(3000);
		solo.clickInList(1, 0);
		assertTrue("com.olx.olx.ui.activities.ItemActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.ItemActivity.class));
		solo.takeScreenshot();
		solo.sleep(3000);
		solo.clickOnView(solo.getView(com.olx.olx.R.id.menu_favorite));
		solo.takeScreenshot();
		
		// Press menu back key
		solo.goBack();
		solo.takeScreenshot();
		
		// Click on HomeView
		solo.setNavigationDrawer(Solo.OPENED);
		solo.takeScreenshot();
		// Click on FrameLayout Favorite Ads CATEGORIES
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_favorites));
		solo.takeScreenshot();
		// Wait for activity: 'com.olx.olx.ui.activities.MyFavoritesActivity'
		assertTrue("com.olx.olx.ui.activities.MyFavoritesActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.MyFavoritesActivity.class));
		assertTrue(solo.getView(com.olx.olx.R.id.myfavorites_item_favorite) != null);
		solo.takeScreenshot();
	}
	
	
}
