package pt.isep.cms.shippingLocations.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.*;
import java.util.*;

import pt.isep.cms.DBConnection.DBConnection;
import pt.isep.cms.shippingLocations.client.*;
import pt.isep.cms.shippingLocations.shared.ShippingLocation;
import pt.isep.cms.shippingLocations.shared.*;
import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

@SuppressWarnings("serial")
public class ShippingLocationsServiceImpl extends RemoteServiceServlet implements
    ShippingLocationsService {



  private Connection connection = new DBConnection().getConnection();
  private final HashMap<String, ShippingLocation> shippingLocations = new HashMap<String, ShippingLocation>();

  public ShippingLocationsServiceImpl() {
    initShippingLocations();
  }
  
  private void initShippingLocations() {
    // TODO: Create a real UID for each shippingLocation
    //
    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from SHIPPING_LOCATION"
      );
      ResultSet rSet = ps.executeQuery();

      while (rSet.next()) {
        List<String> list = new ArrayList<String>();
        String concat;
        PreparedStatement ps2 = connection.prepareStatement(
                "SELECT * FROM WAREHOUSE_LOCATION WHERE locationId=?"
        );

        Integer id = rSet.getInt("id");
        String name = rSet.getString("name");
        ps2.setString(1, id.toString());

        ResultSet rSet2 = ps2.executeQuery();
        while (rSet2.next()){

            Integer idWareLocation = rSet2.getInt("warehouseId");
            list.add(idWareLocation.toString());
        }
        concat = String.join(",", list);
        shippingLocations.put(id.toString(), new ShippingLocation(id.toString(), name, concat));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting shipping location");
      sqle.printStackTrace();
    }
  }
  
  public ShippingLocation addShippingLocation(ShippingLocation shippingLocation) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "insert into SHIPPING_LOCATION values (null,?)", Statement.RETURN_GENERATED_KEYS
      );
      ps.setString(1, shippingLocation.getName());
      ps.executeUpdate();
      ResultSet rs_key = ps.getGeneratedKeys();
      rs_key.next();
      String is = rs_key.getString(1);

      List<String> warehouseList = Arrays.asList(shippingLocation.getWarehouses().split(","));
      for (int i = 0; i < warehouseList.size(); i++) {
        PreparedStatement ps2 = connection.prepareStatement(
                "insert into WAREHOUSE_LOCATION values (null,?,?)"
        );
                ps2.setString(1, warehouseList.get(i).toString());
                ps2.setString(2, rs_key.getString(1));
                ps2.executeUpdate();
      }
      return shippingLocation;
    } catch (SQLException sqle) {
      System.out.println("Error while creating shipping location");
      sqle.printStackTrace();
    }
    return null;
  }

  public ShippingLocation updateShippingLocation(ShippingLocation shippingLocation) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "update SHIPPING_LOCATION set name=? where id=?"
      );
      ps.setString(1, shippingLocation.getName());
      ps.setInt(2, Integer.parseInt(shippingLocation.getId()));
      ps.executeUpdate();

      return shippingLocation;
    } catch (SQLException sqle) {
      System.out.println("Error while updating shipping location");
      sqle.printStackTrace();
    }
    return null;
  }

  public Boolean deleteShippingLocation(String id) {
    try {
      PreparedStatement ps1 = connection.prepareStatement(
              "delete from WAREHOUSE_LOCATION where locationId=?"
      );
      ps1.setInt(1, Integer.parseInt(id));
      ps1.executeUpdate();
      PreparedStatement ps = connection.prepareStatement(
              "delete from SHIPPING_LOCATION where id=?"
      );
      ps.setInt(1, Integer.parseInt(id));

      return ps.executeUpdate() != 0;
    } catch (SQLException sqle) {
      System.out.println("Error while deleting shipping location");
      sqle.printStackTrace();
    }
    return null;
  }
  
  public ArrayList<ShippingLocationDetails> deleteShippingLocations(ArrayList<String> ids) {

    for (String id : ids) {
      deleteShippingLocation(id);
    }
    return getShippingLocationDetails();
  }


  
  public ArrayList<ShippingLocationDetails> getShippingLocationDetails() {
    ArrayList<ShippingLocationDetails> shippingLocationDetails = new ArrayList<ShippingLocationDetails>();
    List<String> list = new ArrayList<String>();

    try {
      PreparedStatement ps = connection.prepareStatement(
              "select *  from SHIPPING_LOCATION"
      );
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");

        PreparedStatement ps2 = connection.prepareStatement(
                "select * from WAREHOUSE_LOCATION"
        );
        ResultSet rs2 = ps2.executeQuery();
        while(rs2.next()) {
          Integer warId = rs2.getInt("warehouseId");
          list.add(warId.toString());

        }

        String concat = String.join(",", list);
        System.out.println("Olha aqui o concat" + concat);
        shippingLocationDetails.add(
                new ShippingLocation(id.toString(), name, concat).getLightWeightShippingLocation()
        );
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while getting Warehouses");
      sqle.printStackTrace();
    }
    return shippingLocationDetails;
  }

  public ShippingLocation getShippingLocation(String id) {
    try {
      List<String> list = new ArrayList<String>();
      PreparedStatement ps = connection.prepareStatement(
              "select *  from SHIPPING_LOCATION where id=?"
      );
      ps.setString(1, id);
      ResultSet rSet = ps.executeQuery();

      while (rSet.next()) {


        PreparedStatement ps2 = connection.prepareStatement(
                "SELECT * FROM WAREHOUSE_LOCATION WHERE locationId=?"
        );

        Integer id2 = rSet.getInt("id");
        String name = rSet.getString("name");
        ps2.setString(1, id.toString());

        ResultSet rSet2 = ps2.executeQuery();
        while (rSet2.next()){

          Integer idWareLocation = rSet2.getInt("warehouseId");
          list.add(idWareLocation.toString());
        }
        String concat = String.join(",", list);
        System.out.println("estou aqui" + concat);
        shippingLocations.put(id2.toString(), new ShippingLocation(id2.toString(), name, concat));
      }
    } catch (SQLException sqle) {
      System.out.println("Database error while retrieving Warehouse");
      sqle.printStackTrace();
    }
    return shippingLocations.get(id);
  }
}
