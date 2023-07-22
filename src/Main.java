import Services.Actions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char answer;
        Actions actions = new Actions();

        do {
            actions.doSomething();
            actions.show();

            System.out.println("\nwant to do something else? (y/n)");
            answer = sc.nextLine().charAt(0);
        } while (Character.toString(answer).equalsIgnoreCase("Y"));

        System.out.println("This social media is over!");
        sc.close();
    }
}