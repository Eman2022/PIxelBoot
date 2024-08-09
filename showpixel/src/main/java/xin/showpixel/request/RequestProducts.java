package xin.showpixel.request;

public class RequestProducts {
    private int page;

    private int numberPerPage;

    public RequestProducts(){}

    public RequestProducts(int page, int numberPerPage) {
        this.page = page;
        this.numberPerPage = numberPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNumberPerPage() {
        return numberPerPage;
    }

    public void setNumberPerPage(int numberPerPage) {
        this.numberPerPage = numberPerPage;
    }
}
