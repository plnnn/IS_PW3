package lt.viko.eif.nychyporuk.is_pw3_1;

import java.util.Scanner;

public class Menu {

    public static int displayMenu(Scanner input) {
        System.out.println("select");
        System.out.println("----------------");
        System.out.printf("1. %s\n", "encrypt from input");
        System.out.printf("2. %s\n", "decrypt from file");
        System.out.printf("0. %s\n", "quit");
        return input.nextInt();
    }

    public static void processUserChoice() {
        Scanner input = new Scanner(System.in);

        int userChoice;
        do {
            userChoice = displayMenu(input);
            switch (userChoice) {
                case 1:
                    Encryptor.encrypt();
                    break;
                case 2:
                    Decryptor.decrypt();
                    break;
                case 0:
                    System.out.println("bye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("no such action");
                    break;
            }
        } while (true);
    }
}