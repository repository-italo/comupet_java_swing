package org.example.database.dao;

import org.example.database.modelos.Pet;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PetDAO extends ClassDAO {
    public PetDAO(EntityManager em){
        super(em);
    }

    @Override
    public void cadastrar (Pet pet){
        this.em.persist(pet);
    }

    @Override
    public void atualizar(Pet pet){
        this.em.merge(pet);
    }

    public void remover(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Pet pet = em.find(Pet.class, id);
            if (pet != null) {
                // Remove a entidade
                em.remove(pet);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void safeDelete (int id){
        String jpql = "DELETE FROM Pet WHERE id = :id";
        int deleted = em.createQuery(jpql).setParameter("id", id).executeUpdate();
    }
    public Pet buscarPorId (Long id){
        return this.em.find(Pet.class, id);
    }

    public List<Pet> buscarTodos (){
        String jpql = "SELECT p FROM Pet p";
        return em.createQuery(jpql, Pet.class).getResultList();
    }
}
