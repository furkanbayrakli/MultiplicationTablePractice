package oopproje;

import java.util.ArrayList;
import java.util.Date;


public class Reports implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	private ArrayList<Question> questions;
	private int score;
	private String gameName;
	public Reports() {
		questions=new ArrayList<Question>(); 
	}
	
	public int getScore() {
		return score;
	}

	public void calculateScore() {
		score=0;
		for(Question q: questions) 
			if(q.getBool()) 
				score=score+10;
		score=score-(int)(endDate.getTime()-startDate.getTime())/2000;
		if(score<0)
			score=0;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}


	public void addQuestion(Question aQuestion) {

		questions.add(aQuestion);
	}
	public void setStartDate() {
		startDate=new Date();
	}
	public void setEndDate() {
		endDate=new Date();
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameName() {
		return gameName;
	}

	public String getTotalTime() {
		Long t=(endDate.getTime()-startDate.getTime())/1000;
		return t/3600+":"+(t/60)%60+":"+t%60;
	}

}
