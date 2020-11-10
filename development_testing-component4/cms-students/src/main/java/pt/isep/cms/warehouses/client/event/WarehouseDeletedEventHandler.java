package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface WarehouseDeletedEventHandler extends EventHandler {
  void onWarehouseDeleted(WarehouseDeletedEvent event);
}
