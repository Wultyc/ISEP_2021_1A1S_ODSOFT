package pt.isep.cms.warehouses.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Warehouse implements Serializable {
    public String id;
    public String name;
    public String totalCap;
    public String location;
	
	public Warehouse() {}
	
	public Warehouse(String id, String name, String totalCap, String location) {
		this.id = id;
		this.name = name;
		this.totalCap = totalCap;
		this.location = location;
	}
	
	public WarehouseDetails getLightWeightWarehouse() {
	  return new WarehouseDetails(id, getFullDetails());
	}
	
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTotalCap() { return totalCap; }
    public void setTotalCap(String totalCap) { this.totalCap = totalCap; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getFullDetails() { return name + ": " + totalCap; }
}