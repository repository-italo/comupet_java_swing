package org.example.database.dao;

import org.example.database.modelos.PedidoAdocao;
import org.example.database.modelos.Pet;
import org.example.database.modelos.Usuario;

import javax.persistence.EntityManager;
// CLASSE ABSTRATA
public abstract class ClassDAO {
    public EntityManager em;

    public ClassDAO(EntityManager em){
        this.em = em;
    }

    void cadastrar(PedidoAdocao pedido) {

    }
// APLICAÇÃO DOS CONCEITOS DE POLIMORFISMO
    void cadastrar(Usuario usuario) {

    }

    void cadastrar(Pet pet) {

    }

    void atualizar (Pet pet){

    }
}
