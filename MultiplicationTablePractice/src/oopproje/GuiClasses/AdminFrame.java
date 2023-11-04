package oopproje;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class AdminFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JFrame frame;
	private JLabel label;
	private JTextField userText;
	private JTextField userText2;
	private JTextField userText3;
	private JTextField userText4;
	private JButton button;
	private JButton button2;
	
	public AdminFrame(Users users,Adult admin,GameModes gameModes) {
		panel=new JPanel();
		frame = new JFrame();
		frame.setSize(560, 600);
		frame.setLocation(400, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		
		button = new JButton("Yeni sınav ayarı oluştur");
		button.setBounds(50, 100, 200, 50);
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
				
				label = new JLabel();
				label.setText("Birinci aralık sınırı: ");
				label.setBounds(60, 80, 200, 30);
				panel.add(label);
				userText = new JTextField(30);
				userText.setBounds(170, 80, 150, 30);
				panel.add(userText);
				
				label = new JLabel();
				label.setText("İkinci aralık sınırı: ");
				label.setBounds(60, 130, 200, 30);
				panel.add(label);
				
				userText2 = new JTextField(30);
				userText2.setBounds(170, 130, 150, 30);
				panel.add(userText2);
				
				label = new JLabel();
				label.setText("Soru adedi: ");
				label.setBounds(100, 180, 80, 30);
				panel.add(label);
				
				userText3 = new JTextField(30);
				userText3.setBounds(170, 180, 150, 30);
				panel.add(userText3);
				
				label = new JLabel();
				label.setText("Sınav adı: ");
				label.setBounds(100, 230, 80, 30);
				panel.add(label);
				
				userText4 = new JTextField(30);
				userText4.setBounds(170, 230, 150, 30);
				panel.add(userText4);
				
				button2 = new JButton("Ekle");
				button2.setBounds(200, 300, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(userText.getText().isBlank() ||userText3.getText().isBlank()||userText4.getText().isBlank()||userText2.getText().isBlank())
								JOptionPane.showMessageDialog(frame," Hiçbir alan boş bırakılamaz!!");
							else if(gameModes.returnGameModes().containsKey(userText4.getText()))
								JOptionPane.showMessageDialog(frame,"Sistemde "+userText4.getText()+" isimli bir oyun zaten kayıtlı!!");
							else if(Integer.parseInt(userText3.getText())<1)
								JOptionPane.showMessageDialog(frame,"Soru sayısı 0 dan büyük olmalı!!");
							else if(Integer.parseInt(userText.getText())>10 || Integer.parseInt(userText2.getText())>10)
								JOptionPane.showMessageDialog(frame,"Aralık sınırları 1 ile 10 arasında olmalı!!");
							else {
								Game aGame= new Game(Integer.parseInt(userText.getText()),Integer.parseInt(userText2.getText()),Integer.parseInt(userText3.getText()));
								gameModes.addMode(userText4.getText(),aGame);
							}
						}
						catch(NumberFormatException exception) {
							JOptionPane.showMessageDialog(frame,"Gecersiz giriş!!");
						}finally {
							gameModes.saveGameModes();
							frame.dispose();
							AdminFrame ad= new AdminFrame(users,users.getAdmin(),gameModes);
						}

                    }
				});
				panel.add(button2);
				frame.setVisible(true);

            }
		});
		panel.add(button);
		button = new JButton("Sınav ayarı değiştir.");
		button.setBounds(300, 100, 200, 50);
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

				label = new JLabel();
				label.setText("Değiştirmek istediğiniz Sınavın adı: ");
				label.setBounds(50, 80, 200, 30);
				panel.add(label);
				userText = new JTextField(30);
				userText.setBounds(250, 80, 150, 30);
				panel.add(userText);

				label = new JLabel();
				label.setText("Yeni birinci aralık sınırı: ");
				label.setBounds(110, 130, 200, 30);
				panel.add(label);

				userText2 = new JTextField(30);
				userText2.setBounds(250, 130, 150, 30);
				panel.add(userText2);

				label = new JLabel();
				label.setText("Yeni ikinci aralık sınırı: ");
				label.setBounds(110, 180, 200, 30);
				panel.add(label);

				userText3 = new JTextField(30);
				userText3.setBounds(250, 180, 150, 30);
				panel.add(userText3);

				label = new JLabel();
				label.setText("Yeni soru adedi: ");
				label.setBounds(150, 230, 100, 30);
				panel.add(label);

				userText4 = new JTextField(30);
				userText4.setBounds(250, 230, 150, 30);
				panel.add(userText4);

				button2 = new JButton("Değiştir");
				button2.setBounds(300, 300, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(userText.getText().isBlank() ||userText3.getText().isBlank()||userText4.getText().isBlank()||userText2.getText().isBlank())
								JOptionPane.showMessageDialog(frame," Hiçbir alan boş bırakılamaz!!");
							else if(!gameModes.returnGameModes().containsKey(userText.getText()))
								JOptionPane.showMessageDialog(frame,"Girilen isimde bir sınav bulunmamakta!!");
							else if(Integer.parseInt(userText4.getText())<1)
								JOptionPane.showMessageDialog(frame,"Soru sayısı 0 dan büyük olmalı!!");
							else if(Integer.parseInt(userText3.getText())>10 || Integer.parseInt(userText2.getText())>10)
								JOptionPane.showMessageDialog(frame,"Aralık sınırları 1 ile 10 arasında olmalı!!");
							else {
								Game aGame= new Game(Integer.parseInt(userText.getText()),Integer.parseInt(userText2.getText()),Integer.parseInt(userText3.getText()));
								gameModes.addMode(userText4.getText(),aGame);
								gameModes.saveGameModes();
								frame.dispose();
								AdminFrame ad= new AdminFrame(users,users.getAdmin(),gameModes);
							}
						}
						catch(NumberFormatException exception) {
							JOptionPane.showMessageDialog(frame,"Gecersiz giriş!!");
						}
					}
				});
				panel.add(button2);
				button2 = new JButton("İptal");
				button2.setBounds(150, 300, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
								frame.dispose();
								AdminFrame ad= new AdminFrame(users,users.getAdmin(),gameModes);
					}
				});
				panel.add(button2);
				frame.setVisible(true);
			}
		});
		panel.add(button);

		button2 = new JButton("Yuksek skor tabloları");
		button2.setBounds(50, 200, 200, 50);
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
								listScoreTable(users,m.getValue(),gameModes);
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
						new oopproje.AdminFrame(users,admin,gameModes);
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
		button2 = new JButton("Raporları görüntüle");
		button2.setBounds(300, 200, 200, 50);
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
				label.setText(" RAPORLAR ");
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				label.setBorder(BorderFactory.createEmptyBorder(20,20,30,10));
				panel.add(label);

				for(Child aChild : users.getChilderen()){
					button2 = new JButton(aChild.getName());
					button2.setLayout(new BoxLayout(button2, BoxLayout.Y_AXIS));
					button2.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
					button2.setAlignmentX(Component.CENTER_ALIGNMENT);
					button2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(aChild.getReports().size()>0) {
								frame.dispose();
								listReports(users,gameModes,aChild);
							}
							else
								JOptionPane.showMessageDialog(frame,"Seçtiğiniz çocuğun bir sınav kaydı bulunmamakta");
						}
					});
					panel.add(button2);
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
						new AdminFrame(users,admin,gameModes);
					}
				});
				panel.add(button);
				JScrollPane s= new JScrollPane(panel);
				s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				frame.add(s);
				frame.setVisible(true);
			}
		});
		panel.add(button2);

		button2 = new JButton("Şifre değiştir");
		button2.setBounds(300, 300, 200, 50);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				panel=new JPanel();
				frame = new JFrame();
				frame.setSize(500, 400);
				frame.setLocation(400, 150);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(panel);
				panel.setLayout(null);
				
				label = new JLabel();
				label.setText(" Yeni şifreniz:");
				label.setBounds(80, 120, 200, 30);
				panel.add(label);
				userText = new JTextField(30);
				userText.setBounds(190, 120, 150, 30);
				panel.add(userText);
				button2 = new JButton("Onayla");
				button2.setBounds(110, 170, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( !userText.getText().isBlank()) {
							admin.setPassword(userText.getText());
							users.saveUsers();
							frame.dispose();
							AdminFrame ad= new AdminFrame(users,users.getAdmin(),gameModes);
						}
						else {
							JOptionPane.showMessageDialog(frame,"Şifre boş bırakılamaz.");
						}

                    }
				});
				panel.add(button2);
				button2 = new JButton("İptal");
				button2.setBounds(260, 170, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						AdminFrame ad= new AdminFrame(users,users.getAdmin(),gameModes);
                    }
				});
				panel.add(button2);
				frame.setVisible(true);

            }
		});
		panel.add(button2);
		button2 = new JButton("İsim değiştir");
		button2.setBounds(50, 300, 200, 50);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				panel=new JPanel();
				frame = new JFrame();
				frame.setSize(500, 400);
				frame.setLocation(400, 150);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(panel);
				panel.setLayout(null);
				label = new JLabel();
				label.setText(" Yeni kullanıcı adınız:");
				label.setBounds(80, 120, 200, 30);
				panel.add(label);
				userText = new JTextField(30);
				userText.setBounds(230, 120, 150, 30);
				panel.add(userText);
				button2 = new JButton("Onayla");
				button2.setBounds(150, 170, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( !userText.getText().isBlank()) {
							admin.setName(userText.getText());
							users.saveUsers();
							frame.dispose();
							AdminFrame ad= new AdminFrame(users,users.getAdmin(),gameModes);
						}
						else {
							JOptionPane.showMessageDialog(frame,"İsim boş bırakılamaz.");
						}

                    }
				});
				panel.add(button2);
				button2 = new JButton("İptal");
				button2.setBounds(300, 170, 80, 30);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						AdminFrame ad= new AdminFrame(users,users.getAdmin(),gameModes);
                    }
				});
				panel.add(button2);
				frame.setVisible(true);

            }
		});
		
		panel.add(button2);
		button2 = new JButton("Çıkış");
		button2.setBounds(175, 400, 200, 50);
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

	public void listReports(Users users,GameModes gameModes,Child aChild){
		int x=20,y=60;
		String[] row_heading = {"SINAV", "BAŞLANGIÇ", "BİTİŞ","PUAN"};
		String[] row_heading2 = {"-----------------------------------","SORU", "DOĞRU/YANLIŞ", "AYRILAN SÜRE"};
		String[] empty_row = {"", "", "",""};
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setLocation(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultTableModel model = new DefaultTableModel(row_heading,1);
		JTable jtable=new JTable(model);
		jtable.setSelectionBackground(Color.LIGHT_GRAY);
		jtable.setGridColor(Color.white);
		jtable.setBackground(Color.PINK);

		for(Reports report: aChild.getReports()) {
			ArrayList<String> arr= new ArrayList<>();
			arr.add(report.getGameName());
			arr.add(report.getStartDate().getHours()+":"+report.getStartDate().getMinutes()+":"+report.getStartDate().getSeconds());
			arr.add(report.getEndDate().getHours()+":"+report.getEndDate().getMinutes()+":"+report.getEndDate().getSeconds());
			arr.add(report.getScore()+"");
			model.addRow(arr.toArray());
			model.addRow(row_heading2);
			if(y<400)y=y+20;
			int i=1;
			for(Question q : report.getQuestions()){
				ArrayList<String> arr2= new ArrayList<>();
				arr2.add(i+". Soru:");
				arr2.add(q.getQuestion()+q.getAnswer());
				if(q.getBool())
					arr2.add("doğru");
				else
					arr2.add("yanlış");
				arr2.add(q.getTime());
				model.addRow(arr2.toArray());
				i++;
				if(y<400)
					y=y+20;
			}
			model.addRow(empty_row);
		}
		JScrollPane s= new JScrollPane(jtable);
		s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		s.setBounds(50, 20, 500, y);

		button = new JButton("Geri");
		button.setBounds(240, y+50, 80, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminFrame(users,users.getAdmin(),gameModes);
			}
		});
		frame.add(button);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setBounds(50, 20, 500, y);
		panel.add(s);
		frame.add(panel);
		frame.setVisible(true);
	}
	public void listScoreTable(Users users,Game exam,GameModes gameModes) {
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
				arr.add("    "+i);
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

		button = new JButton("Geri");
		button.setBounds(200, y+70, 80, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminFrame(users,users.getAdmin(),gameModes);
			}
		});
		panel.add(button);
		frame.setVisible(true);
	}

}
