import java.util.Random;
import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PokePanel extends JPanel {
    Poke poke = new Poke();
    int MyScore = 0;  //玩家分數初始化
    int ComputerScore = 0; //電腦分數初始化
    ArrayList myCardList = new ArrayList<>(); //玩家的撲克牌存放在陣列裡面
    ArrayList computerCardList = new ArrayList<>(); //電腦的撲克牌存放在陣列裡面
    boolean mylose = false;
    boolean computerlose = false;
    static int n = 1;
    //用boolean判斷遊戲是否要結束
    public boolean ComputerContinue = true;

    public PokePanel() {
        poke.Shuffle();
        poke.Show();
        this.setVisible(true);
        repaint();
    }
    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("電腦", 540, 45);
        for (int i = 0; i < computerCardList.size(); i++) {
            Card card = (Card) computerCardList.get(i);
            card.setPos((i+1) * 35+150, 100);
            card.print();
            if (computerlose == false || mylose == false) {
                card.drawBackgroundCard(g, this);
            }
            if(computerlose == true || mylose == true){
                card.drawPoke(g, this);
            }
        }
        g.drawString("玩家", 540, 530);
        for (int i = 0; i < myCardList.size(); i++) {
            Card card = (Card) myCardList.get(i);
            card.setPos((i+1) *35+150, 350);
            card.print();
            card.drawPoke(g, this);
        }
        System.out.println();
    }
    //當電腦出牌，玩家選擇出牌與否
    public void MyPlayer() {
        myCardList.add(poke.getOneCard(n));
        computeMyscore();
        repaint();
        IsOrNoShuffle();
        repaint();
        if (MyScore > 21) {
            mylose = true;
            JOptionPane.showMessageDialog(null, "電腦贏,玩家出局 \n電腦分數 : " 
                                                + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                                                JOptionPane.INFORMATION_MESSAGE);
            repaint();
        }
    }
    //計算玩家目前得分
    public void computeMyscore() {
        MyScore = 0;
        for (int i = 0; i < myCardList.size(); i++) {
            Card card = (Card) myCardList.get(i);
            MyScore += card.getCount();
        }
    }
    //當玩家出牌，電腦選擇出牌與否
    public void ComputerPlayer() {
        computerCardList.add(poke.getOneCard(n));
        repaint();
        IsOrNoShuffle();
        repaint();
        //0、1當作電腦做的選擇
        Random rand = new Random();
        int randomValue = rand.nextInt(3);
        System.out.println(randomValue);
        ComputeComputerScore();
        //如果分數大於18，電腦選擇停牌
        if (randomValue == 1){
            ComputerContinue = false;
            JOptionPane.showMessageDialog(null, "電腦選擇停牌不叫", "提示", JOptionPane.INFORMATION_MESSAGE);
            ComputeAllScore();
        }
        //計算電腦目前點數
        else if (ComputerScore > 21) {
            computerlose = true; 
            ComputerContinue = false;
            JOptionPane.showMessageDialog(null, "電腦出局,玩家贏 \n電腦分數 : " 
                                            + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                                            JOptionPane.INFORMATION_MESSAGE);
        }
        repaint();
    }
    //計算電腦目前得分
    public void ComputeComputerScore() {
        ComputerScore = 0;
        for (int i = 0; i < computerCardList.size(); i++) {
            Card card = (Card) computerCardList.get(i);
            ComputerScore += card.getCount();
        }
    }
    //計算目前得分，用分數判斷是否這局結束
    public void ComputeAllScore() {
        this.computeMyscore();
        this.ComputeComputerScore();
        if (MyScore > ComputerScore && MyScore <= 21) {
            computerlose = true;
            JOptionPane.showMessageDialog(null, "電腦出局,玩家贏 \n電腦分數 : " 
                                               + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                                            JOptionPane.INFORMATION_MESSAGE);
        } else if (MyScore < ComputerScore && ComputerScore <= 21) {
            JOptionPane.showMessageDialog(null, "電腦贏,玩家出局 \n電腦分數 : " 
                                               + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                                            JOptionPane.INFORMATION_MESSAGE);
            mylose = true;
        } else if(MyScore == ComputerScore) {
            JOptionPane.showMessageDialog(null, "雙方平手! \n電腦分數 : " 
                                               + ComputerScore + "\n玩家分數 :" + MyScore, "平手",
                                            JOptionPane.INFORMATION_MESSAGE);
            mylose = true;
        }
        else if(MyScore > 21){
            JOptionPane.showMessageDialog(null, "電腦贏,玩家出局 \n電腦分數 : " 
            + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                                            JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "電腦出局,玩家贏 \n電腦分數 : " 
            + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                                            JOptionPane.INFORMATION_MESSAGE);
        }
        repaint();
    }
    public void GameRestart() {
        /*
        玩家、電腦分數歸0,
        重新洗牌
        */ 
        MyScore = 0;
        ComputerScore = 0;
        mylose = false;
        computerlose = false;
        ComputerContinue = true;
        myCardList.clear();
        computerCardList.clear();
        MyPlayer();
        ComputerPlayer();
        repaint();
    }

    public void IsOrNoShuffle() {
        if (n >= 52) {
            n = 1;
            poke.Shuffle();
        } else {
            n++;
        }
    }
}
