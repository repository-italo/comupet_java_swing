package org.example.activities;

import org.example.activities.components.DataPet;
import org.example.activities.components.PetBox;
import org.example.database.modelos.PedidoAdocao;
import org.example.database.modelos.Pet;
import org.example.database.modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoAdocaoView extends JFrame {
    private JPanel mainPanel;
    private JLabel nomeCompleto;
    private JLabel fillNome;
    private JLabel justificativaLabel;
    private JTextArea textAreajustificativaFill;
    private JLabel jatevePetsLabel;
    private JCheckBox tevePetsCheckBox;
    private JLabel ondePetLabel;
    private JTextArea textAreaOndePetFilll;
    private JLabel profissao_atual;
    private JCheckBox estacienteCompromissoCheckBox;
    private JButton voltarButton;
    private JLabel labelestAciente;
    private JLabel labelProfissaoFill;
    private JLabel mainLabel;
    private JLabel secondLabel;
    private JPanel petPanel;
    private Usuario usuarioLogado;
    private Pet petPedido;
    private PedidoAdocao pedido;

    public PedidoAdocaoView(PedidoAdocao pedidoAdocao, Usuario usuario){
        this.petPedido = pedidoAdocao.getPet_pedido();
        this.usuarioLogado = usuario;
        this.pedido = pedidoAdocao;
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        createUI();
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                });
            }
        });
    }

    public void createUI(){
        fillNome.setText(this.pedido.getUsuario_pedinte().getNome());
        textAreajustificativaFill.setText(this.pedido.getJustificativa());
        tevePetsCheckBox.setSelected(this.pedido.isJaTevePets());
        textAreaOndePetFilll.setText(this.pedido.getLocalPetDuranteDiaENoite());
        labelProfissaoFill.setText(this.pedido.getProfissaoAtual());
        estacienteCompromissoCheckBox.setSelected(this.pedido.isEstaCienteCompromisso());
        JPanel dataPet = new DataPet(this.pedido.getPet_pedido(), this.usuarioLogado);
        petPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        petPanel.add(dataPet);

    }
}
