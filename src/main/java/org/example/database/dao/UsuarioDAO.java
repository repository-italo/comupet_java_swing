package org.example.database.dao;

import org.example.database.modelos.Usuario;

import javax.persistence.EntityManager;

public class UsuarioDAO extends ClassDAO {

    public UsuarioDAO (EntityManager em){
        super(em);
    }
    @Override
    public void cadastrar (Usuario usuario){
        this.em.persist(usuario);
    }

    public Usuario buscarPorEmail(String email){
        String jpql = "SELECT u FROM Usuario u WHERE u.email = :email";
        return this.em.createQuery(jpql, Usuario.class).setParameter("email", email).getSingleResult();
    }

    public Usuario buscarPorId(Long id){
        return this.em.find(Usuario.class, id);
    }
}
