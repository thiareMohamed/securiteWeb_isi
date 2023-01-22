package sn.thiare.securiteweb_isi.Dao;

import sn.thiare.securiteweb_isi.entity.ComptesEntity;
import sn.thiare.securiteweb_isi.entity.dto.CompteDto;

import java.util.List;

public interface CompteDao {
    public boolean login(String login, String password);
    public int create(ComptesEntity comptesEntity);
    public int update(ComptesEntity comptesEntity) throws Exception;
    public int delete(ComptesEntity comptesEntity) throws Exception;
    public List<ComptesEntity> findAll() throws Exception;
    public int deleteById(int Id) throws Exception;
    public ComptesEntity findById(int id);
}
