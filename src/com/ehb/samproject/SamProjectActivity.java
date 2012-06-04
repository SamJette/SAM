package com.ehb.samproject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

import com.loopj.android.http.PersistentCookieStore;

public class SamProjectActivity extends TabActivity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
		RestClient.client.setCookieStore(myCookieStore);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Question Time ?");

		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		intent = new Intent().setClass(this, LeerlingenTab.class);
		spec = tabHost.newTabSpec("Leerlingen")
				.setIndicator(myTabTextView("Leerlingen")).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, TabGroup1Activity.class);
		spec = tabHost.newTabSpec("Vragen")
				.setIndicator(myTabTextView("Vragen")).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ResultatenTab.class);
		spec = tabHost.newTabSpec("Resultaten")
				.setIndicator(myTabTextView("Resultaten")).setContent(intent);

		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SettingsTab.class);
		spec = tabHost.newTabSpec("Settings")
				.setIndicator(myTabTextView("Settings")).setContent(intent);
		tabHost.addTab(spec);

		// tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#8E1208"));

		// tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#E26608"));

		tabHost.setCurrentTab(0);

	}

	public TextView myTabTextView(String myTabTitle) {
		TextView txtTab = new TextView(this);
		txtTab.setTextColor(Color.BLACK);
		txtTab.setPadding(8, 9, 8, 9);
		txtTab.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		txtTab.setBackgroundResource(R.drawable.list_tab_selector);
		txtTab.setTypeface(Typeface.DEFAULT_BOLD);
		txtTab.setTextSize(20);
		txtTab.setText(myTabTitle);
		return txtTab;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.menu, menu);
		menu.add(0, 0, Menu.NONE, "Refresh");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			Activity myCurrentActivity = this.getCurrentActivity();
			if (LeerlingenTab.class.isInstance(myCurrentActivity) == true) {
				((LeerlingenTab) myCurrentActivity).refreshStudentList();
			}
			return true;
		}
		return false;
	}

	/*
	 * public void Login() {
	 * 
	 * RequestParams params = new RequestParams();
	 * 
	 * params.put("teacher[email]", "kristof.polleunis@gmail.com");
	 * params.put("teacher[password]", "123"); params.put("teacher[classid]",
	 * "1");
	 * 
	 * RestClient.post("", params, new AsyncHttpResponseHandler() { private
	 * ProgressDialog dialog;
	 * 
	 * @Override public void onStart() { dialog =
	 * ProgressDialog.show(SamProjectActivity.this, "Loading", "Data loading",
	 * true, true, new OnCancelListener() { public void onCancel(DialogInterface
	 * dialog) { dialog.dismiss(); // cancel(true); } }); }
	 * 
	 * @Override public void onSuccess(String response) { if
	 * (this.dialog.isShowing()) this.dialog.dismiss();
	 * System.out.println(response); } });
	 * 
	 * }
	 */

}