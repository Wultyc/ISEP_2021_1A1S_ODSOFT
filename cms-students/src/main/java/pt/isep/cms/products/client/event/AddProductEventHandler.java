package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddProductEventHandler extends EventHandler {
  void onAddProduct(AddProductEvent event);
}
