package org.example.activities;

import org.example.database.dao.UsuarioDAO;
import org.example.database.modelos.Usuario;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JPanel loginPanel;
    private JTextField textFieldEmail;
    private JTextField textFieldSenha;
    private JLabel mainLabel;
    private JLabel emailLabel;
    private JLabel senhaLabel;
    private JButton buttonlogin;
    private JButton cadastreSeButton;
    private JLabel nlogin;
    private JLabel errorLabel;
    private JButton telaPrincipalbutton;

    public LoginForm(){
        super("Login do Comupet");
        setContentPane(loginPanel);
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cadastreSeButton.addActionListener(e -> SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(false);
                JFrame frame = new CadastroForm();
                frame.setSize(600, 500);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }));
        buttonlogin.addActionListener(e -> {
            EntityManager em = JPAUtil.getEntityManager();
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            em.getTransaction().begin();
            try{
                Usuario busca = usuarioDAO.buscarPorEmail(textFieldEmail.getText());
                em.getTransaction().commit();
                if(busca.getSenha().equals(textFieldSenha.getText())){
                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new TelaMenuPets(busca);
                        setVisible(false);
                        frame.setVisible(true);
                    });
                }else{
                    errorLabel.setVisible(true);
                    errorLabel.setText("Senha Incorreta");
                }
            }catch (NoResultException ex){
                errorLabel.setVisible(true);
                errorLabel.setText("Email não Cadastrado");
            }
        });
        telaPrincipalbutton.addActionListener(new ActionListener() {
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
