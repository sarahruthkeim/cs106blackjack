public class Testing {
    public static void main (String[] args) {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.Shuffle();
        System.out.println(myDeck);
        System.out.println(myDeck.getCardIndex());
    }

}
