package strategy;

public class Main {
    public static void main(String[] args) {
        StrategyFunctions strategyFunctions = new StrategyFunctions();
        double[] startPoint = strategyFunctions.createStartPoint();
        double sigma = 1.5;
        int i = 1, acceptedMutations = 0;
        while (strategyFunctions.calculateFitness(startPoint) > 0.0001) {
            double[] Y = strategyFunctions.mutate(startPoint, sigma);
            if (strategyFunctions.calculateFitness(startPoint) > strategyFunctions.calculateFitness(Y)) {
                acceptedMutations++;
                startPoint = Y;
            }
            if (i % 100 == 0) {
                System.out.println("Iteration: " + i + " Fitness: " +
                        strategyFunctions.calculateFitness(startPoint));
            }
            i++;
            sigma = strategyFunctions.calculateSigma(sigma, acceptedMutations, i);
        }
    }
}
