package sn.thiare.securiteweb_isi.Dao;


import sn.thiare.securiteweb_isi.entity.ComptesEntity;
import sn.thiare.securiteweb_isi.entity.DroitEntity;
import sn.thiare.securiteweb_isi.entity.dto.CompteDto;
import sn.thiare.securiteweb_isi.entity.dto.DroitDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CompteImpl implements CompteDao {

    private EntityManager em;
    private EntityTransaction tx;
    private DroitDao droitDao;
    public CompteImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        droitDao = new DroitImpl();
    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public int create(CompteDto compteDto, int idDroit) throws Exception {
        ComptesEntity comptesEntity = new ComptesEntity();
        comptesEntity.setEmail(compteDto.getEmail());
        comptesEntity.setPassword(compteDto.getPassword());
        DroitDto droitEntity = droitDao.getDroitById(idDroit);
        DroitEntity droitEntity1 = new DroitEntity();
        droitEntity1.setId(droitEntity.getId());
        droitEntity1.setName(droitEntity.getName());
        comptesEntity.setDroitEntity(droitEntity1);
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
        DroitEntity droitEntity = new DroitEntity();
        droitEntity.setId(compteDto.getDroit().getId());
        droitEntity.setName(compteDto.getDroit().getName());
        compte.setDroitEntity(droitEntity);
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
         List<ComptesEntity> comptesEntities = em.createQuery("select c from ComptesEntity c").getResultList();
            List<CompteDto> compteDtos = new ArrayList<>();
            for (ComptesEntity comptesEntity : comptesEntities) {
                CompteDto compteDto = new CompteDto();
                compteDto.setId(comptesEntity.getId());
                compteDto.setEmail(comptesEntity.getEmail());
                compteDto.setPassword(comptesEntity.getPassword());
                DroitDto droitDto = new DroitDto();
                droitDto.setId(comptesEntity.getDroitEntity().getId());
                droitDto.setName(comptesEntity.getDroitEntity().getName());
                compteDto.setDroit(droitDto);
                compteDtos.add(compteDto);
            }
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
        CompteDto compteDto = new CompteDto(comptesEntity.getId(), comptesEntity.getEmail(), comptesEntity.getPassword(), new DroitDto(comptesEntity.getDroitEntity().getId(), comptesEntity.getDroitEntity().getName()));
        return compteDto;
    }

    @Override
    public CompteDto findByEmail(String email) {
        tx.begin();
        ComptesEntity comptesEntity = em.find(ComptesEntity.class, email);
        tx.commit();
        DroitDto droitDto = new DroitDto(comptesEntity.getDroitEntity().getId(), comptesEntity.getDroitEntity().getName());
        CompteDto compteDto = new CompteDto(comptesEntity.getId(), comptesEntity.getEmail(), comptesEntity.getPassword(), droitDto);
        return compteDto;
    }
}
