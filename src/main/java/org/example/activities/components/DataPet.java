package org.example.activities.components;

import org.example.activities.PedidoAdocaoForm;
import org.example.activities.PetsEditForm;
import org.example.database.dao.PetDAO;
import org.example.database.modelos.Pet;
import org.example.database.modelos.Usuario;
import org.example.database.modelos.enums.Especie;
import org.example.database.utils.JPAUtil;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataPet extends JPanel {
    private JPanel mainPanelPetBox;
    private JLabel especiePetLabel;
    private JLabel nomeLabel;
    private JLabel idadeLabel;
    private JLabel sexoLabel;
    private JLabel racaLabel;
    private JButton editarPetButton;
    private JButton excluirPetButton;
    private JButton adotarPetButton;
    private Pet pet;
    private Usuario usuarioLogado;

    public DataPet(Pet pet, Usuario usuarioLogado) {
        this.pet = pet;
        this.usuarioLogado = usuarioLogado;
        createUI();

    }

    private JLabel createLeftAlignedLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    public void createUI(){
        Dimension fixedSize = new Dimension(320, 250);
        this.setPreferredSize(fixedSize);
        this.setMinimumSize(fixedSize);
        this.setMaximumSize(fixedSize);

        this.setLayout(new BorderLayout());
        this.mainPanelPetBox = new JPanel();
        mainPanelPetBox.setLayout(new BorderLayout());
        mainPanelPetBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        especiePetLabel = new JLabel(this.pet.getEspecie().toString(), SwingConstants.CENTER);
        especiePetLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        especiePetLabel.setVerticalAlignment(SwingConstants.TOP);
        especiePetLabel.setVerticalTextPosition(SwingConstants.TOP);

        ImageIcon icon = null;
        if (pet.getEspecie().equals(Especie.CACHORRO)) {
            icon = new ImageIcon("dog.png");
        } else if (pet.getEspecie().equals(Especie.GATO)) {
            icon = new ImageIcon("cat.png");
        }

        if (icon != null) {
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            especiePetLabel.setIcon(new ImageIcon(img));
        }

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        nomeLabel = createLeftAlignedLabel("Nome: " + this.pet.getNome());
        idadeLabel = createLeftAlignedLabel("Idade: " + this.pet.getIdade());
        sexoLabel = createLeftAlignedLabel("Sexo: " + this.pet.getSexo().toString());
        racaLabel = createLeftAlignedLabel("Raça: " + this.pet.getRaca());

        infoPanel.add(nomeLabel);
        infoPanel.add(idadeLabel);
        infoPanel.add(sexoLabel);
        infoPanel.add(racaLabel);
        infoPanel.add(Box.createVerticalStrut(10));

        editarPetButton = new JButton("Editar Pet");
        editarPetButton.setBackground(Color.BLUE);
        editarPetButton.setForeground(Color.WHITE);

        excluirPetButton = new JButton("Excluir Pet");
        excluirPetButton.setBackground(Color.RED);
        excluirPetButton.setForeground(Color.WHITE);

        adotarPetButton = new JButton("Adotar Pet");
        adotarPetButton.setBackground(Color.BLACK);
        adotarPetButton.setForeground(Color.WHITE);

        Font buttonFont = new Font("Arial Black", Font.PLAIN, 9);
        editarPetButton.setFont(buttonFont);
        excluirPetButton.setFont(buttonFont);
        adotarPetButton.setFont(buttonFont);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.add(editarPetButton);
        buttonPanel.add(adotarPetButton);
        buttonPanel.add(excluirPetButton);
        editarPetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new PetsEditForm(pet, usuarioLogado);
                    frame.setVisible(true);
                });
            }
        });
        excluirPetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza de que deseja excluir esse Pet?",
                        "Confirmação de Exclusão",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                // Verifica a resposta do usuário
                if (resposta == JOptionPane.YES_OPTION) {
                    EntityManager em = JPAUtil.getEntityManager();
                    PetDAO petDAO = new PetDAO(em);
                    petDAO.remover(Long.valueOf(pet.getId()));
                    setVisible(false);
                } else {

                }
            }
        });
        adotarPetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new PedidoAdocaoForm(pet, usuarioLogado);
                    frame.setVisible(true);
                });
            }
        });
        mainPanelPetBox.add(especiePetLabel, BorderLayout.NORTH);
        mainPanelPetBox.add(infoPanel, BorderLayout.CENTER);
        mainPanelPetBox.add(buttonPanel, BorderLayout.SOUTH);
        adotarPetButton.setVisible(false);
        excluirPetButton.setVisible(false);
        editarPetButton.setVisible(false);
        excluirPetButton.setVisible(false);

        // Adiciona o painel principal ao PetBox
        this.add(mainPanelPetBox, BorderLayout.CENTER);
        if(!usuarioLogado.isAdmin()){
            editarPetButton.setVisible(false);
            excluirPetButton.setVisible(false);
        }else if(usuarioLogado.isAdmin()){
            adotarPetButton.setVisible(false);
        }
    }

}