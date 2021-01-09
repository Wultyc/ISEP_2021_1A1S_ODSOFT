package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditShippingLocationEventHandler extends EventHandler {
  void onEditShippingLocation(EditShippingLocationEvent event);
}
