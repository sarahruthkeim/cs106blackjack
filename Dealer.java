import java.util.ArrayList;

public class Dealer implements Moves{
    int pot;


    public void hit(DeckOfCards cardQueue) {
        hand.add(cardQueue.getDeck().dequeue());
        pot=sumCards();

    }

    public int sumCards() {
        int sum = 0;
        for(Card item:hand) {
            int value = item.getValue();
            sum += value;
        }
        return sum;

    }

    public void deal(DeckOfCards cardQueue){
        hand.add(cardQueue.getDeck().dequeue());
        hand.add(cardQueue.getDeck().dequeue());
    }


    public boolean checkSeventeen(DeckOfCards Cards) {
        if(this.sumCards() < 17) {
           return true;
        }
        else {
          return false;
        }

    }
//hello

}
