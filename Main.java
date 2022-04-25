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
        Node<Player> currentNode = gamePlayerList.getFirst();
        Player currentPlayer = currentNode.getElement();

        DeckOfCards gameDeck = new DeckOfCards();
        gameDeck.shuffle();
        int playerIndex = 0;
        while (gamePlayerList.size() > 0  && playerIndex < playerListIndex.size()) {  // while the round is still going on
            playerIndex++;
            currentPlayer.deal(gameDeck);

            String hit = playerMoves.nextLine();
            boolean hitBool; hitBool = hit.equalsIgnoreCase("y") || hit.equalsIgnoreCase("yes");

            while (hitBool && !currentPlayer.checkStatus()) {
                if (gameDeck.isEmpty()) {gameDeck.shuffle();}
                currentPlayer.hit(gameDeck);

                hit = playerMoves.nextLine();
                hitBool = hit.equals("y");

                if (!hitBool) {
                    currentNode = currentNode.getNext();
                    currentPlayer = currentNode.getElement();
                } else {
                    if (currentPlayer.checkStatus()) {
                        gamePlayerList.removeAt(playerIndex);
                        if (currentPlayer.sumCards() == 21) {
                            System.out.println("congrats! you've won");
                        } else {
                            System.out.println("You've went over, better luck next time!");
                        }
                    } else {
                        System.out.println("Draw another card? (y/n)");
                    }
                }
            }
        }

        // add while loop here to deal cards into the dealers hand
        // add while loop here to compare players to dealer


    }
}
