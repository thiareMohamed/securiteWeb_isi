package sn.thiare.securiteweb_isi.Dao;


import sn.thiare.securiteweb_isi.entity.ComptesEntity;
import sn.thiare.securiteweb_isi.entity.dto.CompteDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CompteImpl implements CompteDao {

    private EntityManager em;
    private EntityTransaction tx;
    public CompteImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public int create(ComptesEntity comptesEntity) {
        try {
            tx.begin();
            em.persist(comptesEntity);
            tx.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(ComptesEntity comptesEntity) throws Exception {
        tx.begin();
        ComptesEntity compte = em.find(ComptesEntity.class, comptesEntity.getId());
        if (compte == null) {
            throw new Exception("Compte introuvable");
        }
        compte.setEmail(comptesEntity.getEmail());
        compte.setPassword(comptesEntity.getPassword());
        tx.commit();
        return 1;
    }

    @Override
    public int delete(ComptesEntity comptesEntity) throws Exception {
        tx.begin();
        ComptesEntity compte = em.find(ComptesEntity.class, comptesEntity.getId());
        if (compte == null) {
            throw new Exception("Compte introuvable");
        }
        em.remove(compte);
        tx.commit();
        return 1;
    }
    @Override
    public List<ComptesEntity> findAll() throws Exception {
        tx.begin();
        List<ComptesEntity> comptes = em.createQuery("SELECT c FROM ComptesEntity c").getResultList();
        tx.commit();
        return comptes;
    }

    @Override
    public int deleteById(int id) throws Exception {
        ComptesEntity compte = em.find(ComptesEntity.class, id);
        if (compte == null) {
            throw new Exception("Compte introuvable");
        }
        em.remove(compte);
        tx.commit();
        return 1;
    }
}
