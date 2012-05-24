package com.ehb.samproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class LeerlingenTab extends Activity {

	// All static variables
	static final String URL = "http://dl.dropbox.com/u/471136/students.xml";
	// XML node keys
	static final String KEY_ROW = "row"; // parent node
	static final String KEY_ID = "ID";
	static final String KEY_FIRSTNAME = "FIRSTNAME";
	static final String KEY_NAME = "NAME";
	static final String KEY_EMAIL = "EMAIL";

	public ArrayList<Student> students;
	private ListView myListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leerlingen_tab);

		// ArrayList<HashMap<String, String>> menuItems = new
		// ArrayList<HashMap<String, String>>();

		// Adding menuItems to ListView
		// ListAdapter adapter = new SimpleAdapter(this, menuItems,
		// R.layout.list_item, new String[] { KEY_NAME, KEY_FIRSTNAME,
		// KEY_EMAIL }, new int[] { R.id.name_label,
		// R.id.firstname_label, R.id.mail_label });

		// ListView studentList = (ListView)
		// findViewById(R.id.listViewTabLeerlingen);
		// populateList();
		// studentList.setAdapter(adapter);
		getInterventionListing listing = new getInterventionListing();
		listing.execute();

	}

	private class getInterventionListing extends
			AsyncTask<Integer, Integer, ArrayList<Student>> {

		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			// super.onPreExecute();
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
		protected ArrayList<Student> doInBackground(Integer... params) {
			Log.w("demo", "Start doInBackground");

			URL url = null;
			try {
				url = new URL(
						"http://sam.stofke72.cloudbees.net/myoutput/?format=xml");
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
					Log.d("demo", "Students in the getStudents()= " + students);

				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Log.d("demo", "Array of students = " + students);

			return students;
		}

		@Override
		protected void onPostExecute(ArrayList<Student> result) {
			Log.w("demo", "Start onPostExecute" + result);
			// super.onPostExecute(result);

			ListAdapter adapter = new ArrayAdapter<Student>(LeerlingenTab.this,
					android.R.layout.simple_list_item_1, result);
			myListView = (ListView) findViewById(R.id.listViewTabLeerlingen);
			myListView.setAdapter(adapter);

			if (this.dialog.isShowing())
				this.dialog.dismiss();

			/*
			 * SimpleAdapter mSchedule = new SimpleAdapter (F1Activity.this,
			 * listItem, R.layout.listview, new String[] {"img", "titre",
			 * "description"}, new int[] {R.id.img, R.id.titre,
			 * R.id.description}); maListViewPerso.setAdapter(mSchedule);
			 */

			/*
			 * myListView.setOnItemClickListener(new OnItemClickListener() {
			 * 
			 * @Override public void onItemClick(AdapterView<?> arg0, View arg1,
			 * int arg2, long arg3) { // TODO Auto-generated method stub Student
			 * temp = students.get(arg2);
			 * 
			 * textViewFirstName = (TextView)
			 * findViewById(R.id.textViewFirstName);
			 * textViewFirstName.setText(temp.firstname);
			 * 
			 * textViewName = (TextView) findViewById(R.id.textViewLastName);
			 * textViewName.setText(temp.name);
			 * 
			 * textNumber = (TextView) findViewById(R.id.textViewAge);
			 * textNumber.setText(temp.number);
			 * 
			 * } }); // myListView.setOnItemClickListener(this);
			 */

		}

	}

}
