import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoicePartTwo {

	private static List<Product> products = new ArrayList<Product>();

	public static void addProducts() {

		products.add(new Product("Bison Sweater", Double.valueOf(55.99)));
		products.add(new Product("Bison Tee", Double.valueOf(14.99)));
		products.add(new Product("Bison Hoodie", Double.valueOf(23.99)));
		products.add(new Product("Bison Bumpersticker", Double.valueOf(4.99)));

	}

	public static void userOptions() {

		Scanner input = new Scanner(System.in);
		String answer = "";
		List<Product> purchases = new ArrayList<>();

		do {
			System.out.println("What do you want");
			System.out.println("1) Add Purchase 2) change Purchase 3) show purchase 4) finish transaction");
			answer = input.nextLine();

			if (answer.equals("1")) {

				addPurchase(purchases, input);

			} else if (answer.equals("2")) {

				changePurchase(purchases, input);

			} else if (answer.equals("3")) {
				showPurchases(purchases);

			} else if (answer.equals("4")) {

				showTotal(purchases);
			}

		} while (!answer.equals("4"));

	}

	public static void printMenu() {

		int i = 0;
		for (Product element : products) {

			System.out.println("Enter " + i + " for " + element.getName() + " with price " + element.getPrice());
			i++;
		}

	}

	public static void addPurchase(List<Product> purchases, Scanner scanner) {

		int selectedProduct = 100;
		printMenu();
		selectedProduct = scanner.nextInt();

		if (selectedProduct < 0 || selectedProduct > products.size() - 1) {
			System.out.println("Invalid Product entered");
		} else {
			purchases.add(products.get(selectedProduct));
		}
	}

	private static void changePurchase(List<Product> purchases, Scanner input) {
		int selectedProduct = 100;
		int replacedProduct = 100;
		System.out.println("Enter the product number to be replaced");
		replacedProduct = input.nextInt();
		System.out.println("Enter the product to be replaced with");
		selectedProduct = input.nextInt();

		if (selectedProduct < 0 || selectedProduct > products.size() - 1) {
			System.out.println("Invalid Product entered");
		} else {

			if (replacedProduct < 0 || replacedProduct > purchases.size() - 1) {
				System.out.println("Invalid Product selected for replacement");
			} else {
				purchases.set(replacedProduct, products.get(selectedProduct));
			}
		}

	}

	private static void showPurchases(List<Product> purchases) {

		System.out.println("\n Following products in purchase list so far:");
		int i = 0;
		for (Product element : purchases) {
			System.out.println(i + ") " + element.getName());
			i++;
		}

	}

	private static void showTotal(List<Product> purchases) {

		double total = purchases.stream().map(element -> element.getPrice()).reduce(0.0,
				(subtotal, element) -> subtotal + element);
		System.out.println("Total price: " + total);
	}

	public static void main(String[] args) {
		addProducts();
		userOptions();
	}

}

class Product {

	private String name;
	private Double price;

	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
