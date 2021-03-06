package pt.isep.cms.products.shared;

import pt.isep.cms.batches.shared.Batche;

import javax.annotation.Nullable;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {
    public String id;
    public String name;
    public String descrip;
    public String price;
    public @Nullable
    String batch;
	
	public Product() {}
	
	public Product(String id, String name, String descrip, String price, @Nullable String batch) {
		this.id = id;
		this.name = name;
		this.descrip = descrip;
		this.price = price;
		this.batch = batch;

	}
	
	public ProductDetails getLightWeightProduct() {
	  return new ProductDetails(id, getFullDetails());
	}
	
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getFullDetails() { return name + ": " + descrip; }
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }
}
