package pt.isep.cms.contacts.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditContactEventHandler extends EventHandler {
  void onEditContact(EditContactEvent event);
}
