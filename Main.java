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
        if(operator.equals("q")){ operator.toUpperCase();}
        getOperation(operator, input);
            getFraction(input, count, fractionsArr);
            String frac1 = fractionsArr[0];
            String frac2 = fractionsArr[1];
            String[] tempArr1 = frac1.split("/", 0);
            String[] tempArr2 = frac2.split("/", 0);
            Fraction firstFraction = new Fraction(Integer.parseInt(tempArr1[0]), Integer.parseInt(tempArr1[1]));
            Fraction secondFraction = new Fraction(Integer.parseInt(tempArr2[0]), Integer.parseInt(tempArr2[1]));

            Fraction calculation = new Fraction(4,7);
            System.out.println("Operator: "+operator);

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
                        equalStr = "equ";
                    } else {
                        equalStr = "!equ";
                    }
                    break;
            }
            printResults(equalStr, operator, firstFraction, secondFraction, calculation);
        }
    }

    public static void intro() {
        System.out.println("This program is a fraction calculator\nIt will add, subtract, multiply and divide fractions until you type Q to quit.\nPlease enter your fractions in the form a/b, where a and b are integers.");
    }

    private static String getOperation(String operator, Scanner input) {
        while (!operator.matches("[+/*=]") && !operator.equalsIgnoreCase("Q") && !operator.equals("-")) {
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

    private static void printResults(String equalStr, String operator, Fraction firstFraction, Fraction secondFraction, Fraction calculation) {
        if (equalStr.equals("equ")) {
            System.out.println(firstFraction.toString() + " " + operator + " " + secondFraction.toString() + " = " + equalStr);
        } else {
            System.out.println(firstFraction.toString() + " " + operator + " " + secondFraction.toString() + " = " + calculation);
        }
    }
}
