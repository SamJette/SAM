package com.ehb.samproject;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class SamProjectActivity extends TabActivity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		intent = new Intent().setClass(this, LeerlingenTab.class);
		spec = tabHost.newTabSpec("Leerlingen").setIndicator("Leerlingen")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, VragenTab.class);
		spec = tabHost.newTabSpec("Vragen").setIndicator("Vragen")
				.setContent(intent);
		tabHost.addTab(spec);

		/*
		 * intent=new Intent().setClass(this, VragenSetTab.class);
		 * spec=tabHost.newTabSpec("VragenSet") .setIndicator("VragenSet")
		 * .setContent(intent); tabHost.addTab(spec);
		 */

		intent = new Intent().setClass(this, ResultatenTab.class);
		spec = tabHost.newTabSpec("Resultaten").setIndicator("Resultaten")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SettingsTab.class);
		spec = tabHost.newTabSpec("Settings").setIndicator("Settings")
				.setContent(intent);
		tabHost.addTab(spec);

		// tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#8E1208"));

		// tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#E26608"));

		tabHost.setCurrentTab(0);

	}

}