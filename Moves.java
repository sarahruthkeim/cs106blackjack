import java.util.ArrayList;

/**
* Interface that holds hit and sumCards methods used by both player and dealer
*/

public interface Moves {
   public void hit(DeckOfCards cardQueue);
   public int sumCards();
}

