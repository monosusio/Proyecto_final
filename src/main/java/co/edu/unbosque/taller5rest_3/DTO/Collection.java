package co.edu.unbosque.taller5rest_3.DTO;

public class Collection {

    //private Integer co_id;
    private String name;
    private String description;
    private String category;
    private String email;

    public Collection(String name, String description, String category, String email) {

        this.name = name;
        this.description = description;
        this.category = category;
        this.email = email;
    }

    public Collection(){

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
