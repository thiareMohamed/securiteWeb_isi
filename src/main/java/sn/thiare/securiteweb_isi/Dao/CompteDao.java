package sn.thiare.securiteweb_isi.Dao;

import sn.thiare.securiteweb_isi.entity.dto.CompteDto;

import java.util.List;

public interface CompteDao {
    public boolean login(String login, String password);
    public int create(CompteDto comptesEntity, int idDroit) throws Exception;
    public int update(CompteDto comptesEntity) throws Exception;
    public int delete(CompteDto comptesEntity) throws Exception;
    public List<CompteDto> findAll() throws Exception;
    public int deleteById(int Id) throws Exception;
    public CompteDto findById(int id);

    public CompteDto findByEmail(String email);
}
