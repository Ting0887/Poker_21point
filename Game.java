import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class Game extends JFrame {
    PokePanel pokePanel = new PokePanel();
    JButton button1 = new JButton("玩家要牌");
    JButton button2 = new JButton("玩家停牌");
    JButton button3 = new JButton("重新開局");
    JButton button4 = new JButton("退出遊戲");

    public Game() {
        JPanel paneone = new JPanel();
        //建立4個按鈕
        button1.setPreferredSize(new Dimension(140,40));
        button2.setPreferredSize(new Dimension(140,40));
        button3.setPreferredSize(new Dimension(140,40));
        button4.setPreferredSize(new Dimension(140,40));

        button1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        button2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        button3.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        button4.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        paneone.setLayout(new FlowLayout());
        paneone.add(button1); //玩家要牌
        paneone.add(button2); //玩家停牌
        paneone.add(button3); //重新開局
        paneone.add(button4); //退出遊戲
        paneone.setBounds(100, 100, 200, 200);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(pokePanel, BorderLayout.CENTER);
        container.add(paneone, BorderLayout.SOUTH);
        setTitle("撲克牌 21點"); 
        setVisible(true);  
        setResizable(false); //固定視窗大小
        setBounds(100, 100, 1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("你選擇了玩家要牌模式");
                pokePanel.MyPlayer();
                //如果玩家目前未輸
                if (pokePanel.mylose == false) {
                    //電腦做是否選擇要出牌
                    pokePanel.ComputerPlayer();
                }
            }
        });
        button2.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("你選擇了玩家停牌模式");
                if(pokePanel.mylose == false || pokePanel.computerlose == false){
                    pokePanel.ComputeAllScore();
                } 
            }
        });
        //重新開始
        button3.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("你選擇了重新開始遊戲");
                pokePanel.GameRestart();
            }
        });
        //退出遊戲
        button4.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(1);
            }
        });
    }
    public static void main(String[] args) {
        new Game();
    }
}
