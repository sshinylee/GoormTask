public class Product {
	
	private static int idCount = 0;
	
	private int id = idCount++;
	private String name;
	private int price;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Product && ((Product) obj).name == this.name) {
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return id;
	}

}
