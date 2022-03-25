import java.util.Random;
import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PokePanel extends JPanel {
    Poke poke = new Poke();
    int MyScore = 0;
    int ComputerScore = 0;
    ArrayList myCardList = new ArrayList();
    ArrayList computerCardList = new ArrayList();
    boolean mylose = false;
    boolean computerlose = true;
    static int n = 1;
    public boolean ComputerContinue = true;

    public PokePanel() {
        poke.Shuffle();
        poke.Show();
        this.setVisible(true);
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("電腦牌", 200, 45);
        for (int i = 0; i < computerCardList.size(); i++) {
            Card card = (Card) computerCardList.get(i);
            card.setPos(i * 50, 100);
            card.print();
            card.drawBackgroundCard(g, this);
            if (computerlose == false || mylose == false) {
                card.drawPoke(g, this);
            }
        }
        g.drawString("玩家牌", 200, 470);
        for (int i = 0; i < myCardList.size(); i++) {
            Card card = (Card) myCardList.get(i);
            card.setPos(i * 50, 300);
            card.print();
            card.drawPoke(g, this);
        }
        System.out.println();
    }
    //當電腦出牌，玩家可選擇出牌與否
    public void MyPlayer() {
        myCardList.add(poke.getOneCard(n));
        computeMyscore();
        repaint();
        IsOrNoShuffle();
        if (MyScore > 21) {
            mylose = true;
            JOptionPane.showMessageDialog(null, "電腦贏玩家輸 \n電腦分數 : " + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            repaint();
        }
    }
    // 當電腦選擇不出，玩家必須出牌
    public void MyPlayerMustDraw(){
        myCardList.add(poke.getOneCard(n));
        ComputeComputerScore();
        IsOrNoShuffle();
        if (MyScore > 21) {
            mylose = true;
            JOptionPane.showMessageDialog(null, "電腦贏玩家輸 \n電腦分數 : " + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            repaint();
        }
    }

    public void computeMyscore() {
        MyScore = 0;
        for (int i = 0; i < myCardList.size(); i++) {
            Card card = (Card) myCardList.get(i);
            MyScore += card.getCount();
        }
    }

    //當玩家出牌，電腦可選擇出牌與否
    public void ComputerPlayer() {
        ComputeComputerScore();
        IsOrNoShuffle();
        repaint();
        Random rand = new Random();
        int randomValue = rand.nextInt() % 2;

        if (ComputerScore > 21) {
            computerlose = true;
            ComputerContinue = false;
            JOptionPane.showMessageDialog(null, "電腦輸玩家贏 \n電腦分數 : " + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            repaint();
        } else if (randomValue == 1){
            ComputerContinue = false;
            JOptionPane.showMessageDialog(null, "電腦選擇停牌不叫", "提示", JOptionPane.INFORMATION_MESSAGE);
            //電腦選擇不出牌，玩家必須出牌
            MyPlayerMustDraw();
            repaint();
        }
        computerCardList.add(poke.getOneCard(n));
        repaint();
    }
    //當玩家選擇不出，電腦必須出牌
    public void ComputerPlayerMustDraw(){
        computerCardList.add(poke.getOneCard(n));
        ComputeComputerScore();
        IsOrNoShuffle();
        if (ComputerScore > 21) {
            computerlose = true;
            ComputerContinue = false;
            JOptionPane.showMessageDialog(null, "電腦輸玩家贏 \n電腦分數 : " + ComputerScore + "\n玩家分數 : " + MyScore, "提示",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        repaint();
    }

    public void ComputeComputerScore() {
        ComputerScore = 0;
        for (int i = 0; i < computerCardList.size(); i++) {
            Card card = (Card) computerCardList.get(i);
            ComputerScore += card.getCount();
        }
    }

    public void ComputeAllScore() {
        this.computeMyscore();
        this.ComputeComputerScore();
        if (MyScore > ComputerScore) {
            computerlose = true;
            JOptionPane.showMessageDialog(null, "玩家贏, 電腦輸 \n電腦分數 : " + ComputerScore + "\n玩家分數 :" + MyScore, "贏了",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (MyScore < ComputerScore) {
            JOptionPane.showMessageDialog(null, "玩家輸, 電腦贏 \n電腦分數 : " + ComputerScore + "\n玩家分數 :" + MyScore, "輸了",
                    JOptionPane.INFORMATION_MESSAGE);
            mylose = true;
        } else {
            JOptionPane.showMessageDialog(null, "玩家輸, 電腦贏 \n電腦分數 : " + ComputerScore + "\n玩家分數 :" + MyScore, "平手",
                    JOptionPane.INFORMATION_MESSAGE);
            mylose = true;
        }
        repaint();
    }

    public void GameRestart() {
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
