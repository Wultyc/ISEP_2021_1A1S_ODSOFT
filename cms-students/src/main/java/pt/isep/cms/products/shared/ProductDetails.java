package pt.isep.cms.products.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductDetails implements Serializable {
  private String id;
  private String displayName;
  
  public ProductDetails() {
    new ProductDetails("0", "");
  }

  public ProductDetails(String id, String displayName) {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
