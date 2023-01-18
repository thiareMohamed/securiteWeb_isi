package sn.thiare.securiteweb_isi.Dao;

import sn.thiare.securiteweb_isi.entity.DroitEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
    public void create(DroitEntity droit) throws Exception {
        tx.begin();
        em.persist(droit);
        tx.commit();
    }

    @Override
    public void updateDroit(DroitEntity droit) {
        tx.begin();
        em.merge(droit);
        tx.commit();
    }

    @Override
    public void deleteDroit(DroitEntity droit) {
        tx.begin();
        em.remove(droit);
        tx.commit();
    }

    @Override
    public DroitEntity getDroitById(int id) {
        return em.find(DroitEntity.class, id);
    }

    @Override
    public List<DroitEntity> getAllDroit() {
        return em.createQuery("select d from DroitEntity d").getResultList();
    }
}
