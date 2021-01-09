package pt.isep.cms.batches.client.presenter;

import pt.isep.cms.batches.client.BatchesServiceAsync;
import pt.isep.cms.batches.client.event.AddBatcheEvent;
import pt.isep.cms.batches.client.event.EditBatcheEvent;
import pt.isep.cms.batches.shared.BatcheDetails;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class BatchesPresenter implements Presenter {

	private List<BatcheDetails> batcheDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final BatchesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public BatchesPresenter(BatchesServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddBatcheEvent());
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteSelectedBatches();
			}
		});

		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					String id = batcheDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditBatcheEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchBatcheDetails();
	}

	public void sortBatcheDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < batcheDetails.size(); ++i) {
			for (int j = 0; j < batcheDetails.size() - 1; ++j) {
				if (batcheDetails.get(j).getDisplayName()
						.compareToIgnoreCase(batcheDetails.get(j + 1).getDisplayName()) >= 0) {
					BatcheDetails tmp = batcheDetails.get(j);
					batcheDetails.set(j, batcheDetails.get(j + 1));
					batcheDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setBatcheDetails(List<BatcheDetails> batcheDetails) {
		this.batcheDetails = batcheDetails;
	}

	public BatcheDetails getBatcheDetail(int index) {
		return batcheDetails.get(index);
	}

	private void fetchBatcheDetails() {
		rpcService.getBatcheDetails(new AsyncCallback<ArrayList<BatcheDetails>>() {
			public void onSuccess(ArrayList<BatcheDetails> result) {
				batcheDetails = result;
				sortBatcheDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(batcheDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching batche details");
			}
		});
	}

	private void deleteSelectedBatches() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(batcheDetails.get(selectedRows.get(i)).getId());
		}

		rpcService.deleteBatches(ids, new AsyncCallback<ArrayList<BatcheDetails>>() {
			public void onSuccess(ArrayList<BatcheDetails> result) {
				batcheDetails = result;
				sortBatcheDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(batcheDetails.get(i).getDisplayName());
				}

				display.setData(data);

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected batches");
			}
		});
	}
}
