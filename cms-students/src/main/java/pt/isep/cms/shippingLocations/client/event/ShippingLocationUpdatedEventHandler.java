package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ShippingLocationUpdatedEventHandler extends EventHandler{
  void onShippingLocationUpdated(ShippingLocationUpdatedEvent event);
}
