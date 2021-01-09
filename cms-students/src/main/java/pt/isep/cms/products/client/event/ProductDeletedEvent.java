package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProductDeletedEvent extends GwtEvent<ProductDeletedEventHandler>{
  public static Type<ProductDeletedEventHandler> TYPE = new Type<ProductDeletedEventHandler>();
  
  @Override
  public Type<ProductDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ProductDeletedEventHandler handler) {
    handler.onProductDeleted(this);
  }
}
