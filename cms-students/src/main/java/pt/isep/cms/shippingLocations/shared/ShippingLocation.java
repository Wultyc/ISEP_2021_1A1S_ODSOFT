package pt.isep.cms.shippingLocations.shared;

import pt.isep.cms.warehouses.shared.Warehouse;

import javax.annotation.Nullable;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ShippingLocation implements Serializable {
    public String id;
    public String name;
    public String warehouses;
    // @Nullable Warehouse
    // warehouse;
    
	
	public ShippingLocation() {}
	
	public ShippingLocation(String id, String name, String warehouses /*Warehouse warehouse */) {
		this.id = id;
		this.name = name;
		this.warehouses = warehouses;
		// this.warehouse = warehouse;
		
	}
	
	public ShippingLocationDetails getLightWeightShippingLocation() {
	  return new ShippingLocationDetails(id, getFullDetails());
	}
	
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFullDetails() { return name; }
    public String getWarehouses() { return warehouses; }
    public void setWarehouses(String warehouses) { this.warehouses = warehouses; }
  //  public Warehouse getWarehouse() { return warehouse; }
   // public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }
}
