package pt.isep.cms.batches.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.batches.client.BatchesService;
import pt.isep.cms.batches.client.BatchesServiceAsync;
import pt.isep.cms.batches.client.presenter.BatchesPresenter;
import pt.isep.cms.batches.client.view.BatchesView;
import pt.isep.cms.batches.shared.Batche;
import pt.isep.cms.batches.shared.BatcheDetails;


// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
	private BatchesPresenter batchesPresenter;
	private BatchesServiceAsync rpcService;
	private HandlerManager eventBus;
	private BatchesPresenter.Display mockDisplay;

	public String getModuleName() {
		return "pt.isep.cms.batches.TestCMSJUnit";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(BatchesService.class);
		mockDisplay = new BatchesView();
		batchesPresenter = new BatchesPresenter(rpcService, eventBus, mockDisplay);
	}

	public void testBatcheSort() {
		ArrayList<BatcheDetails> batcheDetails = new ArrayList<BatcheDetails>();
		batcheDetails.add(new BatcheDetails("0", "c_batche"));
		batcheDetails.add(new BatcheDetails("1", "b_batche"));
		batcheDetails.add(new BatcheDetails("2", "a_batche"));
		batchesPresenter.setBatcheDetails(batcheDetails);
		batchesPresenter.sortBatcheDetails();
		assertTrue(batchesPresenter.getBatcheDetail(0).getDisplayName().equals("a_batche"));
		assertTrue(batchesPresenter.getBatcheDetail(1).getDisplayName().equals("b_batche"));
		assertTrue(batchesPresenter.getBatcheDetail(2).getDisplayName().equals("c_batche"));
	}

	public void testBatchesService() {
		// Create the service that we will test.
		BatchesServiceAsync batchesService = GWT.create(BatchesService.class);
		ServiceDefTarget target = (ServiceDefTarget) batchesService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "batches/batchesService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		batchesService.getBatche("2", new AsyncCallback<Batche>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Batche result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}
	
	
	public void testUpdateBatche() {
		// Create the service that we will test.
		BatchesServiceAsync batchesService = GWT.create(BatchesService.class);
		ServiceDefTarget target = (ServiceDefTarget) batchesService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "batches/batchesService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");
		
		// Post a batche
		batchesService.addBatche(new Batche(null, "abc", "descrip1", "12-12-2020"), new AsyncCallback<Batche>() {
					public void onFailure(Throwable caught) {
						// The request resulted in an unexpected error.
						fail("Request failure: " + caught.getMessage());
					}

					public void onSuccess(Batche result) {
						assertTrue(result != null);
						// Get batche by id
						batchesService.getBatche("2", new AsyncCallback<Batche>() {
							public void onFailure(Throwable caught) {
								// The request resulted in an unexpected error.
								fail("Request failure: " + caught.getMessage());
							}

							public void onSuccess(Batche result) {
								// change batche attributes
								result.setName("A123");
								result.setDescrip("descricao");
								result.setManDate("2-1-2021");
								
								

								// put (update batche)
								batchesService.updateBatche(result, new AsyncCallback<Batche>() {
									public void onFailure(Throwable caught) {
										// The request resulted in an unexpected error.
										fail("Request failure: " + caught.getMessage());
									}

									public void onSuccess(Batche result) {
										// just ensure the update was successful
										assertTrue(result != null);

										// get again to check if update worked
										batchesService.getBatche(result.getId(), new AsyncCallback<Batche>() {
											public void onFailure(Throwable caught) {
												// The request resulted in an unexpected error.
												fail("Request failure: " + caught.getMessage());
											}

											public void onSuccess(Batche result) {
												assertTrue(result != null);
												// checking if changes are equal to expected
												assertEquals("A123", result.getName());
												assertEquals("descricao", result.getDescrip());
												assertEquals("2-1-2021", result.getManDate());
												
												batchesService.deleteBatche(result.getId(), new AsyncCallback<Boolean>() {
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
