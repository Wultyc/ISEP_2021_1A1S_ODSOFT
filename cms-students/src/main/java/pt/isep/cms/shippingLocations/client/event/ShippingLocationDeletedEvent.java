package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShippingLocationDeletedEvent extends GwtEvent<ShippingLocationDeletedEventHandler>{
  public static Type<ShippingLocationDeletedEventHandler> TYPE = new Type<ShippingLocationDeletedEventHandler>();
  
  @Override
  public Type<ShippingLocationDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShippingLocationDeletedEventHandler handler) {
    handler.onShippingLocationDeleted(this);
  }
}
