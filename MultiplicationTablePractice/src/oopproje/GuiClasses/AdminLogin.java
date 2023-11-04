package oopproje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class AdminLogin extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JFrame frame;
	private JLabel label;
	private JTextField userText;
	private JLabel label2;
	private JButton button;
	private JPasswordField userPassword;
	
	//private  JPanel usergui;
	public AdminLogin(Users users,GameModes gameModes) {
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
		
		label2 = new JLabel();
		label2.setText("Şifre: ");
		label2.setBounds(100, 180, 80, 30);
		panel.add(label2);
		
		userPassword= new JPasswordField(30);
		userPassword.setBounds(170, 180, 150, 30);
		panel.add(userPassword);
		
		button = new JButton("Giris");
		button.setBounds(240, 230, 80, 25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(users.getAdmin()==null){
					JOptionPane.showMessageDialog(frame,"Sistemde bir admin kayıtlı değil kaydolun!!");
				}
				else if(users.getAdmin().getName().equals(userText.getText()) && users.getAdmin().getPassword().equals(userPassword.getText())) {
					frame.dispose();
					new oopproje.AdminFrame(users,users.getAdmin(),gameModes);
				}
				else {
					JOptionPane.showMessageDialog(frame,"Geçersiz kullanıcı adı veya şifresi!!");

				}

            }
		});
		panel.add(button);
		button = new JButton("Kaydol");
		button.setBounds(120, 230, 80, 25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userText.getText().isBlank() || userPassword.getText().isBlank()) {
					JOptionPane.showMessageDialog(frame,"Kullanıcı adı ve şifre boş bırakılamaz");
				}
				else if(users.getAdmin()==null){
					users.setAdmin(new Adult(userText.getText(),userPassword.getText()));
					users.saveUsers();
					frame.dispose();
					new oopproje.Login(users,gameModes);
				}
				else if(users.getAdmin().getName().compareToIgnoreCase(userText.getText())!=0) {
					int i=JOptionPane.showConfirmDialog(null,"Eski admine ayit bütün kayıtlı ayarlar silinsin mi?","Ayar kaydı tercihi (kayıtları sil->Yes -- silme->No)",JOptionPane.YES_NO_CANCEL_OPTION);
					if(i==0){
						int k=JOptionPane.showConfirmDialog(null,"Bütün sınav ayarlarını siliyorsunuz eminmisiniz","DİKKAT!!!",JOptionPane.OK_CANCEL_OPTION);
						if(k==0){
							users.getAdmin().setName(userText.getText());
							users.getAdmin().setPassword(userPassword.getText());
							gameModes.returnGameModes().clear();
							gameModes.saveGameModes();
							users.saveUsers();
							frame.dispose();
							new oopproje.Login(users,gameModes);
						}
						else if(k==2){
							frame.dispose();
							new oopproje.Login(users,gameModes);
						}
					}
					else if(i==1){
						users.getAdmin().setName(userText.getText());
						users.getAdmin().setPassword(userPassword.getText());
						users.saveUsers();
						frame.dispose();
						new oopproje.Login(users,gameModes);
					}
					else if(i==2){
						frame.dispose();
						new oopproje.Login(users,gameModes);
					}
				}
				else{
					JOptionPane.showMessageDialog(frame,"Bu isimli admin zaten kayıtlı şifrenizi girip giriş yapın.");
				}
			}
		});

		panel.add(button);
		
		frame.setVisible(true);
	}

	//@Override
	
}
