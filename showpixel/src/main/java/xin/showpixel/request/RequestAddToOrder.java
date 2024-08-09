package xin.showpixel.request;

public class RequestAddToOrder {
    private String productID;

    private int quantity;

    public RequestAddToOrder(){}

    public RequestAddToOrder(String productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
