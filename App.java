package mortgage.calc;

import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    final static byte MONTHS_PER_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = 0;
        byte years = 0;
        float annualInterest = 0;

        principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 35);
        years = (byte) readNumber("Years: ", 1, 60);

        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("Mortgage");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);

        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_PER_YEAR; month++){
           double balance = caculateNumberOfPayments(principal, annualInterest, years, month );
           System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }

    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double caculateNumberOfPayments(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade) {

        short numberOfPayments = (short) (years * MONTHS_PER_YEAR);
        float monthlyInterest = annualInterest / PERCENT / MONTHS_PER_YEAR;

        double balance = principal 
        * (Math.pow(1 + monthlyInterest , numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
        / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years) {

        short numberOfPayments = (short) (years * MONTHS_PER_YEAR);
        float monthlyInterest = annualInterest / PERCENT / MONTHS_PER_YEAR;

        // System.out.print(numberOfPayments, monthlyInterest, principal,
        // annualInterest, years);

        double mortgage = principal
                * ((monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                        / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1));

        return mortgage;
    }
}
