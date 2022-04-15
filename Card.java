public class Card {
    public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES};
    enum Face {ACE, JACK, QUEEN, KING};

    private Suit suit;
    private int cardType;
    private int value;

    public Card (Suit inputSuit, int inputCardType) {
        this.suit = inputSuit;
        this.cardType = inputCardType;

        if (cardType == 1) {
            this.value = inputCardType; // ace can be worth 1 or 11, will need to implement a call to the user to choose
            // what its worth
        }  else if (cardType > 10){
            this.value = 10;
        }else {
            this.value = inputCardType;
        }

    }

    public int getValue() {
        return this.value;
    }

    public int getCardType() {
        return this.cardType;
    }

    public void updateAceValue() {
        if (cardType == 1) {
            value += 10;
            value = value % 20;
        }
    }

    public String toString() {
        return "[" + cardType + ", " + suit + "]";
    }
}


