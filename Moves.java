import java.util.ArrayList;

public interface Moves {
    ArrayList<Card> hand =new ArrayList<Card>();

    public void hit(DeckOfCards cardQueue);
    public int sumCards();
}
