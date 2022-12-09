package org.example;

import static java.lang.System.arraycopy;

import java.util.Objects;




/**
 * Prefix Calculator to use:
 * static method calculate.
 */
public class Calc {
    /**
     *A variable that stores the number of operands in action.
     */

    static int number = 2;

    /**
     * operation minus.
     *
     * @param operand1 first operand.
     *
     * @param operand2 second operand.
     *
     * @return double value.
     */
    private static double minus(double operand1, double operand2) {
        return operand1 - operand2;
    }

    /**
     * operation plus.
     *
     * @param operand1 first operand.
     *
     * @param operand2 second operand.
     *
     * @return double value.
     */

    private static double plus(double operand1, double operand2) {
        return operand1 + operand2;
    }

    /**
     * operation multiply.
     *
     * @param operand1 first operand.
     *
     * @param operand2 second operand.
     *
     * @return double value.
     */

    private static double multiply(double operand1, double operand2) {
        return operand1 * operand2;
    }

    /**
     * operation diverse.
     *
     * @param operand1 first operand.
     *
     * @param operand2 second operand.
     *
     * @return double value.
     */

    private static double divide(double operand1, double operand2) {
        if (operand2 == 0) {
            throw new ArithmeticException("Invalid input");
        } else {
            return operand1 / operand2;
        }
    }

    /**
     * operation sin.
     *
     * @param operand the only one operand.
     *
     * @return double value.
     */

    private static double sin(double operand) {
        return Math.sin(operand);
    }

    /**
     * operation cos.
     *
     * @param operand the only one operand.
     *
     * @return double value.
     */

    private static double cos(double operand) {
        return Math.cos(operand);
    }

    /**
     * operation ln.
     *
     * @param operand the only one operand.
     *
     * @return double value.
     */

    private static double log(double operand) {
        if (operand <= 0) {
            throw new ArithmeticException("Invalid input");
        } else {
            return Math.log(operand);
        }
    }

    /**
     * operation pow.
     *
     * @param operand1 first operand(number).
     *
     * @param operand2 second operand(degree).
     *
     * @return double value.
     */

    private static double pow(double operand1, double operand2) {
        return Math.pow(operand1, operand2);
    }

    /**
     * operation sqrt.
     *
     * @param operand the only one operand.
     *
     * @return double value.
     */

    private static double sqrt(double operand) {
        if (operand <= 0) {
            throw new ArithmeticException("Invalid input");
        } else {
            return Math.sqrt(operand);
        }
    }

    /**
     * functions performs calculations depending on the operator.
     * changes the value of the number of operands.
     *
     * @param line an array of strings representing an expression.
     *
     * @return double value after calculate.
     */

    private static double operator(String [] line) {
        switch (line[0]) {
            case "+" -> {
                number = 2;
                return plus(Double.parseDouble(line[1]), Double.parseDouble(line[2]));
            }
            case "-" -> {
                number = 2;
                return minus(Double.parseDouble(line[1]), Double.parseDouble(line[2]));
            }
            case "*" -> {
                number = 2;
                return multiply(Double.parseDouble(line[1]), Double.parseDouble(line[2]));
            }
            case "/" -> {
                number = 2;
                return divide(Double.parseDouble(line[1]), Double.parseDouble(line[2]));
            }
            case "pow" -> {
                number = 2;
                return pow(Double.parseDouble(line[1]), Double.parseDouble(line[2]));
            }
            case "sin" -> {
                number = 1;
                return sin(Double.parseDouble(line[1]));
            }
            case "cos" -> {
                number = 1;
                return cos(Double.parseDouble(line[1]));
            }
            case "log" -> {
                number = 1;
                return log(Double.parseDouble(line[1]));
            }
            case "sqrt" -> {
                number = 1;
                return sqrt(Double.parseDouble(line[1]));
            }
            default -> {
                number = 0;
                return Double.parseDouble(line[0]);
            }
        }
    }

    /**
     * The function replaces the prefix expression in the list with the calculated value.
     *
     * @param arr   an array of strings representing an expression.
     *
     * @param value calculated value.
     *
     * @return modified array.
     */

    private static String [] change_arr(String [] arr, double value) {
        String rep = String.valueOf(value);
        String [] ans = new String[arr.length - number];
        arraycopy(arr, 0, ans, 0, 0);
        ans[0] = rep;
        if (ans.length - (1) > 0) {
            arraycopy(arr, 1 + number, ans, 1, ans.length - 1);
        }
        return ans;
    }

    /**
     * the function checks whether an array element is a number.
     *
     * @param elem the element to check.
     *
     * @return true or false.
     */

    private static Boolean num(String elem) {
        Boolean cond1 = !Objects.equals(elem, "log");
        Boolean cond2 = !Objects.equals(elem, "cos");
        Boolean cond3 = !Objects.equals(elem, "sin");
        Boolean cond4 = !Objects.equals(elem, "sqrt");
        Boolean cond5 = !Objects.equals(elem, "pow");
        Boolean cond6 = !Objects.equals(elem, "+");
        Boolean cond7 = !Objects.equals(elem, "-");
        Boolean cond8 = !Objects.equals(elem, "*");
        Boolean cond9 = !Objects.equals(elem, "/");
        return cond1 && cond2 && cond3 && cond4 && cond5 && cond6 && cond7 && cond8 && cond9;
    }

    /**
     * the function checks whether an array element is an operator with one operand.
     *
     * @param elem the element to check.
     *
     * @return true or false.
     */

    private static Boolean one_operand(String elem) {
        Boolean cond1 = Objects.equals(elem, "log");
        Boolean cond2 = Objects.equals(elem, "cos");
        Boolean cond3 = Objects.equals(elem, "sin");
        Boolean cond4 = Objects.equals(elem, "sqrt");
        return cond1 || cond2 || cond3 || cond4;
    }

    /**
     * recursive function for counting.
     * checks which operator and makes subtractions for it.
     * if the operands of the operator are other operators.
     * performs subtractions first for them.
     *
     * @param line an array of strings representing an expression.
     *
     * @return an array with a single response element.
     */

    private static String [] calc(String[] line) {
        if (num(line[0])) {
            return line;
        }
        if (one_operand(line[0])) {
            if ((num(line[1]))) {
                return change_arr(line, operator(line));
            }
        } else {
            if ((num(line[1])) && (num(line[2]))) {
                return change_arr(line, operator(line));
            }
        }
        if (!num(line[1])) {
            String[] n = new String[line.length - 1];
            arraycopy(line, 1, n, 0, n.length);
            String[] n3 = calc(n);
            String[] n2 = new String[n3.length + 1];
            arraycopy(line, 0, n2, 0, 1);
            arraycopy(n3, 0, n2, 1, n3.length);
            return calc(n2);
        } else if (!num(line[2])) {
            String[] n = new String[line.length - 2];
            arraycopy(line, 2, n, 0, n.length);
            String[] n3 = calc(n);
            String[] n2 = new String[n3.length + 2];
            arraycopy(line, 0, n2, 0, 2);
            arraycopy(n3, 0, n2, 2, n3.length);
            return calc(n2);
        }
        return line;
    }

    /**
     * returns a double value.
     *
     * @param line an array with a single calculated value.
     *
     * @return double value.
     */

    public static Double calculate(String line) {
        String [] input = line.split(" ");
        String [] ans = calc(input);
        return Double.parseDouble(ans[0]);
    }
}
