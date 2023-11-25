import java.util.Scanner;
import java.time.LocalDate;
import java.util.Optional;

public class main {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "-----------------------------------------------------------");
            System.out.println("               Welcome to the Expense Tracker");
            System.out.println("-----------------------------------------------------------");
            System.out.println("                  Main Menu");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Edit Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. View Total Spending");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Expense Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Expense Amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Expense Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Expense Date (YYYY-MM-DD): ");
                    String dateString = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateString);

                    expenseTracker.addExpense(description, amount, category, date);
                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    System.out.println("Expenses:");
                    for (Expense expense : expenseTracker.getExpenses()) {
                        System.out.println(expense);
                    }
                    break;

                case 3:
                    System.out.print("Enter the ID of the expense you want to edit: ");
                    int idToEdit = scanner.nextInt();
                    Optional<Expense> expenseToEdit = expenseTracker.getExpenseById(idToEdit);
                    if (expenseToEdit.isPresent()) {
                        System.out.print("Enter New Description: ");
                        String newDescription = scanner.nextLine();
                        System.out.print("Enter New Amount: $");
                        double newAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Enter New Category: ");
                        String newCategory = scanner.nextLine();
                        System.out.print("Enter New Date (YYYY-MM-DD): ");
                        String newDateString = scanner.nextLine();
                        LocalDate newDate = LocalDate.parse(newDateString);

                        expenseTracker.editExpense(idToEdit, newDescription, newAmount, newCategory, newDate);
                        System.out.println("Expense edited successfully!");
                    } else {
                        System.out.println("Expense with ID " + idToEdit + " not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter the ID of the expense you want to delete: ");
                    int idToDelete = scanner.nextInt();
                    Optional<Expense> expenseToDelete = expenseTracker.getExpenseById(idToDelete);
                    if (expenseToDelete.isPresent()) {
                        expenseTracker.deleteExpense(idToDelete);
                        System.out.println("Expense deleted successfully!");
                    } else {
                        System.out.println("Expense with ID " + idToDelete + " not found.");
                    }
                    break;

                case 5:
                    System.out.println("Total Spending: $" + expenseTracker.getTotalSpending());
                    break;

                case 6:
                    System.out.println("Exiting Expense Tracker. Have a great day!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
