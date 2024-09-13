package org.example.database.dao;

import org.example.database.modelos.PedidoAdocao;
import org.example.database.modelos.Usuario;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
//Aplicação dos conceitos de HERANÇA
public class PedidoAdocaoDAO extends ClassDAO {

    public PedidoAdocaoDAO(EntityManager em){
        super(em);
    }

    public List<PedidoAdocao> pedidosPorPessoa (Usuario usuario){
        String jpql = "SELECT p FROM PedidoAdocao p";

        List<PedidoAdocao> pedidos = this.em.createQuery(jpql, PedidoAdocao.class).getResultList();
        return pedidos.stream()
                .filter(p -> p.getUsuario_pedinte().getId().equals(usuario.getId()))
                .collect(Collectors.toList());
    }

    public List<PedidoAdocao> buscarTodosPedidos(){
        String jpql = "SELECT p FROM PedidoAdocao p";
        return this.em.createQuery(jpql, PedidoAdocao.class).getResultList();
    }

    @Override
    public void cadastrar(PedidoAdocao pedido) {
        this.em.persist(pedido);
    }


}
