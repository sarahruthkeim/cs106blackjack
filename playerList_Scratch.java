import java.util.ArrayList;
import java.util.Scanner;

public class playerList_Scratch {
    ArrayList<Player> numberOfPlayers = new ArrayList<Player>();
    Player newPlayer = new Player();

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number of players");
    int size = sc.nextInt();

    for(int i=0; i<size; i++) {
        numberOfPlayers.add(newPlayer);

    }
}
