package pt.isep.cms.shippingLocations.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.shippingLocations.client.event.AddShippingLocationEvent;
import pt.isep.cms.shippingLocations.client.event.AddShippingLocationEventHandler;
import pt.isep.cms.shippingLocations.client.event.ShippingLocationUpdatedEvent;
import pt.isep.cms.shippingLocations.client.event.ShippingLocationUpdatedEventHandler;
import pt.isep.cms.shippingLocations.client.event.EditShippingLocationEvent;
import pt.isep.cms.shippingLocations.client.event.EditShippingLocationEventHandler;
import pt.isep.cms.shippingLocations.client.event.EditShippingLocationCancelledEvent;
import pt.isep.cms.shippingLocations.client.event.EditShippingLocationCancelledEventHandler;
import pt.isep.cms.shippingLocations.client.presenter.ShippingLocationsPresenter;
import pt.isep.cms.shippingLocations.client.presenter.EditShippingLocationPresenter;
import pt.isep.cms.shippingLocations.client.presenter.Presenter;
import pt.isep.cms.shippingLocations.client.view.ShippingLocationsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.shippingLocations.client.view.ShippingLocationsDialog;

public class ShippingLocationsController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final ShippingLocationsServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public ShippingLocationsController(ShippingLocationsServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddShippingLocationEvent.TYPE, new AddShippingLocationEventHandler() {
			public void onAddShippingLocation(AddShippingLocationEvent event) {
				doAddNewShippingLocation();
			}
		});

		eventBus.addHandler(EditShippingLocationEvent.TYPE, new EditShippingLocationEventHandler() {
			public void onEditShippingLocation(EditShippingLocationEvent event) {
				doEditShippingLocation(event.getId());
			}
		});

		eventBus.addHandler(EditShippingLocationCancelledEvent.TYPE, new EditShippingLocationCancelledEventHandler() {
			public void onEditShippingLocationCancelled(EditShippingLocationCancelledEvent event) {
				doEditShippingLocationCancelled();
			}
		});

		eventBus.addHandler(ShippingLocationUpdatedEvent.TYPE, new ShippingLocationUpdatedEventHandler() {
			public void onShippingLocationUpdated(ShippingLocationUpdatedEvent event) {
				doShippingLocationUpdated();
			}
		});
	}

	private void doAddNewShippingLocation() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new EditShippingLocationPresenter(rpcService, eventBus, new ShippingLocationsDialog(globalConstants, ShippingLocationsDialog.Type.ADD));
		presenter.go(container);

	}

	private void doEditShippingLocation(String id) {
		Presenter presenter = new EditShippingLocationPresenter(rpcService, eventBus, new ShippingLocationsDialog(globalConstants, ShippingLocationsDialog.Type.UPDATE), id);
		presenter.go(container);
	}

	private void doEditShippingLocationCancelled() {
		// Nothing to update...
	}

	private void doShippingLocationUpdated() {
		// (ATB) Update the list of warehouses...
		Presenter presenter = new ShippingLocationsPresenter(rpcService, eventBus, new ShippingLocationsView());
		presenter.go(container);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new ShippingLocationsPresenter(rpcService, eventBus, new ShippingLocationsView());
		presenter.go(container);
	}

}
