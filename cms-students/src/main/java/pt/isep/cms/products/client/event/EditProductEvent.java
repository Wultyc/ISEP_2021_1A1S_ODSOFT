package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditProductEvent extends GwtEvent<EditProductEventHandler>{
  public static Type<EditProductEventHandler> TYPE = new Type<EditProductEventHandler>();
  private final String id;
  
  public EditProductEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditProductEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditProductEventHandler handler) {
    handler.onEditProduct(this);
  }
}
