import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		Set<Product> productSet = new HashSet<>();
		
		Product milk = new Product("우유", 2500);
		Product kimchi = new Product("김치", 3000);
		Product tomato = new Product("토마토", 5000);
		Product rice = new Product("쌀", 10000);
		Product onion = new Product("양파", 5500);
		
		productSet.add(milk);
		productSet.add(kimchi);
		productSet.add(tomato);
		productSet.add(rice);
		productSet.add(onion);
		
		productSet.add(rice);
		productSet.add(milk);
		productSet.add(onion);
		
		
		
		System.out.println("고유한 상품 목록:");
		for(Product product : productSet) {
			System.out.println("%s: %d원".formatted(product.getName(), product.getPrice()));
		}
		System.out.println("---");
		
		Cart myCart = new Cart();
		
		myCart.addProduct(onion, 3);
		myCart.addProduct(onion, 3);
		myCart.addProduct(kimchi, 1);
		myCart.addProduct(milk, 5);
		myCart.addProduct(tomato, 100);
		System.out.print(myCart.showItems());
		
		System.out.println("---");
		
		myCart.removeProduct(kimchi, 100);
		myCart.removeProduct(onion, 2);
		myCart.removeProduct(tomato, 100);
		myCart.addProduct(rice, 5);
		System.out.print(myCart.showItems());
		System.out.println("---");
		
		try {
		File file = new File("products.csv");
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(myCart.getItems());
		writer.flush();
		writer.close();
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String s;
		StringBuilder content = new StringBuilder();
		
		while((s = reader.readLine()) != null) {
			content.append(s).append(" ");
		}
		
		reader.close();
		
		Cart cartFromFile = new Cart(content.toString());
		System.out.println("파일에서 불러온 카트");
		System.out.print(cartFromFile.showItems());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
