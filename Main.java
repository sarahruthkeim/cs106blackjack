import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        ArrayList<Player> playerListIndex = new ArrayList<Player>();
        int roundNumber = 0;

        playerListIndex.add(player1);
        playerListIndex.add(player2);
        playerListIndex.add(player3);
        playerListIndex.add(player4);

        CircularlyLinkedList<Player> gamePlayerList = new CircularlyLinkedList<Player>();
        for (int i = roundNumber; i < playerListIndex.size(); i++) {
            gamePlayerList.addLast(playerListIndex.get(i));
        }

        for (int i = 0; i < roundNumber; i++) {
            gamePlayerList.addLast(playerListIndex.get(i));
        }



        while (gamePlayerList.size() > 0) {  // while the round is still going on

        }

    }
}
