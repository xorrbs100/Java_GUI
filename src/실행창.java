import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.IIOException;
import javax.swing.*;

public class 실행창 extends JFrame implements Runnable{
	Container c = getContentPane();
	boolean flag =true;
	RainThread rt; JPanel jp;
	JLabel jl;
	public 실행창(){
		setTitle("실행창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setBackground(Color.BLACK);
		createToolBar();
		jp = new JPanel();
		c.add(jp,BorderLayout.CENTER);
		jp.setLayout(null);
		jp.setBackground(Color.BLACK);
		jp.setSize(500,300);
		setSize(500,400);
		setResizable(false);
		setLocation(450,150);
		setVisible(true);
	}
	@Override
	public void run() {
		int n=0;
		
		while(n<50) {
		if(flag==true) {	
		rt= new RainThread(jp);
		try{
			Thread.sleep(300);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		n++;
		}
		else if(flag==false)
		{
			jp.removeAll();
			JLabel la = new JLabel("STOP");
			la.setForeground(Color.WHITE);
			la.setLocation(jp.getWidth()/2-10,jp.getHeight()/2-50);
			la.setSize(100,100);
			la.setBackground(Color.black);
			la.setOpaque(true);
			jp.add(la);
			jp.repaint();
			return;
		}
	}
}
	private void createToolBar() {
		//툴바
		JToolBar jt = new JToolBar("실행툴바");
		jt.setBackground(Color.gray);
		//버튼
		JButton btn1 = new JButton("NEW");
		JButton btn2 = new JButton(new ImageIcon(".\\img\\시작.jpg"));			//이미지버튼
		JButton btn3 = new JButton(new ImageIcon(".\\img\\정지.jpg"));	
		//레이블
		JLabel la = new JLabel("실행할 프로그램을 선택해주세요>>");
		//콤보박스
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("꽃");
		comboBox.addItem("사격게임");
		comboBox.addItem("두더지잡기");
		comboBox.addItem("타이머");
		//콤보박스에 리스너부착
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jb = (JComboBox)e.getSource();
				if(jb.getSelectedIndex()==0) {
				}else if (jb.getSelectedIndex()==0) {
					new 꽃();
				}else if (jb.getSelectedIndex()==1) {
					new 사격게임();
				}else if (jb.getSelectedIndex()==2) {
					new 두더지잡기();
				}else if (jb.getSelectedIndex()==3) {
					new 타이머();
				}
			}
		});
		//NEW버튼 리스너부착
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main(null);
			}
		});
		//시작버튼에 리스너부착
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				flag = true;
				main(null);
			}
		});
		//정지버튼에 리스너부착
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = false;
			}
		});
		jt.add(btn1);
		jt.addSeparator();			//간격추가
		jt.add(btn2);
		jt.addSeparator();
		jt.add(btn3);
		jt.add(la);
		jt.add(comboBox);
		//툴바부착
		add(jt,BorderLayout.NORTH);
	}
	private class RainThread extends Thread{
		JLabel la;
		JPanel jp;
		int y=0;
		RainThread(JPanel jp){
			this.jp=jp;
			start();
		}
		public void run() {
			if(flag==true) {
			la=new JLabel("*");
			la.setForeground(Color.WHITE);
			la.setSize(10,10);
			la.setBackground(Color.BLACK);
			la.setOpaque(true);
			int x=(int)(Math.random()*jp.getWidth()-20);	
			la.setLocation(x,y);
			while(true){
			int y1 = la.getY()+5;
			try {
				sleep(20);
				la.setLocation(x,y1);
				if(la.getY()>jp.getHeight()) {
				la.setLocation(la.getX(), y);
				}
			}catch(InterruptedException e) {
				return;
			}
			jp.add(la);
			jp.add(la);
			jp.repaint();
			if(flag==false) {
				return;
			}
			}
		}
			
		}
	}
	public static void main(String [] args) {
		실행창 w = new 실행창();
		Thread th = new Thread(w);
		th.start();
	}

}