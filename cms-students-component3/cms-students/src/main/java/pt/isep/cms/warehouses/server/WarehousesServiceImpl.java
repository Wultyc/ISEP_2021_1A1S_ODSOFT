package pt.isep.cms.warehouses.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.warehouses.client.WarehousesService;
import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

@SuppressWarnings("serial")
public class WarehousesServiceImpl extends RemoteServiceServlet implements
    WarehousesService {

  private static final String[] warehousesNameData = new String[] {
      "Warehouse X", "Warehouse Y", "Warehouse Z"};
  
  private final String[] warehousesAddressData = new String[] {
      "Warehouse X street", "Warehouse Y street", "Warehouse Z street"};
  
  private final String[] warehousesEmailData = new String[] {
      "comercial@warehouse.xxx", "financial@warehouse.yyy", "administration@warehouse.zzz"};
      
  private final HashMap<String, Warehouse> warehouses = new HashMap<String, Warehouse>();

  public WarehousesServiceImpl() {
    initWarehouses();
  }
  
  private void initWarehouses() {
    // TODO: Create a real UID for each warehouse
    //
    for (int i = 0; i < warehousesNameData.length && i < warehousesAddressData.length && i < warehousesEmailData.length; ++i) {
      Warehouse warehouse = new Warehouse(String.valueOf(i), warehousesNameData[i], warehousesAddressData[i], warehousesEmailData[i]);
      warehouses.put(warehouse.getId(), warehouse); 
    }
  }
  
  public Warehouse addWarehouse(Warehouse warehouse) {
    warehouse.setId(String.valueOf(warehouses.size()));
    warehouses.put(warehouse.getId(), warehouse); 
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
    
    Iterator<String> it = warehouses.keySet().iterator();
    while(it.hasNext()) { 
      Warehouse warehouse = warehouses.get(it.next());          
      warehouseDetails.add(warehouse.getLightWeightWarehouse());
    }
    
    return warehouseDetails;
  }

  public Warehouse getWarehouse(String id) {
    return warehouses.get(id);
  }
}
