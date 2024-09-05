import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        System.out.println(date);

        // Get loan details for calculationsw

            // Get loan balance
            System.out.print("Enter loan balance: ");
            double loanBalance = input.nextDouble();

            // Get loan interest
            System.out.print("Enter interest rate: ");
            float interestRate = (input.nextFloat() / 100);
            double monthlyInterest = loanBalance * interestRate;
            System.out.println(monthlyInterest);

            // Get loan payment amount
            System.out.print("Enter payment amount: ");
            double paymentAmount = input.nextDouble();

            // Get payment duration
            System.out.print("Enter payment duration in months: ");
            long paymentDuration = input.nextLong();

            // Get payment frequency
            ArrayList<String> frequencyList = new ArrayList<String>();
            long[] frequencies = {1, 2, 1};
            String[] period = {"Months", "Weeks"};
            System.out.print("Choose frequency:\n(1) Monthly\n(2) Bi-Weekly\n(3) Weekly\nSelect: ");
            int i = input.nextInt() - 1;
            long paymentFrequency = frequencies[i];
            long payments = 0;
            if (i == 2) {
                payments = paymentDuration * 4;
            } else {
                payments = paymentDuration * paymentFrequency;
            }

           
            
            // Make a list of all payment dates
            ArrayList<String> paymentDates = new ArrayList<String>();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Make a list of loan balances that aligns with payment dates
            ArrayList<String> balanceList = new ArrayList<String>();
            balanceList.add("" + loanBalance);

            // Get end payment date
            LocalDate endPaymentsDate = date.plusMonths(paymentDuration);
            String formattedEndPaymentsDate = endPaymentsDate.format(dateFormat);
            String formattedDate = date.format(dateFormat);
            LocalDate nextDate = date;
            String formattedNextDate = nextDate.format(dateFormat);
            
            boolean endPayments = false;
                do { 
                    // Start with today
                    paymentDates.add(formattedDate);
                    // Depending on frequency add dates to the list
                    if (i == 0) {
                        nextDate = date.plusMonths(paymentFrequency);
                        // System.out.println("Months");
                        // System.out.println(date);
                    } else {
                        nextDate = date.plusWeeks(paymentFrequency);
                        // System.out.println("Weeks");
                        // System.out.println(date);
                    }

                    // Every day do this until payments are reached
                    formattedNextDate = nextDate.format(dateFormat);
                    do {
                        date = date.plusDays(1);
                        formattedDate = date.format(dateFormat);

                            // Loop every day until reaching a payment date
                            // Calculate the payments
                                // Every day calculate monthly interest
                                // monthlyInterest = loanBalance * interestRate;
                                // System.out.println("Monthly interest: " + monthlyInterest);
                                // Add daily interest to current loan balance
                                double dailyInterest = (loanBalance * interestRate) / 365;
                                System.out.printf("$%f Added / Day", dailyInterest);
                                loanBalance += dailyInterest;
                                System.out.println("After daily interest accrual: " + loanBalance);
                                // Check if day is a payment date
                                if (formattedDate.equals(formattedNextDate)) {
                                    // Subtract payment amount from loan balance
                                    loanBalance -= paymentAmount;
                                    // Add new loan balance to list
                                    balanceList.add("" + loanBalance);
                                }

                        if (formattedDate.equals(formattedEndPaymentsDate)) {
                            endPayments = true;
                        }
                    } while (!formattedNextDate.equals(formattedDate));

                } while (!endPayments);
                System.out.println(paymentDates + "\n" + balanceList);     
    }
    
}
