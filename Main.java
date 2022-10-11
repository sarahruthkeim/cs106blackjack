/**
* This file runs the text-based version of the game
*/


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public static void main (String[] args) {
       System.out.println("hello");
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter the number of players: ");  // number of people who will play the game
       int size = sc.nextInt();

       ArrayList<Player> playerListIndex = new ArrayList<Player>(size);  // this will be the array of players. This
       // array is maintained in between rounds so that player data is preserved
       for(int i = 0; i <size; i++) {
           Player newPlayer = new Player();
           playerListIndex.add(i, newPlayer);
       }

       for (int i=0; i < playerListIndex.size(); i++){
           Scanner scname = new Scanner(System.in);
           System.out.println("Enter a player name: ");
           playerListIndex.get(i).name=scname.nextLine();
       }

       for(int i = 0; i <size; i++) {
           playerListIndex.get(i).pot = 500;
       }


       int roundIndex = 0;  // keeps track of the current round, increases by 1 each time
       int roundNumber = 0; // same as roundIndex, but a modulo is applied to it so there isn't an out of bounds
       // error when the block of code in the two for loops below is executed
       Scanner sc2 = new Scanner(System.in);
       System.out.println("Enter the number of rounds: ");  // program will stop after this number is reached
       int numRounds = sc.nextInt();

       while (roundIndex < numRounds) {
           CircularlyLinkedList<Player> gamePlayerList = new CircularlyLinkedList<Player>(); // gamePlayerList is the
           // main data structure that is traversed during each round. When a player's game ends, they are removed from the list.
           for (int i = roundNumber; i < playerListIndex.size(); i++) {  // first player who starts is changed every
               // round using these two for-loops. This is done so that no one has an unfair
               gamePlayerList.addLast(playerListIndex.get(i));
           }

           for (int i = 0; i < roundNumber; i++) {
               gamePlayerList.addLast(playerListIndex.get(i));
           }

           Player lastPlayer;  // lastPlayer kept track of so the program knows when to end the game (this is the break
           // condition of the while loop)
           if (roundNumber == 0) { // lastPlayer retrieved from playerListIndex since it doesn't change during the round
               lastPlayer = playerListIndex.get(playerListIndex.size() - 1);
           } else {
               lastPlayer = playerListIndex.get(roundNumber - 1);
           }


           Node<Player> currentNode = gamePlayerList.getFirst();
           Player currentPlayer = currentNode.getElement();

           int betIndex = 1;
           int amount = 0; //Default minimum bet of 5. If they enter a wrong number (i.e negative, decimal, amount bigger than pot) then they just bet 5
           Scanner playerBet = new Scanner(System.in);
           if (!gamePlayerList.isEmpty()) {

               // players make bets before round begins
               System.out.println("Current Player: " + currentPlayer + " (Player #" + betIndex + ")");
               System.out.println("Current Pot: " + currentPlayer.pot + "\n");
               System.out.println("How much do you want to bet:");
               String amountBet = playerBet.nextLine();
               while (true) {
                   if ((Double.parseDouble(amountBet) % 1 == 0)) {
                       amount = Integer.parseInt(amountBet);
                   }
                   if (amount <= currentPlayer.pot && amount > 0) {
                       break;
                   } else {
                       System.out.println("You cannot bet that amount. Enter a valid amount:");
                       amountBet = playerBet.nextLine();
                   }
               }

               currentPlayer.bet(amount);
               currentPlayer.currentBet(amount);
               currentPlayer.roundBet=amount;
               System.out.println("Updated Pot:" + currentPlayer.pot);
               betIndex += 1;
               System.out.println("--------------------------");

               currentNode = currentNode.getNext();
               currentPlayer = currentNode.getElement();
               amount = 0;
               while (currentPlayer != gamePlayerList.first()) {
                   System.out.println("Current Player: " + currentPlayer + " (Player #" + betIndex + ")");
                   System.out.println("Current Pot: " + currentPlayer.pot + "\n");
                   System.out.println("How much do you want to bet:");
                   amountBet = playerBet.nextLine();
                   while (true) {
                       if (((Double.parseDouble(amountBet) % 1 == 0))) {
                           amount = Integer.parseInt(amountBet);
                       }
                       if (amount <= currentPlayer.pot && amount > 0) {
                           break;
                       } else {
                           System.out.println("You can't bet that amount. Enter a valid amount:");
                           amountBet = playerBet.nextLine();
                       }
                   }

                   currentPlayer.bet(amount);
                   currentPlayer.roundBet=amount;
                   System.out.println("Updated Pot:" + currentPlayer.pot);
                   System.out.println("--------------------------");
                   betIndex += 1;
                   currentNode = currentNode.getNext();
                   currentPlayer = currentNode.getElement();
                   amount = 0;
               }
           }


           Scanner playerMoves = new Scanner(System.in);
           DeckOfCards gameDeck = new DeckOfCards();
           gameDeck.shuffle();

           System.out.println("Remaining players: " + gamePlayerList);
           System.out.println("--------------------------");
           System.out.println("Current Player: " + currentPlayer);
           currentPlayer.deal(gameDeck);
           System.out.println("Current hand: " + currentPlayer.getHand());
           System.out.println("Current sum: " + currentPlayer.sumCards());

           while (gamePlayerList.size() > 0) {  // while the round is still going on

               // check to see if the player won on the first turn
               boolean prematureWin = false;
               boolean firstTurnWin = false;
               if (currentPlayer.checkStatus()) {
                   if (currentPlayer.sumCards() == 21) {
                       prematureWin = true;
                       firstTurnWin = true;
                       System.out.println("congrats! you've won");
                       System.out.println("Amount won:" + (currentPlayer.roundBet)*2);
                       currentPlayer.pot=((currentPlayer.pot)+(currentPlayer.roundBet*2));
                       System.out.println("Updated pot:" + currentPlayer.pot);
                       System.out.println("--------------------------");
                   } else { // case where player is given two aces on the first turn (initial sum is 22)
                       Game.changeAce(currentPlayer);
                   }
               }


               boolean hitBool = false;
               String hit;

               // this block of code is for the player's very first move. The while loop after this if for all subsequent moves.
               if (!prematureWin) {  // if they didn't win on the first turn. Otherwise, skip to the end
                   System.out.println("Draw another card? (y/n)");
                   hit = playerMoves.nextLine();  // if the player "hits", they receive another card. If they don't,
                   // it skips to the end
                   hitBool = hit.equalsIgnoreCase("y") || hit.equalsIgnoreCase("yes");

                   if (hitBool) {
                       currentPlayer.hit(gameDeck);
                       System.out.println("Current hand: " + currentPlayer.getHand());
                       System.out.println("Current sum: " + currentPlayer.sumCards());
                   }

                   if (currentPlayer.checkStatus()) {  // player went over, or made 21 exactly
                       if (currentPlayer.sumCards() == 21) { // player made 21 exactly, remove them then go to the next player
                           prematureWin = true;
                           System.out.println("congrats! you've won");
                           System.out.println("Amount won:" + (currentPlayer.roundBet)*2);
                           currentPlayer.pot=((currentPlayer.pot)+(currentPlayer.roundBet*2));
                           System.out.println("Updated pot:" + currentPlayer.pot);
                           System.out.println("--------------------------");
                           currentNode = currentNode.getNext();
                           gamePlayerList.removeElement(currentPlayer);
                       } else {  // player went over 21
                           if (currentPlayer.numAcesInHand() > 0) { // if the player has an ace, they can change its
                               // value to 1 to avoid going over
                               Game.changeAce(currentPlayer);
                               if (currentPlayer.checkStatus()) { // still over
                                   System.out.println("You've went over, better luck next time!");
                                   currentNode = currentNode.getNext();
                                   gamePlayerList.removeElement(currentPlayer);
                               }
                           } else {
                               prematureWin = true;
                               System.out.println("You've went over, better luck next time!");
                               currentNode = currentNode.getNext();
                               gamePlayerList.removeElement(currentPlayer);
                           }
                       }
                   }
               }

               while (hitBool && !prematureWin) {  // while loop executes as long as the player keeps wanting to draw
                   // cards. The !prematureWin is here to ensure that the player isn't asked to draw cards if they
                   // already won on the first turn.
                   System.out.println("Current status: " + currentPlayer.checkStatus());
                   if (gameDeck.isEmpty()) {gameDeck.shuffle();}
                   System.out.println("Draw another card? (y/n)");
                   hit = playerMoves.nextLine();
                   hitBool = hit.equals("y");

                   if (!hitBool) { // player passes, go to the next player, but keep the current player in the list
                       currentPlayer = currentNode.getElement();
                   } else {
                       currentPlayer.hit(gameDeck);
                       System.out.println("Current hand: " + currentPlayer.getHand());
                       System.out.println("Current sum: " + currentPlayer.sumCards());
                       if (currentPlayer.checkStatus()) { // mostly has the same logic as in the block of code on lines 173-200,
                           // would've made this into a function, but it would've been hard to do while keeping all the
                           // variables declared in main
                           if (currentPlayer.sumCards() == 21) {
                               System.out.println("congrats! you've won");
                               System.out.println("Amount won:" + (currentPlayer.roundBet)*2);
                               currentPlayer.pot=((currentPlayer.pot)+(currentPlayer.roundBet*2));
                               System.out.println("Updated pot:" + currentPlayer.pot);
                               System.out.println("--------------------------");
                               currentNode = currentNode.getNext();
                               gamePlayerList.removeElement(currentPlayer);
                               break;
                           } else {
                               if (currentPlayer.numAcesInHand() > 0) {
                                   Game.changeAce(currentPlayer);
                                   if (currentPlayer.checkStatus()) {// still over
                                       if (currentPlayer.sumCards() == 21) {
                                           System.out.println("congrats! you've won");
                                           System.out.println("Amount won:" + (currentPlayer.roundBet)*2);
                                           currentPlayer.pot=((currentPlayer.pot)+(currentPlayer.roundBet*2));
                                           System.out.println("Updated pot:" + currentPlayer.pot);
                                           System.out.println("--------------------------");
                                           currentNode = currentNode.getNext();
                                           gamePlayerList.removeElement(currentPlayer);
                                           break;
                                       } else if (currentPlayer.numAcesInHand() > 1) {  // player has two aces, update the other one
                                           Game.changeAce(currentPlayer);
                                       }
                                       else {
                                           System.out.println("You've went over, better luck next time!");
                                           currentNode = currentNode.getNext();
                                           gamePlayerList.removeElement(currentPlayer);
                                           break;
                                       }
                                   }
                               } else {
                                   System.out.println("You've went over, better luck next time!");
                                   currentNode = currentNode.getNext();
                                   gamePlayerList.removeElement(currentPlayer);
                                   break;
                               }
                           }
                       }
                   }
               }

               if (!hitBool) { // player passed, keep them in the list so that they can be compared to at the end
                   currentNode = currentNode.getNext();
                   if (firstTurnWin) {  // hitBool is initialized to false, so if they win on the first turn it stays
                       // false and this extra condition is necessary to remove them from the list.
                       gamePlayerList.removeElement(currentPlayer);
                   }
               }

               if (currentPlayer.equals(lastPlayer)) {
                   break;
               }

               currentPlayer = currentNode.getElement();  // currentNode was already updated, so to go to the next
               // player you only need to get the element from the current node.
               System.out.println("Remaining players: " + gamePlayerList);
               System.out.println("--------------------------");
               System.out.println("Current Player: " + currentPlayer);

               currentPlayer.deal(gameDeck);
               System.out.println("Current hand: " + currentPlayer.getHand());
               System.out.println("Current sum: " + currentPlayer.sumCards());
           } // end of while loop
          
          
           // this block of code is where the dealer plays the game. Their moves happen automatically.
           Dealer newDealer = new Dealer(); // create a new dealer
           boolean prematureDealerWin = false; // same logic as prematureWin for the players
           while(newDealer.checkSeventeen()) {
               newDealer.hit(gameDeck);
               if (newDealer.sumCards() > 21) { // dealer went over
                   if (newDealer.numAcesInHand() > 0) {
                       Game.changeAce(newDealer);
                       if (newDealer.checkStatus()) {// still over
                           Game.changeAce(newDealer); // will change the other ace if the dealer as more than one
                           if (newDealer.sumCards() == 21) {
                               System.out.println("The dealer made 21, everyone loses...");
                               prematureDealerWin = true;
                               break;
                           } else if (currentPlayer.numAcesInHand() > 1) {  // two aces
                               Game.changeAce(newDealer);
                           }
                           else {
                               System.out.println("The dealer busted, everyone wins!");
                               prematureDealerWin = true;

                               // each player wins 1.5 times the amount they bet
                               int i=0;
                               currentPlayer=gamePlayerList.getFirst().getElement();
                               while(i < gamePlayerList.size()){
                                   System.out.println("player " + currentPlayer + " has won!");
                                   System.out.println("Amount won:" + (currentPlayer.roundBet)*(1.5));
                                   currentPlayer.pot=((currentPlayer.pot)+(currentPlayer.roundBet*1.5));
                                   System.out.println("Updated pot:" + currentPlayer.pot);
                                   System.out.println("--------------------------");
                                   i++;
                                   currentPlayer=currentNode.getNext().getElement();
                               }
                               break;
                           }
                       }
                   } else {
                       System.out.println("The dealer busted, everyone wins!");

                       prematureDealerWin = true;
                       int i=0;
                       currentPlayer=gamePlayerList.getFirst().getElement();
                       while(i < gamePlayerList.size()){
                           System.out.println("player " + currentPlayer + " has won!");
                           System.out.println("Amount won:" + (currentPlayer.roundBet)*(1.5));
                           currentPlayer.pot=((currentPlayer.pot)+(currentPlayer.roundBet*1.5));
                           System.out.println("Updated pot:" + currentPlayer.pot);
                           System.out.println("--------------------------");
                           i++;
                           currentPlayer=currentNode.getNext().getElement();
                       }
                   }
               } else if (newDealer.sumCards() == 21) {
                   System.out.println("The dealer made 21, everyone loses...");
                   prematureDealerWin = true;
               }
           }


           // this next block of code is used to compare the dealer to the other players at the end of the round

           System.out.println("Dealer cards: " + newDealer.getHand());
           System.out.println("Dealer score: " + newDealer.sumCards());
           if(!gamePlayerList.isEmpty()) {
               System.out.println("--------------------------");
               System.out.println(gamePlayerList);
               System.out.println("--------------------------");
               currentPlayer = gamePlayerList.first();
           }

           // compare each remaining player to the dealer
           while(!prematureDealerWin && !gamePlayerList.isEmpty()) {
               System.out.println("--------------------------");
               System.out.println("Dealer cards: " + newDealer.getHand());
               System.out.println("Dealer score: " + newDealer.sumCards());
               System.out.println("Comparing to: " + currentPlayer);
               System.out.println("Player cards: " + currentPlayer.getHand());
               System.out.println("Player score: " + currentPlayer.sumCards());
              
               if (newDealer.compareTo(currentPlayer) >= 0) {
                   System.out.println("Dealer has won!");
               } else {
                   System.out.println("player " + currentPlayer + " has won!");
                   System.out.println("Amount won:" + (currentPlayer.roundBet)*2);
                   currentPlayer.pot=((currentPlayer.pot)+(currentPlayer.roundBet*2));
                   System.out.println("Updated pot:" + currentPlayer.pot);
                   System.out.println("--------------------------");
               }
               currentNode = currentNode.getNext();
               currentPlayer = currentNode.getElement();

               if (currentPlayer.equals(gamePlayerList.getFirst().getElement())) {
                   break;
               }
           }

           // advance to the next round
           roundIndex ++;
           roundNumber = (roundNumber + 1) % playerListIndex.size();
           for (Player x : playerListIndex) {
               x.clear(); // clear extraneous data from players so that it doesn't roll over to the next round
               // (keeps data about their name and pot, but removes data about their cards)
           }
       }
   }
}







