
import java.util.Scanner;

public class Testing {
    public static void main (String[] args) {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.shuffle();
        System.out.println(myDeck);
        System.out.println(myDeck.getCardIndex());

        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number");
        int num1 = sc.nextInt();
        System.out.println("Enter another number");
        int num2 = sc.nextInt();
        System.out.println(num1 + num2);

         */

        CircularlyLinkedList<Integer> testList = new CircularlyLinkedList<Integer>();
        testList.addLast(10);
        testList.addLast(5);
        testList.addLast(7);
        testList.addLast(8);

        System.out.println(testList);
        testList.removeAt(2);

        System.out.println(testList);
    }

}
