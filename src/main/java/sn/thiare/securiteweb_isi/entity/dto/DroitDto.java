package sn.thiare.securiteweb_isi.entity.dto;

public class DroitDto {
    private int id;
    private String name;

    public DroitDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DroitDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
