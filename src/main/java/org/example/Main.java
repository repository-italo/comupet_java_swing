package org.example;

import org.example.activities.TelaPrincipal;
import javax.swing.*;

public class Main  {


    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new TelaPrincipal();
                frame.setVisible(true);
            });
        }
    }
