package pt.isep.cms.warehouses.server;

import junit.framework.TestCase;
import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;


import java.util.ArrayList;
import java.util.HashMap;

public class WarehousesServiceImplJRETest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

        //warehousesServiceImpl = new WarehousesServiceImplTest();
    }

    public void tearDown() throws Exception {
    }

    public void testAddWarehouse() {
    }

    public void testUpdateWarehouse() {
    }

    public void testDeleteWarehouse() {
        WarehousesServiceImpl warehousesServiceImpl = new WarehousesServiceImpl();

        Warehouse warehouse1 = warehousesServiceImpl.getWarehouse("0");
        assertTrue(warehousesServiceImpl.deleteWarehouse(warehouse1.getId()));
    }

    public void testDeleteWarehouses() {
        WarehousesServiceImpl warehousesServiceImpl = new WarehousesServiceImpl();

        ArrayList<WarehouseDetails> warehouseDetails = warehousesServiceImpl.getWarehouseDetails();

        ArrayList<String> warehouseiDs = new ArrayList<String>();
        for (int i = 0; i < warehouseDetails.size(); i++){
            warehouseiDs.add(warehouseDetails.get(i).getId());
        }

        warehouseDetails = warehousesServiceImpl.deleteWarehouses(warehouseiDs);
        int expectNumber = 0; //Empty (Devido a todos serem eleminados correctamente)

        assertEquals(expectNumber,warehouseDetails.size());
    }

    public void testGetWarehouseDetails() {
    }

    public void testGetWarehouse() {
    }
}