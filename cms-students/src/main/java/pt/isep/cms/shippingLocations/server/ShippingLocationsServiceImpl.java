package pt.isep.cms.shippingLocations.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.shippingLocations.client.*;
import pt.isep.cms.shippingLocations.shared.ShippingLocation;
import pt.isep.cms.shippingLocations.shared.*;

@SuppressWarnings("serial")
public class ShippingLocationsServiceImpl extends RemoteServiceServlet implements
    ShippingLocationsService {

  private static final String[] shippingLocationsNameData = new String[] {
      "ShippingLocation X", "ShippingLocation Y", "ShippingLocation Z"};
  
  
      
  private final HashMap<String, ShippingLocation> shippingLocations = new HashMap<String, ShippingLocation>();

  public ShippingLocationsServiceImpl() {
    initShippingLocations();
  }
  
  private void initShippingLocations() {
    // TODO: Create a real UID for each shippingLocation
    //
    for (int i = 0; i < shippingLocationsNameData.length; ++i) {
      ShippingLocation shippingLocation = new ShippingLocation(String.valueOf(i), shippingLocationsNameData[i]);
      shippingLocations.put(shippingLocation.getId(), shippingLocation); 
    }
  }
  
  public ShippingLocation addShippingLocation(ShippingLocation shippingLocation) {
    shippingLocation.setId(String.valueOf(shippingLocations.size()));
    shippingLocations.put(shippingLocation.getId(), shippingLocation); 
    return shippingLocation;
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
