package pt.isep.cms.products;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.products.client.ProductsServiceAsync;
import pt.isep.cms.products.client.presenter.ProductsPresenter;
import pt.isep.cms.products.shared.ProductDetails;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private ProductsPresenter ProductsPresenter;
	private ProductsServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private ProductsPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(ProductsServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(ProductsPresenter.Display.class);
		ProductsPresenter = new ProductsPresenter(mockRpcService, eventBus, mockDisplay);
	}

	public void testProductSort() {
		ArrayList<ProductDetails> ProductDetails = new ArrayList<ProductDetails>();
		ProductDetails.add(new ProductDetails("0", "c_Product"));
		ProductDetails.add(new ProductDetails("1", "b_Product"));
		ProductDetails.add(new ProductDetails("2", "a_Product"));
		ProductsPresenter.setProductDetails(ProductDetails);
		ProductsPresenter.sortProductDetails();
		assertTrue(ProductsPresenter.getProductDetail(0).getDisplayName().equals("a_Product"));
		assertTrue(ProductsPresenter.getProductDetail(1).getDisplayName().equals("b_Product"));
		assertTrue(ProductsPresenter.getProductDetail(2).getDisplayName().equals("c_Product"));
	}
}
