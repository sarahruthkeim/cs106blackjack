public class Card {
   public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES};
   enum Face {ACE, JACK, QUEEN, KING};

   private Suit suit;
   private int cardType;
   private int value;
   private boolean faceUp;

/**
* Constructor method for card class that create a specific card
* @param inputSuit The suit of the card (club, spade, heart, diamond)
* @param inputCardType The value of the card (1 through 13) which is associated with the name value of the card (i.e. Jack, Queen, Kind, Ace)
*/
   public Card (Suit inputSuit, int inputCardType) {
       this.suit = inputSuit;
       this.cardType = inputCardType;
       this.faceUp = false;

       if (cardType == 1) {
           this.value = 11; // ace can be worth 1 or 11, will need to implement a call to the user to choose
           // what its worth
       }  else if (cardType > 10){
           this.value = 10;
       } else {
           this.value = inputCardType;
       }

   }

/**
* Getter method for suit of a card
* @return the suit of the card
*/
   public Suit getSuit() {return this.suit;}

/**
* Getter method for the value of the card
* @return the value of the card
*/
   public int getValue() {
       return this.value;
   }

/**
* Getter method for the card type based on the card value
* @return the type of the card
*/
   public int getCardType() {
       return this.cardType;
   }

/**
* Makes the card faceUp attribute become true
*/
   public void revealCard() {
       this.faceUp = true;
   }

/**
* Updates the value of an ace card from 1 to 11 depending on total hand value
*/
   public void updateAceValue() {
       if (cardType == 1) {
           value += 10;
           value = value % 20;
       }
   }


/**
* ToString method that converts card type integers to actual name value (i.e. Jack, Queen, King, Ace)
* @return Name value of the card based on its card type integer
*/
   public String toString() {
       String printedCard;
       if (cardType == 1) {
           printedCard = "ACE";
       } else if (cardType == 11) {
           printedCard = "JACK";
       } else if (cardType == 12) {
           printedCard = "QUEEN";
       } else if (cardType == 13) {
           printedCard = "KING";
       } else {
           printedCard = String.valueOf(cardType);
       }
       return "[" + printedCard + ", " + suit + "]";
   }



}


