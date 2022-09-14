package strategy;

public class Main {
    public static void main(String[] args) {
        StrategyFunctions strategyFunctions = new StrategyFunctions();
        double[] startPoint = strategyFunctions.createStartPoint();
        double sigma = 1.5;
        int i = 0;
        while (strategyFunctions.calculateFitness(startPoint) > 0.0001) {
            double[] Y = strategyFunctions.mutate(startPoint, sigma);
            sigma = strategyFunctions.calculateSigma(sigma, Y);
            if (strategyFunctions.calculateFitness(startPoint) > strategyFunctions.calculateFitness(Y)) {
                startPoint = Y;
            }
            if (i % 100 == 0) {
                System.out.println("Iteration: " + i + " Fitness: " + strategyFunctions.calculateFitness(startPoint));
            }
            i++;
        }
    }
}
