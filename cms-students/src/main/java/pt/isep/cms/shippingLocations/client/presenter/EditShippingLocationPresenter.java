package pt.isep.cms.shippingLocations.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.shippingLocations.client.ShippingLocationsServiceAsync;
import pt.isep.cms.shippingLocations.client.event.ShippingLocationUpdatedEvent;
import pt.isep.cms.shippingLocations.client.event.EditShippingLocationCancelledEvent;
import pt.isep.cms.shippingLocations.shared.ShippingLocation;

public class EditShippingLocationPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getWarehouse();

		void show();

		void hide();
	}

	private ShippingLocation shippingLocation;
	private final ShippingLocationsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public EditShippingLocationPresenter(ShippingLocationsServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.shippingLocation = new ShippingLocation();
		this.display = display;
		bind();
	}

	public EditShippingLocationPresenter(ShippingLocationsServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getShippingLocation(id, new AsyncCallback<ShippingLocation>() {
			public void onSuccess(ShippingLocation result) {
				shippingLocation = result;
				EditShippingLocationPresenter.this.display.getName().setValue(shippingLocation.getName());
				EditShippingLocationPresenter.this.display.getWarehouse().setValue(shippingLocation.getWarehouses());
				}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving shippingLocation");
			}
		});

	}

	public void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
				display.hide();
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.hide();
				eventBus.fireEvent(new EditShippingLocationCancelledEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}

	private void doSave() {
		shippingLocation.setName(display.getName().getValue());
		shippingLocation.setWarehouses(display.getWarehouse().getValue());

		if (shippingLocation.getId() == null) {
			// Adding new shippingLocation
			rpcService.addShippingLocation(shippingLocation, new AsyncCallback<ShippingLocation>() {
				public void onSuccess(ShippingLocation result) {
					eventBus.fireEvent(new ShippingLocationUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error adding shippingLocation");
				}
			});
		} else {
			// updating existing shippingLocation
			rpcService.updateShippingLocation(shippingLocation, new AsyncCallback<ShippingLocation>() {
				public void onSuccess(ShippingLocation result) {
					eventBus.fireEvent(new ShippingLocationUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error updating shippingLocation");
				}
			});
		}
	}

}
