package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditProductEventHandler extends EventHandler {
  void onEditProduct(EditProductEvent event);
}
