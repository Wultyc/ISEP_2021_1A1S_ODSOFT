package pt.isep.cms.shippingLocations.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.shippingLocations.shared.ShippingLocation;
import pt.isep.cms.shippingLocations.shared.ShippingLocationDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("shippingLocationsService")
public interface ShippingLocationsService extends RemoteService {
	
  ShippingLocation addShippingLocation(ShippingLocation shippingLocation);
  Boolean deleteShippingLocation(String id); 
  ArrayList<ShippingLocationDetails> deleteShippingLocations(ArrayList<String> ids);
  ArrayList<ShippingLocationDetails> getShippingLocationDetails();
  ShippingLocation getShippingLocation(String id);
  ShippingLocation updateShippingLocation(ShippingLocation shippingLocation);
}
