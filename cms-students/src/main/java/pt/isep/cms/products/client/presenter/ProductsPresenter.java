package pt.isep.cms.products.client.presenter;

import pt.isep.cms.products.client.ProductsServiceAsync;
import pt.isep.cms.products.client.event.AddProductEvent;
import pt.isep.cms.products.client.event.EditProductEvent;
import pt.isep.cms.products.shared.ProductDetails;

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

public class ProductsPresenter implements Presenter {

	private List<ProductDetails> productDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final ProductsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public ProductsPresenter(ProductsServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddProductEvent());
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteSelectedProducts();
			}
		});

		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					String id = productDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditProductEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchProductDetails();
	}

	public void sortProductDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < productDetails.size(); ++i) {
			for (int j = 0; j < productDetails.size() - 1; ++j) {
				if (productDetails.get(j).getDisplayName()
						.compareToIgnoreCase(productDetails.get(j + 1).getDisplayName()) >= 0) {
					ProductDetails tmp = productDetails.get(j);
					productDetails.set(j, productDetails.get(j + 1));
					productDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	public ProductDetails getProductDetail(int index) {
		return productDetails.get(index);
	}

	private void fetchProductDetails() {
		rpcService.getProductDetails(new AsyncCallback<ArrayList<ProductDetails>>() {
			public void onSuccess(ArrayList<ProductDetails> result) {
				productDetails = result;
				sortProductDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(productDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching product details");
			}
		});
	}

	private void deleteSelectedProducts() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(productDetails.get(selectedRows.get(i)).getId());
		}

		rpcService.deleteProducts(ids, new AsyncCallback<ArrayList<ProductDetails>>() {
			public void onSuccess(ArrayList<ProductDetails> result) {
				productDetails = result;
				sortProductDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(productDetails.get(i).getDisplayName());
				}

				display.setData(data);

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected products");
			}
		});
	}
}
