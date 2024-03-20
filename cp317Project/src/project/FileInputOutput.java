
/*
 * Group-11 Members:
 * "Asvith Kiruba" <Kiru4608@mylaurier.ca>
 * "Ethan Katsiroubas" <kats6218@mylaurier.ca>
 * "Geon Woo Park" <park1088@mylaurier.ca>
 * "Khaled KHALED" <khal8103@mylaurier.ca>
 */

package project;

// Code created by Ethan Katsiroubas and Khaled KHALED

// Import statements for IO operations
import java.io.BufferedReader;       // Import BufferedReader class
import java.io.BufferedWriter;       // Import BufferedWriter class
import java.io.FileReader;           // Import FileReader class
import java.io.FileWriter;           // Import FileWriter class
import java.io.IOException;          // Import IOException class

// Import statements for collections and utilities
import java.util.ArrayList;          // Import ArrayList class
import java.util.Comparator;         // Import Comparator class
import java.util.HashMap;            // Import HashMap class
import java.util.List;               // Import List interface
import java.util.Map;                // Import Map interface
import java.util.stream.Collectors;   // Import Collectors class


// Abstraction in FileInputOutput's main application class through file operations
public class FileInputOutput {
	public static void main(String[] args) {
		String supplierFileName = "SupplierFile.txt";
		String productFileName = "ProductFile.txt";
		String inventoryFileName = "InventoryFile.txt";

		try {
			Map<Integer, Supplier> suppliers = readSuppliersFromFile(supplierFileName);
			List<Product> products = readProductsFromFile(productFileName);

			List<InventoryItem> inventoryItems = createInventoryItems(products, suppliers);
			inventoryItems.sort(Comparator.comparingInt(Product::getProductID));

			writeInventoryToFile(inventoryItems, inventoryFileName);
			System.out.println("Inventory data successfully written to " + inventoryFileName);
          	} 
		catch (IOException e) {
			System.err.println("Error reading or writing files: " + e.getMessage());
          	}
      	}
   
   
	// Reading supplier information from a file and maps it by supplier ID
    private static Map<Integer, Supplier> readSuppliersFromFile(String fileName) throws IOException {
        Map<Integer, Supplier> suppliers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int supplierID = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String address = parts[2].trim();
                String phone = parts[3].trim();
                String email = parts[4].trim();
                suppliers.put(supplierID, new Supplier(supplierID, name, address, phone, email));
                }
        	}
        	return suppliers;
    	}
    
    
    // Reading product information from a file and returns a list of Product objects
    private static List<Product> readProductsFromFile(String fileName) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length < 7) {  // Skips this line if it doesn't contain enough fields
                    continue;
                }
                int productID;
                if (!parts[0].isEmpty()) { // Checks if the product ID data is not empty
                    productID = Integer.parseInt(parts[0].trim());
                } else {
                    productID = 0;  // Sets a default value for the product ID, which is set to 0
                }
                String name = parts[1].trim();
                String description = parts[2].trim();
                double price = Double.parseDouble(parts[3].substring(1).trim()); // Assumes the prices are prefixed with $
                int quantity = Integer.parseInt(parts[4].trim());
                char status = parts[5].trim().charAt(0);
                int supplierID = Integer.parseInt(parts[6].trim());
                products.add(new Product(productID, name, description, price, quantity, status, supplierID));
            }
        }
        return products;
    }

   
    // Combining Product and Supplier information to create a list of InventoryItem objects
    private static List<InventoryItem> createInventoryItems(List<Product> products, Map<Integer, Supplier> suppliers) {
    	return products.stream().map(product -> {
    		Supplier supplier = suppliers.getOrDefault(product.getSupplierID(), new Supplier(0, "Unknown", "", "", ""));
    		return new InventoryItem(product, supplier.getName());
           	}).collect(Collectors.toList());
       	}
    
    
    // Writing the list of InventoryItem objects to a file
    private static void writeInventoryToFile(List<InventoryItem> inventoryItems, String fileName) throws IOException {
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
    		for (InventoryItem item : inventoryItems) {
    			bw.write(item.toString());
                bw.newLine();
                }
        	}
    	}
	}