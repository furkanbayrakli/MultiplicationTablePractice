package oopproje;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Login {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JFrame frame;
	private JButton button;
	private JLabel label;

	public Login(Users users,GameModes gameModes) {

		panel = new JPanel();
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLocation(400, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		frame.add(panel);

		label =new JLabel();
		label.setText("ANA MENÜ");
		label.setBounds(220, 50, 200, 30);
		panel.add(label);
		button = new JButton("Admin Girişi");
		button.setBounds(50, 150, 150, 50);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new oopproje.AdminLogin(users,gameModes);
				frame.dispose();
            }
		});
		panel.add(button);

		button = new JButton("Çocuk Girişi");
		button.setBounds(300, 150, 150, 50);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new oopproje.ChildLogin(users,users.getChilderen(),gameModes);
				frame.dispose();
            }
		});
		panel.add(button);
		button = new JButton("Çıkış");
		button.setBounds(175, 250, 150, 50);
		button.addActionListener(new ActionListener() {
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

		panel.add(button);
		frame.setVisible(true);
	}
	
}
