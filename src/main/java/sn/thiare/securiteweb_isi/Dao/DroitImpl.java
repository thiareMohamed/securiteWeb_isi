package sn.thiare.securiteweb_isi.Dao;

import sn.thiare.securiteweb_isi.entity.DroitEntity;
import sn.thiare.securiteweb_isi.entity.dto.DroitDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class DroitImpl implements DroitDao {
    private EntityManager em;
    private EntityTransaction tx;
    public DroitImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @Override
    public int create(DroitDto droit) throws Exception {
        DroitEntity droitEntity = new DroitEntity();
        droitEntity.setName(droit.getName());
        try {
            tx.begin();
            em.persist(droitEntity);
            tx.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateDroit(DroitDto droit) throws Exception {
        tx.begin();
        DroitEntity droitEntity = em.find(DroitEntity.class, droit.getId());
        if (droitEntity == null) {
            throw new Exception("Droit introuvable");
        }
        droitEntity.setName(droit.getName());
        tx.commit();
        return 1;
    }


    @Override
    public DroitDto getDroitById(int id) {
        tx.begin();
        DroitEntity droitEntity = em.find(DroitEntity.class, id);
        tx.commit();
        DroitDto droitDto = new DroitDto(droitEntity.getId(), droitEntity.getName());
        return droitDto;
    }

    @Override
    public List<DroitDto> getAllDroit() {
        tx.begin();
        List<DroitEntity> droitEntities = em.createQuery("select d from DroitEntity d").getResultList();
        tx.commit();
        List<DroitDto> droitDtos = new ArrayList<>();
        droitEntities.forEach(droitEntity -> {
            DroitDto droitDto = new DroitDto();
            droitDto.setId(droitEntity.getId());
            droitDto.setName(droitEntity.getName());
            droitDtos.add(droitDto);
        });
        return droitDtos;
    }

    @Override
    public int deleteById(int id) throws Exception {
        tx.begin();
        DroitEntity droitEntity = em.find(DroitEntity.class, id);
        if (droitEntity == null) {
            throw new Exception("Droit introuvable");
        }
        em.remove(droitEntity);
        tx.commit();
        return 1;
    }
}
