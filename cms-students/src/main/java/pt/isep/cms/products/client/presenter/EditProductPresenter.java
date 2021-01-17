package pt.isep.cms.products.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import pt.isep.cms.products.client.ProductsServiceAsync;
import pt.isep.cms.products.client.event.ProductUpdatedEvent;
import pt.isep.cms.products.client.event.EditProductCancelledEvent;
import pt.isep.cms.products.shared.Product;
import pt.isep.cms.batches.client.BatchesService;
import pt.isep.cms.batches.client.BatchesServiceAsync;
import pt.isep.cms.batches.shared.Batche;

import java.util.Locale;

public class EditProductPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getDescrip();

		HasValue<String> getPrice();
		
		HasValue<String> getBatch();

		void show();

		void hide();
	}

	private Product product;
	private final ProductsServiceAsync rpcService;
	private final BatchesServiceAsync batService;
	
	private final HandlerManager eventBus;
	private final Display display;

	public EditProductPresenter(ProductsServiceAsync rpcService, BatchesServiceAsync batService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.batService = batService;
		this.eventBus = eventBus;
		this.product = new Product();
		this.display = display;
		bind();
	}

	public EditProductPresenter(ProductsServiceAsync rpcService, BatchesServiceAsync batService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.batService = batService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getProduct(id, new AsyncCallback<Product>() {
			public void onSuccess(Product result) {
				product = result;
				EditProductPresenter.this.display.getName().setValue(product.getName());
				EditProductPresenter.this.display.getDescrip().setValue(product.getDescrip());
				EditProductPresenter.this.display.getPrice().setValue(product.getPrice());
				EditProductPresenter.this.display.getBatch().setValue(product.getBatch().toString());

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving product");
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
				eventBus.fireEvent(new EditProductCancelledEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}

	private void doSave() {
		product.setName(display.getName().getValue());
		product.setDescrip(display.getDescrip().getValue());
		product.setPrice(display.getPrice().getValue());
		batService.getBatch(display.getBatch().getValue(), new AsyncCallback<Batche>() {

			public void onSuccess (Batche result) {
				product.setBatch(result);
			}
			public void onFailure (Throwable caught) {
				Window.alert("Error adding batche: provide a valid WAREHOUSE ID");
			}
		});
			
			
		if (product.getId() == null) {
			// Adding new product
			rpcService.addProduct(product, new AsyncCallback<Product>() {
				public void onSuccess(Product result) {
					eventBus.fireEvent(new ProductUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error adding product");
				}
			});
		} else {
			// updating existing product
			rpcService.updateProduct(product, new AsyncCallback<Product>() {
				public void onSuccess(Product result) {
					eventBus.fireEvent(new ProductUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error updating product");
				}
			});
		}
	}

}
