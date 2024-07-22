package ie.atu.sw;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.*;


public class CLIMenu {
    private enum AlgorithmOptions{
        /* apply strategy pattern - call on specific functions when needed.
        Call the required algorithm from different class */
        COSINE,
        DOT,
        EUCLIDEAN


    }

    public enum LogLevel{
        // specify your fields here
        INFO ("Information. "),
        WARN ("Warning. Check Before Continuing. "),
        ERROR ( "Error. Check immediately. ");

        // specify field values
        private final String message;

        // constructor used to set field values
        LogLevel(String message){
            this.message = message;
        }

        // then we create methods to access fields
        public String getMessage(){
            return message;
        }
    }

    private String outputLocation = "./out.txt";
    private int numberOfResults = 10;
    private boolean showWeights = false;
    private final Scanner s;
    private boolean running = true;
    private int topWords = 10;

    // Set ModelWeights as an object variable which we can track
    private ModelWeights currentModel = null;

    public CLIMenu (){
        s = new Scanner(in);
    }

    public void setCurrentModel(ModelWeights currentModel) {
        this.currentModel = currentModel;
    }

    public void start(){
        do {
            showOptions();
            int choice = s.nextInt();
            switch (choice) {
                case 1 -> loadWeightDataset();
                case 2 -> out.println("option 2");
                case 3 -> out.println("option 3");
                case 4 -> out.println("option 4. Top words is: " + topWords);
                case 5 -> setShowWeights(!showWeights);
                case 6 -> beginWordSimilaritySearch();
                case 7 -> {
                    running = false;
                    out.println("Existing program...");
                }
                default -> out.println("Not an option. Please try again");

            }
        }while(running);
    }

    private void loadWeightDataset(){
        // TODO: Create exception handling here and os file check
        try{
            Scanner inputFilePath = new Scanner(in);
            out.print("Provide relative file path to 50d word dataset: ");
            String filePath = inputFilePath.nextLine();

            // Now verify if the file exists
            File checkFile = new File(filePath);
            if (checkFile.exists()){
                setCurrentModel(new ModelWeights(filePath));    // set current model with new ModelWeights object
                out.println(LogLevel.INFO.getMessage() + "Dataset weights have been loaded successfully! \n");
            } else{
                out.println(LogLevel.WARN.getMessage() + "This file " + filePath + " does not exist! Please try again \n");
                setCurrentModel(null);

            }

        } catch (Exception err){
            out.println(LogLevel.ERROR.getMessage() + "An issue has occurred while setting file: "+ err);
        }

    }

    private void setShowWeights(boolean showWeights) {
        this.showWeights = showWeights;
        out.println("Show weight details: " + showWeights + "\n");
    }

    private void beginWordSimilaritySearch(){
        if (currentModel == null){
            out.println("There is no dataset loaded. Use option 1) to load an appropriate file");
        }else{
            if (!currentModel.verifyWeights()){
                out.println(LogLevel.WARN.getMessage() + "wordList / weightList is not valid. Load dataset using option 1) \n");
            }else{
                out.println(LogLevel.INFO.getMessage() + "wordList and weightList loaded correctly \n");
            }
        }
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
        out.print("Select an option [1-7]: ");
    }
}

