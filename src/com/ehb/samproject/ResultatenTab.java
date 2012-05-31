package com.ehb.samproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ResultatenTab extends Activity {

	static final String KEY_FIRSTNAME = "FIRSTNAME";
	static final String KEY_NAME = "NAME";
	static final String KEY_ISJUIST = "ISJUIST";

	static final String KEY_QUESTIONTEXT = "QUESTIONTEXT";
	static final String KEY_DATE = "CREATEDDATE";
	static final String KEY_ANSWER = "ANSWERTEXT";

	Student aStudent;
	Question aQuestion;
	Result aResult;
	Answer anAnswer;

	public static final ArrayList<Student> students = new ArrayList<Student>();
	public static final ArrayList<Question> questions = new ArrayList<Question>();
	public static final ArrayList<Result> results = new ArrayList<Result>();
	public static final ArrayList<Answer> answers = new ArrayList<Answer>();

	// private ArrayList<Result> resultArray
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultaten_tab);

		ArrayList groupList = new ArrayList();
		Map groupData1 = new HashMap();
		groupData1.put(KEY_FIRSTNAME, "Jean");
		groupData1.put(KEY_NAME, " Dupond ");
		groupData1.put(KEY_ISJUIST, "1/2");
		groupList.add(groupData1);

		Map groupData2 = new HashMap();
		groupData2.put(KEY_FIRSTNAME, "Capucine");
		groupData2.put(KEY_NAME, " Durand ");
		groupData2.put(KEY_ISJUIST, "2/2");
		groupList.add(groupData2);

		ArrayList childList = new ArrayList();

		ArrayList childList1 = new ArrayList();
		Map childMap1 = new HashMap();
		childMap1.put(KEY_QUESTIONTEXT, "Is een vlinder een vogel ?");
		childMap1.put(KEY_DATE, "01/06/2012");
		childMap1.put(KEY_ANSWER, "Ja");
		childList1.add(childMap1);

		Map childMap2 = new HashMap();
		childMap2.put(KEY_QUESTIONTEXT, "2+2= ?");
		childMap2.put(KEY_DATE, "01/06/2012");
		childMap2.put(KEY_ANSWER, "4");
		childList1.add(childMap2);

		childList.add(childList1);

		ArrayList childList2 = new ArrayList();
		Map childMap21 = new HashMap();
		childMap21.put(KEY_QUESTIONTEXT, "Is een vlinder een vogel ?");
		childMap21.put(KEY_DATE, "01/06/2012");
		childMap21.put(KEY_ANSWER, "Nee");
		childList2.add(childMap21);

		Map childMap22 = new HashMap();
		childMap22.put(KEY_QUESTIONTEXT, "2+2= ?");
		childMap22.put(KEY_DATE, "01/06/2012");
		childMap22.put(KEY_ANSWER, "4");
		childList2.add(childMap22);

		childList.add(childList2);

		SimpleExpandableListAdapter listAdapter = new SimpleExpandableListAdapter(
				this, groupList, R.layout.list_item_resultaten_student,
				new String[] { KEY_FIRSTNAME, KEY_NAME, KEY_ISJUIST },
				new int[] { R.id.firstnameResults, R.id.nameResults,
						R.id.resultsTextView }, childList,
				R.layout.list_item_resultaten_results, new String[] {
						KEY_QUESTIONTEXT, KEY_DATE, KEY_ANSWER }, new int[] {
						R.id.questionTextView, R.id.dateAnswerTextView,
						R.id.answerTextTextView });
		ExpandableListView myListView = (ExpandableListView) findViewById(R.id.listViewTabResultaten);
		myListView.setAdapter(listAdapter);

	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();
		Log.d("demo", "onContentChanged");
	}

	/*
	 * private List<HashMap<String, Object>> createGroupList() {
	 * ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,
	 * Object>>(); Student student1 = new Student(); student1.firstName = "Jim";
	 * student1.name = "Dupond"; student1.correctResults = 2;
	 * student1.totalQuestion = 4; student1.resultText =
	 * String.valueOf(student1.correctResults) + " / " +
	 * String.valueOf(student1.totalQuestion);
	 * 
	 * students.add(student1);
	 * 
	 * Student student2 = new Student(); student2.firstName = "Capucine";
	 * student2.name = "Martin"; student2.correctResults = 4;
	 * student2.totalQuestion = 4;
	 * 
	 * student2.resultText = String.valueOf(student2.correctResults) + " / " +
	 * String.valueOf(student2.totalQuestion);
	 * 
	 * students.add(student2);
	 * 
	 * for (int i = 0; i < students.size(); i++) { HashMap<String, Object> m =
	 * new HashMap<String, Object>(); Student temp = students.get(i);
	 * 
	 * m.put(KEY_FIRSTNAME, temp.firstName); m.put(KEY_NAME, temp.name);
	 * m.put(KEY_ANSWER, temp.resultText); result.add(m); } return result; }
	 * 
	 * private ArrayList<HashMap<String, Object>> createChildList() {
	 * ArrayList<HashMap<String, Object>> resultArray = new
	 * ArrayList<HashMap<String, Object>>(); Question question1 = new
	 * Question(); question1.questionText = "Is a vlinder a vogel ?";
	 * 
	 * // for (int i=0; i<)
	 * 
	 * return resultArray; }
	 */
}
