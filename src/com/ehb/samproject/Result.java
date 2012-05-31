package com.ehb.samproject;

import java.util.Date;

public class Result {
	public int ID;
	public int questionID;
	public Date createDate;
	public int answerID;
	public int studentID;

	public Result(int iD, int questionID, Date createDate, int answerID,
			int studentID) {
		super();
		ID = iD;
		this.questionID = questionID;
		this.createDate = createDate;
		this.answerID = answerID;
		this.studentID = studentID;
	}

	public Result() {
		super();
	}

}
