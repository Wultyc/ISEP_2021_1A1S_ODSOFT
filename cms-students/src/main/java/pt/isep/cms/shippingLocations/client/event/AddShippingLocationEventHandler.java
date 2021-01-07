package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddShippingLocationEventHandler extends EventHandler {
  void onAddShippingLocation(AddShippingLocationEvent event);
}
