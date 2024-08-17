package ie.atu.sw;

public class ResultsStore {
    private Word[] wordStore;
    private final boolean showWeights;
    private final String outputLocation;

    // Create a new results store that will be used to keep track of the top x words
    public ResultsStore(int maxWords, String outputFile, boolean showWeight) {
        wordStore = new Word[maxWords];
        outputLocation = outputFile;
        showWeights = showWeight;
    }
    public Word[] getWordStore() {
        return wordStore;
    }
    // when inserting new word, check to make sure there is space
    public void CheckAndAddNewWord(Word newWord) {

    }

}
