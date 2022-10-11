// this class condenses some of the methods used in main
import java.util.Scanner;


public class Game {

/**
* Method that checks a player hand for Aces after hitting and if the player is over 21 with an ace in hand it will
* convert the ace to a 1 (ace by default is 11) if that will make the players hand 21 or less
* @param inputPlayer Current player whose hand is being checked for aces
*/
   public static void changeAce(Player inputPlayer) {
       Card.Suit[] suitArray = Card.Suit.values();
       for (Card.Suit suits : suitArray) {
           Card currentAce = inputPlayer.getCardFromHand(1, suits);
           if (currentAce != null) {  // should update the value of only one of the aces
               if (currentAce.getValue() != 1) {  // ace hasn't already been updated
                   currentAce.updateAceValue();
                   System.out.println(currentAce.getValue());
                   System.out.println("Went over, changing ace value to 1");
                   System.out.println("New current hand: " + inputPlayer.getHand());
                   System.out.println("New current sum: " + inputPlayer.sumCards());
                   break;
               }
           }
       }
   }
}


