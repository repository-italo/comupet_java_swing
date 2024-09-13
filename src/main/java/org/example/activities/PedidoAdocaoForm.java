package org.example.activities;

import org.example.database.dao.PedidoAdocaoDAO;
import org.example.database.modelos.PedidoAdocao;
import org.example.database.modelos.Pet;
import org.example.database.modelos.Usuario;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoAdocaoForm extends JFrame {

    private JPanel mainPanel;
    private JTextField textFieldnome;
    private JLabel nomeCompleto;
    private JTextField textFieldtelefone;
    private JTextField textFieldEmail;
    private JLabel telefoneLabel;
    private JLabel emailLabel;
    private JTextArea justificativatextArea;
    private JLabel justificativaLabel;
    private JCheckBox tevePetsCheckBox;
    private JLabel jatevePetsLabel;
    private JTextArea ondePetTextArea;
    private JLabel ondePetLabel;
    private JLabel profissao_atual;
    private JTextField textFieldprofissaoAtual;
    private JButton confirmarButton;
    private JButton voltarButton;
    private JCheckBox estacienteCompromissoCheckBox;
    private Usuario usuarioLogado;
    private Pet petAdocao;
    private PedidoAdocao pedidoAdocao;

    public PedidoAdocaoForm(Pet pet, Usuario usuario){
        this.petAdocao = pet;
        this.usuarioLogado = usuario;
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 900);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUI();
                setVisible(false);
            }
        });
        voltarButton.addActionListener(new ActionListener() {
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
        this.pedidoAdocao = new PedidoAdocao
                        (this.usuarioLogado,
                        this.petAdocao,
                        textFieldnome.getText(),
                        textFieldtelefone.getText(),
                        textFieldEmail.getText(),
                        justificativatextArea.getText(),
                        tevePetsCheckBox.isSelected(),
                        ondePetTextArea.getText(),
                        estacienteCompromissoCheckBox.isSelected(),
                        textFieldprofissaoAtual.getText());

        EntityManager em = JPAUtil.getEntityManager();
        PedidoAdocaoDAO pedidoAdocaoDAO = new PedidoAdocaoDAO(em);
        em.getTransaction().begin();
        pedidoAdocaoDAO.cadastrar(this.pedidoAdocao);
        em.getTransaction().commit();
        em.close();
    }
}
