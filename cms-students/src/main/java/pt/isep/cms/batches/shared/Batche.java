package pt.isep.cms.batches.shared;

import pt.isep.cms.warehouses.shared.Warehouse;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Batche implements Serializable {
    public String id;
    public String name;
    public String descrip;
    public String manDate;
    public @Nullable
    String warehouse;
	
	public Batche() {}
	
	public Batche(String id, String name, String descrip, String manDate, String warehouse) {
		this.id = id;
		this.name = name;
		this.descrip = descrip;
		this.manDate = manDate;
		this.warehouse = warehouse;
	}
	
	public BatcheDetails getLightWeightBatche() {
	  return new BatcheDetails(id, getFullDetails());
	}
	
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }
    public String getManDate() { return manDate; }
    public void setManDate(String manDate) { this.manDate = manDate; }
    public String getFullDetails() { return name + ": " + descrip; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; };

}
