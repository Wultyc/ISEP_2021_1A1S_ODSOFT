package pt.isep.cms.batches;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.batches.client.BatchesServiceAsync;
import pt.isep.cms.batches.client.presenter.BatchesPresenter;
import pt.isep.cms.batches.shared.BatcheDetails;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private BatchesPresenter batchesPresenter;
	private BatchesServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private BatchesPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(BatchesServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(BatchesPresenter.Display.class);
		batchesPresenter = new BatchesPresenter(mockRpcService, eventBus, mockDisplay);
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
}
