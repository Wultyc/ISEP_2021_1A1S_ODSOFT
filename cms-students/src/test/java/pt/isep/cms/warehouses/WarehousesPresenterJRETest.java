package pt.isep.cms.warehouses;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import junit.framework.TestCase;
import pt.isep.cms.warehouses.client.WarehousesServiceAsync;
import pt.isep.cms.warehouses.client.presenter.WarehousesPresenter;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;

import static org.easymock.EasyMock.createStrictMock;

public class WarehousesPresenterJRETest extends TestCase {
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

	public void testContactSort() {
		ArrayList<WarehouseDetails> warehousesDetails = new ArrayList<WarehouseDetails>();
		warehousesDetails.add(new WarehouseDetails("0", "c_warehouse"));
		warehousesDetails.add(new WarehouseDetails("1", "bb_warehouse"));
		warehousesDetails.add(new WarehouseDetails("3", "ba_warehouse"));
		warehousesDetails.add(new WarehouseDetails("2", "a_warehouse"));
		WarehousesPresenter.setWarehouseDetails(warehousesDetails);
		WarehousesPresenter.sortWarehouseDetails();
		assertTrue(WarehousesPresenter.getWarehouseDetail(0).getDisplayName().equals("a_warehouse"));
		assertTrue(WarehousesPresenter.getWarehouseDetail(1).getDisplayName().equals("ba_warehouse"));
		assertTrue(WarehousesPresenter.getWarehouseDetail(2).getDisplayName().equals("bb_warehouse"));
		assertTrue(WarehousesPresenter.getWarehouseDetail(3).getDisplayName().equals("c_warehouse"));
	}

}
