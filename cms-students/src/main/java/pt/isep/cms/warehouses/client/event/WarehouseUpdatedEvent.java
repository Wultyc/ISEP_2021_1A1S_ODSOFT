package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.warehouses.shared.Warehouse;

public class WarehouseUpdatedEvent extends GwtEvent<WarehouseUpdatedEventHandler>{
  public static Type<WarehouseUpdatedEventHandler> TYPE = new Type<WarehouseUpdatedEventHandler>();
  private final Warehouse updatedWarehouse;
  
  public WarehouseUpdatedEvent(Warehouse updatedWarehouse) {
    this.updatedWarehouse = updatedWarehouse;
  }
  
  public Warehouse getUpdatedWarehouse() { return updatedWarehouse; }
  

  @Override
  public Type<WarehouseUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WarehouseUpdatedEventHandler handler) {
    handler.onWarehouseUpdated(this);
  }
}
