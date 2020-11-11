package pt.isep.cms.warehouses.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.warehouses.client.event.AddWarehouseEvent;
import pt.isep.cms.warehouses.client.event.AddWarehouseEventHandler;
import pt.isep.cms.warehouses.client.event.WarehouseUpdatedEvent;
import pt.isep.cms.warehouses.client.event.WarehouseUpdatedEventHandler;
import pt.isep.cms.warehouses.client.event.EditWarehouseEvent;
import pt.isep.cms.warehouses.client.event.EditWarehouseEventHandler;
import pt.isep.cms.warehouses.client.event.EditWarehouseCancelledEvent;
import pt.isep.cms.warehouses.client.event.EditWarehouseCancelledEventHandler;
import pt.isep.cms.warehouses.client.presenter.WarehousesPresenter;
import pt.isep.cms.warehouses.client.presenter.EditWarehousePresenter;
import pt.isep.cms.warehouses.client.presenter.Presenter;
import pt.isep.cms.warehouses.client.view.WarehousesView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.warehouses.client.view.WarehousesDialog;

public class WarehousesController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final WarehousesServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public WarehousesController(WarehousesServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddWarehouseEvent.TYPE, new AddWarehouseEventHandler() {
			public void onAddWarehouse(AddWarehouseEvent event) {
				doAddNewWarehouse();
			}
		});

		eventBus.addHandler(EditWarehouseEvent.TYPE, new EditWarehouseEventHandler() {
			public void onEditWarehouse(EditWarehouseEvent event) {
				doEditWarehouse(event.getId());
			}
		});

		eventBus.addHandler(EditWarehouseCancelledEvent.TYPE, new EditWarehouseCancelledEventHandler() {
			public void onEditWarehouseCancelled(EditWarehouseCancelledEvent event) {
				doEditWarehouseCancelled();
			}
		});

		eventBus.addHandler(WarehouseUpdatedEvent.TYPE, new WarehouseUpdatedEventHandler() {
			public void onWarehouseUpdated(WarehouseUpdatedEvent event) {
				doWarehouseUpdated();
			}
		});
	}

	private void doAddNewWarehouse() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new EditWarehousePresenter(rpcService, eventBus, new WarehousesDialog(globalConstants, WarehousesDialog.Type.ADD));
		presenter.go(container);

	}

	private void doEditWarehouse(String id) {
		Presenter presenter = new EditWarehousePresenter(rpcService, eventBus, new WarehousesDialog(globalConstants, WarehousesDialog.Type.UPDATE), id);
		presenter.go(container);
	}

	private void doEditWarehouseCancelled() {
		// Nothing to update...
	}

	private void doWarehouseUpdated() {
		// (ATB) Update the list of warehouses...
		Presenter presenter = new WarehousesPresenter(rpcService, eventBus, new WarehousesView());
		presenter.go(container);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new WarehousesPresenter(rpcService, eventBus, new WarehousesView());
		presenter.go(container);
	}

}
