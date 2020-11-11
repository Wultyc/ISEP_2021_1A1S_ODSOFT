package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class WarehouseDeletedEvent extends GwtEvent<WarehouseDeletedEventHandler>{
  public static Type<WarehouseDeletedEventHandler> TYPE = new Type<WarehouseDeletedEventHandler>();
  
  @Override
  public Type<WarehouseDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WarehouseDeletedEventHandler handler) {
    handler.onWarehouseDeleted(this);
  }
}
