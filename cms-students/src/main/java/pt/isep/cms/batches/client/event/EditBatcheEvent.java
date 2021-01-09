package pt.isep.cms.batches.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBatcheEvent extends GwtEvent<EditBatcheEventHandler>{
  public static Type<EditBatcheEventHandler> TYPE = new Type<EditBatcheEventHandler>();
  private final String id;
  
  public EditBatcheEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditBatcheEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBatcheEventHandler handler) {
    handler.onEditBatche(this);
  }
}
