package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Reading product information from a file and returns a list of Product objects
class productFileReader {
    public static List<Product> readProductsFromFile(String fileName) throws IOException {
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
}
