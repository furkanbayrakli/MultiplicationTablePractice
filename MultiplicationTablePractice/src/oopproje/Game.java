package oopproje;

import java.util.ArrayList;

public class Game implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int limit1;
	private int limit2;
	private int questionCount;
	private ArrayList<ScoreTable> scores;

	public Game(int limit1,int limit2,int questionCount) {
		this.limit1=limit1;this.limit2=limit2;this.questionCount=questionCount;
		scores= new ArrayList<ScoreTable>();
	}
	public void addScore(ScoreTable aScore) {
		int i=0;
		while(i<scores.size() && scores.get(i).getScore()>aScore.getScore()) 
			i++;
		scores.add(i, aScore);
	}
	public int getLimit1() {
		return limit1;
	}

	public ArrayList<ScoreTable> getScores() {
		return scores;
	}

	public int getLimit2() {
		return limit2;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	
}
