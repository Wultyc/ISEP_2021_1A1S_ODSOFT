package pt.isep.cms.batches.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBatcheCancelledEvent extends GwtEvent<EditBatcheCancelledEventHandler>{
  public static Type<EditBatcheCancelledEventHandler> TYPE = new Type<EditBatcheCancelledEventHandler>();
  
  @Override
  public Type<EditBatcheCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBatcheCancelledEventHandler handler) {
    handler.onEditBatcheCancelled(this);
  }
}
