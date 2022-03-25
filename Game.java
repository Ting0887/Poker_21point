import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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

        paneone.setLayout(new FlowLayout());
        paneone.add(button1);
        paneone.add(button2);
        paneone.add(button3);
        paneone.add(button4);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(pokePanel, BorderLayout.CENTER);
        container.add(paneone, BorderLayout.SOUTH);
        this.setTitle("撲克牌 21點");
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(100, 100, 500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("你選擇了玩家要牌模式");
                pokePanel.MyPlayer();
                if (pokePanel.ComputerContinue && pokePanel.mylose == false) {
                    //電腦做是否選擇要出牌
                    pokePanel.ComputerPlayer();
                }
            }
        });
        button2.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("你選擇了玩家停牌模式");
                pokePanel.ComputerPlayerMustDraw();
                if (pokePanel.mylose == false && pokePanel.computerlose == false) {
                    pokePanel.ComputeAllScore();
                }
                
            }
        });
        button3.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("你選擇了重新開始遊戲");
                pokePanel.GameRestart();
            }
        });
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
