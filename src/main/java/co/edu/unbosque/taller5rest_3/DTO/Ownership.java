package co.edu.unbosque.taller5rest_3.DTO;

public class Ownership {

    private Integer owner_id;
    private Integer art_id;
    private String email;
    private String registeredAt;

    public Ownership(Integer owner_id, Integer art_id, String email, String registeredAt) {
        this.owner_id = owner_id;
        this.art_id = art_id;
        this.email = email;
        this.registeredAt = registeredAt;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }
}
