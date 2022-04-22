import java.util.ArrayList;

public class Player implements Moves{
    int pot;

    public void bet(int i){
        if (i > pot){
            throw new IllegalArgumentException("Not enough money to bet that amount");
        }
        else{
            pot-=i;
        }
        //update the community pot as well
        //move to the next person in linked list and loop through until back at first player
    }

    public void stay(){
        //move to next person in linked list
    }

    public void hit(){
        //hand.add(newCard);
        //pot=sumCards(hand);
        //move to next person in linked list
    }

    public int sumCards(ArrayList<Card> cards){
        int sum=0;
        //for (card item:cards){
        //find the value of the card
        //sum+=value of the card
        //}
        return sum;
    }

    public boolean checkStatus(){
        if (sumCards(hand)>=21){
            return true;
        }
        else{return false;}
    }
}
