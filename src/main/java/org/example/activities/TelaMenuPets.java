package org.example.activities;

import org.example.activities.components.PetBox;
import org.example.database.dao.PetDAO;
import org.example.database.modelos.Pet;
import org.example.database.modelos.Usuario;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMenuPets extends JFrame {
    private JPanel mainPanel;
    private JPanel navbar;
    private JPanel innerbar;
    private JButton cadastrarPetButton;
    private JLabel labelLogo;
    private JLabel nameLabel;
    private JPanel listLayout;
    private JButton buttonVerPedidosAdocao;
    private JButton buttonVetTodosPedidos;
    private Usuario usuarioLogado;

    public TelaMenuPets(Usuario usuario){
        this.usuarioLogado = usuario;
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        ImageIcon img = new ImageIcon("LOGO_COMUPET.png");
        nameLabel.setText("Olá " + this.usuarioLogado.getNome());

        if(!this.usuarioLogado.isAdmin()){
            buttonVerPedidosAdocao.setVisible(true);
            cadastrarPetButton.setVisible(false);
            buttonVetTodosPedidos.setVisible(false);
        }else{
            buttonVerPedidosAdocao.setVisible(false);
            cadastrarPetButton.setVisible(true);
            buttonVetTodosPedidos.setVisible(true);
        }

        labelLogo.setIcon(img);
        createUIComponents();
        cadastrarPetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new PetsCreateForm(usuarioLogado);
                    setVisible(false);
                    frame.setVisible(true);
                });
            }
        });
        buttonVerPedidosAdocao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new VerPedidosUsuario(usuarioLogado);
                    setVisible(false);
                    frame.setVisible(true);
                });
            }
        });
        buttonVetTodosPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VerTodosPedidos(usuarioLogado);
                setVisible(false);
                frame.setVisible(true);
            }
        });
    }


    private void createUIComponents() {
        this.listLayout = new JPanel();
        this.listLayout.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        EntityManager em = JPAUtil.getEntityManager();
        PetDAO petDAO = new PetDAO(em);
        em.getTransaction().begin();
        List<Pet> pets = petDAO.buscarTodos();
        em.getTransaction().commit();
        em.close();
        pets.forEach(p -> {
            JPanel petBox = new PetBox(p, usuarioLogado);
            this.listLayout.add(petBox);

        });
        listLayout.setVisible(true);
        this.listLayout.revalidate();
        this.listLayout.repaint();
}
}
