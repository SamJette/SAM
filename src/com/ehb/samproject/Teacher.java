package com.ehb.samproject;

import java.util.ArrayList;

public class Teacher {
	private int ID;
	private String firstName;
	private String name;
	private String password;
	private String email;
	
	private int rolesID;
	private String titleRole;
	
	private int isActivated;
	private ArrayList<Student> studentArray;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRolesID() {
		return rolesID;
	}
	public void setRolesID(int rolesID) {
		this.rolesID = rolesID;
	}
	public String getTitleRole() {
		return titleRole;
	}
	public void setTitleRole(String titleRole) {
		this.titleRole = titleRole;
	}
	public int getIsActivated() {
		return isActivated;
	}
	public void setIsActivated(int isActivated) {
		this.isActivated = isActivated;
	}
	public ArrayList<Student> getStudentArray() {
		return studentArray;
	}
	public void setStudentArray(ArrayList<Student> studentArray) {
		this.studentArray = studentArray;
	}
	
	
	

}
