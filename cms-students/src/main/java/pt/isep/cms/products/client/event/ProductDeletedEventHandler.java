package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProductDeletedEventHandler extends EventHandler {
  void onProductDeleted(ProductDeletedEvent event);
}
