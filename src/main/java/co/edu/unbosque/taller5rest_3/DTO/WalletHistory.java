package co.edu.unbosque.taller5rest_3.DTO;

import java.sql.Date;
import java.sql.Timestamp;

public class WalletHistory {

    private Integer wh_id;
    private String email;
    private float fcoins;
    private Timestamp registeredAt;

    public WalletHistory(Integer wh_id, String email, float fcoins, Timestamp registeredAt) {
        this.wh_id = wh_id;
        this.email = email;
        this.fcoins = fcoins;
        this.registeredAt = registeredAt;
    }

    public WalletHistory(){

    }

    public Integer getWh_id() {
        return wh_id;
    }

    public void setWh_id(Integer wh_id) {
        this.wh_id = wh_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getFcoins() {
        return fcoins;
    }

    public void setFcoins(float fcoins) {
        this.fcoins = fcoins;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }
}
