package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditShippingLocationCancelledEvent extends GwtEvent<EditShippingLocationCancelledEventHandler>{
  public static Type<EditShippingLocationCancelledEventHandler> TYPE = new Type<EditShippingLocationCancelledEventHandler>();
  
  @Override
  public Type<EditShippingLocationCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditShippingLocationCancelledEventHandler handler) {
    handler.onEditShippingLocationCancelled(this);
  }
}
