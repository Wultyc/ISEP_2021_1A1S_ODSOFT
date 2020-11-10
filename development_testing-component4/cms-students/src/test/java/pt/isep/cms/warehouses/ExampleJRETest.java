package pt.isep.cms.warehouses;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.warehouses.client.WarehousesServiceAsync;
import pt.isep.cms.warehouses.client.presenter.WarehousesPresenter;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private WarehousesPresenter WarehousesPresenter;
	private WarehousesServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private WarehousesPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(WarehousesServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(WarehousesPresenter.Display.class);
		WarehousesPresenter = new WarehousesPresenter(mockRpcService, eventBus, mockDisplay);
	}

	public void testWarehouseSort() {
		ArrayList<WarehouseDetails> WarehouseDetails = new ArrayList<WarehouseDetails>();
		WarehouseDetails.add(new WarehouseDetails("0", "c_Warehouse"));
		WarehouseDetails.add(new WarehouseDetails("1", "b_Warehouse"));
		WarehouseDetails.add(new WarehouseDetails("2", "a_Warehouse"));
		WarehousesPresenter.setWarehouseDetails(WarehouseDetails);
		WarehousesPresenter.sortWarehouseDetails();
		assertTrue(WarehousesPresenter.getWarehouseDetail(0).getDisplayName().equals("a_Warehouse"));
		assertTrue(WarehousesPresenter.getWarehouseDetail(1).getDisplayName().equals("b_Warehouse"));
		assertTrue(WarehousesPresenter.getWarehouseDetail(2).getDisplayName().equals("c_Warehouse"));
	}
}
