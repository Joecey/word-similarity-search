package ie.atu.sw;

public class ResultsStore {
    private Word[] wordStore;
    private final boolean showWeights;
    private final String outputLocation;
    private final int maxWords;

    // Create a new results store that will be used to keep track of the top x words
    public ResultsStore(int maxWords, String outputFile, boolean showWeight) {
        this.maxWords = maxWords;
        this.wordStore = new Word[maxWords];
        this.outputLocation = outputFile;
        this.showWeights = showWeight;
    }

    public void getWordStore() {
        for (Word wordData : wordStore) {
            System.out.println(wordData);
        }
    }

    public void generateOutputFile() {
    }

    // when inserting new word, check to make sure there is space
    public void checkAndAddNewWord(Word newWord) {
        // option 1: check if there is null. if there is, add word
        for (int placement = 0; placement < wordStore.length; placement++) {
            // option 1), if there is an empty spot - place it in empty spot
            if (wordStore[placement] == null) {
                wordStore[placement] = newWord;
                return;
            } else{
                if (wordStore[placement].similarityScore() <= newWord.similarityScore()){
                    Word[] newWordStore = new Word[maxWords];

                    // now fill int the gaps around the new placement
                    int afterNewWord = 0;
                    for (int newPosition = 0; newPosition < wordStore.length; newPosition++) {
                        if (newPosition == placement){
                            newWordStore[newPosition] = newWord;
                            afterNewWord = 1;
                        } else{
                            newWordStore[newPosition] = wordStore[newPosition - afterNewWord];
                        }
                    }
                    this.wordStore = newWordStore;
                    return;
                }
            }
        }

    }

}
