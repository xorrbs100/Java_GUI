import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/*
		화면에 "꽃" 글자가 임의의 좌표에 계속 1초마다 찍히는 화면구현 마우스로 화면클릭하면 종료
 */

//스레드
class Th extends Thread{
	Container c ;
	int flag =0;
	
	public Th(Container c){
		this.c=c;
		start();
	}
	public void run() {
		while(true) {
			if(flag==1) {
			c.removeAll();	
			JLabel la = new JLabel("종료되었습니다");
			la.setForeground(Color.CYAN);
			la.setLocation(c.getWidth()/2,c.getHeight()/2);
			la.setSize(100,100);
			la.setBackground(Color.BLACK);
			la.setOpaque(true);
			c.add(la);
			c.repaint();
			return;
			}
			JLabel la = new JLabel("꽃");
			la.setOpaque(true);
			la.setSize(80,30);
			la.setForeground(Color.YELLOW);
			la.setBackground(Color.BLACK);
		int x=(int)(Math.random()*c.getWidth());
		int y=(int)(Math.random()*c.getHeight());
		la.setLocation(x, y);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			return;
		}
		c.add(la);
		c.repaint();
		
		}
		
	}
	
}

public class 꽃  extends JFrame{
	int x,y=0;
	꽃(){
		setTitle("꽃");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(Color.BLACK);
		c.setLayout(null);
		Th th = new Th(c);
		c.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				th.flag=1;
			}
		});
/*		JLabel la = new JLabel("꽃");
		la.setOpaque(true);
		la.setSize(80,30);*/
		
		setSize(1300,700);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new 꽃();

	}

}
