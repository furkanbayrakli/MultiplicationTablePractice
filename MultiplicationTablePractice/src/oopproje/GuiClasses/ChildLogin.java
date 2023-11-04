package oopproje;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChildLogin {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JFrame frame;
	private JLabel label;
	private JTextField userText;
	private JButton button;

	public ChildLogin(Users users,ArrayList<Child> childeren,GameModes gameModes) {
		panel = new JPanel();
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLocation(400, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		
		label = new JLabel();
		label.setText("Kullanıcı adı: ");
		label.setBounds(90, 130, 80, 30);
		panel.add(label);
		
		userText = new JTextField(30);
		userText.setBounds(170, 130, 150, 30);
		panel.add(userText);
		
		
		button = new JButton("Giris");
		button.setBounds(150, 180, 80, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				while(i<childeren.size() && childeren.get(i).getName().compareToIgnoreCase(userText.getText())!=0  ) 
					i++;
				
				if(i<childeren.size() &&  childeren.get(i).getName().compareToIgnoreCase(userText.getText())==0) {
					new oopproje.ChildFrame(users,childeren.get(i),gameModes);
					frame.dispose();
				}
				else 
					JOptionPane.showMessageDialog(frame,"Girilen kullanıcı sistemde bulunmamakta.");

            }
		});
		panel.add(button);
		
		button = new JButton("Kaydol");
		button.setBounds(250, 180, 80, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				while(i<childeren.size() && childeren.get(i).getName().compareToIgnoreCase(userText.getText())!=0  ) 
					i++;
				
				if( !userText.getText().isBlank() && i==childeren.size()) {
					childeren.add(new Child(userText.getText()));
					users.saveUsers();
					frame.dispose();
					new oopproje.Login(users,gameModes);
				}
				else if(userText.getText().isBlank())
					JOptionPane.showMessageDialog(frame,"Lütfen kaydolmak için isim girin.");
				else
					JOptionPane.showMessageDialog(frame,"Girdiğiniz isim sistemde kayıtlı giriş yapabilirsiniz.");

            }
		});
		panel.add(button);
		frame.setVisible(true);
	}
}
