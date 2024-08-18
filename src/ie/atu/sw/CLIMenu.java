package ie.atu.sw;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class CLIMenu {
    private enum AlgorithmOptions {
        /* apply strategy pattern - call on specific functions when needed.
        Call the required algorithm from different class */

        COSINE(Colours.ANSI_CYAN + "COSINE" + Colours.ANSI_BLUE) {
            @Override
            public double calculateSimilarity(double[] targetWeight, double[] testWeight) {
                return WeightComparison.CosineDistance(targetWeight, testWeight);
            }
        }, DOT(Colours.ANSI_YELLOW + "DOT" + Colours.ANSI_BLUE) {
            @Override

            public double calculateSimilarity(double[] targetWeight, double[] testWeight) {
                return WeightComparison.DotProduct(targetWeight, testWeight);
            }
        }, EUCLIDEAN(Colours.ANSI_PURPLE + "EUCLIDEAN" + Colours.ANSI_BLUE) {
            @Override
            public double calculateSimilarity(double[] targetWeight, double[] testWeight) {
                return WeightComparison.EuclideanDistance(targetWeight, testWeight);
            }
        };

        private final String algoType;

        AlgorithmOptions(String algoType) {
            this.algoType = algoType;
        }

        public String getAlgoType() {
            return algoType;
        }


        // !!! abstract here is used to implement corresponding enum type methods (in this case, calculateSimilarity(); )
        // abstracts act as a template that needs to be added on to
        // here, we say we need calculateSimilarity(), but it must be extended from our chosen enum type
        abstract double calculateSimilarity(double[] a, double[] b);

    }

    public enum LogLevel {
        // specify your fields here
        INFO("[INFO]: "), WARN("[WARN]: "), ERROR("[ERROR]: ");

        // specify field values
        private final String message;

        // constructor used to set field values
        LogLevel(String message) {
            this.message = message;
        }

        // then we create methods to access fields
        public String getMessage() {
            return message;
        }
    }

    private String outputLocation = "./out.txt";
    private boolean showWeights = false;
    private boolean running = true;
    private int topWords = 10;

    // Set ModelWeights as an object variable which we can track
    private ModelWeights currentModel = null;
    private AlgorithmOptions currentAlgo = AlgorithmOptions.COSINE;

    public CLIMenu() {
    }

    private void setCurrentModel(ModelWeights currentModel) {
        this.currentModel = currentModel;
    }

    private void setTopWords(int topWords) {
        this.topWords = topWords;
    }

    private void setOutputLocation(String outputLocation) {
        this.outputLocation = outputLocation;
    }

    public void start() throws InterruptedException {
        do {
            TimeUnit.MILLISECONDS.sleep(500);
            showOptions();
            try {
                Scanner choiceScanner = new Scanner(in);
                int choice = choiceScanner.nextInt();
                switch (choice) {
                    case 1 -> loadWeightDataset();
                    case 2 -> {
                        if (currentModel != null) {
                            currentModel.showAvailableWordsCount();
                        } else {
                            out.println("No model loaded. Try again!");
                        }
                    }
                    case 3 -> setNewOutputFile();
                    case 4 -> cycleAlgoMethod();
                    case 5 -> updateTopWords();
                    case 6 -> setShowWeights(!showWeights);
                    case 7 -> beginWordSimilaritySearch();
                    case 8 -> {
                        running = false;
                        out.println("Existing program...");
                    }
                    default -> out.println("Not an option. Please try again");
                }
            } catch (InputMismatchException err) {
                out.println(LogLevel.WARN.getMessage() + "Input provided is not a valid integer. Please try again");
            }

        } while (running);
    }

    private void showOptions() {
        // TODO: Add option to show available words and do word count
        out.println();
        out.println(Colours.ANSI_PURPLE + "^^^^\t^^^^\t^^^^\t^^^^\t^^^^\t^^^^");
        out.println("\t\t\tWord Similarity Search\t\t");
        out.println("^^^^\t^^^^\t^^^^\t^^^^\t^^^^\t^^^^");
        out.println(Colours.ANSI_BLUE + "1) Provide file path for 50d word embeddings dataset");
        out.println(Colours.ANSI_BLUE + "2) Print full list of words and total count");
        out.println("3) Provide file path for output (current location: " + outputLocation + ")");
        out.println("4) Cycle similarity search algorithm (currently using " + currentAlgo.getAlgoType() + " algorithm)");
        out.println("5) Change number of words to show in similarity ranking (current amount: " + topWords + ")");
        out.println(Colours.ANSI_YELLOW + "6) Enable/Disable weight details (" + showWeights + ")");
        out.println(Colours.ANSI_GREEN + "7) Begin word similarity search");
        out.println(Colours.ANSI_RED + "8) Quit" + Colours.ANSI_RESET);
        out.print("Select an option [1-8]: ");
    }


    private void loadWeightDataset() {
        // TODO: Create exception handling here and os file check
        try {
            Scanner inputFilePath = new Scanner(in);
            out.print("Provide relative file path to 50d word dataset: ");
            String filePath = inputFilePath.nextLine();

            // Now verify if the file exists
            setCurrentModel(new ModelWeights(filePath));    // set current model with new ModelWeights object
            out.println(LogLevel.INFO.getMessage() + "Dataset weights have been loaded successfully! \n");


        } catch (Exception err) {
            out.println(LogLevel.ERROR.getMessage() + "An issue has occurred while setting file: " + err);


        }
    }

    private void setNewOutputFile() {
        try {
            Scanner outputFileScanner = new Scanner(in);
            out.print("Provide new name for output file (spaces will be replaced with _ ): ");
            String outputFilePath = outputFileScanner.nextLine();

            if (!outputFilePath.contains(".txt")) {
                out.println(LogLevel.WARN.getMessage() + "No .txt extension found. Appending to file...");
                outputFilePath += ".txt";
            }
            setOutputLocation(outputFilePath.replace(' ', '_'));
        } catch (Exception err) {
            out.println(LogLevel.ERROR.getMessage() + "An issue has occurred while setting output file: " + err);
        }

    }

    private void cycleAlgoMethod() {
        switch (currentAlgo) {
            case COSINE -> this.currentAlgo = AlgorithmOptions.DOT;
            case DOT -> this.currentAlgo = AlgorithmOptions.EUCLIDEAN;
            case EUCLIDEAN -> this.currentAlgo = AlgorithmOptions.COSINE;
        }
    }

    private void updateTopWords() {
        try {
            Scanner topWordsScanner = new Scanner(in);
            out.print("Provide the amount of words you would like displayed in ranking: ");
            int newTopWords = topWordsScanner.nextInt();
            setTopWords(newTopWords);
        } catch (InputMismatchException inputErr) {
            out.println(LogLevel.WARN.getMessage() + "That is not a valid integer. Please provide an integer value for top words");
        } catch (Exception err) {
            out.println(LogLevel.ERROR.getMessage() + "An error has occurred while updating top words. Please try again");
        }
    }

    private void setShowWeights(boolean showWeights) {
        this.showWeights = showWeights;
        out.println("Show weight details: " + showWeights + "\n");
    }

    private void beginWordSimilaritySearch() {
        // TODO: make new class to store calculations and top words
        if (currentModel == null) {
            out.println(LogLevel.WARN.getMessage() + "There is no dataset loaded. Use option 1) to load an appropriate file");
        } else {
            try {
                out.println(LogLevel.INFO.getMessage() + "wordList and weightList loaded correctly \n");
                Scanner inputSentence = new Scanner(in);
                out.println("Provide a single word (or word sequence) to being word similarity search: ");
                out.println(Colours.ANSI_RED + "Note: The longer the sequence, the more time it will take to process" + Colours.ANSI_RESET);
                String sentence = inputSentence.nextLine().toLowerCase();
                String[] sentenceSplit = sentence.split("\\s+");    // split by any whitespace (e.g. space or tab)

                String[] wordList = currentModel.getWordList();
                double[][] modelWeights = currentModel.getWeightMatrix();

                // put this here to put a hard limit for the sentence length (i.e. context length)
                int sentenceLimit = 50;
                if (sentenceSplit.length >= sentenceLimit) {
                    throw new Exception("Sentence length  of " + sentenceLimit + " words has been exceeded ");
                }

                // now we create a new results store
                ResultsStore finalResults = new ResultsStore(topWords, outputLocation, showWeights);
                ProgressBar bar = new ProgressBar();
                // for each word in wordList, get an average similarity score
                for (int wordListIndex = 0; wordListIndex < wordList.length; wordListIndex++) {
                    double progress = Math.round(((double) wordListIndex / wordList.length) * 100.0) / 100.0;
                    bar.showProgress(progress);
                    int validWords = 0;
                    double similarityScoreSum = 0.0d;

                    for (String word : sentenceSplit) {
                        // if word is not in wordList, skip the word
                        int wordIndex = currentModel.findWord(word);
                        if (wordIndex == -1) {
                            continue;
                        }
                        // if it is, check if the current wordList word is not the current sentence word
                        if (wordListIndex != wordIndex) {
                            double[] targetWeights = modelWeights[wordIndex];
                            double[] testWeights = modelWeights[wordListIndex];
                            double similarityScore = currentAlgo.calculateSimilarity(targetWeights, testWeights);
                            similarityScoreSum += similarityScore;
                            validWords += 1;
                        }
                    }
                    // only calculate final similarity score if validWords is greater than 0
                    // prevents the same word showing in the event of single word search
                    if (validWords != 0) {
                        double averageSimilarityScore = similarityScoreSum / validWords;

                        // create a new word record and attempt to add it to final results
                        Word newWord = new Word(wordList[wordListIndex], averageSimilarityScore);
                        finalResults.checkAndAddNewWord(newWord);
                    }
                }
                // now, we display our final results
                finalResults.getWordStore();
            } catch (Exception err) {
                out.println(LogLevel.ERROR.getMessage() + "An error has occurred while performing word similarity search. " + err);
            }

        }
    }

}

