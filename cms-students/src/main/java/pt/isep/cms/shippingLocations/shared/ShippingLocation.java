package pt.isep.cms.shippingLocations.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ShippingLocation implements Serializable {
    public String id;
    public String name;
    
	
	public ShippingLocation() {}
	
	public ShippingLocation(String id, String name) {
		this.id = id;
		this.name = name;
		
	}
	
	public ShippingLocationDetails getLightWeightShippingLocation() {
	  return new ShippingLocationDetails(id, getFullDetails());
	}
	
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFullDetails() { return name; }
}
