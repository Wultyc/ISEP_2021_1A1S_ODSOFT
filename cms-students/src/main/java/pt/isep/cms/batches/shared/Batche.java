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
    public Date manDate;
    public @Nullable
    Warehouse warehouse;
	
	public Batche() {}
	
	public Batche(String id, String name, String descrip, Date manDate, Warehouse warehouse) {
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
    public Date getManDate() { return manDate; }
    public void setManDate(Date manDate) { this.manDate = manDate; }
    public String getFullDetails() { return name + ": " + descrip; }
    public Warehouse getWarehouse() { return warehouse; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; };

}
