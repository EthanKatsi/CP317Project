package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Reading supplier information from a file and maps it by supplier ID
class supplierFileReader {
    public static Map<Integer, Supplier> readSuppliersFromFile(String fileName) throws IOException {
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
}
