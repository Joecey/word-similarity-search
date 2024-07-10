package ie.atu.sw;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;


public class CLIMenu {
    private enum AlgorithmOptions{
        // apply strategy pattern
        COSINE,
        DOT,
        EUCLIDEAN


    }
    private String wordWeightsLocation;
    private String outputLocation = "./out.txt";
    private int numberOfResults = 10;
    private boolean showWeights = false;
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
                case 5 -> setShowWeights(!showWeights);
                case 6 -> out.println("option 6");
                case 7 -> {
                    running = false;
                    out.println("Existing program...");
                }
                default -> out.println("Not an option. Please try again");

            }
        }while(running);
    }

    private void setShowWeights(boolean showWeights) {
        this.showWeights = showWeights;
        out.println("Show weight details: " + showWeights + "\n");
    }

    private void showOptions(){
        out.println(Colours.ANSI_PURPLE + "^^^^\t^^^^\t^^^^\t^^^^\t^^^^\t^^^^");
        out.println("\t\t\tWord Similarity Search\t\t");
        out.println("^^^^\t^^^^\t^^^^\t^^^^\t^^^^\t^^^^");
        out.println(Colours.ANSI_BLUE + "1) Provide file path for 50d word embeddings dataset");
        out.println("2) Provide file path for output (current location: " +outputLocation+ ")");
        out.println("3) Select similarity search algorithm (COSINE, DOT, EUCLIDEAN)");
        out.println("4) Change number of words to show in similarity ranking (current amount: " + numberOfResults + ")");
        out.println(Colours.ANSI_YELLOW +"5) Enable/Disable weight details ("+showWeights+")");
        out.println(Colours.ANSI_GREEN + "6) Begin word similarity search");
        out.println(Colours.ANSI_RED +  "7) Quit" + Colours.ANSI_RESET);
        out.println("Select an option [1-7]");
    }
}

