package pt.isep.cms.batches.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class BatcheDeletedEvent extends GwtEvent<BatcheDeletedEventHandler>{
  public static Type<BatcheDeletedEventHandler> TYPE = new Type<BatcheDeletedEventHandler>();
  
  @Override
  public Type<BatcheDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BatcheDeletedEventHandler handler) {
    handler.onBatcheDeleted(this);
  }
}
