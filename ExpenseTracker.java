import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpenseTracker {
    private List<Expense> expenses = new ArrayList();
    private int nextExpenseId = 1; // Unique identifier for expenses

    public void addExpense(String description, double amount, String category, LocalDate date) {
        Expense expense = new Expense(nextExpenseId, description, amount, category, date);
        expenses.add(expense);
        nextExpenseId++;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalSpending() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public Optional<Expense> getExpenseById(int id) {
        return expenses.stream().filter(expense -> expense.getId() == id).findFirst();
    }

    public void editExpense(int id, String newDescription, double newAmount, String newCategory, LocalDate newDate) {
        Optional<Expense> optionalExpense = getExpenseById(id);
        optionalExpense.ifPresent(expense -> {
            expense.setDescription(newDescription);
            expense.setAmount(newAmount);
            expense.setCategory(newCategory);
            expense.setDate(newDate);
        });
    }

    public void deleteExpense(int id) {
        Optional<Expense> optionalExpense = getExpenseById(id);
        optionalExpense.ifPresent(expense -> expenses.remove(expense));
    }
}
