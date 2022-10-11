import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
   // public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES};

   private LinkedQueue<Card> deck;
   private ArrayList<Card> cardIndex = new ArrayList<Card>(); // used to store the cards, never accessed during game

/**
* Constructor method for DeckOfCards class that categorizes cards into suits
*/
   public DeckOfCards () { // constructor
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

/**
* Getter method to retrieve the deck
* @return the deck of cards
*/
   public LinkedQueue<Card> getDeck() {
       return this.deck;
   }

/**
* Checks if the deck is empty
* @return true if the deck is empty and false otherwise
*/
   public boolean isEmpty() {
       return deck.isEmpty();
   }

/**
* Shuffles the deck to make change the order of the queue for deck of cards to make dealing cards random
*/
   public void shuffle() {
       ArrayList<Card> cardArrayList = new ArrayList<Card>(cardIndex);
       while (cardArrayList.size() > 0) {
           Random rand = new Random(); // https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
           int randomInt = rand.nextInt(cardArrayList.size());

           deck.enqueue(cardArrayList.get(randomInt));
           cardArrayList.remove(randomInt);
       }
   }

/**
* Getter method to return a cards index within the array list of cards
* @return the card index
*/
   public ArrayList<Card> getCardIndex() {return this.cardIndex;}

/**
* To string method for deck of cards
* @return String value of deck of cards
*/
   public String toString() {
       return deck.toString();
   }
}


