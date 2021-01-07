package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.shippingLocations.shared.ShippingLocation;

public class ShippingLocationUpdatedEvent extends GwtEvent<ShippingLocationUpdatedEventHandler>{
  public static Type<ShippingLocationUpdatedEventHandler> TYPE = new Type<ShippingLocationUpdatedEventHandler>();
  private final ShippingLocation updatedShippingLocation;
  
  public ShippingLocationUpdatedEvent(ShippingLocation updatedShippingLocation) {
    this.updatedShippingLocation = updatedShippingLocation;
  }
  
  public ShippingLocation getUpdatedShippingLocation() { return updatedShippingLocation; }
  

  @Override
  public Type<ShippingLocationUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShippingLocationUpdatedEventHandler handler) {
    handler.onShippingLocationUpdated(this);
  }
}
