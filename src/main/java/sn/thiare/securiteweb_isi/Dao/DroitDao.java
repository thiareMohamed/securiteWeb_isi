package sn.thiare.securiteweb_isi.Dao;

import sn.thiare.securiteweb_isi.entity.DroitEntity;
import sn.thiare.securiteweb_isi.entity.dto.DroitDto;

import java.util.List;

public interface DroitDao{
    public int create(DroitDto droit) throws Exception;
    public int updateDroit(DroitDto droit) throws Exception;
    public DroitDto getDroitById(int id) throws Exception;
    public List<DroitDto> getAllDroit() throws Exception;
    public int deleteById(int id) throws Exception;
}
