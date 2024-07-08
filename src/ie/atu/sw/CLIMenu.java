package ie.atu.sw;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class CLIMenu {
    private String configLocation;
    private final Scanner s;
    private boolean running = true;
    private int topWords = 10;
    public CLIMenu (){
        s = new Scanner(in);
    }

    public void start(){
        do {
            showOptions();
            int choice = s.nextInt();
            switch (choice) {
                case 1 -> out.println("option 1");
                case 2 -> out.println("option 2");
                case 3 -> out.println("option 3");
                case 4 -> out.println("option 4. Top words is: " + topWords);
                case 5 -> out.println("option 5");
                case 6 -> out.println("option 6");
                case 7 -> {
                    running = false;
                    out.println("Existing program...");
                }
                default -> out.println("Not an option. Please try again");

            }
        }while(running);
    }

    private void showOptions(){
        out.println("^^^^ ^^^^ ^^^^ ^^^^ ^^^^ ^^^^");
        out.println("^^^^ Student Manager Menu ^^^^");
        out.println("^^^^ ^^^^ ^^^^ ^^^^ ^^^^ ^^^^");
        out.println("1) Provide file path for 50d word embeddings dataset");
        out.println("2) Provide file path for output (default /out.txt)");
        out.println("3) Select similarity search algorithm");
        out.println("4) Change number of words to show in similarity ranking");
        out.println("5) Enable/Disable weight details");
        out.println("6) Begin word similarity search");
        out.println("7) Quit");
        out.println("Select an option [1-7]");
    }
}
