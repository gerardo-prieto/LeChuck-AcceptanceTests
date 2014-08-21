//package com.olx.olx.test;
//
//import android.test.ActivityInstrumentationTestCase2;
//import android.widget.EditText;
//
//import com.olx.olx.R;
//import com.robotium.solo.Solo;
//import com.robotium.solo.Timeout;
//
//public class SmokeTestSuite extends ActivityInstrumentationTestCase2 {
//	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.olx.olx.ui.activities.SplashActivity";
//	private Solo solo;
//	private static Class launcherActivityClass;
//
//	private static final String COUNTRY = "India";
//	private static final String CITY_LOCATION = "Delhi";
//
//	private static final String PRODUCTION_ENVIRONMENT = "^.*Production.*$";
//	private static final String TESTING_ENVIRONMENT = "^.*Testing.*$";
//
//	private static final String MOBILE_AND_TABLETS_CATEGORY = "Mobiles & Tablets";
//	private static final String MOBILE_PHONES_SUBCATEGORY = "Mobile Phones";
//
//	private static final String PUBLISH_TITLE = "This is my title for a test";
//	private static final String PUBLISH_DESCRIPTION = "This is my description for a test with 30 char at least";
//	private static final String PUBLISH_EMAIL = "automated_qa_test@olx.com";
//	private static final String PUBLISH_PRICE = "5000";
//	private static final String PUBLISH_CITY = CITY_LOCATION;
//	private static final String PUBLISH_CONTACT_NAME = "Alberto Martinez";
//	private static final String PUBLISH_CONFIRMATION_MESSAGE = "^.*Your Ad is now submitted and pending approval by our team.*$";
//
//	private static final String LOGIN_USERNAME = "damianb@olx.com";
//	private static final String LOGIN_PASSWORD = "dami21";
//	private static final String PUBLISH_PHONE_NUMBER = "9512312";
//
//	public SmokeTestSuite() throws ClassNotFoundException {
//		super(launcherActivityClass);
//	}
//
//	static {
//		try {
//			launcherActivityClass = Class
//					.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//
//		}
//	}
//
//	protected void setUp() throws Exception {
//		super.setUp();
//		solo = new Solo(getInstrumentation(), getActivity());
//	}
//
//	public void test0SeTestingEnvironment() {
//		if (solo.searchText("Select Country")) {
//			solo.clickOnMenuItem(COUNTRY);
//		}
//		solo.clickOnText("^.*Welcome.*$");
//		if (solo.searchText(PRODUCTION_ENVIRONMENT)) {
//			solo.clickOnText(TESTING_ENVIRONMENT);
//			solo.clickOnText("Testing");
//		}
//		solo.clickOnText("^.*OLX.*$");
//		solo.sendKey(Solo.MENU);
//		solo.clickOnText("Change Location");
//		solo.clickOnMenuItem(COUNTRY);
//		assertTrue(solo.searchText("Latest in India"));
//	}
//
//	public void testPostAnAd() {
////		solo.clickOnView(solo.getView(R.id.view_home_post_banner_make_money));
//	
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.home_post_button));
//
//		solo.clickOnView(solo.getView(R.id.publish_category));
////		solo.clickOnText(MOBILE_AND_TABLETS_CATEGORY);
////		solo.clickOnText(MOBILE_PHONES_SUBCATEGORY);
//		
//		// Click on FrameLayout Mobiles & Tablets
//		solo.clickInList(1, 0);
//		// Click on FrameLayout Mobile Phones
//		solo.clickInList(1, 0);
//		
////		solo.clickOnMenuItem("OK");
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
//		
////		solo.clickOnText("Describe your Item");
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_describe));
//
//		solo.typeText(0, PUBLISH_TITLE);
//		solo.typeText(1, PUBLISH_DESCRIPTION);
//		
//		// Click on Empty Text View
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.text_field_text));
//		// Enter the text: 'This is my automated title'
//		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
//		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), "This is my automated title");
//		// Enter the text: 'This is my automated description'
//		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1));
//		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text, 1), "This is my automated description");
//		
//		
//		
//		
////		solo.typeText((EditText) solo.getView(R.id.price_field_text_price),
////				PUBLISH_PRICE);
//		
//		// Click on Precio Fijo
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.price_field_spinner_type));
//		// Click on Empty Text View
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.price_field_text_price));
//		// Click on Empty Text View
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.price_field_text_price));
//
//		
//		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.price_field_text_price));
//		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.price_field_text_price), "1000");
//		
//		
////		solo.clickOnMenuItem("OK");
//		
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.action_selection_ok));
//
////		solo.clickOnText("Add your Info");
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_contact));
//		
//	//	solo.typeText(0, PUBLISH_EMAIL);
//	//	solo.typeText(1, PUBLISH_PHONE_NUMBER);
//
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.text_field_text));
//		// Enter the text: 'john.doe.automated@olx.com'
//		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text));
//		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.text_field_text), "john.doe.automated@olx.com");
//		
//		
//		solo.clickOnView(solo.getView(R.id.location_field_location));
//		solo.clickOnText(PUBLISH_CITY);
//		solo.clickOnMenuItem("OK");
//
////		assertTrue(solo.searchText(PUBLISH_CONFIRMATION_MESSAGE));
//		
//		// Click on Publicar ahora
//
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.publish_post));
//		// Set default small timeout to 13440 milliseconds
//		Timeout.setSmallTimeout(13440);
//
//		solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class);
//		
//		// Wait for activity: 'com.olx.olx.ui.activities.SplashActivity'
//		assertTrue("com.olx.olx.ui.activities.SplashActivity is not found!", solo.waitForActivity(com.olx.olx.ui.activities.SplashActivity.class));
//	
//	}
//
//	public void testReplyAnAd() {
//		solo.clickOnText(MOBILE_AND_TABLETS_CATEGORY);
//		if (solo.searchText("Select Location"))
//			solo.clickOnText(CITY_LOCATION);
//		solo.clickInList(2);
//		solo.clickOnText("E-mail");
//		solo.typeText(0, PUBLISH_CONTACT_NAME);
//		solo.clearEditText(1);
//		solo.typeText(1, PUBLISH_EMAIL);
//		solo.typeText(2, PUBLISH_DESCRIPTION);
//		solo.clickOnView(solo.getView(R.id.menu_send));
//		assertTrue(solo.searchText("Contact"));
//	}
//
//	public void testLogin() {
//		solo.clickOnView(solo.getView(android.widget.LinearLayout.class, 11));
//		if (solo.searchText("Salir") || solo.searchText("Logout") ) {
//			solo.clickInList(5, 0);
//			// Wait for dialog
//			solo.waitForDialogToOpen(5000);
//			// Click on OK
//			solo.clickOnView(solo.getView(android.R.id.button1));
//			solo.clickOnView(solo.getView(android.widget.LinearLayout.class, 11));
//		}
//		solo.clickInList(5, 0);
//	
//		// Click on Empty Text View
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
//		// Enter the text: 'damianb@olx.com'
//		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email));
//		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_email), "damianb@olx.com");
//		// Click on Empty Text View
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
//		// Enter the text: 'dami21'
//		solo.clearEditText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password));
//		solo.enterText((android.widget.EditText) solo.getView(com.olx.olx.R.id.fragment_login_edt_password), "dami21");
//		// Click on Ingresar
//		solo.clickOnView(solo.getView(com.olx.olx.R.id.fragment_login_btn_username));
//		assertTrue(solo.searchText("damian_olx"));
//	}
//
//	public void testBrowseACategory() {
//		solo.clickOnText(MOBILE_AND_TABLETS_CATEGORY);
//		if (solo.searchText("Select Location"))
//			solo.clickOnText(CITY_LOCATION);
//		assertTrue(solo.searchText(MOBILE_AND_TABLETS_CATEGORY)
//				&& solo.searchText("results in Delhi")
//				&& solo.searchText("Sort") && solo.searchText("Filters"));
//	}
//
//	public void testLocateInACountry() {
//		solo.sendKey(Solo.MENU);
//		solo.clickOnText("Change Location");
//		solo.clickOnMenuItem(COUNTRY);
//		assertTrue(solo.searchText("Latest in India"));
//	}
//
//	
//	protected void tearDown() throws Exception {
//		solo.finishOpenedActivities();
//	}
//
//}
