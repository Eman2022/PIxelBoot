package xin.showpixel.request;

public class RequestSubmitOrder {
    private String orderId;

    private String requestingUserId;

    public RequestSubmitOrder() {}

    public RequestSubmitOrder(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRequestingUserId() {
        return requestingUserId;
    }

    public void setRequestingUserId(String requestingUserId) {
        this.requestingUserId = requestingUserId;
    }
}
