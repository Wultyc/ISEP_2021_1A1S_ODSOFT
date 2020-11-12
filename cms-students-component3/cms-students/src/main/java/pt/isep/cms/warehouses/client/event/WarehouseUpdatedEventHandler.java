package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface WarehouseUpdatedEventHandler extends EventHandler{
  void onWarehouseUpdated(WarehouseUpdatedEvent event);
}
