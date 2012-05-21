package com.ehb.samproject;

import java.lang.reflect.Array;

public class Question {
	private int ID;
	private String questionText;
	private int teachersID;
	private boolean isGepusht;
	
	private int answerID;
	private Array answers;
	private String answerText;
	
	private boolean isCorrect;
	private boolean isOpen;
	
	private int openQuestionsID;
	private String studentAnswer;
	private int resultID;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public int getTeachersID() {
		return teachersID;
	}
	public void setTeachersID(int teachersID) {
		this.teachersID = teachersID;
	}
	public boolean isGepusht() {
		return isGepusht;
	}
	public void setGepusht(boolean isGepusht) {
		this.isGepusht = isGepusht;
	}
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public Array getAnswers() {
		return answers;
	}
	public void setAnswers(Array answers) {
		this.answers = answers;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public int getOpenQuestionsID() {
		return openQuestionsID;
	}
	public void setOpenQuestionsID(int openQuestionsID) {
		this.openQuestionsID = openQuestionsID;
	}
	public String getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	public int getResultID() {
		return resultID;
	}
	public void setResultID(int resultID) {
		this.resultID = resultID;
	}
	
	
}
