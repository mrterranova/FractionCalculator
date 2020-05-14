package Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isCalculating = true;
        int count = 0;
        String equalStr = "";
        String finalCalc = "";
        String[] fractionsArr = new String[2];
        int fractionNum1 = 0;
        int fractionDen1 = 1;
        int fractionNum2 = 0;
        int fractionDen2 = 1;

        intro();
        while (isCalculating) {
            System.out.print("--------------------------------------------------------\nPlease enter an operation (+, -, /, *, = or Q to quit):");
            String operator = input.nextLine();
            if (operator.equals("q")) {
                operator.toUpperCase();
                isCalculating = false;
                break;
            }
            operator = getOperation((operator), input);
            getFraction(input, count, fractionsArr);
            String frac1 = fractionsArr[0];
            String frac2 = fractionsArr[1];
            String[] tempArr1 = frac1.split("/", 0);
            String[] tempArr2 = frac2.split("/", 0);
            //case where single value add 1 to denominator.
            int denominatortemp = 1;
            if (tempArr1.length == 2) {
                denominatortemp = Integer.parseInt(tempArr1[1]);
            }
            ;
            if (tempArr2.length == 2) {
                denominatortemp = Integer.parseInt(tempArr2[1]);
            }
            ;
            Fraction firstFraction = new Fraction(Integer.parseInt(tempArr1[0]), denominatortemp);
            Fraction secondFraction = new Fraction(Integer.parseInt(tempArr2[0]), denominatortemp);

            Fraction calculation = new Fraction();


            switch (operator) {
                case "Q":
                    isCalculating = false;
                    break;
                case "+":
                    calculation = firstFraction.add(secondFraction);
                    break;
                case "-":
                    calculation = firstFraction.subtract(secondFraction);
                    break;
                case "*":
                    calculation = firstFraction.multiply(secondFraction);
                    break;
                case "/":
                    calculation = firstFraction.divide(secondFraction);
                    break;
                case "=":
                    boolean isEqual = calculation.equals(secondFraction);
                    if (isEqual) {
                        equalStr = "are equal";
                    } else {
                        equalStr = "are not equal";
                    }
                    break;
            }
            normalizeResults(equalStr, operator, firstFraction, secondFraction, calculation);
        }
    }

    public static void intro() {
        System.out.println("This program is a fraction calculator\nIt will add, subtract, multiply and divide fractions until you type Q to quit.\nPlease enter your fractions in the form a/b, where a and b are integers.");
    }

    private static String getOperation(String operator, Scanner input) {
        while (!operator.trim().matches("[+/*=]") && !operator.trim().equalsIgnoreCase("Q") && !operator.trim().equals("-")) {
            System.out.print("Invalid input (+, -, /, *, = or Q to quit). Try again: ");
            operator = input.nextLine();
        }
        return operator;
    }

    private static void getFraction(Scanner input, int count, String[] fractionsArr) {
        while (count < 2) {
            System.out.print("Please enter a Fraction (a/b) or integer (a): ");
            String userNumber = input.nextLine();
            boolean isValid = validFraction(userNumber);
            if (isValid) {
                fractionsArr[count] = userNumber;
                count++;
            } else {
                System.out.print("Invalid fraction. ");
            }
        }
    }

    public static boolean validFraction(String userNumber) {
        int numerator = 0;
        int denominator = 0;
        String[] tempArr = userNumber.split("/", 0);
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i].matches("[0-9]+") && tempArr.length < 3) {
                numerator = Integer.parseInt(tempArr[0]);
                if (tempArr.length == 1 || tempArr[1] == "") {
                    denominator += 1;
                } else {
                    denominator = Integer.parseInt(tempArr[1]);
                }
            }
        }
        if (denominator == 0) {
            return false;
        }
        return true;
    }

    public static void normalizeResults(String equalStr, String operator, Fraction firstFraction, Fraction secondFraction, Fraction calculation) {
        String firstNormalizedFraction = "";
        String secondNormalizedFraction = "";
        String calculationNormalizedFraction = "";
        String[] normalizeFirstFrac = firstFraction.toString().split("/", 0);
        if (normalizeFirstFrac.length == 2 && normalizeFirstFrac[1].equals("1")) {
            firstNormalizedFraction = normalizeFirstFrac[0];
        } else {
            firstNormalizedFraction = firstFraction.toString();
        }
        String[] normalizeSecondFrac = secondFraction.toString().split("/", 0);
        if (normalizeSecondFrac.length == 2 && normalizeSecondFrac[1].equals("1")) {
            secondNormalizedFraction = normalizeSecondFrac[0];
        } else {
            secondNormalizedFraction = secondFraction.toString();
        }
        String[] normalizeCalcFrac = calculation.toString().split("/", 0);
        if (normalizeCalcFrac.length == 2 && normalizeCalcFrac[1].equals("1")) {
            calculationNormalizedFraction = normalizeCalcFrac[0];
        } else {
            calculationNormalizedFraction = calculation.toString();
        }

        printResults(equalStr, operator, firstNormalizedFraction, secondNormalizedFraction, calculationNormalizedFraction);
    }

    private static void printResults(String equalStr, String operator, String firstNormalizedFrac, String secondNormalizedFrac, String calcNormalizedFrac) {
        if (equalStr.equals("are equal") || equalStr.equals("are not equal")) {
            System.out.println(firstNormalizedFrac + " " + operator + " " + secondNormalizedFrac + " = " + equalStr);
        } else {
            System.out.println(firstNormalizedFrac + " " + operator + " " + secondNormalizedFrac + " = " + calcNormalizedFrac);
        }
    }
}
