public class Player implements Moves{
    int pot;

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

    public void hit(DeckOfCards cardQueue){
        hand.add(cardQueue.getDeck().dequeue());
        //move to next person in linked list
    }

    public int sumCards(){
        int sum=0;
        for (Card item:hand){
            sum+= item.getValue();
        }
        return sum;
    }

    public boolean checkStatus(){
        if (sumCards()>=21){
            return true;
        }
        else{return false;}
    }

    public void deal(DeckOfCards cardQueue){
        hand.add(cardQueue.getDeck().dequeue());
        hand.add(cardQueue.getDeck().dequeue());
    }

}
