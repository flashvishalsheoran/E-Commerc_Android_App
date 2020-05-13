package devops.vishal.ecommerce.models;

public class CartModel {
    private String product;

    public CartModel(String product) {
        this.product = product;
    }

    public CartModel(){

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
