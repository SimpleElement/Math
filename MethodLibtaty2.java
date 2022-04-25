import java.util.ArrayList;
import java.util.List;

public class MethodLibtaty2 {

    String x1 = "7xy + 2x^2 - 4y^2 = 0";
    String x2 = "x^2 - 5xy + y = - 11";

    private final double e = 0.01;

    public void getAnswer(Double x, Double y) {
        List<List<Double>> x1 = getDifference(x, y, getСomposition(Yakhobi(x, y), getAnswerDefault(x, y)));

        while (checkApproximation(x, y, x1)) {
            x = Double.parseDouble(String.valueOf(x1.get(0).get(0)).substring(0, 11));
            y =Double.parseDouble(String.valueOf(x1.get(1).get(0)).substring(0, 11));


            x1 = getDifference(x, y, getСomposition(Yakhobi(x, y), getAnswerDefault(x, y)));
        }
    }

    public List<List<Double>> Yakhobi(double x, double y) {
        List<List<Double>> matrix = new ArrayList<>();

        matrix.add(new ArrayList<>());
        matrix.get(0).add(7*y + 4*x);
        matrix.get(0).add(7*x-8*y);
        matrix.add(new ArrayList<>());
        matrix.get(1).add(2*x-5*y);
        matrix.get(1).add(-5*x+1);

        double opr = matrix.get(0).get(0) * matrix.get(1).get(1) - matrix.get(1).get(0) * matrix.get(0).get(1);

        List<List<Double>> matrixUnderlaunch = new ArrayList<>();

        matrixUnderlaunch.add(new ArrayList<>());
        matrixUnderlaunch.get(0).add(matrix.get(1).get(1)/opr);
        matrixUnderlaunch.get(0).add(-matrix.get(1).get(0)/opr);
        matrixUnderlaunch.add(new ArrayList<>());
        matrixUnderlaunch.get(1).add(-matrix.get(0).get(1)/opr);
        matrixUnderlaunch.get(1).add(matrix.get(0).get(0)/opr);

        return matrixUnderlaunch;
    }

    public List<List<Double>> getAnswerDefault(double x, double y) {
        List<List<Double>> matrix = new ArrayList<>();

        matrix.add(new ArrayList<>());
        matrix.get(0).add(7*x*y + 2*x*x - 4 * y * y);
        matrix.add(new ArrayList<>());
        matrix.get(1).add(x*x - 5*x*y + y + 11);

        return matrix;
    }

    public List<List<Double>> getСomposition(List<List<Double>> Yakhobi, List<List<Double>> defaultMatrix) {
        List<List<Double>> result = new ArrayList<>();

        result.add(new ArrayList<>());
        result.get(0).add(Yakhobi.get(0).get(0) * defaultMatrix.get(0).get(0) + Yakhobi.get(0).get(1) * defaultMatrix.get(1).get(0));
        result.add(new ArrayList<>());
        result.get(1).add(Yakhobi.get(1).get(0) * defaultMatrix.get(1).get(0) + Yakhobi.get(1).get(1) * defaultMatrix.get(0).get(0));

        return result;
    }

    public List<List<Double>> getDifference(Double x, Double y, List<List<Double>> composition) {
        List<List<Double>> result = new ArrayList<>();

        result.add(new ArrayList<>());
        result.get(0).add(x * composition.get(0).get(0));
        result.add(new ArrayList<>());
        result.get(1).add(y * composition.get(1).get(0));

        return result;
    }

    public boolean checkApproximation(Double x, Double y, List<List<Double>> difference) {
        if (
                Math.sqrt(Math.pow((x - difference.get(0).get(0)), 2) + Math.pow((y - difference.get(1).get(0)), 2)) < e
        )
            return false;
        return true;
    }
}
