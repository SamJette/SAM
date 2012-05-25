package com.ehb.samproject;

public class Student {
	public int ID;
	public String firstName;
	public String name;
	public String password;
	public String email;
	public String number;
	public boolean isOnLine;

	public String classesStudentsID;
	public String schoolYearsID;
	public String startDate;
	public String endDate;

	public String classesID;
	public String classText;

	public String teachersID;

	@Override
	public String toString() {
		return "Student: " + ID + " " + firstName + " " + name + " " + password
				+ " " + email + " " + number + " is online: " + isOnLine;
	}

}
