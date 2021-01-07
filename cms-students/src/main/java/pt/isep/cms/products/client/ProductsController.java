package pt.isep.cms.products.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.products.client.event.AddProductEvent;
import pt.isep.cms.products.client.event.AddProductEventHandler;
import pt.isep.cms.products.client.event.ProductUpdatedEvent;
import pt.isep.cms.products.client.event.ProductUpdatedEventHandler;
import pt.isep.cms.products.client.event.EditProductEvent;
import pt.isep.cms.products.client.event.EditProductEventHandler;
import pt.isep.cms.products.client.event.EditProductCancelledEvent;
import pt.isep.cms.products.client.event.EditProductCancelledEventHandler;
import pt.isep.cms.products.client.presenter.ProductsPresenter;
import pt.isep.cms.products.client.presenter.EditProductPresenter;
import pt.isep.cms.products.client.presenter.Presenter;
import pt.isep.cms.products.client.view.ProductsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.products.client.view.ProductsDialog;

public class ProductsController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final ProductsServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public ProductsController(ProductsServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddProductEvent.TYPE, new AddProductEventHandler() {
			public void onAddProduct(AddProductEvent event) {
				doAddNewProduct();
			}
		});

		eventBus.addHandler(EditProductEvent.TYPE, new EditProductEventHandler() {
			public void onEditProduct(EditProductEvent event) {
				doEditProduct(event.getId());
			}
		});

		eventBus.addHandler(EditProductCancelledEvent.TYPE, new EditProductCancelledEventHandler() {
			public void onEditProductCancelled(EditProductCancelledEvent event) {
				doEditProductCancelled();
			}
		});

		eventBus.addHandler(ProductUpdatedEvent.TYPE, new ProductUpdatedEventHandler() {
			public void onProductUpdated(ProductUpdatedEvent event) {
				doProductUpdated();
			}
		});
	}

	private void doAddNewProduct() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new EditProductPresenter(rpcService, eventBus, new ProductsDialog(globalConstants, ProductsDialog.Type.ADD));
		presenter.go(container);

	}

	private void doEditProduct(String id) {
		Presenter presenter = new EditProductPresenter(rpcService, eventBus, new ProductsDialog(globalConstants, ProductsDialog.Type.UPDATE), id);
		presenter.go(container);
	}

	private void doEditProductCancelled() {
		// Nothing to update...
	}

	private void doProductUpdated() {
		// (ATB) Update the list of products...
		Presenter presenter = new ProductsPresenter(rpcService, eventBus, new ProductsView());
		presenter.go(container);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new ProductsPresenter(rpcService, eventBus, new ProductsView());
		presenter.go(container);
	}

}
