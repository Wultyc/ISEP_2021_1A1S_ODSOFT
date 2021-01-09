package pt.isep.cms.batches.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BatcheDeletedEventHandler extends EventHandler {
  void onBatcheDeleted(BatcheDeletedEvent event);
}
