package pt.isep.cms.shippingLocations.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditShippingLocationEvent extends GwtEvent<EditShippingLocationEventHandler>{
  public static Type<EditShippingLocationEventHandler> TYPE = new Type<EditShippingLocationEventHandler>();
  private final String id;
  
  public EditShippingLocationEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditShippingLocationEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditShippingLocationEventHandler handler) {
    handler.onEditShippingLocation(this);
  }
}
