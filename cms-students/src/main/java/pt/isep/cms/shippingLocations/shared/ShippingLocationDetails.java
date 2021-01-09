package pt.isep.cms.shippingLocations.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ShippingLocationDetails implements Serializable {
  private String id;
  private String displayName;
  
  public ShippingLocationDetails() {
    new ShippingLocationDetails("0", "");
  }

  public ShippingLocationDetails(String id, String displayName) {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
