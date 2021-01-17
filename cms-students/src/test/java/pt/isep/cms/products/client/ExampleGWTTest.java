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
		productDetails.add(new ProductDetails("0", "c_Product"));
		productDetails.add(new ProductDetails("1", "b_Product"));
		productDetails.add(new ProductDetails("2", "a_Product"));
		productsPresenter.setProductDetails(productDetails);
		productsPresenter.sortProductDetails();
		assertTrue(productsPresenter.getProductDetail(0).getDisplayName().equals("a_Product"));
		assertTrue(productsPresenter.getProductDetail(1).getDisplayName().equals("b_Product"));
		assertTrue(productsPresenter.getProductDetail(2).getDisplayName().equals("c_Product"));
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
	
/*	public void testUpdateProduct() {
		// Create the service that we will test.
				ProductsServiceAsync productsService = GWT.create(ProductsService.class);
				ServiceDefTarget target = (ServiceDefTarget) productsService;
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "products/productsService");

				// Since RPC calls are asynchronous, we will need to wait for a response
				// after this test method returns. This line tells the test runner to wait
				// up to 10 seconds before timing out.
				delayTestFinish(10000);

				// fail("Ainda nao implementado");
		
				// Post a warehouse
				productsService.addProduct(new Product(null, "ProdutoX", "excelente", "500"), new AsyncCallback<Product>() {
					public void onFailure(Throwable caught) {
						// The request resulted in an unexpected error.
						fail("Request failure: " + caught.getMessage());
					}

					public void onSuccess(Product result) {
						assertTrue(result != null);
						// Get warehouse by id
						productsService.getProduct("2", new AsyncCallback<Product>() {
							public void onFailure(Throwable caught) {
								// The request resulted in an unexpected error.
								fail("Request failure: " + caught.getMessage());
							}

							public void onSuccess(Product result) {
								// change warehouse attributes
								
								result.setName("Prod1");
								result.setDescrip("mtBom");
								result.setPrice("20");
								
	
								// put (update product)
								productsService.updateProduct(result, new AsyncCallback<Product>() {
									public void onFailure(Throwable caught) {
										// The request resulted in an unexpected error.
										fail("Request failure: " + caught.getMessage());
									}

									public void onSuccess(Product result) {
										// just ensure the update was successful
										assertTrue(result != null);

										// get again to check if update worked
										productsService.getProduct(result.getId(), new AsyncCallback<Product>() {
											public void onFailure(Throwable caught) {
												// The request resulted in an unexpected error.
												fail("Request failure: " + caught.getMessage());
											}

											public void onSuccess(Product result) {
												assertTrue(result != null);
												// checking if changes are equal to expected
												assertEquals("Prod1", result.getName());
												assertEquals("mtBom", result.getDescrip());
												assertEquals("20", result.getPrice());
												
												
												productsService.deleteProduct(result.getId(), new AsyncCallback<Boolean>() {
													public void onFailure(Throwable caught) {
														// The request resulted in an unexpected error.
														fail("Request failure: " + caught.getMessage());
													}
													public void onSuccess(Boolean result) {
														assertTrue(result);
														// Now that we have received a response, we need to tell the test runner
														// that the test is complete. You must call finishTest() after an
														// asynchronous test finishes successfully, or the test will time out.
														finishTest();
													}
												});
											}
										});
									}
								});
							}
						});
					}
				});
	}*/
}
