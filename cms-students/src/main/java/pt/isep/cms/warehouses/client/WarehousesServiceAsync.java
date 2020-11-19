package pt.isep.cms.warehouses.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;

public interface WarehousesServiceAsync {

  public void addWarehouse(Warehouse warehouse, AsyncCallback<Warehouse> callback);
  public void deleteWarehouse(String id, AsyncCallback<Boolean> callback);
  public void deleteWarehouses(ArrayList<String> ids, AsyncCallback<ArrayList<WarehouseDetails>> callback);
  public void getWarehouseDetails(AsyncCallback<ArrayList<WarehouseDetails>> callback);
  public void getWarehouse(String id, AsyncCallback<Warehouse> callback);
  public void updateWarehouse(Warehouse warehouse, AsyncCallback<Warehouse> callback);
}

