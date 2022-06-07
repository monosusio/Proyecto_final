package co.edu.unbosque.taller5rest_3.DTO;

public class Coleccion {

    private Integer collection_id;
    private String nombre;
    private String descripcion;
    private Integer obra_id;

    public Coleccion(Integer collection_id, String nombre, String descripcion, Integer obra_id) {
        this.collection_id = collection_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obra_id = obra_id;
    }

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getObra_id() {
        return obra_id;
    }

    public void setObra_id(Integer obra_id) {
        this.obra_id = obra_id;
    }
}
