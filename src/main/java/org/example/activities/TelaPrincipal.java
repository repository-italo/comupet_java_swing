package org.example.activities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private JPanel mainPanel; // ENCAPSULAMENTO
    private JButton cadastrarButton;
    private JPanel navbar;
    private JButton loginButton;
    private JLabel labelLogo;
    private JPanel innerbar;
    private JPanel contentPanel;
    private JTextPane mainTextPane;
    private JLabel ifamImage;

    public TelaPrincipal(){
        super("Tela Principal");
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        ImageIcon img = new ImageIcon("LOGO_COMUPET.png");
        labelLogo.setIcon(img);
        mainTextPane.setContentType("text/html");
        mainTextPane.setText(
                "<html>" +
                        "<body style='width: 200px'>" +
                        "<p style='font-size: 12" +
                        "px; font-family: sans-serif; text-align: center;'>O programa <strong style='color: #1C3879'>ComuPet</strong> fornece todas as informações que você precisa</p>"+
                        "<p style='font-size: 12px; font-family: sans-serif; text-align: center;'>O <strong style='color: #1C3879'>IFAM-CMZL</strong> reforça que: </p> <br>"+
                        "<p style='font-size: 12px; font-family: sans-serif; text-align: center; border: 1px solid blue; background-color: #1C3879; " +
                        "color: white; font-weight: bold; padding: 10px; border-radius: 15px;'> AMIGO NÃO SE COMPRA!</p>"+
                        "</body>" +
                        "</html>");
    ifamImage.setIcon(new ImageIcon("ifam_image.png"));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JFrame frame = new LoginForm();
                            setVisible(false);
                            frame.setVisible(true);
                        }
                    });

            }
        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new CadastroForm();
                        setVisible(false);
                        frame.setVisible(true);
                    }
                });
            }
        });
    }

}

