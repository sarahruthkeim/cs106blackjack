import java.util.ArrayList;
import java.util.Scanner;

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



        Scanner playerMoves = new Scanner(System.in);
        Player currentPlayer = gamePlayerList.first();
        DeckOfCards gameDeck = new DeckOfCards();
        gameDeck.shuffle();
        while (gamePlayerList.size() > 0) {  // while the round is still going on
            // deal two cards to the player
            System.out.println("Draw another card? (y/n)");
            String hit = playerMoves.nextLine();
            boolean hitBool; hitBool = hit.equalsIgnoreCase("y") || hit.equalsIgnoreCase("yes");

            while (hitBool && !currentPlayer.checkStatus()) {
                if (gameDeck.isEmpty()) {gameDeck.shuffle();}
                currentPlayer.deal();
                hit = playerMoves.nextLine();
                hitBool = hit.equals("y");

                if (currentPlayer.checkStatus()) {
                    gamePlayerList.remove()
                    if (currentPlayer.sumCards() == 21) {
                        System.out.println("congrats! you've won");
                    } else {

                    }
                }

            }



        }

    }
}
