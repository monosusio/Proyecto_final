package co.edu.unbosque.taller5rest_3.DTO;

public class Likes {
    private Integer like_id;
    private Integer art_id;
    private String email;
    private String registeredAt;

    public Likes(Integer like_id, Integer art_id, String email, String registeredAt) {
        this.like_id = like_id;
        this.art_id = art_id;
        this.email = email;
        this.registeredAt = registeredAt;
    }

    public Integer getLike_id() {
        return like_id;
    }

    public void setLike_id(Integer like_id) {
        this.like_id = like_id;
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
