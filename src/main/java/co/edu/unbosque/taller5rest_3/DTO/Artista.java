package co.edu.unbosque.taller5rest_3.DTO;

public class Artista {

    private Integer artista_id;
    private String descripcion;

    public Artista(Integer artista_id, String descripcion) {
        this.artista_id = artista_id;
        this.descripcion = descripcion;
    }

    public Integer getArtista_id() {
        return artista_id;
    }

    public void setArtista_id(Integer artista_id) {
        this.artista_id = artista_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
