package pt.isep.cms.products.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.products.shared.Product;
import pt.isep.cms.products.shared.ProductDetails;

import java.util.ArrayList;

public interface ProductsServiceAsync {

  public void addProduct(Product product, AsyncCallback<Product> callback);
  public void deleteProduct(String id, AsyncCallback<Boolean> callback);
  public void deleteProducts(ArrayList<String> ids, AsyncCallback<ArrayList<ProductDetails>> callback);
  public void getProductDetails(AsyncCallback<ArrayList<ProductDetails>> callback);
  public void getProduct(String id, AsyncCallback<Product> callback);
  public void updateProduct(Product product, AsyncCallback<Product> callback);
}

