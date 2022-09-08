package ackley;

public class Ackley {
    private int a;
    private double b;
    private double c;
    private double d;

    public Ackley() {
        this.a = 20;
        this.b = 0.2;
        this.c = 2 * Math.PI;
        this.d = 30;
    }

    // Ackley function
    public double ackley(double[] x) {
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(x[i], 2);
            sum2 += Math.cos(c * x[i]);
        }

        return -a * Math.exp(-b * Math.sqrt(sum1 / d)) - Math.exp(sum2 / d) + a + Math.exp(1);
    }
}
