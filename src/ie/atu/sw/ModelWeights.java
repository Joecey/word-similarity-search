package ie.atu.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class ModelWeights {

    // load instance variables - this is where we will be calling our ModelWeights
    private String[] wordList;
    private double[][] weightMatrix;

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
    }

    public void setWeightMatrix(double[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
    }

    public String[] getWordList() {
        return wordList;
    }

    public double[][] getWeightMatrix() {
        return weightMatrix;
    }

    public ModelWeights(String filePath) throws Exception {
        File modelFile = new File(filePath);
        if (!modelFile.exists()) {
            throw new Exception("This file does not exist!");
        } else {
            try {

                // once we are all good, introduce buffer reader to read modelFile
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(modelFile)));
                String currentLine = null;
                int count = 0;
                while ((currentLine = br.readLine()) != null) {
                    processLine(currentLine);
                    count++;
                }
                br.close();
                if (wordList != null) {
                    if (wordList.length != count) {
                        setWordList(null);
                        throw new Exception("Wordlist length does not match text file line size. Aborting...");
                    } else {
                        System.out.println(count + " words have been successfully loaded");
                        System.out.println(weightMatrix.length + ' ' + weightMatrix[0].length);
                    }
                }

            } catch (Exception err) {
                throw new Exception("[WARN]: There was an issue processing the file" + err);
            }
        }
    }

    private void processLine(String currentLineBuffer) throws Exception {
        try {
            String trimmed = currentLineBuffer.replaceAll("\\s+", "");
            String[] trimmedSplit = trimmed.split(",");
            String currentWord = trimmedSplit[0];
            String[] currentWeights = new String[trimmedSplit.length - 1];  // -1 to account for length and [0] index

            // doing this manually to show what ArrayCopyOf would do
            for (int i = 1; i < trimmedSplit.length; i++) {
                currentWeights[i - 1] = trimmedSplit[i];
            }
            appendWordArray(currentWord);
            appendWeightMatrix(currentWeights);
        } catch (Exception err) {
            throw new Exception(err);
        }

    }

    private void appendWordArray(String currentWord) {
        String[] newWordArray;
        if (wordList == null) {
            newWordArray = new String[1];
        } else {
            newWordArray = Arrays.copyOf(wordList, wordList.length + 1);
        }
        newWordArray[newWordArray.length - 1] = currentWord;
        setWordList(newWordArray);
    }

    // TODO: this is very inefficient - need to fix this
    // https://stackoverflow.com/questions/2068370/efficient-system-arraycopy-on-multidimensional-arrays
    private void appendWeightMatrix(String[] currentWeights) {
        double[][] newWeightMatrix;
        double[] newWeightRow = new double[currentWeights.length];  // this should be 50, but we can make it dynamic

        for (int weightIndex = 0; weightIndex < currentWeights.length; weightIndex++) {
            String addDoubleTail = currentWeights[weightIndex] + 'd';
            newWeightRow[weightIndex] = Double.parseDouble(addDoubleTail);
        }

        if (weightMatrix == null) {
            newWeightMatrix = new double[1][currentWeights.length];
        }else{
            System.out.println(weightMatrix.length);
            newWeightMatrix = new double[weightMatrix.length + 1][currentWeights.length];
            for (int row = 0; row < weightMatrix.length; row++) {
                newWeightMatrix[row] = weightMatrix[row].clone();
            }
        }
        newWeightMatrix[newWeightMatrix.length - 1] = newWeightRow;
        setWeightMatrix(newWeightMatrix);

    }

    public void showAvailableWords() {
        for (String word : wordList
        ) {
            System.out.println(word);
        }
        System.out.println("There are " + wordList.length + " words in the dataset");
    }


}
