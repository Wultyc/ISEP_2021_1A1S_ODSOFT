package pt.isep.cms.batches.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.batches.client.event.AddBatcheEvent;
import pt.isep.cms.batches.client.event.AddBatcheEventHandler;
import pt.isep.cms.batches.client.event.BatcheUpdatedEvent;
import pt.isep.cms.batches.client.event.BatcheUpdatedEventHandler;
import pt.isep.cms.batches.client.event.EditBatcheEvent;
import pt.isep.cms.batches.client.event.EditBatcheEventHandler;
import pt.isep.cms.batches.client.event.EditBatcheCancelledEvent;
import pt.isep.cms.batches.client.event.EditBatcheCancelledEventHandler;
import pt.isep.cms.batches.client.presenter.BatchesPresenter;
import pt.isep.cms.batches.client.presenter.EditBatchePresenter;
import pt.isep.cms.batches.client.presenter.Presenter;
import pt.isep.cms.batches.client.view.BatchesView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.batches.client.view.BatchesDialog;

public class BatchesController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final BatchesServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public BatchesController(BatchesServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddBatcheEvent.TYPE, new AddBatcheEventHandler() {
			public void onAddBatche(AddBatcheEvent event) {
				doAddNewBatche();
			}
		});

		eventBus.addHandler(EditBatcheEvent.TYPE, new EditBatcheEventHandler() {
			public void onEditBatche(EditBatcheEvent event) {
				doEditBatche(event.getId());
			}
		});

		eventBus.addHandler(EditBatcheCancelledEvent.TYPE, new EditBatcheCancelledEventHandler() {
			public void onEditBatcheCancelled(EditBatcheCancelledEvent event) {
				doEditBatcheCancelled();
			}
		});

		eventBus.addHandler(BatcheUpdatedEvent.TYPE, new BatcheUpdatedEventHandler() {
			public void onBatcheUpdated(BatcheUpdatedEvent event) {
				doBatcheUpdated();
			}
		});
	}

	private void doAddNewBatche() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new EditBatchePresenter(rpcService, eventBus, new BatchesDialog(globalConstants, BatchesDialog.Type.ADD));
		presenter.go(container);

	}

	private void doEditBatche(String id) {
		Presenter presenter = new EditBatchePresenter(rpcService, eventBus, new BatchesDialog(globalConstants, BatchesDialog.Type.UPDATE), id);
		presenter.go(container);
	}

	private void doEditBatcheCancelled() {
		// Nothing to update...
	}

	private void doBatcheUpdated() {
		// (ATB) Update the list of batches...
		Presenter presenter = new BatchesPresenter(rpcService, eventBus, new BatchesView());
		presenter.go(container);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new BatchesPresenter(rpcService, eventBus, new BatchesView());
		presenter.go(container);
	}

}
