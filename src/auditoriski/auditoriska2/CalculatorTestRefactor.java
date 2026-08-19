package auditoriski.auditoriska2;

import java.util.Scanner;

@FunctionalInterface
interface Operation1 {
    double apply(double result, double value);
}

class Calculator{

    private double result;

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MNOZI = '*';
    private static final char DELI = '/';

    private static final Operation1 ADD = (result, value) -> result + value;
    private static final Operation1 SUBTRACT = (result, value) -> result - value;
    private static final Operation1 MULTIPLY = (result, value) -> result * value;
    private static final Operation1 DIVIDE = (result, value) -> result / value;

    public Calculator() {
        this.result = 0;
    }

    public String init(){
        return String.format("result = %f", result);
    }

    public double getResult(){
        return result;
    }

    static class UnknownOperatorException extends Exception{
        public UnknownOperatorException(char operator){
            super(String.format("%c is unknown operator", operator));
        }
    }

    public static Operation1 getOperation(char operator) throws UnknownOperatorException {

        if (operator == PLUS){
            return ADD;
        }
        else if (operator == MINUS){
            return SUBTRACT;
        }
        else if (operator == MNOZI){
            return MULTIPLY;
        }
        else if (operator == DELI){
            return DIVIDE;
        }
        else {
            throw new UnknownOperatorException(operator);
        }
    }

    public String execute(char operator, double value) throws UnknownOperatorException {
        Operation1 op = getOperation(operator);
        result = op.apply(result, value);
        return String.format("result %c %f = %f", operator, value, result);
    }

    @Override
    public String toString() {
        return String.format("updated result = %f", result);
    }
}

public class CalculatorTestRefactor {
    static final char RESULT = 'r';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Calculator calculator = new Calculator();
            System.out.println(calculator.init());
            while (true) {
                String line = scanner.nextLine();
                char choice = getCharLower(line);
                if (choice == RESULT) {
                    System.out.println(String.format("final result = %f", calculator.getResult()));
                    break;
                }
                String[] parts = line.split("\\s+");
                char operator = parts[0].charAt(0);
                double value = Double.parseDouble(parts[1]);
                try {
                    String result = calculator.execute(operator, value);
                    System.out.println(result);
                    System.out.println(calculator);
                } catch (Calculator.UnknownOperatorException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("(Y/N)");
            String line = scanner.nextLine();
            char choice = getCharLower(line);
            if (choice == 'n') {
                break;
            }
        }
    }

    static char getCharLower(String line) {
        if (line.trim().length() > 0) {
            return Character.toLowerCase(line.charAt(0));
        }
        return '?';
    }
}
