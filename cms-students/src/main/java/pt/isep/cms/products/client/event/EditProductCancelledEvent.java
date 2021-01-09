package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditProductCancelledEvent extends GwtEvent<EditProductCancelledEventHandler>{
  public static Type<EditProductCancelledEventHandler> TYPE = new Type<EditProductCancelledEventHandler>();
  
  @Override
  public Type<EditProductCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditProductCancelledEventHandler handler) {
    handler.onEditProductCancelled(this);
  }
}
