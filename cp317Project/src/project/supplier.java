package project;

// Encapsulation in Supplier class with private fields and public getters
class Supplier { 
	private int supplierID;
    private String name, address, phone, email;

    // Constructor initializing the Supplier object with supplied values
    public Supplier(int supplierID, String name, String address, String phone, String email) {
        this.supplierID = supplierID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        }

    // Getters providing controlled access to the object's properties
    public int getSupplierID() { 
    	return supplierID; 
    	}
    public String getName() { 
    	return name; 
    	}
    public String getAddress() { 
    	return address; 
    	}
    public String getPhone() { 
    	return phone; 
    	}
    public String getEmail() { 
    	return email; 
    	}
    }
