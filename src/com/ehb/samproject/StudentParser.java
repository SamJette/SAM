package com.ehb.samproject;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StudentParser extends DefaultHandler {
	public ArrayList<Student> students;
	private Student tempStudent;
	private StringBuilder builder;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String tempString = new String(ch, start, length);
		builder.append(tempString);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.toLowerCase().equals("row")) {
			this.students.add(tempStudent);
			// Log.d("demo", "tempStudent = " + tempStudent);

		}
		// finished reading "firstname" tag assign it to the temp person
		else if (localName.equalsIgnoreCase("FIRSTNAME")) {
			tempStudent.firstName = builder.toString();
		}
		// finished reading "lastname" tag assign it to the temp person
		else if (localName.equalsIgnoreCase("NAME")) {
			tempStudent.name = builder.toString();
		}
		// finished reading "number" tag assign it to the temp person
		else if (localName.equalsIgnoreCase("NUMBER")) {
			tempStudent.number = builder.toString();
		}
	}

	@Override
	public void startDocument() throws SAXException {
		students = new ArrayList<Student>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equalsIgnoreCase("data")) {

			builder = new StringBuilder();
		}

		if (localName.equalsIgnoreCase("row")) {
			tempStudent = new Student();
			builder = new StringBuilder();
		}

		else if (localName.toLowerCase().equals("FIRSTNAME")) {
			builder = new StringBuilder();
		}

		else if (localName.toLowerCase().equals("NAME")) {
			builder = new StringBuilder();
		}

		else if (localName.toLowerCase().equals("NUMBER")) {
			builder = new StringBuilder();
		}
	}

}
