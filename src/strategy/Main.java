package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            File myObj = new File("fitness.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter("fitness.txt");

        StrategyFunctions strategyFunctions = new StrategyFunctions();
        double[] startPoint = strategyFunctions.createStartPoint();
        int i = 1;
        while (strategyFunctions.calculateFitness(startPoint) > 0.01) {
            double[] Y = strategyFunctions.mutate(startPoint);
            if (strategyFunctions.calculateFitness(startPoint) > strategyFunctions.calculateFitness(Y)) {
                startPoint = Y;
            }
            System.out.println("Iteration: " + i + " Fitness: " + strategyFunctions.calculateFitness(startPoint));
            try {
                writer.write(strategyFunctions.calculateFitness(startPoint) + "\n");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            i++;
        }
        writer.close();
    }
}
