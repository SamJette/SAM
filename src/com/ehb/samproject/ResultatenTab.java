package com.ehb.samproject;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultatenTab extends Activity {

//	private ArrayList<Result> resultArray
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.resultaten_tab);
        
        String[] resultaten = getResources().getStringArray(R.array.leerlingen_array);
        
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.list_item_with_resultaten, R.id.tvViewRowResultaten , resultaten);

        //ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, resultaten);
        ListView resultatenLijst = (ListView)findViewById(R.id.listViewTabResultaten);
        resultatenLijst.setAdapter(adapter);
          

	}
	
	

}
