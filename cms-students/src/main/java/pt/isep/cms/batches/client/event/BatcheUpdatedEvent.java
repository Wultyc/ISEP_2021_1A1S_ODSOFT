package pt.isep.cms.batches.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.batches.shared.Batche;

public class BatcheUpdatedEvent extends GwtEvent<BatcheUpdatedEventHandler>{
  public static Type<BatcheUpdatedEventHandler> TYPE = new Type<BatcheUpdatedEventHandler>();
  private final Batche updatedBatche;
  
  public BatcheUpdatedEvent(Batche updatedBatche) {
    this.updatedBatche = updatedBatche;
  }
  
  public Batche getUpdatedBatche() { return updatedBatche; }
  

  @Override
  public Type<BatcheUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BatcheUpdatedEventHandler handler) {
    handler.onBatcheUpdated(this);
  }
}
