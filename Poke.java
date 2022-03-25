import java.awt.Image;

import java.awt.Toolkit;
import java.util.Random;

public class Poke {
    static final int count = 52;
    static String color[] = { "Hearts", "Clubs", "Diamonds", "Spades" };
    static String value[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    private Image cardImage[] = new Image[count];
    static Card card[] = new Card[count];

    public void getCardImage() {
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < value.length; j++) {
                cardImage[i * value.length + j] = Toolkit.getDefaultToolkit().getImage(
                        getClass().getResource("poke_images/" + (i + 1) + "-" + (j + 1) + ".jpg"));
            }
        }
    }
    public Poke() {
        this.getCardImage();
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < value.length; j++) {
                card[i * 13 + j] = new Card(color[i], value[j], cardImage[i * 13 + j]);
            }
        }
    }
    public void Shuffle() {
        Random random = new Random();
        for (int i = 0; i < card.length; i++) {
            int p = random.nextInt(card.length);
            Card temp = card[i];
            card[i] = card[p];
            card[p] = temp;
        }
    }

    public Card[] getCard() {
        return Poke.card;
    }

    public Card getOneCard(int n) {
        return Poke.card[n - 1];
    }

    public void Show() {
        for (int i = 0; i < count; i++) {
            card[i].print();
        }
        System.out.println();
    }
}
