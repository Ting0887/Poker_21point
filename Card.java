import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class Card {
    private String color;
    private String value;
    private Image cardImage;
    private int count;
    private int x; 
    private int y;
    private int CardHeight = 100; //撲克牌長度
    private int CardWeight = 80; //撲克牌寬度
    private Image CardBackground = Toolkit.getDefaultToolkit().getImage("card_background.jpg"); //撲克牌背面圖案

    public Card(String color, String value, Image cardImage) {
        this.color = color;
        this.value = value;
        this.cardImage = cardImage;
        // J Q K 都當作10點 
        if (value == "J" || value == "Q" || value == "K") {
            this.count = 10;
        // A為1點
        } else if (value == "A") {
            this.count = 1;
        } else {
            this.count = Integer.parseInt(value);
        }
    }

    public Card(String value, Image cardImage) {
        this.color = "King";
        this.value = value;
        this.cardImage = cardImage;
        this.count = 0;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawPoke(Graphics g, JPanel i) {
        g.drawImage(cardImage, x, y, CardWeight, CardHeight, (ImageObserver) i);
    }

    public void drawBackgroundCard(Graphics g, JPanel i) {
        g.drawImage(CardBackground, x, y, CardWeight, CardHeight, (ImageObserver) i);
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }
    public void print() {
        System.out.print("Suit " + color);
        System.out.print("num " + value);
        System.out.print("count " + count);
    }
}
