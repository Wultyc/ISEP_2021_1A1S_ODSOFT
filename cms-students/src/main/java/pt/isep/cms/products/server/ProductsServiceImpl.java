package pt.isep.cms.products.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.products.client.ProductsService;
import pt.isep.cms.products.shared.Product;
import pt.isep.cms.products.shared.ProductDetails;

@SuppressWarnings("serial")
public class ProductsServiceImpl extends RemoteServiceServlet implements
    ProductsService {

  private static final String[] productsNameData = new String[] {
      "Product X", "Product Y", "Product Z"};
  
  private final String[] productsDescrip = new String[] {
      "5000", "2000", "3000"};
  
  private final String[] productsPrice = new String[] {
      "10", "20", "30"};
      
  private final HashMap<String, Product> products = new HashMap<String, Product>();

  public ProductsServiceImpl() {
    initProducts();
  }
  
  private void initProducts() {
    // TODO: Create a real UID for each product
    //
    for (int i = 0; i < productsNameData.length && i < productsDescrip.length && i < productsPrice.length; ++i) {
      Product product = new Product(String.valueOf(i), productsNameData[i], productsDescrip[i], productsPrice[i]);
      products.put(product.getId(), product); 
    }
  }
  
  public Product addProduct(Product product) {
    product.setId(String.valueOf(products.size()));
    products.put(product.getId(), product); 
    return product;
  }

  public Product updateProduct(Product product) {
	  String lid=product.getId();
    products.remove(product.getId());
    products.put(product.getId(), product); 
    return product;
  }

  public Boolean deleteProduct(String id) {
    products.remove(id);
    return true;
  }
  
  public ArrayList<ProductDetails> deleteProducts(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deleteProduct(ids.get(i));
    }
    
    return getProductDetails();
  }
  
  public ArrayList<ProductDetails> getProductDetails() {
    ArrayList<ProductDetails> productDetails = new ArrayList<ProductDetails>();
    
    Iterator<String> it = products.keySet().iterator();
    while(it.hasNext()) { 
      Product product = products.get(it.next());          
      productDetails.add(product.getLightWeightProduct());
    }
    
    return productDetails;
  }

  public Product getProduct(String id) {
    return products.get(id);
  }
}
