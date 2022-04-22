import java.util.ArrayList;

public interface Moves {
    ArrayList<Card> hand =new ArrayList<Card>();

    public void hit();
    public void stay();
    public int sumCards(ArrayList<Card> hand);
}
