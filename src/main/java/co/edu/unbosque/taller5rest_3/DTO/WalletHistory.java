package co.edu.unbosque.taller5rest_3.DTO;

public class WalletHistory {


    private String email;
    private String type;
    private float fcoins;
    private String registeredAt;

    public WalletHistory(String email, String type, float fcoins, String registeredAt) {

        this.email = email;
        this.type = type;
        this.fcoins = fcoins;
        this.registeredAt = registeredAt;
    }

    public WalletHistory(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getFcoins() {
        return fcoins;
    }

    public void setFcoins(float fcoins) {
        this.fcoins = fcoins;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }
}
