package fundamentals;

import java.text.NumberFormat;
import java.util.Scanner;

public class Mortgage_Payments_Methods {

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        // principal = amount of loan = 100000$ <= P
        // anual interest rate = 3.92 % <= r
        // period (years) = 30
        // Mortgage = 472.81$


        int principal = (int) readNumber("Type your Principal ($1K - $1M): ", 1000, 1000000);
        double anualRate = readNumber("Annual Interest Rate: ", 1, 30);
        int period = (int) readNumber("Period (Years): ", 1, 30);

        // formula => M = P * {[r * (1+r)ⁿ] / [(1+r)ⁿ - 1]}

        double mortgage = calculateMortgage(principal, anualRate, period);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Your Montly Payments: " + mortgageFormatted);
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");

        for(int month = 1; month <= period * MONTHS_IN_YEAR; month++ ){
            double balance = calculateLoanBalance(principal, anualRate, period, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner read = new Scanner(System.in);
        double value;
        // "Type your Principal ($1K - $1M): "
        while (true) {
            System.out.print(prompt);
            value = read.nextInt();
            if (value >= min && value <= max) {
                break;
            } else {
                System.out.println("Enter a value between " + min + " and " + max);
            }
        }
        return value;
    }

    public static double calculateMortgage(int principal, double anualRate, int period) {

        double montlyRate = (anualRate / PERCENT) / MONTHS_IN_YEAR;
        int numberOfPayments = period * MONTHS_IN_YEAR; // <= ⁿ

        double value = Math.pow((1 + montlyRate), numberOfPayments);
        //System.out.println(value);
        double mortgage = principal * ((montlyRate * value) / (value - 1));
        return mortgage;

    }

    public static double calculateLoanBalance(int principal, double anualRate, int period, int numberOfPaymentsMade) {

        double montlyRate = (anualRate / PERCENT) / MONTHS_IN_YEAR;
        int numberOfPayments = period * MONTHS_IN_YEAR; // <= ⁿ

        double balance = principal * (Math.pow(1+montlyRate,numberOfPayments) - Math.pow(1 + montlyRate, numberOfPaymentsMade)) /
                (Math.pow(1+montlyRate, numberOfPayments) - 1);

        return balance;
    }
}
