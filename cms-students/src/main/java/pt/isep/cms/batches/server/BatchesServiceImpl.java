package pt.isep.cms.batches.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.DBConnection.DBConnection;
import pt.isep.cms.batches.client.*;
import pt.isep.cms.batches.shared.Batche;
import pt.isep.cms.batches.shared.*;
import pt.isep.cms.products.shared.Product;
import pt.isep.cms.products.shared.ProductDetails;
import pt.isep.cms.warehouses.server.WarehousesServiceImpl;
import pt.isep.cms.warehouses.shared.Warehouse;

@SuppressWarnings("serial")
public class BatchesServiceImpl extends RemoteServiceServlet implements
    BatchesService {

  private final HashMap<String, Batche> batches = new HashMap<String, Batche>();
  private Connection connection = new DBConnection().getConnection();
  public WarehousesServiceImpl warehousesService = new WarehousesServiceImpl();

  public BatchesServiceImpl() {
    initBatches();
  }
  
  private void initBatches() {
    // TODO: Create a real UID for each batche
    //
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from BATCH"
      );
      ResultSet rSet = ps.executeQuery();
      while (rSet.next()) {
        Integer id = rSet.getInt("id");
        String name = rSet.getString("name");
        String description = rSet.getString("description");
        String mandDate = rSet.getString("mandDate");
        Integer warehouseId = rSet.getInt("wareId");

        Warehouse warehouse = warehousesService.getWarehouse(warehouseId.toString());

        batches.put(id.toString(), new Batche(id.toString(), name, description, mandDate, warehouse));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting batch");
      sqle.printStackTrace();
    }
  }
  
  public Batche addBatche(Batche batche) {
    try {
      System.out.println(batche.getName());


      PreparedStatement ps = connection.prepareStatement(
              "insert into BATCH values (null,?,?, ?, ?)"
      );
      ps.setString(1, batche.getName());
      ps.setString(2, batche.getDescrip());
      ps.setString(3, batche.getManDate());
      ps.setInt(4, 2/*Integer.parseInt(batche.getWarehouse().id)*/);

      ps.executeUpdate();

      return batche;
    } catch (SQLException sqle) {
      System.out.println("Error while creating batch");
      sqle.printStackTrace();
    }
    return null;
  }

  public Batche updateBatche(Batche batch) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "update BATCH set name=? ,description=?, mandDate=? where id=?"
      );
      ps.setString(1, batch.getName());
      ps.setString(2, batch.getDescrip());
      ps.setString(3, batch.getManDate());

      System.out.println(ps);

      ps.executeUpdate();

      return batch;
    } catch (SQLException sqle) {
      System.out.println("Error while updating batch");
      sqle.printStackTrace();
    }
    return null;
  }

  public Boolean deleteBatche(String id) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "delete from BATCH where id=?"
      );
      ps.setInt(1, Integer.parseInt(id));

      return ps.executeUpdate() != 0;
    } catch (SQLException sqle) {
      System.out.println("Error while deleting batch");
      sqle.printStackTrace();
    }
    return null;
  }
  
  public ArrayList<BatcheDetails> deleteBatches(ArrayList<String> ids) {

    for (String id : ids) {
      deleteBatche(id);
    }
    return getBatcheDetails();
  }
  
  public ArrayList<BatcheDetails> getBatcheDetails() {
    ArrayList<BatcheDetails> batcheDetails = new ArrayList<BatcheDetails>();
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from BATCH"
      );
      ResultSet rSet = ps.executeQuery();

      while (rSet.next()) {
        Integer id = rSet.getInt("id");
        String name = rSet.getString("name");
        String description = rSet.getString("description");
        String mandDate = rSet.getString("mandDate");
        Integer warehouseId = rSet.getInt("wareId");

        Warehouse warehouse = warehousesService.getWarehouse(warehouseId.toString());

        Batche batch = new Batche(id.toString(), name, description, mandDate, warehouse);
        batcheDetails.add(
                new BatcheDetails(id.toString(), batch.getFullDetails())
        );
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting Warehouses");
      sqle.printStackTrace();
    }
    return batcheDetails;
  }

  public Batche getBatche(String id) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from BATCH where id=?"
      );
      ps.setString(1, id);
      ResultSet rSet = ps.executeQuery();
      while (rSet.next()) {
        String name = rSet.getString("name");
        String description = rSet.getString("description");
        String mandDate = rSet.getString("mandDate");
        Integer warehouseId = rSet.getInt("wareId");

        Warehouse warehouse = warehousesService.getWarehouse(warehouseId.toString());

        batches.put(id, new Batche(id.toString(), name, description, mandDate, warehouse));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while retrieving teacher");
      sqle.printStackTrace();
    }
    return batches.get(id);
  }
}
