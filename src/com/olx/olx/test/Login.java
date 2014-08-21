package com.olx.olx.test;

import android.test.ActivityInstrumentationTestCase2;

import com.olx.grog.GrogApplication;
import com.olx.grog.db.DBHelper;
import com.olx.olx.helpers.PreferencesHelper;
import com.olx.olx.test.repository.Repository;
import com.olx.olx.ui.activities.SplashActivity;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

public class Login extends ActivityInstrumentationTestCase2<SplashActivity> {
	private Solo solo;
  	private DBHelper dbHelper;

	public Login() {
		super(SplashActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		PreferencesHelper.clear();
		PreferencesHelper.setTutorialseen(true);
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
		}
	}

	@Override
	public void tearDown() throws Exception {
		//Logout
		solo.setNavigationDrawer(Solo.OPENED);
		if (PreferencesHelper.getUser() != null) {
			solo.clickInList(5, 0);
			solo.waitForDialogToOpen(5000);
			solo.clickOnView(solo.getView(android.R.id.button1));
		}
		solo.finishOpenedActivities();
	}

	public void testLogin() {
		solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class,4000);
		solo.takeScreenshot();
		solo.setNavigationDrawer(Solo.OPENED);
		
		//Check if User was logged before
		if (PreferencesHelper.getUser() != null) {
			solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_logout));
			solo.waitForDialogToOpen(5000);
			solo.clickOnView(solo.getView(android.R.id.button1));
			solo.setNavigationDrawer(Solo.OPENED);
		}
		solo.takeScreenshot();
		solo.clickOnText(solo.getString(com.olx.olx.R.string.menu_login));
		// Fill Username field
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email), Repository.LOGIN_EMAIL);
	
		// Fill Password field
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password), Repository.LOGIN_PASSWORD);
		solo.takeScreenshot();
		
		// Click on Ingresar
		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_btn_username));
		solo.waitForActivity(solo.getCurrentActivity().toString());
		
		solo.takeScreenshot();
		assertTrue(solo.searchText(Repository.LOGIN_USERNAME));
	}
}
