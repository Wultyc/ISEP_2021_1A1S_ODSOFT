package pt.isep.cms.products.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.products.shared.Product;

public class ProductUpdatedEvent extends GwtEvent<ProductUpdatedEventHandler>{
  public static Type<ProductUpdatedEventHandler> TYPE = new Type<ProductUpdatedEventHandler>();
  private final Product updatedProduct;
  
  public ProductUpdatedEvent(Product updatedProduct) {
    this.updatedProduct = updatedProduct;
  }
  
  public Product getUpdatedProduct() { return updatedProduct; }
  

  @Override
  public Type<ProductUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ProductUpdatedEventHandler handler) {
    handler.onProductUpdated(this);
  }
}
