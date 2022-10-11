import java.util.ArrayList;

public class Dealer extends Player {
   ArrayList<Card> hand = new ArrayList<Card>();
   int pot;

   public Dealer() {}

/**
* Method that checks the sum of cards for the dealer and see if it is over 17. Use when dealer is deciding to hit.
* (If less than 17 then they always hit. They stop once they have sum of cards at or greater than 17)
* @return true if sumCards is less than 17 and false otherwise
*/
   public boolean checkSeventeen(DeckOfCards Cards) {
       if (this.sumCards() < 17) {
           return true;
       } else {
           return false;
       }

   }

/**
* compareTo function to compare sum of cards between players
* @param p the player the current player is comparing hands with
* @return 1, 0, or -1 depending on if the players hand that is being compared is greater or less
*/
   public int compareTo(Player p) {
       int s1 = p.sumCards();
       int s2 = sumCards();

       if (s2 == s1) {
           return 1;  // dealer advantage when tied
       } else {
           return Integer.compare(s2, s1);
       }
   }
}
//hello






