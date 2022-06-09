package co.edu.unbosque.taller5rest_3.DTO;

public class Art {

    private String name;
    private float price;
    private String imagePath;
    private boolean forSale;
    private Integer co_id;

    public Art(String name, float price, String imagePath, boolean forSale, Integer co_id) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.forSale = forSale;
        this.co_id = co_id;
    }

    public Art() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }
}
