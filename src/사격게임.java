import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class 사격게임 extends JFrame{

	사격게임(){
		setTitle("사격게임");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//판넬생성
		GamePanel Gp = new GamePanel();
		setContentPane(Gp);
		//배경붙이기
		ImageIcon im = new ImageIcon(".\\img\\123.png");
		JLabel la = new JLabel(im);
		la.setSize(1000,500);
		la.setLocation(0,0);
		Gp.add(la);
		setSize(1000,500);
		setResizable(false);  //창크기고정
		setVisible(true);
		
		//게임스타트 메소드 호출
		Gp.startGame();
	}
	public static void main(String[] args) {
		new 사격게임();
	}
}
class GamePanel extends JPanel{
	JLabel baseLabel;
	JLabel targetLabel;
	JLabel bulletLabel;
	TargetTh targetThread=null;
	//생성자
	public GamePanel() {
		setLayout(null);
		baseLabel = new JLabel();		//총대
		baseLabel.setSize(40,40);
		baseLabel.setOpaque(true);
		baseLabel.setBackground(Color.YELLOW);
		//목표물레이블
		ImageIcon img = new ImageIcon(".\\img\\축구공.png");
		targetLabel = new JLabel(img);
		targetLabel.setSize(img.getIconWidth(),img.getIconHeight());
		//총알레이블
		bulletLabel = new JLabel();
		bulletLabel.setSize(10,10);
		bulletLabel.setOpaque(true);
		bulletLabel.setBackground(Color.RED);
		BulletTh bt = new BulletTh(bulletLabel, targetLabel, targetThread);
		add(baseLabel);
		add(targetLabel);
		add(bulletLabel);
	}
	//메소드 - 위치값을 설정하고 리스너를 부착해주는 메소드
	void startGame() {
		//총알,목표물등 초기 위치값 설정
		//포위치
		baseLabel.setLocation(GamePanel.this.getWidth()/2-20,GamePanel.this.getHeight()/2+200);
		//총알위치
		bulletLabel.setLocation(GamePanel.this.getWidth()/2-5,GamePanel.this.getHeight()/2+190);
		//목표물위치
		targetLabel.setLocation(0,5);
		//목표물스레드생성
		targetThread=new TargetTh(targetLabel);
		targetThread.start();
		//리스너
		
		baseLabel.addMouseListener(new MouseAdapter() {
			BulletTh bulletThread = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				//총알스레드
				if(bulletThread==null||!bulletThread.isAlive()) {    //총알스레드가 없거나 죽었다면
					//총알스레드를 생성해라
					bulletThread=new BulletTh(bulletLabel,targetLabel,targetThread);
					bulletThread.start();
				}
			}
		});
		baseLabel.requestFocus();
	}
	//목표물 스레드
	class TargetTh extends Thread{
		JLabel target;
		
		public TargetTh(JLabel targetLabel) {		//JComponent해도됨
			this.target= targetLabel;
			targetLabel.setLocation(-10,0);//시작위치
			targetLabel.repaint();
		}
		//스레드코드
		public void run() {
			while(true) {
				int x = target.getX()+5;
				int y = target.getY();
				if(x>GamePanel.this.getWidth())
					target.setLocation(-20,0);
				else
					target.setLocation(x, y);
				target.repaint();
				try {
					sleep(10);
				}catch(InterruptedException e) {		//맞았을때
					target.setLocation(-20, 0);
					target.repaint();
					try {
						sleep(10);
					}catch(InterruptedException e1) {
						return;
					}
				}
		
			}
		}
	}
	//총알 스레드
	class BulletTh extends Thread{
		JComponent bullet, target;
		Thread targetThread;
		public BulletTh() {};
		public BulletTh(JComponent bullet, JComponent target, Thread targetThread) {
			this.bullet=bullet;this.target=target;this.targetThread=targetThread;
		}
		//스레드코드 hit메소드사용
		public void run() {
			
			while(true) {
				if(hit()==true) { //맞았다면
					targetThread.interrupted();
					bullet.setLocation(GamePanel.this.getWidth()/2-5,GamePanel.this.getHeight()/2+190);
					target.setLocation(-10,0);
				}else {	//안맞았다면
					int x= bullet.getX();
					int y=bullet.getY()-5; //총알이 5씩 위로올라가는 좌표값 설정
					
					if(y<0) {
						bullet.setLocation(GamePanel.this.getWidth()/2-5,GamePanel.this.getHeight()/2+190);
						bullet.getParent().repaint();
						return;
					}
					
					bullet.setLocation(x, y);
					bullet.getParent().repaint();
				}
				try {
					sleep(20);
				}catch(InterruptedException e) {
					return;
				}
			}
			
		}
		//총알이 맞았을때
		boolean hit() {
			//타겟컨디션메소드활용
			if(targetCondition(bullet.getX(),bullet.getY())||
				targetCondition(bullet.getX()+bullet.getWidth()-1,bullet.getY())||
				targetCondition(bullet.getX()+bullet.getWidth()-1, bullet.getY()+bullet.getHeight()-1)||
				targetCondition(bullet.getX(), bullet.getY()+bullet.getHeight()-1)) {
				return true;
				
			}else return false;
			
		}
		//총알이 맞았는지 안맞았는지 true,false반환메소드
		boolean targetCondition(int x, int y) {
			if(((target.getX()<=x)&&(target.getX()+target.getWidth()-20>=x))&&
				((target.getY()<=y)&&(target.getY()+target.getHeight()-20>=y))) {
				return true;
			}
			else return false;
		}
	}
}










