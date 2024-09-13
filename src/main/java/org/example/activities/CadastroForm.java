package org.example.activities;

import org.example.database.dao.UsuarioDAO;
import org.example.database.modelos.Usuario;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroForm extends JFrame {
    private JPanel cadastroPane;
    private JLabel comupetLabel;
    private JLabel emailLabel;
    private JTextField textFieldEmail;
    private JLabel senhaLabel;
    private JTextField textFieldSenha;
    private JButton cadastrarButton;
    private JLabel nomeLabel;
    private JTextField textFieldNome;
    private JLabel jaTemConta;
    private JButton loginButton;
    private JButton telaPrincipalButton;
    private JCheckBox simCheckBox;
    private JLabel ehAdmin;
    private JRadioButton radioButton1;

    public CadastroForm(){
        super("Cadastro do Comupet");
        setContentPane(cadastroPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        JFrame frame = new LoginForm();
                        frame.setSize(600, 500);
                        frame.setVisible(true);
                    }
                });
            }
        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntityManager em = JPAUtil.getEntityManager();
                em.getTransaction().begin();
                UsuarioDAO usuarioDAO = new UsuarioDAO(em);
                Usuario usuario = new Usuario(textFieldNome.getText(), textFieldEmail.getText(), textFieldSenha.getText(), simCheckBox.isSelected());
                usuarioDAO.cadastrar(usuario);
                em.getTransaction().commit();
                em.close();

            }
        });
        telaPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new TelaPrincipal();
                    setVisible(false);
                    frame.setVisible(true);
                });
            }
        });
    }
}
