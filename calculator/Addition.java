package calculator;

public class Addition implements Calculator{


     double num1;
     double num2;

    public Addition(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public double calculate() {
        return num1+num2;
    }
}
