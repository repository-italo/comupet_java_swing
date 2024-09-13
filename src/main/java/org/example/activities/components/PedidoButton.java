package org.example.activities.components;

import org.example.activities.PedidoAdocaoView;
import org.example.database.dao.PedidoAdocaoDAO;
import org.example.database.modelos.PedidoAdocao;
import org.example.database.modelos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoButton extends JButton {
    private PedidoAdocao pedido;
    private Usuario usuarioLogado;
    public PedidoButton(PedidoAdocao pedidoAdocao, Usuario usuario){
        super();
        this.pedido = pedidoAdocao;
        this.usuarioLogado = usuario;
        this.setText("Pedido #" + pedidoAdocao.getId());
        this.setSize(50, 30);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new PedidoAdocaoView(pedido, usuarioLogado);
                    frame.setVisible(true);
                });
            }
        });
    }
}
