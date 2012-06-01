package com.ehb.samproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class LeerlingenTab extends Activity {

	// All static variables
	public ArrayList<Student>	students;
	public Student				aStudent;

	private ListView			myListView;

	// XML node keys
	static final String			KEY_ROW				= "row";			// parent node
	static final String			KEY_DATA			= "data";
	static final String			KEY_ID				= "ID";
	static final String			KEY_FIRSTNAME		= "FIRSTNAME";
	static final String			KEY_NAME			= "NAME";
	static final String			KEY_EMAIL			= "EMAIL";
	static final String			KEY_NUMBER			= "NUMBER";
	static final String			KEY_ISONLINE		= "ISONLINE";
	static final String			KEY_PASSWORD		= "PASSWORD";
	static final String			KEY_IMAGE_ISONLINE	= "IMAGE_ISONLINE";

	// dialog onItemClick
	static final private int	STUDENT_DIALOG		= 1;

	// menu to refresh
	// ! also needed in SamProjectActivity !!
	// static final int MENU_UPDATE = Menu.FIRST;
	private final int			ID_MENU_UPDATE		= 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leerlingen_tab);

		studentListing();
	}

	public void refreshStudentList(View v) {
		studentListing();
	}

	public void refreshStudentList() {
		studentListing();
	}

	public void studentListing() {
		RestClient.get("students.xml", null, new AsyncHttpResponseHandler() {
			private ProgressDialog	dialog;

			@Override
			public void onStart() {
				dialog = ProgressDialog.show(LeerlingenTab.this, "Loading", "Data loading", true, true, new OnCancelListener() {
					public void onCancel(DialogInterface dialog) {
						dialog.dismiss();
					}
				});
			}

			@Override
			public void onSuccess(String response) {
				if (this.dialog.isShowing())
					this.dialog.dismiss();

				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

				// create an instance of our parser
				StudentParser parser = new StudentParser();

				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser sp = null;
				try {
					sp = factory.newSAXParser();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				XMLReader reader = null;
				try {
					reader = sp.getXMLReader();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// add our own parser to the xml reader and start parsing
				// the
				// file
				reader.setContentHandler(parser);
				try {
					reader.parse(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				students = parser.students;
				// Log.d("demo", "Students in the getStudents()= " +
				// students);

				for (int i = 0; i < students.size(); i++) {
					Student temp = students.get(i);

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put(KEY_FIRSTNAME, temp.firstName);
					map.put(KEY_NAME, temp.name);
					map.put(KEY_EMAIL, temp.email);
					map.put(KEY_ISONLINE, temp.isOnLine);

					// change image if student is online or not
					Log.d("demo", "is on line= " + temp.isOnLine);
					if (temp.isOnLine.equalsIgnoreCase("1")) {
						map.put(KEY_IMAGE_ISONLINE, R.drawable.vert);
					} else {
						map.put(KEY_IMAGE_ISONLINE, R.drawable.orange);
					}

					listItem.add(map);
				}

				myListView = (ListView) findViewById(R.id.listViewTabLeerlingen);

				SimpleAdapter adapter = new SimpleAdapter(LeerlingenTab.this, listItem, R.layout.list_item_student, new String[] { KEY_FIRSTNAME, KEY_NAME,
						KEY_IMAGE_ISONLINE }, new int[] { R.id.firstNameTextView, R.id.lastNameTextView, R.id.logo });
				myListView.setAdapter(adapter);

				myListView.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// Student temp = students.get(arg2);

						aStudent = students.get(arg2);
						showDialog(STUDENT_DIALOG);

					}
				});

				System.out.println(response);
			}
		});
	}

	// dialog onItemClick

	@Override
	public Dialog onCreateDialog(int id) {
		switch (id) {
			case (STUDENT_DIALOG):
				LayoutInflater li = LayoutInflater.from(this);
				View quakeDetailsView = li.inflate(R.layout.student_detail, null);

				AlertDialog.Builder quakeDialog = new AlertDialog.Builder(this);
				quakeDialog.setTitle("Student Time");
				quakeDialog.setView(quakeDetailsView);
				return quakeDialog.create();
		}
		return null;
	}

	@Override
	public void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
			case (STUDENT_DIALOG):
				String studentText = "Email: " + aStudent.email + "\n" + "Password: " + aStudent.password + "\n" + "IsOnLine: " + aStudent.isOnLine;

				AlertDialog studentDialog = (AlertDialog) dialog;
				studentDialog.setTitle(aStudent.name);
				TextView tv = (TextView) studentDialog.findViewById(R.id.studentDetailsTextViewInDialog);
				tv.setText(studentText);

				break;
		}
	}

}
