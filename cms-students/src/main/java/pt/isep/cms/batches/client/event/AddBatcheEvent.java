package pt.isep.cms.batches.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddBatcheEvent extends GwtEvent<AddBatcheEventHandler> {
  public static Type<AddBatcheEventHandler> TYPE = new Type<AddBatcheEventHandler>();
  
  @Override
  public Type<AddBatcheEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddBatcheEventHandler handler) {
    handler.onAddBatche(this);
  }
}
