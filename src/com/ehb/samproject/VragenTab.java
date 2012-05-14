package com.ehb.samproject;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VragenTab extends Activity  {
	
		private static final int ACTIVITY_CREATE = 0;
		private static final int ACTIVITY_DETAIL=1;

		private ListView m_listview;
	    private static final String TAG = "VragenList";

		private static final int DELETE_ID = Menu.FIRST + 1;
		private static final int PUSH_ID=Menu.FIRST +2;
		private static final int COPY_ID=Menu.FIRST +3;


	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vragen_tab);
        
        m_listview = (ListView) findViewById(R.id.listViewTabVragen);

        String[] items = new String[] {"Is een vlinder een vogel ?", "Vraag 2", "Vraag 3", "Vraag 4"};
        
        // Adapter for a list with push button
    //    ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.list_item_with_button, R.id.tvViewRow , items);
        
        ListAdapter adapter=new ArrayAdapter<String>(this, R.layout.list_item, items);


        m_listview.setAdapter(adapter);
        
        // to create a Context Menu for the "long press" 
        m_listview.setOnCreateContextMenuListener(this);
        m_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        // click on an item in the listView
        
       m_listview.setOnItemClickListener(
                new OnItemClickListener()
                {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                            int position, long id) {
                      /*  Object o = m_listview.getItemAtPosition(position);
                        String vraag = o.toString();
                        Toast.makeText(getApplicationContext(), "You have chosen the question: " + " " + vraag, Toast.LENGTH_LONG).show();
						*/
                		Intent i = new Intent(VragenTab.this, VragenDetails.class);

                		startActivityForResult(i, ACTIVITY_DETAIL);

                    }   
                }       
        );
    }

    
	//long press to delete, to copy, to push ? ....
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("CONTEXT MENU");
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
		menu.add(0, PUSH_ID, 0, "Push");
		menu.add(0, COPY_ID, 0, "Copy");
	}
	
	// one of the Context Item is selected
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		// to find the item of the list view that was clicked
	//	AdapterView.AdapterContextMenuInfo info = 
	     //       (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		//int index=info.position;

		switch (item.getItemId()) {
		case DELETE_ID:
	        Toast.makeText(getApplicationContext(), "Delete the question", Toast.LENGTH_LONG).show();
	        break;
           // delete(info.id);	
			//return true;
		case PUSH_ID:
	        Toast.makeText(getApplicationContext(), "Push the question", Toast.LENGTH_LONG).show();
	        break;
		case COPY_ID:
	        Toast.makeText(getApplicationContext(), "Copy the question", Toast.LENGTH_LONG).show();
	        break;
			//copy(info.id);
			//return true;
		
		}
		
		return super.onContextItemSelected(item);
	}
	
    // onClick - button "Add Question"
	public void onClick(View v){
        Toast.makeText(getApplicationContext(), "Add question", Toast.LENGTH_LONG).show();

		
	}
    

	// Called with the result of the other activity
	// requestCode was the origin request code send to the activity
	// resultCode is the return code, 0 is everything is ok
	// intend can be used to get data
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
	}



	
	// the push button clicker
	
/*	public void onPushButtonClick(View v){
        Toast.makeText(getApplicationContext(), "Push the question", Toast.LENGTH_LONG).show();

	}
	*/
	
	

    

	



}
