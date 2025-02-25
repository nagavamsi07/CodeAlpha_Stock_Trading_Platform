import java.util.HashMap;
import java.util.Scanner;

public class StockTradingPlatform {
    private HashMap<String, Double> marketData = new HashMap<>();
    private HashMap<String, Integer> portfolio = new HashMap<>();
    private double balance = 10000.0;
    
    public StockTradingPlatform() {
        marketData.put("AAPL", 150.0);
        marketData.put("GOOGL", 2800.0);
        marketData.put("AMZN", 3400.0);
        marketData.put("TSLA", 700.0);
    }
    
    public void buyStock(String stock, int quantity) {
        if (marketData.containsKey(stock)) {
            double price = marketData.get(stock) * quantity;
            if (balance >= price) {
                balance -= price;
                portfolio.put(stock, portfolio.getOrDefault(stock, 0) + quantity);
                System.out.println("Bought " + quantity + " shares of " + stock);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Stock not found!");
        }
    }
    
    public void sellStock(String stock, int quantity) {
        if (portfolio.containsKey(stock) && portfolio.get(stock) >= quantity) {
            double price = marketData.get(stock) * quantity;
            balance += price;
            portfolio.put(stock, portfolio.get(stock) - quantity);
            if (portfolio.get(stock) == 0) {
                portfolio.remove(stock);
            }
            System.out.println("Sold " + quantity + " shares of " + stock);
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }
    
    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (String stock : portfolio.keySet()) {
            System.out.println(stock + ": " + portfolio.get(stock) + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
    
    public static void main(String[] args) {
        StockTradingPlatform platform = new StockTradingPlatform();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                System.out.print("Enter stock symbol: ");
                String stock = scanner.next().toUpperCase();
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                platform.buyStock(stock, quantity);
            } else if (choice == 2) {
                System.out.print("Enter stock symbol: ");
                String stock = scanner.next().toUpperCase();
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                platform.sellStock(stock, quantity);
            } else if (choice == 3) {
                platform.displayPortfolio();
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }
}
