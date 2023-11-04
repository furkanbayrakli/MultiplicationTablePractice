package oopproje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    // Timerı seyyar bir kronometre gibi tasarladım ki kullanıcıya soruları her seferinde frame i güncelleyerek sunarken Kronometre sol üstte sabit kalsın
public class TimerGui extends JFrame {
    private JLabel lblClock = new JLabel();
    private JLabel label;
    private JPanel panel;
	private JFrame frame;
    public TimerGui() {
        panel=new JPanel();
		frame = new JFrame();
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		label = new JLabel();
		label.setText(" KRONOMETRE ");
		label.setSize(50, 50);
		label.setBounds(100, 20, 200, 30);
		panel.add(label);
		lblClock.setBounds(120, 50, 200, 100);
		lblClock.setSize(50, 50);
		panel.add(lblClock);
        createStopwatch();
        frame.setVisible(true);
    }
    
    public void frameClose(JFrame frame) {
    	frame.dispose();
    }
    public JFrame getFrame() {
    	return frame;
    }
    private void createStopwatch() {
        Timer timer = new Timer(1000, new ActionListener() {
            private int second = 0;
            private int minute = 0;
            private int hour = 0;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                String stopwatch = hour + " : " + minute + " : " + second;
                lblClock.setText(stopwatch);
    
                if(second>0 && second%59==0){
                   minute++;
                }
                if(minute>1 &&minute%59==0){
                    hour++;
                    minute=0;
                }
                if(hour>23){
                    hour=0;
                }
                if(second>59){
                    second=0;
                }
                second++;
            }
        });
        timer.start();
    }
}