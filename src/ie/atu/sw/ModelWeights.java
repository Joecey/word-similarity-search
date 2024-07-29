package ie.atu.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
                if (wordList != null){
                    System.out.println(wordList.length);
                    System.out.println(count + " words have been successfully loaded");
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
//            System.out.println(trimmedSplit.length); // TODO use this to check that parsed array is correct
            appendWordArray(trimmedSplit);
            appendWeightMatrix(trimmedSplit);
        } catch (Exception err) {
            throw new Exception(err);
        }

    }

    private void appendWordArray(String[] currentLineArray) throws Exception {
        String[] newWordArray;
        if (wordList == null){
            newWordArray = new String[1];
        }else{
            newWordArray = Arrays.copyOf(wordList, wordList.length + 1);
        }
        newWordArray[newWordArray.length - 1] = currentLineArray[0];
        setWordList(newWordArray);
    }

    private void appendWeightMatrix(String[] currentLineArray) throws Exception {}


}
