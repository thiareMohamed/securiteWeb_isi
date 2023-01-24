package sn.thiare.securiteweb_isi.entity.dto;

import sn.thiare.securiteweb_isi.entity.DroitEntity;

import java.util.List;
import java.util.Set;

public class CompteDto {
    private int id;
    private String email;
    private String password;

    private DroitDto droit;

    public CompteDto() {
    }


    public CompteDto(int id, String email, String password, DroitDto droit) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.droit = droit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DroitDto getDroit() {
        return droit;
    }

    public void setDroit(DroitDto droit) {
        this.droit = droit;
    }

}
