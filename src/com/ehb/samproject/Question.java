package com.ehb.samproject;

import java.lang.reflect.Array;

public class Question {
	public int ID;
	public String questionText;
	public int teachersID;
	public boolean isGepusht;

	public int answerID;
	public Array answers;
	public String answerText;

	public boolean isCorrect;
	public boolean isOpen;

	public int openQuestionsID;
	public String studentAnswer;
	public int resultID;

	public Question() {
		super();
	}

	public Question(int iD, String questionText, int teachersID,
			boolean isGepusht, int answerID, Array answers, String answerText,
			boolean isCorrect, boolean isOpen, int openQuestionsID,
			String studentAnswer, int resultID) {
		super();
		ID = iD;
		this.questionText = questionText;
		this.teachersID = teachersID;
		this.isGepusht = isGepusht;
		this.answerID = answerID;
		this.answers = answers;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
		this.isOpen = isOpen;
		this.openQuestionsID = openQuestionsID;
		this.studentAnswer = studentAnswer;
		this.resultID = resultID;
	}

}
