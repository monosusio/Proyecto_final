package co.edu.unbosque.taller5rest_3.DTO;

public class Comprador {

    private Integer comprador_id;
    private Integer obra_id;
    private Integer fcoins;

    public Comprador(Integer comprador_id, Integer obra_id, Integer fcoins) {
        this.comprador_id = comprador_id;
        this.obra_id = obra_id;
        this.fcoins = fcoins;
    }

    public Integer getComprador_id() {
        return comprador_id;
    }

    public void setComprador_id(Integer comprador_id) {
        this.comprador_id = comprador_id;
    }

    public Integer getObra_id() {
        return obra_id;
    }

    public void setObra_id(Integer obra_id) {
        this.obra_id = obra_id;
    }

    public Integer getFcoins() {
        return fcoins;
    }

    public void setFcoins(Integer fcoins) {
        this.fcoins = fcoins;
    }
}
