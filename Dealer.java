import java.util.ArrayList;

public class Dealer implements Moves{

    public void stay() {

    }

    public void hit() {


    }
    public int sumCards(ArrayList<Card> hand) {
        int sum = 0;
        for(Card item:hand) {
            int value = item.getValue();
            sum += value;
        }
        return sum;

    }

    public void checkSeventeen(ArrayList<Card> Cards) {
        if(this.sumCards(Cards) < 17) {
            hit();
        }
        else {
          stay();
        }



    }


}
