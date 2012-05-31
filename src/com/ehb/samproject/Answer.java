package com.ehb.samproject;

public class Answer {
	public String AnswerText;
	public boolean isJuist;

	public Answer(String answerText, boolean isJuist) {
		super();
		AnswerText = answerText;
		this.isJuist = isJuist;
	}

	public Answer() {
		super();
	}

}
