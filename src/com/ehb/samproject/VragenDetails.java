package com.ehb.samproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VragenDetails extends Activity {
	Button saveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.vragen_details);

        saveButton=(Button)findViewById(R.id.buttonBewaren);
       
	}
	
	public void saveTheQuestionOnCLick(View v){
		

	}
	
	

}
