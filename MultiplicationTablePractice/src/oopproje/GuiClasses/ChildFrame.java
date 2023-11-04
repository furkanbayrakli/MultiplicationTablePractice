package oopproje;

import oopproje.GuiClasses.StartExam;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

public class ChildFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JFrame frame;
	private JLabel label;
	private JTextField userText;
	private JButton button;
	private JButton button2;
	
	public ChildFrame(Users users,Child child,GameModes gameModes) {
		panel=new JPanel();
		frame = new JFrame();
		frame.setSize(580, 500);
		frame.setLocation(400, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		
		button = new JButton("Sınav Seçimi");
		button.setBounds(50, 150, 200, 50);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				panel=new JPanel();
				frame = new JFrame();
				frame.setSize(500, 500);
				frame.setLocation(400, 150);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(panel);
				panel.setLayout(null);
				int y=50;
				String[] row_heading = {"SINAV ADI", "1. ARALIK","2. ARALIK","SORU SAYISI"};
				DefaultTableModel model = new DefaultTableModel(row_heading,1);
				JTable jtable=new JTable(model);
				jtable.setSelectionBackground(Color.LIGHT_GRAY);
				jtable.setGridColor(Color.white);
				jtable.setBackground(Color.ORANGE);
				for (Entry<String, Game> exam : gameModes.returnGameModes().entrySet()) {
					ArrayList<String> arr=new ArrayList<>();
					arr.add(exam.getKey());
					arr.add("1---"+exam.getValue().getLimit1());
					arr.add("1---"+exam.getValue().getLimit2());
					arr.add("    "+exam.getValue().getQuestionCount());
					model.addRow(arr.toArray());
					if(y<300)y=y+15;
				}
				JScrollPane s= new JScrollPane(jtable);
				s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				s.setBounds(50, 50, 400, y);
				panel.add(s);
				label = new JLabel();
				label.setText("Çözmek istediğiniz sınavın adı: ");
				label.setBounds(60, y+70, 200, 30);
				panel.add(label);
				userText = new JTextField(30);
				userText.setBounds(250, y+70, 150, 30);
				panel.add(userText);
				
				
				button2 = new JButton("Onayla");
				button2.setBounds(285, y+110, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(gameModes.returnGameModes().get(userText.getText())!=null) {
							new StartExam(users,gameModes,gameModes.returnGameModes().get(userText.getText()),child);
							frame.dispose();
						}
						else 
							JOptionPane.showMessageDialog(frame,"Geçersiz giriş!!");

                    }
				});
				panel.add(button2);
				frame.setVisible(true);

            }
		});
		panel.add(button);
		button2 = new JButton("Yuksek skor tabloları");
		button2.setBounds(300, 150, 200, 50);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				panel=new JPanel();
				frame = new JFrame();
				frame.setSize(500, 500);
				frame.setLocation(400, 150);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				panel.setBorder(LineBorder.createBlackLineBorder());
				panel.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));
				label = new JLabel();
				label.setText(" SINAVLAR ");
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				label.setBorder(BorderFactory.createEmptyBorder(20,20,30,10));
				panel.add(label);
				for (Entry<String,Game> m : gameModes.returnGameModes().entrySet())  {
					button = new JButton(m.getKey());
					button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
					button.setAlignmentX(Component.CENTER_ALIGNMENT);
					button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(m.getValue().getScores().size()!=0) {
								frame.dispose();
								listScoreTable(users,child,m.getValue(),gameModes);
							}
							else
								JOptionPane.showMessageDialog(frame,"Seçtiğiniz puan tablosu boş");
						}
					});

					panel.add(button);
					label = new JLabel();
					label.setBorder(BorderFactory.createEmptyBorder(0,0,20,10));
					panel.add(label);
				}
				button = new JButton("Geri");
				button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
				button.setAlignmentX(Component.CENTER_ALIGNMENT);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						new oopproje.ChildFrame(users,child,gameModes);
					}
				});
				JScrollPane s= new JScrollPane(panel);
				s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panel.add(button);
				frame.add(s);
				frame.setVisible(true);
            }
		});
		panel.add(button2);
		button2 = new JButton("Çıkış");
		button2.setBounds(170, 250, 200, 50);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users.saveUsers();
				gameModes.saveGameModes();
				Excel excel= new Excel();
				try {
					excel.writeExcel(users);
				} catch (ClassNotFoundException ex) {
					throw new RuntimeException(ex);
				}
				frame.dispose();

            }
		});
		panel.add(button2);
		frame.setVisible(true);
	}
	public void listScoreTable(Users users,Child child,Game exam,GameModes gameModes) {
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
			jtable.setGridColor(Color.white);
			jtable.setBackground(Color.PINK);
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
		button = new JButton("bitir");
		button.setBounds(200, y+50, 80, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ChildFrame cf= new ChildFrame(users,child,gameModes);
            }
		});
		panel.add(button);
		frame.setVisible(true);
	}
}
