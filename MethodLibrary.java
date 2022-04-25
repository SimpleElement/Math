import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodLibrary {

    private final double e = 0.01;

    private List<Double> equation;

    private List<Double> segmentsOfVariables;

    public MethodLibrary() {
        this.equation = new ArrayList<>();
        this.equation.add(2.95);
        this.equation.add(-23.74);
        this.equation.add(3.41);
        this.equation.add(2.0);
        this.segmentsOfVariables = getSegmentsOfVariables(this.equation);
    }

    public void oldNewtonMethod() {
        double x0;
        double x1;
        double a = this.segmentsOfVariables.get(2);
        double b = this.segmentsOfVariables.get(3);

        if (getDecision(a) * (3 * 2 * this.equation.get(3) * a + 2 * this.segmentsOfVariables.get(2)) > 0) {
            x0 = a;
            while (true) {

                x1 = x0 - (getDecision(x0) / (3 * this.equation.get(3) * Math.pow(x0, 2) + 2 * this.equation.get(2) * x0 + this.equation.get(1)));

                if (getDecision(x1) > e) {
                    x0 = x1;
                } else {
                    System.out.println(x1);
                    break;
                }
            }
        } else if (getDecision(b) * (3 * 2 * this.equation.get(3) * b + 2 * this.segmentsOfVariables.get(2)) > 0) {
            x0 = b;
            while (true) {

                x1 = x0 - (getDecision(x0) / (3 * this.equation.get(3) * Math.pow(x0, 2) + 2 * this.equation.get(2) * x0 + this.equation.get(1)));

                if (getDecision(x1) > e) {
                    x0 = x1;
                } else {
                    System.out.println(x1);
                    break;
                }
            }
        }
    }

    public void simpleIterationMethod() {
        double x0 = (this.segmentsOfVariables.get(1) + this.segmentsOfVariables.get(2)) / 2;
        double x1;

        while (true) {
            x1 = - (this.equation.get(3) * Math.pow(x0, 3) + this.equation.get(2) * Math.pow(x0, 2) + this.equation.get(0)) / this.equation.get(1);

            if (Math.abs(x1 - x0) > e) {
                x0 = x1;
            } else {
                System.out.println(x1);
                break;
            }
        }
    }

    public void halfDivisionMethod() {
        double xI;
        double aI = this.segmentsOfVariables.get(0);
        double bI = this.segmentsOfVariables.get(1);

        while (true) {
            xI = (aI + bI) / 2;

            if (Math.abs(getDecision(xI)) > e) {
                if (getDecision(aI) * getDecision(xI) < 0) {
                    bI = xI;
                } else {
                    aI = xI;
                }
            } else {
                System.out.println(xI);
                break;
            }
        }
    }

    private double getDecision(Double x) {
        double result = 0;

        for (int i = 0; i < this.equation.size(); i++) {
            result += this.equation.get(i) * Math.pow(x, i);
        }

        return result;
    }

    private List<Double> getSegmentsOfVariables(List<Double> equation) {
        List<Double> derivative = new ArrayList<>();

        for (int i = 1; i < equation.size(); i++) {
            derivative.add(equation.get(i) * i);
        }

        double D = Math.pow(derivative.get(1), 2) - 4 * derivative.get(2) * derivative.get(0);

        double x1 = (-derivative.get(1) - Math.sqrt(D)) / (2 * derivative.get(2));
        double x2 = (-derivative.get(1) + Math.sqrt(D)) / (2 * derivative.get(2));

        double range = Math.abs(x1) + Math.abs(x2) + Math.abs(derivative.get(0));

        List<Double> result = new ArrayList<>();

        result.add(-range);
        result.add(range);
        result.add(x1);
        result.add(x2);

        Collections.sort(result);

        return result;
    }
}
