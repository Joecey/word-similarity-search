package ie.atu.sw;

public class WeightComparison {
    private WeightComparison() {
    }

    public static double DotProduct(double[] targetWeights, double[] testWeights) {
        /*
        * if perpendicular = value is 0 meaning zero similarity
        * the largest value = point in same direction
        * the lowest value = points in opposite direction
        * */

        double similarityScore = 0.0d;

        for (int weight = 0; weight < targetWeights.length; weight++) {
            similarityScore += (targetWeights[weight] * testWeights[weight]);
        }


        return similarityScore;
    }

    public static double CosineDistance(double[] targetWeights, double[] testWeights) {
        // dot product of vector with itself == vector magnitude squared

        double magnitudeTargetSquared = DotProduct(targetWeights, targetWeights);
        double magnitudeTestSquared = DotProduct(testWeights, testWeights);
        double dotProduct = DotProduct(targetWeights, testWeights);

        // here, the closer to 1, the better the value (i.e. should be okay to assume bigger value the better
        return (dotProduct) / Math.sqrt(magnitudeTargetSquared * magnitudeTestSquared);

    }

    public static double EuclideanDistance(double[] targetWeights, double[] testWeights) {
        double similarityScore = 0.0d;

        for (int weight = 0; weight < targetWeights.length; weight++) {
            similarityScore += Math.pow(targetWeights[weight] - testWeights[weight], 2);
        }
        similarityScore = Math.sqrt(similarityScore);

        // here is a bit different, the smaller the value the better (i.e. vectors are closer together)
        // for this, we can return the negative similarityScore. i.e. larger value will imply shorter distance
        return -similarityScore;
    }
}
