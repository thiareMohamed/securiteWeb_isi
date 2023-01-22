package sn.thiare.securiteweb_isi.Dao;


import sn.thiare.securiteweb_isi.entity.ComptesEntity;
import sn.thiare.securiteweb_isi.entity.dto.CompteDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
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
    public int create(CompteDto compteDto) {
        ComptesEntity comptesEntity = new ComptesEntity();
        comptesEntity.setEmail(compteDto.getEmail());
        comptesEntity.setPassword(compteDto.getPassword());
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
    public int update(CompteDto compteDto) throws Exception {
        tx.begin();
        ComptesEntity compte = em.find(ComptesEntity.class, compteDto.getId());
        if (compte == null) {
            throw new Exception("Compte introuvable");
        }
        compte.setEmail(compteDto.getEmail());
        compte.setPassword(compteDto.getPassword());
        tx.commit();
        return 1;
    }

    @Override
    public int delete(CompteDto compteDto) throws Exception {
        tx.begin();
        ComptesEntity compte = em.find(ComptesEntity.class, compteDto.getId());
        if (compte == null) {
            throw new Exception("Compte introuvable");
        }
        em.remove(compte);
        tx.commit();
        return 1;
    }
    @Override
    public List<CompteDto> findAll() throws Exception {
        tx.begin();
        List<ComptesEntity> comptes = em.createQuery("SELECT c FROM ComptesEntity c").getResultList();
        tx.commit();
        List<CompteDto> compteDtos = new ArrayList<>();
        comptes.forEach(comptesEntity -> {
            CompteDto compteDto = new CompteDto();
            compteDto.setId(comptesEntity.getId());
            compteDto.setEmail(comptesEntity.getEmail());
            compteDto.setPassword(comptesEntity.getPassword());
            compteDtos.add(compteDto);
        });

        return compteDtos;
    }

    @Override
    public int deleteById(int id) throws Exception {
        tx.begin();
        ComptesEntity compte = em.find(ComptesEntity.class, id);
        if (compte == null) {
            throw new Exception("Compte introuvable");
        }
        em.remove(compte);
        tx.commit();
        return 1;
    }

    @Override
    public CompteDto findById(int id) {
        tx.begin();
        ComptesEntity comptesEntity = em.find(ComptesEntity.class, id);
        tx.commit();
        CompteDto compteDto = new CompteDto(comptesEntity.getId(), comptesEntity.getEmail(), comptesEntity.getPassword());
        return compteDto;
    }
}
