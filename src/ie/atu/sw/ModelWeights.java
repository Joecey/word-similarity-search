package ie.atu.sw;

public class ModelWeights {

    // load instance variables - this is where we will be calling our ModelWeights
    private String[] wordList = null;
    private float[][] weightMatrix = null;

    public ModelWeights(String filePath) throws Exception {
        System.out.println("New model weights created. " + filePath);
    }

    public String[] createWordArray(String weightsFilePath) throws Exception{
        System.out.println("Word array: " + weightsFilePath);
        return null;
    }

    public double[][] createWeightMatrix(String weightsFilePath) throws Exception{
        System.out.println("Weight matrix: " + weightsFilePath);
        return null;
    }

    public boolean verifyWeights(){
        return true;
    }

}
