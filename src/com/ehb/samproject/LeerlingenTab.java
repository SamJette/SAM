package com.ehb.samproject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LeerlingenTab extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leerlingen_tab);
		
        String[] leerlingen = getResources().getStringArray(R.array.leerlingen_array);
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, leerlingen);
        ListView leerlingenLijst = (ListView)findViewById(R.id.listViewTabLeerlingen);
        leerlingenLijst.setAdapter(adapter);
 
        // to retrieve the IP Address 
        try{
        	String addr=getLocalIPAddress();
        	if (addr==null){
        		Toast.makeText(this, "IP address not avaiable - are you online ?", Toast.LENGTH_LONG).show();
        	}
        	else{
        	//	ClipboardManager cm=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        		//cm.setText(addr);
        		TextView et=(TextView)findViewById(R.id.idTextView);
        		et.setText("Login student to http://"+addr +":8080");
        		Toast.makeText(this, "IP Address clipped!", Toast.LENGTH_SHORT).show();
        	}
        }
        catch (Exception e) {
        	Log.e("IPCLipper", "Exception getting IP address", e);
        	Toast.makeText(this, "Could not obtain IP address", Toast.LENGTH_LONG).show();
        }

	}
	
	// method to retrieve the IP address
	private String getLocalIPAddress() throws SocketException{
		Enumeration<NetworkInterface>nics=NetworkInterface.getNetworkInterfaces();
		
		while (nics.hasMoreElements()){
			NetworkInterface intf=nics.nextElement();
			Enumeration<InetAddress> addrs=intf.getInetAddresses();
			
			while (addrs.hasMoreElements()) {
				InetAddress addr =  addrs.nextElement();
				
				if (!addr.isLoopbackAddress()) {
					return (addr.getHostAddress().toString());
				}
			}
		}
		return (null);
	}
}