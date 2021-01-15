package pt.isep.cms.warehouses.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.warehouses.client.WarehousesService;
import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;
import pt.isep.cms.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


@SuppressWarnings("serial")
public class WarehousesServiceImpl extends RemoteServiceServlet implements
    WarehousesService {

  private Connection connection = new DBConnection().getConnection();
  private HashMap<String, Warehouse> warehouses = new HashMap<>();

  public WarehousesServiceImpl() {
    getWarehouses();
  }
  
  private void getWarehouses() {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from WAREHOUSE"
      );
      ResultSet rSet = ps.executeQuery();
      while (rSet.next()) {
        Integer id = rSet.getInt("id");
        String name = rSet.getString("name");
        Integer totalCap = rSet.getInt("totalCapacity");
        warehouses.put(id.toString(), new Warehouse(id.toString(), name, totalCap.toString()));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting warehouses");
      sqle.printStackTrace();
    }
  }
  
  public Warehouse addWarehouse(Warehouse warehouse) {

    return warehouse;
  }

  public Warehouse updateWarehouse(Warehouse warehouse) {
	  String lid=warehouse.getId();
    warehouses.remove(warehouse.getId());
    warehouses.put(warehouse.getId(), warehouse); 
    return warehouse;
  }

  public Boolean deleteWarehouse(String id) {
    warehouses.remove(id);
    return true;
  }
  
  public ArrayList<WarehouseDetails> deleteWarehouses(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deleteWarehouse(ids.get(i));
    }
    
    return getWarehouseDetails();
  }
  
  public ArrayList<WarehouseDetails> getWarehouseDetails() {
    ArrayList<WarehouseDetails> warehouseDetails = new ArrayList<WarehouseDetails>();
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from WAREHOUSE"
      );
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String totalCap = rs.getString("totalCapacity");

        warehouseDetails.add(
                new Warehouse(id.toString(), name, totalCap.toString()).getLightWeightWarehouse()
        );
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting Warehouses");
      sqle.printStackTrace();
    }
    return warehouseDetails;
  }

  public Warehouse getWarehouse(String id) {

    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from WAREHOUSE where id=?"
      );
      ps.setString(1, id);
      ResultSet rSet = ps.executeQuery();
      while (rSet.next()) {
        Integer id1 = rSet.getInt("id");
        String name = rSet.getString("name");
        String totalCap = rSet.getString("totalCapacity");

        warehouses.put(id1.toString(), new Warehouse(id1.toString(), name, totalCap.toString()));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while retrieving teacher");
      sqle.printStackTrace();
    }
    return warehouses.get(id);

  }
}
