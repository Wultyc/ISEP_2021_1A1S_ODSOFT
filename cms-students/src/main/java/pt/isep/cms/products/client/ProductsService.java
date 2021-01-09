package pt.isep.cms.products.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.products.shared.Product;
import pt.isep.cms.products.shared.ProductDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("productsService")
public interface ProductsService extends RemoteService {
	
  Product addProduct(Product product);
  Boolean deleteProduct(String id); 
  ArrayList<ProductDetails> deleteProducts(ArrayList<String> ids);
  ArrayList<ProductDetails> getProductDetails();
  Product getProduct(String id);
  Product updateProduct(Product product);
}
