import java.util.ArrayList;
import java.util.List;

public class Fridge {
    private int balance;
    private ArrayList<String> foodItems = new ArrayList<>();

    public Fridge(int initialBalance) {
        this.balance = initialBalance;
    }

    public void addFood(String item, int cost) {
        if (balance - cost < 0 || item == null || item == "Ice Cream") {
            System.out.println("Error");
        } else {
            balance -= cost;
            foodItems.add(item);
            System.out.println("Item " + item + " has been added to the fridge.");
        }
    }

    public void getFood(String item) {
        if (foodItems.contains(item)) {
            foodItems.remove(item);
            System.out.println("Item " + item + " has been removed from the fridge.");
        } else {
            System.out.println("Error");
        }
    }

    public void checkStatus() {
        System.out.println("Food items:");
        if (foodItems.size() == 0) {
            System.out.println("(none)");
        } else {
            for (String food : foodItems) {
                System.out.println(food);
            }
        }
        System.out.println("Balance: €" + balance);
    }
}
