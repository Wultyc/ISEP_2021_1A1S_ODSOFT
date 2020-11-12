package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddWarehouseEventHandler extends EventHandler {
  void onAddWarehouse(AddWarehouseEvent event);
}
