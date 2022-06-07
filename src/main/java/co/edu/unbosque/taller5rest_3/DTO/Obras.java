package co.edu.unbosque.taller5rest_3.DTO;

public class Obras {

    private Integer obra_id;
    private String titulo;
    private String imagen;//URL
    private Integer precio;

    public Obras(Integer obra_id, String titulo, String imagen, Integer precio) {
        this.obra_id = obra_id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.precio = precio;
    }

    public Integer getObra_id() {
        return obra_id;
    }

    public void setObra_id(Integer obra_id) {
        this.obra_id = obra_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
