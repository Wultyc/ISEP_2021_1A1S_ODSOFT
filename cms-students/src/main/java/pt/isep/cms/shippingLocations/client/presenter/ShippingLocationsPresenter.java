package pt.isep.cms.shippingLocations.client.presenter;

import pt.isep.cms.shippingLocations.client.ShippingLocationsServiceAsync;
import pt.isep.cms.shippingLocations.client.event.AddShippingLocationEvent;
import pt.isep.cms.shippingLocations.client.event.EditShippingLocationEvent;
import pt.isep.cms.shippingLocations.shared.ShippingLocationDetails;

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

public class ShippingLocationsPresenter implements Presenter {

	private List<ShippingLocationDetails> shippingLocationDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final ShippingLocationsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public ShippingLocationsPresenter(ShippingLocationsServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddShippingLocationEvent());
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteSelectedShippingLocations();
			}
		});

		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					String id = shippingLocationDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditShippingLocationEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchShippingLocationDetails();
	}

	public void sortShippingLocationDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < shippingLocationDetails.size(); ++i) {
			for (int j = 0; j < shippingLocationDetails.size() - 1; ++j) {
				if (shippingLocationDetails.get(j).getDisplayName()
						.compareToIgnoreCase(shippingLocationDetails.get(j + 1).getDisplayName()) >= 0) {
					ShippingLocationDetails tmp = shippingLocationDetails.get(j);
					shippingLocationDetails.set(j, shippingLocationDetails.get(j + 1));
					shippingLocationDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setShippingLocationDetails(List<ShippingLocationDetails> shippingLocationDetails) {
		this.shippingLocationDetails = shippingLocationDetails;
	}

	public ShippingLocationDetails getShippingLocationDetail(int index) {
		return shippingLocationDetails.get(index);
	}

	private void fetchShippingLocationDetails() {
		rpcService.getShippingLocationDetails(new AsyncCallback<ArrayList<ShippingLocationDetails>>() {
			public void onSuccess(ArrayList<ShippingLocationDetails> result) {
				shippingLocationDetails = result;
				sortShippingLocationDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(shippingLocationDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching shippingLocation details");
			}
		});
	}

	private void deleteSelectedShippingLocations() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(shippingLocationDetails.get(selectedRows.get(i)).getId());
		}

		rpcService.deleteShippingLocations(ids, new AsyncCallback<ArrayList<ShippingLocationDetails>>() {
			public void onSuccess(ArrayList<ShippingLocationDetails> result) {
				shippingLocationDetails = result;
				sortShippingLocationDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(shippingLocationDetails.get(i).getDisplayName());
				}

				display.setData(data);

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected shippingLocations");
			}
		});
	}
}
