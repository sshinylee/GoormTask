import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Cart {
	
	private Map<Product, Integer> items  = new HashMap<>();
	
	public Cart(String products) {
		
		String[] contents = products.split(" ");
		
		for(String s : contents) {
			String[] data = s.split(",");
			items.put(new Product(data[0], Integer.parseInt(data[1])), Integer.parseInt(data[2]));
		}
		
		
	}
	
	public Cart() {
	}
	
	public void addProduct(Product product, int count) {
		
		Integer currentCount = items.get(product);	
		items.put(product, currentCount != null ? currentCount+count : count);
	}
	
	public void removeProduct(Product product, int count) {
		
		Integer currentCount = items.get(product);	
		if(currentCount != null) {
			if(currentCount <= count)
				items.remove(product);
			else
				items.put(product, currentCount-count);
			}
	}
	
	public String showItems() {
		
		StringBuilder s = new StringBuilder();
		
		items.forEach((k, v) -> s.append("%s %d개%n".formatted(k.getName(), v)));
		return s.toString();
	}
	
	public String getItems() {
		StringBuilder s = new StringBuilder();
		
		items.forEach((k, v) -> s.append("%s,%d,%d%n".formatted(k.getName(), k.getPrice(), v)));
		return s.toString();
	}

}
