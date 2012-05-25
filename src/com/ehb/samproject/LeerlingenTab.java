package com.ehb.samproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class LeerlingenTab extends Activity {

	// All static variables
	static final String URL = "http://sam.stofke72.cloudbees.net/myoutput/?format=xml";
	public ArrayList<Student> students;
	private ListView myListView;

	// XML node keys
	static final String KEY_ROW = "row"; // parent node
	static final String KEY_DATA = "data";
	static final String KEY_ID = "ID";
	static final String KEY_FIRSTNAME = "FIRSTNAME";
	static final String KEY_NAME = "NAME";
	static final String KEY_EMAIL = "EMAIL";
	static final String KEY_NUMBER = "NUMBER";
	static final String KEY_ISONLINE = "ISONLINE";
	static final String KEY_PASSWORD = "PASSWORD";

	// dialog onItemClick
	static final private int STUDENT_DIALOG = 1;
	private Student aStudent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leerlingen_tab);

		getInterventionListing listing = new getInterventionListing();
		listing.execute();

	}

	private class getInterventionListing extends
			AsyncTask<Integer, Integer, ArrayList<HashMap<String, String>>> {

		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog = ProgressDialog.show(LeerlingenTab.this, "Loading",
					"Data loading", true, true, new OnCancelListener() {
						@Override
						public void onCancel(DialogInterface dialog) {
							dialog.dismiss();
							cancel(true);
						}
					});
		}

		@Override
		protected ArrayList<HashMap<String, String>> doInBackground(
				Integer... params) {
			// Log.w("demo", "Start doInBackground");
			ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

			URL url = null;
			try {
				url = new URL(URL);
				// "http://sam.stofke72.cloudbees.net/myoutput/?format=xml");
			} catch (MalformedURLException el) {
				el.printStackTrace();
			}
			// create an instance of our parser
			StudentParser parser = new StudentParser();

			try {
				// InputStream in =
				// getResources().openRawResource(R.raw.students);

				InputStream in = url.openStream();
				if (in == null)
					Log.e("erreur android", "null");
				else {
					SAXParserFactory factory = SAXParserFactory.newInstance();
					SAXParser sp = null;
					try {
						sp = factory.newSAXParser();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					XMLReader reader = sp.getXMLReader();

					// add our own parser to the xml reader and start parsing
					// the
					// file
					reader.setContentHandler(parser);
					reader.parse(new InputSource(in));
					students = parser.students;
					// Log.d("demo", "Students in the getStudents()= " +
					// students);

					for (int i = 0; i < students.size(); i++) {
						Student temp = students.get(i);
						// Log.d("demo", "firstname student = " +
						// temp.firstName);

						HashMap<String, String> map = new HashMap<String, String>();

						map.put(KEY_FIRSTNAME, temp.firstName);
						map.put(KEY_NAME, temp.name);
						map.put(KEY_EMAIL, temp.email);
						listItem.add(map);
					}

				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return listItem;
		}

		@Override
		protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
			Log.w("demo", "Start onPostExecute" + result);

			if (this.dialog.isShowing())
				this.dialog.dismiss();

			// super.onPostExecute(result);

			myListView = (ListView) findViewById(R.id.listViewTabLeerlingen);

			SimpleAdapter adapter = new SimpleAdapter(LeerlingenTab.this,
					result, R.layout.list_item_student, new String[] {
							KEY_FIRSTNAME, KEY_NAME }, new int[] {
							R.id.firstNameTextView, R.id.lastNameTextView });
			myListView.setAdapter(adapter);

			myListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// Student temp = students.get(arg2);

					aStudent = students.get(arg2);
					showDialog(STUDENT_DIALOG);

				}
			});

		}

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
			String studentText = "Email: " + aStudent.email + "\n"
					+ "Password: " + aStudent.password + "\n" + "IsOnLine: "
					+ aStudent.isOnLine;

			AlertDialog studentDialog = (AlertDialog) dialog;
			studentDialog.setTitle(aStudent.name);
			TextView tv = (TextView) studentDialog
					.findViewById(R.id.studentDetailsTextViewInDialog);
			tv.setText(studentText);

			break;
		}
	}

}
