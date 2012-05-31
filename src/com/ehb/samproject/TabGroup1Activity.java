package com.ehb.samproject;

import android.content.Intent;
import android.os.Bundle;

public class TabGroup1Activity extends TabGroupActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startChildActivity("VragenTab", new Intent(this, VragenTab.class));

	}

}
