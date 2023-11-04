package oopproje.GuiClasses;

import oopproje.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StartExam {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JFrame frame;
	private JLabel label;
	private JTextField userText;
	private JButton button;
	private oopproje.TimerGui timer;
	public StartExam(Users users,GameModes gameModes,Game exam,Child child) {
		panel=new JPanel();
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLocation(400, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);

		button = new JButton("Sınavı başlat");
		button.setBounds(150, 150, 200, 50);
		button.addActionListener(new ActionListener() {
			Random rand=new Random(); 
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				Reports report=new Reports();
				child.setReport(report);
				child.setStartDate();
				int counter=0;
				timer= new oopproje.TimerGui();
				if(counter<exam.getQuestionCount()) {
					child.getReport().setGameName(gameModes.getKey(exam));
					for(int i=0;i<exam.getQuestionCount();i++){// sorular random şekilde oluşturuluyor
						Integer a=rand.nextInt(1,exam.getLimit1());
						Integer b=rand.nextInt(1,exam.getLimit2());
						Integer c=a*b;
						child.getReport().addQuestion(new Question(a.toString()+"x"+b.toString()+"=",(c).toString()));
						// sorulan soruların güncesini tutmak için raporlara ekliyorum.
					}
					child.addReport();
					try {
						getQuestionGui(users,gameModes,child,exam, counter);
						//her seferinde yeni sorunun bulunduğu bir frame açılıyor bunu da recrusive bir fonksiyon olan getQuestionGui() ile gerçekleştirdim
					} catch (ClassNotFoundException ex) {
						throw new RuntimeException(ex);
					}

				}
				child.addReport();
				users.saveUsers();
				gameModes.saveGameModes();

			}
		});
		panel.add(button);
		
		frame.setVisible(true);
	}
	
	public void getQuestionGui(Users users,GameModes gameModes,Child child,Game exam,int counter) throws ClassNotFoundException {
		if(counter<exam.getQuestionCount()) {
			panel=new JPanel();
			frame = new JFrame();
			frame.setSize(500, 500);
			frame.setLocation(400, 200);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(panel);
			panel.setLayout(null);
			
			long time1 = System.currentTimeMillis()/1000; 
			label = new JLabel();
			label.setText(counter+1+". Soru: ");
			label.setBounds(60, 20, 200, 30);
			panel.add(label);
			label = new JLabel();
			label.setText(child.getReport().getQuestions().get(counter).getQuestion());
			label.setBounds(100, 50, 200, 30);
			panel.add(label);
			userText = new JTextField(30);
			userText.setBounds(200, 50, 150, 30);
			panel.add(userText);
			
			button = new JButton("Devam");
			button.setBounds(100, 150, 200, 50);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					long time2 = System.currentTimeMillis()/1000; 
					if((time2-time1)>=3600) {
						Integer a=(int)(time2-time1) /3600 ;
						Integer b=((int)(time2-time1) %3600)/60 ;
						Integer c=(int)(time2-time1) %60 ;
						child.getReport().getQuestions().get(counter).setTime((a).toString()+" saat, "+(b).toString()+" dakika, "+(c).toString()+" saniye.");
					}
					else if((time2-time1)>=60) {
						Integer a=(int)(time2-time1) /60 ;
						Integer b=(int)(time2-time1) %60 ;
						child.getReport().getQuestions().get(counter).setTime((a).toString()+" dakika, "+(b).toString()+" saniye.");
					}
					else {
						Integer b=(int)(time2-time1);
						child.getReport().getQuestions().get(counter).setTime(b.toString()+ " saniye.");
					}
					
					if(userText.getText().compareToIgnoreCase(child.getReport().getQuestions().get(counter).getAnswer())==0) {
						child.getReport().getQuestions().get(counter).setBool(true);
					}
					else {
						child.getReport().getQuestions().get(counter).setBool(false);
					}
					child.addReport();
					users.saveUsers();
					frame.dispose();
					try {
						getQuestionGui(users,gameModes,child,exam,counter+1);
					} catch (ClassNotFoundException ex) {
						throw new RuntimeException(ex);
					}
				}
			});
			panel.add(button);
			frame.setVisible(true);
		}else {
			child.setEndDate();
			timer.frameClose(timer.getFrame());
			child.getReport().calculateScore();
			exam.addScore(new ScoreTable(child.getName(),gameModes.getKey(exam),child.getReport().getScore()));
			gameModes.saveGameModes();
			users.saveUsers();
			listResult(users,gameModes,child,exam);
		}
	}
	public void listScoreTable(Users users,GameModes gameModes,Game exam) {
		panel=new JPanel();
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLocation(400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);

		label = new JLabel();
		label.setText("PUAN  TABLOSU");
		label.setBounds(200, 20, 600, 30);
		label.setForeground(Color.blue);
		panel.add(label);

		Integer y=80,i=1;
		if(exam.getScores().size()>0) {
			ArrayList<String> arr= new ArrayList<>();
			arr.add("SINAV ADI: ");
			arr.add(exam.getScores().get(0).getExamName().toUpperCase());
			arr.add("");
			DefaultTableModel model = new DefaultTableModel(arr.toArray(),1);
			JTable jtable=new JTable(model);
			jtable.setSelectionBackground(Color.LIGHT_GRAY);
			jtable.setGridColor(Color.blue);
			jtable.setBackground(Color.ORANGE);
			String[] row_heading = {"  SIRALAMA", "   İSİM","   PUAN"};
			model.addRow(row_heading);
			for(ScoreTable aScore: exam.getScores()) {
				arr.clear();
				arr.add("    "+i.toString());
				arr.add("    "+aScore.getName());
				arr.add("    "+aScore.getScore());
				model.addRow(arr.toArray());
				if(y<300)
					y=y+14;
				i++;
			}
			JScrollPane s= new JScrollPane(jtable);
			s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			s.setBounds(50, 50, 400, y);
			panel.add(s);
		}
		button = new JButton("Ana Menü");
		button.setBounds(200, y+50, 100, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new oopproje.Login(users,gameModes);
			}
		});
		panel.add(button);
		frame.setVisible(true);
	}
	public void listResult(Users users,GameModes gameModes,Child child,Game exam) {
		panel=new JPanel();
		frame = new JFrame();
		frame.setSize(600, 500);
		frame.setLocation(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		int y=50,i=1;
		String[] row_heading2 = {"SORU", "DOĞRU/YANLIŞ", "AYRILAN SÜRE"};

		label = new JLabel();
		label.setText("Başlangıç: "+child.getStartDate()+"  Bitiş:"+child.getEndDate());
		label.setBounds(60, 20, 600, 30);
		panel.add(label);
		DefaultTableModel model = new DefaultTableModel(row_heading2,1);
		JTable jtable=new JTable(model);
		jtable.setSelectionBackground(Color.LIGHT_GRAY);
		jtable.setGridColor(Color.white);
		jtable.setBackground(Color.PINK);
		ArrayList<String> arr = new ArrayList<>();
		for(Question q:child.getReport().getQuestions()) {
			arr.clear();
			arr.add(q.getQuestion()+q.getAnswer());
			if(!q.getBool())
				arr.add("yanlış");
			else
				arr.add("doğru");
			arr.add( q.getTime());
			model.addRow(arr.toArray());
			if(y<150)y=y+15;
			i++;
		}
		JScrollPane s= new JScrollPane(jtable);
		s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		s.setBounds(50, 100, 400, y);
		panel.add(s);
		label = new JLabel();
		label.setText("Toplam süre: "+child.getReport().getTotalTime());
		label.setBounds(60, y+100, 600, 30);
		panel.add(label);
		label = new JLabel();
		label.setText("PUAN: "+child.getReport().getScore()+"                            (Doğru yanıt * 10  - (harcanan süre/2))");
		label.setBounds(60, y+150, 600, 30);
		panel.add(label);
		button = new JButton("Puan Tablosu");
		button.setBounds(200, y+200, 200, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				listScoreTable(users,gameModes,exam);
			}
		});
		panel.add(button);
		frame.setVisible(true);
	}
	
}
