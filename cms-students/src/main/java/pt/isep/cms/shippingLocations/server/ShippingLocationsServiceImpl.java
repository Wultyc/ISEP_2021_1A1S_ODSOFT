package pt.isep.cms.shippingLocations.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import pt.isep.cms.DBConnection.DBConnection;
import pt.isep.cms.shippingLocations.client.*;
import pt.isep.cms.shippingLocations.shared.ShippingLocation;
import pt.isep.cms.shippingLocations.shared.*;
import pt.isep.cms.warehouses.shared.Warehouse;

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
                "SELECT * FROM WAREHOUSE_LOCATION WHERE ID=?"
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
      System.out.println("Database error while getting warehouses");
      sqle.printStackTrace();
    }
  }
  
  public ShippingLocation addShippingLocation(ShippingLocation shippingLocation) {
    try {
      PreparedStatement ps = connection.prepareStatement(
              "insert into SHIPPING_LOCATION values (null,?)"
      );
      ps.setString(1, shippingLocation.getName());
      ps.executeUpdate();
      ResultSet rs_key = ps.getGeneratedKeys();

      List<String> warehouseList = Arrays.asList(shippingLocation.getWarehouses().split(","));
      Iterator iterator = warehouseList.iterator();
      while (iterator.hasNext()) {
        PreparedStatement ps2 = connection.prepareStatement(
                "inset into WAREHOUSE_LOCATION values (null,?,?)"
        );
                ps2.setString(1, iterator.next().toString());
                ps2.setString(2, rs_key.getString(1));
      }
      return shippingLocation;
    } catch (SQLException sqle) {
      System.out.println("Error while creating warehouse");
      sqle.printStackTrace();
    }
    return null;
  }

  public ShippingLocation updateShippingLocation(ShippingLocation shippingLocation) {
	  String lid=shippingLocation.getId();
    shippingLocations.remove(shippingLocation.getId());
    shippingLocations.put(shippingLocation.getId(), shippingLocation); 
    return shippingLocation;
  }

  public Boolean deleteShippingLocation(String id) {
    shippingLocations.remove(id);
    return true;
  }
  
  public ArrayList<ShippingLocationDetails> deleteShippingLocations(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deleteShippingLocation(ids.get(i));
    }
    
    return getShippingLocationDetails();
  }
  
  public ArrayList<ShippingLocationDetails> getShippingLocationDetails() {
    ArrayList<ShippingLocationDetails> shippingLocationDetails = new ArrayList<ShippingLocationDetails>();
    
    Iterator<String> it = shippingLocations.keySet().iterator();
    while(it.hasNext()) { 
      ShippingLocation shippingLocation = shippingLocations.get(it.next());          
      shippingLocationDetails.add(shippingLocation.getLightWeightShippingLocation());
    }
    
    return shippingLocationDetails;
  }

  public ShippingLocation getShippingLocation(String id) {
    return shippingLocations.get(id);
  }
}
