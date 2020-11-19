package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddWarehouseEvent extends GwtEvent<AddWarehouseEventHandler> {
  public static Type<AddWarehouseEventHandler> TYPE = new Type<AddWarehouseEventHandler>();
  
  @Override
  public Type<AddWarehouseEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddWarehouseEventHandler handler) {
    handler.onAddWarehouse(this);
  }
}
