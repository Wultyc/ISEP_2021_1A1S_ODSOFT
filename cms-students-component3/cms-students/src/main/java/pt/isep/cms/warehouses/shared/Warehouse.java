package pt.isep.cms.warehouses.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Warehouse implements Serializable {
    public String id;
    public String name;
    public String address;
    public String emailAddress;
	
	public Warehouse() {}
	
	public Warehouse(String id, String name, String address, String emailAddress) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.emailAddress = emailAddress;
	}
	
	public WarehouseDetails getLightWeightWarehouse() {
	  return new WarehouseDetails(id, getFullDetails());
	}
	
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getFullDetails() { return name + ": " + address; }
}