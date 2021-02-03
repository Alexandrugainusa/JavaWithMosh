package fundamentals;

import java.text.NumberFormat;
import java.util.Scanner;

public class Mortgage_Payments {
    public static void main(String[] args) {
        // principal = amount of loan = 100000$ <= P
        // anual interest rate = 3.92 % <= r
        // period (years) = 30
        // Mortgage = 472.81$


        Scanner read = new Scanner(System.in);
        System.out.print("Type your Principal: ");
        int principal = read.nextInt();
        System.out.print("Annual Interest Rate: ");
        double anualRate = read.nextDouble(); // per year
        System.out.print("Period (Years): ");
        int period = read.nextInt(); // years

        double montlyRate = (anualRate/100) / 12;
        int numberOfPayments = period * 12; // <= ⁿ
        double value = Math.pow((1+montlyRate),numberOfPayments);
        System.out.println(value);

        double mortgage = principal * ((montlyRate * value) / (value - 1));
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String result = currency.format(mortgage);

        System.out.print("Your Mortgage is: " + result);

        // formula => M = P * {[r * (1+r)ⁿ] / [(1+r)ⁿ - 1]}



    }
}
