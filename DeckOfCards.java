import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
    // public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES};

    private LinkedQueue<Card> deck;
    private ArrayList<Card> cardIndex = new ArrayList<Card>(); // used to store the cards, never accessed during game

    public DeckOfCards () {
        this.deck = new LinkedQueue();
        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 5; j ++) {
                Card.Suit localSuit;
                if (j == 1) {
                    localSuit = Card.Suit.HEARTS;
                } else if (j == 2) {
                    localSuit = Card.Suit.DIAMONDS;
                } else if (j == 3) {
                    localSuit = Card.Suit.CLUBS;
                } else {
                    localSuit = Card.Suit.SPADES;
                }
                Card newCard = new Card(localSuit, i);
                cardIndex.add(newCard);
            }
        }
    }

    public void Shuffle() {
        ArrayList<Card> cardArrayList = new ArrayList<Card>(cardIndex);
        while (cardArrayList.size() > 0) {
            Random rand = new Random(); // https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
            int randomInt = rand.nextInt(cardArrayList.size());

            deck.enqueue(cardArrayList.get(randomInt));
            cardArrayList.remove(randomInt);
        }
    }

    public ArrayList<Card> getCardIndex() {return this.cardIndex;}

    public String toString() {
        return deck.toString();
    }
 }
