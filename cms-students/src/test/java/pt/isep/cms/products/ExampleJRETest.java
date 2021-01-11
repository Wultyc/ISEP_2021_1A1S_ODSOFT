package pt.isep.cms.products;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.products.client.ProductsServiceAsync;
import pt.isep.cms.products.client.presenter.ProductsPresenter;
import pt.isep.cms.products.shared.ProductDetails;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private ProductsPresenter productsPresenter;
	private ProductsServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private ProductsPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(ProductsServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(ProductsPresenter.Display.class);
		productsPresenter = new ProductsPresenter(mockRpcService, eventBus, mockDisplay);
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
	
	
}
