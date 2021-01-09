package pt.isep.cms.batches.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.batches.client.BatchesServiceAsync;
import pt.isep.cms.batches.client.event.BatcheUpdatedEvent;
import pt.isep.cms.batches.client.event.EditBatcheCancelledEvent;
import pt.isep.cms.batches.shared.Batche;

public class EditBatchePresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getDescrip();

		HasValue<String> getManDate();

		void show();

		void hide();
	}

	private Batche batche;
	private final BatchesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public EditBatchePresenter(BatchesServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.batche = new Batche();
		this.display = display;
		bind();
	}

	public EditBatchePresenter(BatchesServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getBatche(id, new AsyncCallback<Batche>() {
			public void onSuccess(Batche result) {
				batche = result;
				EditBatchePresenter.this.display.getName().setValue(batche.getName());
				EditBatchePresenter.this.display.getDescrip().setValue(batche.getDescrip());
				EditBatchePresenter.this.display.getManDate().setValue(batche.getManDate());
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving batche");
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
				eventBus.fireEvent(new EditBatcheCancelledEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}

	private void doSave() {
		batche.setName(display.getName().getValue());
		batche.setDescrip(display.getDescrip().getValue());
		batche.setManDate(display.getManDate().getValue());

		if (batche.getId() == null) {
			// Adding new batche
			rpcService.addBatche(batche, new AsyncCallback<Batche>() {
				public void onSuccess(Batche result) {
					eventBus.fireEvent(new BatcheUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error adding batche");
				}
			});
		} else {
			// updating existing batche
			rpcService.updateBatche(batche, new AsyncCallback<Batche>() {
				public void onSuccess(Batche result) {
					eventBus.fireEvent(new BatcheUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error updating batche");
				}
			});
		}
	}

}