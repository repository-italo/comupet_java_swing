package org.example.database.dao;

import org.example.database.modelos.Vacina;

import javax.persistence.EntityManager;

public class VacinaDAO {
    private EntityManager em;

    public VacinaDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Vacina vacina){
        this.em.persist(vacina);
    }
}
