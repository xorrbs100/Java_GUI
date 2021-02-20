import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class 두더지잡기 extends JFrame {

   public 두더지잡기() {
      setTitle("두더지잡기");
      //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      MainPanel mp=new MainPanel();
      setContentPane(mp);
      ImageIcon img = new ImageIcon(".\\img\\판.jpg");
      JLabel mainLa= new JLabel(img);
      mainLa.setOpaque(true);
      mainLa.setSize(img.getIconWidth(),img.getIconHeight());
      mainLa.setLocation(0,0);
      mp.add(mainLa);
      setSize(960,600);
      setResizable(false);
      setVisible(true);
      mp.startGame();
   }
   public static void main(String [] args) {
      new 두더지잡기();
   }
}
//게임클래스
class MainPanel extends JPanel{

   int count = 0;
   int x[] ={180,380,600};
   int y[] = {40,240,400};
   //두더지 레이블 인덱스번호
   int index=0;
   //두더지9개, 스코어창 생성
   JLabel [] targetLabel = new JLabel[9];
   ImageIcon img = new ImageIcon(".\\img\\두더지.png");
   JLabel scoreLabel = new JLabel(Integer.toString(count));
   public MainPanel() {
      this.setSize(960,600);
      setLayout(null);
      //두더지아이콘 부착, 레이블 판넬에 부착
      for(int i=0;i<targetLabel.length;i++) {
         targetLabel[i]=new JLabel(img);
         targetLabel[i].setSize(img.getIconWidth(),img.getIconHeight());
         add(targetLabel[i]);
         targetLabel[i].setVisible(false);
      }
      //두더지 시작위치 지정
      targetLabel[0].setLocation(180, 40);
      targetLabel[1].setLocation(180, 240);
      targetLabel[2].setLocation(180, 400);
      targetLabel[3].setLocation(380, 40);
      targetLabel[4].setLocation(380, 240);
      targetLabel[5].setLocation(380, 400);
      targetLabel[6].setLocation(600, 40);
      targetLabel[7].setLocation(600, 240);
      targetLabel[8].setLocation(600, 400);
      //스코어라벨 부착
      scoreLabel.setBackground(Color.black);
      scoreLabel.setOpaque(true);
      scoreLabel.setLocation(10,10);
      scoreLabel.setSize(150,70);
      scoreLabel.setForeground(Color.RED);
      scoreLabel.setFont(new Font("a",Font.ITALIC,50));
      add(scoreLabel,SwingConstants.CENTER);
   }
   //두더지 잡는 마우스 리스너부착
   void startGame() {
      while(index<9) {
         targetLabel[index].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(targetLabel[index].isVisible()) {
                  count+=10;
                  scoreLabel.setText(Integer.toString(count));
                  targetLabel[index].setVisible(false);
               }
            }
         });
         targetLabel[index].requestFocus();
         index++;
      }
      MoveTh mt = new MoveTh(targetLabel);
      mt.start();
      }
//스레드클래스
   class MoveTh extends Thread{
      JLabel[] targetLabel;
      //생성자
      public MoveTh(JLabel[] targetLabel){
         this.targetLabel=targetLabel; 
      }
      //두더지 생성메소드
      public void run() {
         while(true) {
            //인덱스번호 0부터 8까지
            index = (int)(Math.random()*9-1);
            targetLabel[index].setVisible(true);
            try {
               sleep(1000);
               targetLabel[index].setVisible(false);
            }catch(InterruptedException e) {
               return;
            }
            repaint();
         }
      }
   }
}


