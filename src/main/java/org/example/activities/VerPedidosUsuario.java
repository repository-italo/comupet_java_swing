package org.example.activities;

import org.example.activities.components.PedidoButton;
import org.example.database.dao.PedidoAdocaoDAO;
import org.example.database.modelos.PedidoAdocao;
import org.example.database.modelos.Usuario;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerPedidosUsuario extends JFrame {
    private JPanel mainPanel;
    private JPanel navbar;
    private JPanel innerbar;
    private JButton verPetsButton;
    private JLabel nameLabel;
    private JLabel labelLogo;
    private JPanel listPanel;
    private Usuario usuarioLogado;

    public VerPedidosUsuario(Usuario usuario){
        this.usuarioLogado = usuario;
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        createUI();
        labelLogo.setIcon(new ImageIcon("LOGO_COMUPET.png"));
        verPetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new TelaMenuPets(usuarioLogado);
                    setVisible(false);
                    frame.setVisible(true);
                });
            }
        });
    }

    public void createUI(){
        nameLabel.setText(usuarioLogado.getNome());
        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        EntityManager em = JPAUtil.getEntityManager();
        PedidoAdocaoDAO pedidoAdocaoDAO = new PedidoAdocaoDAO(em);

        em.getTransaction().begin();
        em.flush();
        List<PedidoAdocao> pedidos =  pedidoAdocaoDAO.pedidosPorPessoa(usuarioLogado);
        em.getTransaction().commit();
        em.close();
        pedidos.forEach(p -> {
            JButton pedidoButton = new PedidoButton(p, usuarioLogado);
            listPanel.add(pedidoButton);
        });
    }

}
