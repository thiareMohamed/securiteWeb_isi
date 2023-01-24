package sn.thiare.securiteweb_isi.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "comptes", schema = "securiteweb_isi")
public class ComptesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;

    @OneToOne()
    @JoinColumn(name = "idDroit", referencedColumnName = "id")
    private DroitEntity droitEntity;

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

    public DroitEntity getDroitEntity() {
        return droitEntity;
    }

    public void setDroitEntity(DroitEntity droitEntity) {
        this.droitEntity = droitEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComptesEntity that = (ComptesEntity) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
