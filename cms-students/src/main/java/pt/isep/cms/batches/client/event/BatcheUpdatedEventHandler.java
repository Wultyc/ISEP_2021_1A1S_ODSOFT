package pt.isep.cms.batches.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BatcheUpdatedEventHandler extends EventHandler{
  void onBatcheUpdated(BatcheUpdatedEvent event);
}
