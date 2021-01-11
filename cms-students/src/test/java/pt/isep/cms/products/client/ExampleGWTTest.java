package pt.isep.cms.products.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.products.client.ProductsService;
import pt.isep.cms.products.client.ProductsServiceAsync;
import pt.isep.cms.products.client.presenter.ProductsPresenter;
import pt.isep.cms.products.client.view.ProductsView;
import pt.isep.cms.products.shared.Product;
import pt.isep.cms.products.shared.ProductDetails;

// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
	private ProductsPresenter productsPresenter;
	private ProductsServiceAsync rpcService;
	private HandlerManager eventBus;
	private ProductsPresenter.Display mockDisplay;

	public String getModuleName() {
		return "pt.isep.cms.products.TestCMSJUnit";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(ProductsService.class);
		mockDisplay = new ProductsView();
		productsPresenter = new ProductsPresenter(rpcService, eventBus, mockDisplay);
	}

	public void testProductSort() {
		ArrayList<ProductDetails> productDetails = new ArrayList<ProductDetails>();
		productDetails.add(new ProductDetails("0", "c_product"));
		productDetails.add(new ProductDetails("1", "b_product"));
		productDetails.add(new ProductDetails("2", "a_product"));
		productsPresenter.setProductDetails(productDetails);
		productsPresenter.sortProductDetails();
		assertTrue(productsPresenter.getProductDetail(0).getDisplayName().equals("a_product"));
		assertTrue(productsPresenter.getProductDetail(1).getDisplayName().equals("b_product"));
		assertTrue(productsPresenter.getProductDetail(2).getDisplayName().equals("c_product"));
	}

	public void testProductsService() {
		// Create the service that we will test.
		ProductsServiceAsync productsService = GWT.create(ProductsService.class);
		ServiceDefTarget target = (ServiceDefTarget) productsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "products/productsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		productsService.getProduct("2", new AsyncCallback<Product>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Product result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}
}
