package pt.isep.cms.shippingLocations;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.shippingLocations.client.ShippingLocationsServiceAsync;
import pt.isep.cms.shippingLocations.client.presenter.ShippingLocationsPresenter;
import pt.isep.cms.shippingLocations.shared.ShippingLocationDetails;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private ShippingLocationsPresenter shippingLocationsPresenter;
	private ShippingLocationsServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private ShippingLocationsPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(ShippingLocationsServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(ShippingLocationsPresenter.Display.class);
		shippingLocationsPresenter = new ShippingLocationsPresenter(mockRpcService, eventBus, mockDisplay);
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
}
