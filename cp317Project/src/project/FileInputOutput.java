package project;

/*
 * Group-11 Members:
 * "Asvith Kiruba" <Kiru4608@mylaurier.ca>
 * "Ethan Katsiroubas" <kats6218@mylaurier.ca>
 * "Geon Woo Park" <park1088@mylaurier.ca>
 * "Khaled KHALED" <khal8103@mylaurier.ca>
 */

// Code created by Ethan Katsiroubas and Khaled KHALED

// Import statements for IO operations
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Import statements for collections and utilities
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Abstraction in FileInputOutput's main application class through file operations
public class FileInputOutput {
	public static void main(String[] args) {
		String supplierFileName = "SupplierFile.txt";
		String productFileName = "ProductFile.txt";
		String inventoryFileName = "InventoryFile.txt";

		try {
			Map<Integer, Supplier> suppliers = supplierFileReader.readSuppliersFromFile(supplierFileName);
			
			List<Product> products = productFileReader.readProductsFromFile(productFileName);

			List<InventoryItem> inventoryItems = createInventoryItems(products, suppliers);
			inventoryItems.sort(Comparator.comparingInt(Product::getProductID));

			writeInventoryToFile(inventoryItems, inventoryFileName);
			System.out.println("Inventory data successfully written to " + inventoryFileName);
          	} 
		catch (IOException e) {
			System.err.println("Error reading or writing files: " + e.getMessage());
          	}
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
