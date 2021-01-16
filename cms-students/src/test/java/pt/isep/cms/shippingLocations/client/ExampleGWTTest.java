package pt.isep.cms.shippingLocations.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import pt.isep.cms.batches.client.BatchesService;
import pt.isep.cms.batches.client.BatchesServiceAsync;
import pt.isep.cms.batches.shared.Batche;
import pt.isep.cms.shippingLocations.client.ShippingLocationsService;
import pt.isep.cms.shippingLocations.client.ShippingLocationsServiceAsync;
import pt.isep.cms.shippingLocations.client.presenter.ShippingLocationsPresenter;
import pt.isep.cms.shippingLocations.client.view.ShippingLocationsView;
import pt.isep.cms.shippingLocations.shared.ShippingLocation;
import pt.isep.cms.shippingLocations.shared.ShippingLocationDetails;


// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
	private ShippingLocationsPresenter shippingLocationsPresenter;
	private ShippingLocationsServiceAsync rpcService;
	private HandlerManager eventBus;
	private ShippingLocationsPresenter.Display mockDisplay;

	public String getModuleName() {
		return "pt.isep.cms.shippingLocations.TestCMSJUnit";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(ShippingLocationsService.class);
		mockDisplay = new ShippingLocationsView();
		shippingLocationsPresenter = new ShippingLocationsPresenter(rpcService, eventBus, mockDisplay);
	}

	public void testShippingLocationSort() {
		ArrayList<ShippingLocationDetails> shippingLocationDetails = new ArrayList<ShippingLocationDetails>();
		shippingLocationDetails.add(new ShippingLocationDetails("0", "c_shippingLocation"));
		shippingLocationDetails.add(new ShippingLocationDetails("1", "b_shippingLocation"));
		shippingLocationDetails.add(new ShippingLocationDetails("2", "a_shippingLocation"));
		shippingLocationsPresenter.setShippingLocationDetails(shippingLocationDetails);
		shippingLocationsPresenter.sortShippingLocationDetails();
		assertTrue(shippingLocationsPresenter.getShippingLocationDetail(0).getDisplayName().equals("a_shippingLocation"));
		assertTrue(shippingLocationsPresenter.getShippingLocationDetail(1).getDisplayName().equals("b_shippingLocation"));
		assertTrue(shippingLocationsPresenter.getShippingLocationDetail(2).getDisplayName().equals("c_shippingLocation"));
	}

	public void testShippingLocationsService() {
		// Create the service that we will test.
		ShippingLocationsServiceAsync shippingLocationsService = GWT.create(ShippingLocationsService.class);
		ServiceDefTarget target = (ServiceDefTarget) shippingLocationsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "shippingLocations/shippingLocationsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		shippingLocationsService.getShippingLocation("2", new AsyncCallback<ShippingLocation>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(ShippingLocation result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}
	
	public void testUpdateShippingLocation() {
		// Create the service that we will test.
			ShippingLocationsServiceAsync shippingLocationsService = GWT.create(ShippingLocationsService.class);
			ServiceDefTarget target = (ServiceDefTarget) shippingLocationsService;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + "shippingLocations/shippingLocationsService");

			// Since RPC calls are asynchronous, we will need to wait for a response
			// after this test method returns. This line tells the test runner to wait
			// up to 10 seconds before timing out.
			delayTestFinish(10000);

			// fail("Ainda nao implementado");
		
		// Post a shipp loc
		shippingLocationsService.addShippingLocation(new ShippingLocation(null, "abc"), new AsyncCallback<ShippingLocation>() {
					public void onFailure(Throwable caught) {
						// The request resulted in an unexpected error.
						fail("Request failure: " + caught.getMessage());
					}

					public void onSuccess(ShippingLocation result) {
						assertTrue(result != null);
						// Get shipp loc by id
						shippingLocationsService.getShippingLocation("2", new AsyncCallback<ShippingLocation>() {
							public void onFailure(Throwable caught) {
								// The request resulted in an unexpected error.
								fail("Request failure: " + caught.getMessage());
							}

							public void onSuccess(ShippingLocation result) {
								// change shipp loc attributes
								result.setName("ABC123");
																
								

								// put (update shipp loc)
								shippingLocationsService.updateShippingLocation(result, new AsyncCallback<ShippingLocation>() {
									public void onFailure(Throwable caught) {
										// The request resulted in an unexpected error.
										fail("Request failure: " + caught.getMessage());
									}

									public void onSuccess(ShippingLocation result) {
										// just ensure the update was successful
										assertTrue(result != null);

										// get again to check if update worked
										shippingLocationsService.getShippingLocation(result.getId(), new AsyncCallback<ShippingLocation>() {
											public void onFailure(Throwable caught) {
												// The request resulted in an unexpected error.
												fail("Request failure: " + caught.getMessage());
											}

											public void onSuccess(ShippingLocation result) {
												assertTrue(result != null);
												// checking if changes are equal to expected
												assertEquals("ABC123", result.getName());
												
												
												shippingLocationsService.deleteShippingLocation(result.getId(), new AsyncCallback<Boolean>() {
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
	}
	
	
}
