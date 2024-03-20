package project;

//Encapsulation in Product class with private fields and public getters
class Product {
	private int productID;
	private String name, description;
	private double price;
	private int quantity;
	private char status;
	private int supplierID;
	
	// Constructor initializing the Product object with supplied values
	public Product(int productID, String name, String description, double price, int quantity, char status, int supplierID) {
		this.productID = productID;
	    this.name = name;
	    this.description = description;
	    this.price = price;
	    this.quantity = quantity;
	    this.status = status;
	    this.supplierID = supplierID;
	    }
	
	// Getters for each property
	public int getProductID() { 
		return productID; 
		}
	public String getName() { 
		return name; 
		}
	public String getDescription() { 
		return description; 
		}
	public double getPrice() { 
		return price; 
		}
	public int getQuantity() { 
		return quantity; 
		}
	public char getStatus() { 
		return status; 
		}
	public int getSupplierID() { 
		return supplierID; 
		}
	}
