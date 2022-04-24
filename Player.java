import java.util.ArrayList;

public class Player implements Moves{
    int pot;
    public LinkedQueue<Card>cardQueue= new LinkedQueue<Card>();

    public Player(){}

    public void bet(int i){
        if (i > pot){
            throw new IllegalArgumentException("Not enough money to bet that amount");
        }
        else{
            pot-=i;
        }
        //move to the next person in linked list and loop through until back at first player

    }

    public void stay(){
        //move to next person in linked list
    }

    public void hit(){
        hand.add(cardQueue.dequeue());
        pot=sumCards(hand);
        //move to next person in linked list
    }

    public int sumCards(ArrayList<Card> cards){
        int sum=0;
        for (Card item:cards){
            sum+= item.getValue();
        }
        return sum;
    }

    public boolean checkStatus(){
        if (sumCards(hand)>=21){
            return true;
        }
        else{return false;}
    }

    public void deal(){
        hand.add(cardQueue.dequeue());
        hand.add(cardQueue.dequeue());
    }
    
}
