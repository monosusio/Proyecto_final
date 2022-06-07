package co.edu.unbosque.taller5rest_3.DTO;

public class Collection {

    private Integer co_id;
    private String name;
    private String description;
    private String category;
    private String email;

    public Collection(Integer co_id, String name, String description, String category, String email) {
        this.co_id = co_id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.email = email;
    }

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
