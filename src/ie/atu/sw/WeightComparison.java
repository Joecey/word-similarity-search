package ie.atu.sw;

public class WeightComparison {
    private WeightComparison(){}

    public static double DotProduct(double[] weightsA, double[] weightsB ){
        System.out.println("Dot product...");
        /*
        Parallel vectors = 1
        Completely opposite vectors = -1
        Perpendicular vectors = 0
         */
        double similarityScore = 0.0d;

        for (int weight = 0; weight < weightsA.length; weight++) {
            similarityScore += (weightsA[weight] * weightsB[weight]);
        }

        return similarityScore;
    }

    public static double CosineDistance(double[] weightsA, double[] weightsB) {
        System.out.println("Cosine distance...");
        return 0.0;
    }

    public static double EuclideanDistance(double[] weightsA, double[] weightsB) {
        System.out.println("Euclidean distance...");
        return 0.0;
    }
}
