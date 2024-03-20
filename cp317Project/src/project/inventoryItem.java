package project;

//Inheritance in InventoryItem class (extends Product) for adding supplier information
class InventoryItem extends Product { 
	private String supplierName;

 // Constructor combining Product information with Supplier name, utilizing the super() call to set Product properties
 public InventoryItem(Product product, String supplierName) {
 	super(product.getProductID(), product.getName(), product.getDescription(), 
           product.getPrice(), product.getQuantity(), product.getStatus(), product.getSupplierID());
     this.supplierName = supplierName;
     }

 // Polymorphism in InventoryItem class (overrides toString) for custom representation.
 @Override
 public String toString() { 
 	return String.format("ProductID: %d, Name: %s, Quantity: %d, Price: $%.2f, Status: %c, Supplier: %s", 
                          getProductID(), getName(), getQuantity(), getPrice(), getStatus(), supplierName);
 	}
 }
