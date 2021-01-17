package pt.isep.cms.products.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;

import pt.isep.cms.DBConnection.DBConnection;
import pt.isep.cms.batches.server.BatchesServiceImpl;
import pt.isep.cms.batches.shared.Batche;
import pt.isep.cms.products.client.ProductsService;
import pt.isep.cms.products.shared.Product;
import pt.isep.cms.products.shared.ProductDetails;
import pt.isep.cms.batches.server.BatchesServiceImpl;
import pt.isep.cms.batches.shared.Batche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class ProductsServiceImpl extends RemoteServiceServlet implements
    ProductsService {
      
  private final HashMap<String, Product> products = new HashMap<String, Product>();
  private Connection connection = new DBConnection().getConnection();
  public BatchesServiceImpl batchesService = new BatchesServiceImpl();

  public ProductsServiceImpl() {
    initProducts();
  }
  
  private void initProducts() {
    // TODO: Create a real UID for each product
    //
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from PRODUCT"
      );
      ResultSet rSet = ps.executeQuery();
      while (rSet.next()) {
        Integer id = rSet.getInt("id");
        String name = rSet.getString("name");
        String description = rSet.getString("description");
        Integer price = rSet.getInt("price");
        Integer batchId = rSet.getInt("batchId");

        products.put(id.toString(), new Product(id.toString(), name, description, price.toString(), batchId.toString()));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting warehouses");
      sqle.printStackTrace();
    }
  }
  
  public Product addProduct(Product product) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "insert into PRODUCT values (null,?,?, ?, ?)"
      );
      ps.setString(1, product.getName());
      ps.setString(2, product.getDescrip());
      ps.setInt(3, Integer.parseInt(product.getPrice()));
      ps.setInt(4, Integer.parseInt(product.getBatch()));


      ps.executeUpdate();

      return product;
    } catch (SQLException sqle) {
      System.out.println("Error while creating warehouse");
      sqle.printStackTrace();
    }
    return null;
  }

  public Product updateProduct(Product product) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "update PRODUCT set name=? ,description=?, price=? where id=?"
      );
      ps.setString(1, product.getName());
      ps.setString(2, product.getDescrip());
      ps.setInt(3, Integer.parseInt(product.getPrice()));
      ps.setInt(4, Integer.parseInt(product.getId()));

      ps.executeUpdate();

      return product;
    } catch (SQLException sqle) {
      System.out.println("Error while updating warehouse");
      sqle.printStackTrace();
    }
    return null;
  }

  public Boolean deleteProduct(String id) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "delete from PRODUCT where id=?"
      );
      ps.setInt(1, Integer.parseInt(id));

      return ps.executeUpdate() != 0;
    } catch (SQLException sqle) {
      System.out.println("Error while deleting product");
      sqle.printStackTrace();
    }
    return null;
  }
  
  public ArrayList<ProductDetails> deleteProducts(ArrayList<String> ids) {

    for (String id : ids) {
      deleteProduct(id);
    }
    return getProductDetails();
  }
  
  public ArrayList<ProductDetails> getProductDetails() {

    ArrayList<ProductDetails> productDetails = new ArrayList<ProductDetails>();
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from PRODUCT"
      );
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer price = rs.getInt("price");
        Integer batchId = rs.getInt("batchId");

        Batche batch = batchesService.getBatche(batchId.toString());
        Product product = new Product(id.toString(), name, description, price.toString(), batchId.toString());
        productDetails.add(
                new ProductDetails(id.toString(), product.getFullDetails())
        );
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting Warehouses");
      sqle.printStackTrace();
    }
    return productDetails;
  }

  public Product getProduct(String id) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from PRODUCT where id=?"
      );
      ps.setString(1, id);
      ResultSet rSet = ps.executeQuery();
      while (rSet.next()) {
        String name = rSet.getString("name");
        String description = rSet.getString("description");
        Integer price = rSet.getInt("price");
        Integer batchId = rSet.getInt("batchId");
        products.put(id, new Product(id.toString(), name, description, price.toString(), batchId.toString()));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while retrieving teacher");
      sqle.printStackTrace();
    }
    return products.get(id);

  }
}
