package sn.thiare.securiteweb_isi.Dao;

import sn.thiare.securiteweb_isi.entity.DroitEntity;

import java.util.List;

public interface DroitDao{
    public void create(DroitEntity droit) throws Exception;
    public void updateDroit(DroitEntity droit) throws Exception;
    public void deleteDroit(DroitEntity droit) throws Exception;
    public DroitEntity getDroitById(int id) throws Exception;
    public List<DroitEntity> getAllDroit() throws Exception;
}
