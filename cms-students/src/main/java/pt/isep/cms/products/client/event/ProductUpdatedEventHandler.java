package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProductUpdatedEventHandler extends EventHandler{
  void onProductUpdated(ProductUpdatedEvent event);
}
