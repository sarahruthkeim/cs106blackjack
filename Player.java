import java.util.ArrayList;

public class Player implements Moves{
   double pot;
   int roundBet;
   ArrayList<Card> hand = new ArrayList<Card>();
   String name;

   public Player(){}

   /**
    * Updates player pot to take away the initial value being bet
    * @param i the amount being bet
    */
   public void bet(double i){
       if (i > pot){
           throw new IllegalArgumentException("Not enough money to bet that amount");
       }
       else{
           pot-=i;
       }
       //move to the next person in linked list and loop through until back at first player
   }

   /**
    * Getter method to get the player's list of cards
    * @return array list of players cards
    */
   public ArrayList<Card> getHand() {
       return this.hand;
   }

   /**
    * Adds a card to the players hand and updates the array list
    * @param cardQueue The deck of cards that the new card is being added from, which is implemented as a queue.
    */
   public void hit(DeckOfCards cardQueue){
       if (cardQueue.isEmpty()) {
           cardQueue.shuffle();
       }
       hand.add(cardQueue.getDeck().dequeue());
       //move to next person in linked list
   }

   /**
    * Calculates the sum of cards of all the cards in the players hand
    * @return integer value of the sum of cards
    */
   public int sumCards(){
       int sum=0;
       for (Card item:hand){
           sum+= item.getValue();
       }
       return sum;
   }

   /**
    * Checks if the players hand has a sum of cards over, at, or under 21
    * @return True if the sum of cards equals or is over 21 and false if the sum of cards is under 21
    */
   public boolean checkStatus(){
       if (sumCards()>=21){
           return true;
       }
       else{return false;}
   }

   /**
    * Gives the player the first two initial cards in they receive in their hand
    * @param cardQueue The deck of cards that the cards are being added from, which is implemented as a queue.
    */
   public void deal(DeckOfCards cardQueue){
       if (cardQueue.isEmpty()) {
           cardQueue.shuffle();
       }
       hand.add(cardQueue.getDeck().dequeue());
       hand.add(cardQueue.getDeck().dequeue());
   }

   /**
    * compareTo method to compare sum of cards between players
    * @param p the player the current player is comparing hands with
    * @return 1, 0, or -1 depending on if the players hand that is being compared is greater or less
    */
   public int compareTo(Player p) {
       int s1 = p.sumCards();
       int s2 = sumCards();
       return Integer.compare(s2, s1);
   }

   /**
    * Counts the number of Aces in a players hand
    * @return the number of Aces in the players hand
    */
   public int numAcesInHand() {
       int numAces = 0;
       for (Card card : hand) {
           if (card.getCardType() == 1) {
               numAces++;
           }
       }
       return numAces;
   }

   /**
    * Checks if a certain card is in the players hand
    * @param cardIndex The value of the card being searched for (i.e. 2, 6, Jack...)
    * @param suit The suit of the card being searched for (Spade, Club, Heart, Diamond)
    * @return the index where that specific card is in the players hand if it is in the hand
    */
   public Card getCardFromHand(int cardIndex, Card.Suit suit) {
       int index = -1;
       for (Card card : hand) {
           index++;
           if (card.getCardType() == cardIndex && card.getSuit() == suit) {
               return hand.get(index);
           }
       }
       return null;
   }

   /**
    * Sets the round bet for the player
    * @param betValue the amount being bet for that round
    * @return the amount being bet for that round
    */
   public int currentBet(int betValue){
       roundBet=betValue;
       return betValue;
   }

   /**
    * Removes cards from a players hand until there are no cards in their hand
    */
   public void clear() {
       while (!hand.isEmpty()) {
           hand.remove(hand.size() - 1);
       }
   }

   public String toString(){
       return name;
   }


}




