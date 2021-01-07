package pt.isep.cms.shippingLocations.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.shippingLocations.shared.ShippingLocation;
import pt.isep.cms.shippingLocations.shared.ShippingLocationDetails;

import java.util.ArrayList;

public interface ShippingLocationsServiceAsync {

  public void addShippingLocation(ShippingLocation shippingLocation, AsyncCallback<ShippingLocation> callback);
  public void deleteShippingLocation(String id, AsyncCallback<Boolean> callback);
  public void deleteShippingLocations(ArrayList<String> ids, AsyncCallback<ArrayList<ShippingLocationDetails>> callback);
  public void getShippingLocationDetails(AsyncCallback<ArrayList<ShippingLocationDetails>> callback);
  public void getShippingLocation(String id, AsyncCallback<ShippingLocation> callback);
  public void updateShippingLocation(ShippingLocation shippingLocation, AsyncCallback<ShippingLocation> callback);
}

