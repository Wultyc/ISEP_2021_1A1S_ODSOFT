package pt.isep.cms.batches.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BatcheDetails implements Serializable {
  private String id;
  private String displayName;
  
  public BatcheDetails() {
    new BatcheDetails("0", "");
  }

  public BatcheDetails(String id, String displayName) {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
