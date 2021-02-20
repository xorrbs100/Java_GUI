import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class Timer2 implements Runnable{
	JLabel timerLabel;
	public Timer2(JLabel timerLa) {
		this.timerLabel=timerLa;
	}
	public void run() {
		String second = JOptionPane.showInputDialog("입력해주세요","최소 10초 이상입니다");
		int t=Integer.parseInt(second);
		for(int i =t;i>=0;i--) {
			timerLabel.setText(Integer.toString(i));
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e ) {
				return;
			}
		}
		timerLabel.setFont(new Font("d",Font.ITALIC,80));
		timerLabel.setText("끝!");
		Thread.interrupted();
		//3초후 다시 실행
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			return;
		}
		new 타이머();
	}
}
public class 타이머 extends JFrame{

	public 타이머() {
		setTitle("스레드학습");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.setBackground(Color.BLACK);
		JLabel timerLa= new JLabel();
		timerLa.setFont(new Font("고딕",Font.ITALIC,80));
		timerLa.setForeground(Color.WHITE);
		//스레드구현
		Timer2 time = new Timer2(timerLa);
		Thread th1=new Thread(time);
		c.add(timerLa);
		setSize(300,150);
		setVisible(true);
		//스레드처리JVM
		th1.start();
	}
	public static void main(String[] args) {
		new 타이머();
	}

}
