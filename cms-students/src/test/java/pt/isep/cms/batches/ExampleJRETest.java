package pt.isep.cms.batches;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.batches.client.BatchesServiceAsync;
import pt.isep.cms.batches.client.presenter.BatchesPresenter;
import pt.isep.cms.batches.shared.BatcheDetails;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private BatchesPresenter BatchesPresenter;
	private BatchesServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private BatchesPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(BatchesServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(BatchesPresenter.Display.class);
		BatchesPresenter = new BatchesPresenter(mockRpcService, eventBus, mockDisplay);
	}

	public void testProductSort() {
		ArrayList<BatcheDetails> BatcheDetails = new ArrayList<BatcheDetails>();
		BatcheDetails.add(new BatcheDetails("0", "c_Batche"));
		BatcheDetails.add(new BatcheDetails("1", "b_Batche"));
		BatcheDetails.add(new BatcheDetails("2", "a_Batche"));
		BatchesPresenter.setBatcheDetails(BatcheDetails);
		BatchesPresenter.sortBatcheDetails();
		assertTrue(BatchesPresenter.getBatcheDetail(0).getDisplayName().equals("a_Batche"));
		assertTrue(BatchesPresenter.getBatcheDetail(1).getDisplayName().equals("b_Batche"));
		assertTrue(BatchesPresenter.getBatcheDetail(2).getDisplayName().equals("c_Batche"));
	}
}
