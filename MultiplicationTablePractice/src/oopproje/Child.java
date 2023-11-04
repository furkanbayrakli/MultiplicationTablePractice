package oopproje;

import java.util.ArrayList;
import java.util.Date;

public class Child implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Reports report;
	private ArrayList<Reports> reports;
	

	public Child(String name) {
		this.name=name;
		report= new Reports();
		reports = new ArrayList<Reports>();
	}

	public void addReport(){
		if(!reports.contains(report))
			reports.add(report);
	}

	public ArrayList<Reports> getReports() {
		return reports;
	}

	public void setReport(Reports report) {
		this.report = report;
	}

	public void setStartDate() {
		report.setStartDate();
	}

	public String getName() {
		return name;
	}
	public void setEndDate(){report.setEndDate();}
	public Reports getReport() {
		return report;
	}

	public Date getStartDate() {
		return report.getStartDate();
	}

	public Date getEndDate() {
		return report.getEndDate();
	}

	
}
