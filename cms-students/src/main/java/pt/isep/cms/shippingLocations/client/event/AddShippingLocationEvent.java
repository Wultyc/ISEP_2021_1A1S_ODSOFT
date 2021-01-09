package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddShippingLocationEvent extends GwtEvent<AddShippingLocationEventHandler> {
  public static Type<AddShippingLocationEventHandler> TYPE = new Type<AddShippingLocationEventHandler>();
  
  @Override
  public Type<AddShippingLocationEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddShippingLocationEventHandler handler) {
    handler.onAddShippingLocation(this);
  }
}
