package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ShippingLocationDeletedEventHandler extends EventHandler {
  void onShippingLocationDeleted(ShippingLocationDeletedEvent event);
}
