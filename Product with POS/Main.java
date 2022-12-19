import java.util.Scanner;

class Product {
  int productID;
  String productName;
  double price;
  int quantity;

  public Product(int productID, String productName, double price, int quantity) {
    this.productID = productID;
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Product[] product_list = {
      new Product(1, "Shampoo", 120.50, 5),
      new Product(2, "Toothpaste", 75.25, 10),
      new Product(3, "Soap", 45.00, 2)
    };

    Product[] cart = new Product[100];
    int cartIndex = 0;

    while (true) {
      System.out.println("POS Menu:");
      System.out.println("1. Show Products");
      System.out.println("2. Add to Cart by ID");
      System.out.println("3. Show Cart");
      System.out.println("4. Checkout");
      System.out.println("5. Exit");

      int action = scanner.nextInt();

      if (action == 1) {
        // Show all available products
        for (Product product : product_list) {
          System.out.println(product.productID + ": " + product.productName + " - $" + product.price + " (" + product.quantity + " in stock)");
        }
      } else if (action == 2) {
        // Add a product to the cart by inputting the product ID
        System.out.print("Enter product ID: ");
        int productID = scanner.nextInt();
        Product product = null;
        for (Product p : product_list) {
          if (p.productID == productID) {
            product = p;
            break;
          }
        }
        if (product != null) {
          cart[cartIndex++] = product;
          System.out.println(product.productName + " added to cart.");
        } else {
          System.out.println("Invalid product ID.");
        }
      } else if (action == 3) {
        // Show all added products in the cart
        if (cartIndex == 0) {
          System.out.println("Cart is empty.");
        } else {
          for (int i = 0; i < cartIndex; i++) {
            Product product = cart[i];
            System.out.println(product.productID + ": " + product.productName + " - $" + product.price + " x " + product.quantity);
          }
        }
      } else if (action == 4) {
        // Compute total by multiplying price of the item to the quantity in the cart of all the products
        double total = 0;
        for (int i = 0; i < cartIndex; i++) {
          Product product = cart[i];
          total += product.price * product.quantity;
        }
        System.out.println("Total: $" + total);
      } else if (action == 5) {
        // Exit program
        break;
      } else {
        System.out.println("Invalid action.");
      }
    }
  }
}
