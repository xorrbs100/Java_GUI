import javax.swing.JOptionPane;

public class 경마 {
   public static void main(String[] args) {
      //5개말 경주
      Thread myThread = null;
      for (int i = 1; i <= 5; i++) {
         myThread = new Horse(i+"번", new Rank());
         myThread.start();
      }
   }
}
class Horse extends Thread {
   String hname = "";
   int meter = 100;

   //등수변수
   static int count = 1;
   Rank rank;
   public Horse() {}
   /// 말의 이름과 객체를 만들 Rank생성
   public Horse(String h_name, Rank rank) {
      this.hname = h_name;
      this.rank = rank;
   }
   @Override
   public void run() {
      try {
         while (true) {
            // 1~2000 사이의 수 랜덤 발생후 sleep 
            sleep((int) (Math.random() * 2000 + 1));
            System.out.println(hname + " 말의 남은 거리 : " + meter);
            //20씩 이동
            meter -= 20;
            // meter가 0이되면 결승점이기 때문에 rank.finishLine(말의 이름)실행 
            if (meter == 0) {
               this.rank.finishLine(hname);
               break;
            }
         }
      } catch (Exception e) {
         return;
      }
   }
}
class Rank {
   // 말의 등수
   int rank;

   public Rank() {
   }
   public void finishLine(String name) {
      rank = Horse.count++;
      System.out.println(name + " 말 " + rank + "등으로 결승점 도착");
      if (rank == 1) {   //1 등말 다이얼로그이용 띄우기
         JOptionPane.showMessageDialog(null, "1등말은 " + name + " 입니다 !!");
      }
   }
}