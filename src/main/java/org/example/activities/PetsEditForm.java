package org.example.activities;

import org.example.database.dao.PetDAO;
import org.example.database.modelos.Pet;
import org.example.database.modelos.Usuario;
import org.example.database.modelos.enums.Especie;
import org.example.database.modelos.enums.Sexo;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetsEditForm extends JFrame {
    private JPanel mainPanel;
    private JLabel formLabel;
    private JLabel nomeLabel;
    private JTextField textFieldNome;
    private JLabel idadeAproximadaLabel;
    private JTextField textFieldidade;
    private JLabel racaLabel;
    private JTextField textFieldraca;
    private JLabel especieLabel;
    private JCheckBox cachorroCheckBox;
    private JCheckBox gatoCheckBox;
    private JLabel sexoLabel;
    private JCheckBox machoCheckBox;
    private JCheckBox femeaCheckBox;
    private JLabel ehvermifugado;
    private JCheckBox checkBoxehvermifugado;
    private JLabel castradoLabel;
    private JCheckBox checkBoxehcastrado;
    private JButton confirmarButton;
    private JButton voltarButton;
    private JLabel errorLabel;
    private Usuario usuarioLogado;
    private Pet pet;

    public PetsEditForm(Pet editarpet, Usuario usuario) {
        this.usuarioLogado = usuario;
        this.pet = editarpet;
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        createUI();

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if ((cachorroCheckBox.isSelected() && gatoCheckBox.isSelected()) || (machoCheckBox.isSelected() && femeaCheckBox.isSelected())) {
                    errorLabel.setText("Assinale apenas uma opção");
                    errorLabel.setVisible(true);
                } else {
                    if (cachorroCheckBox.isSelected()) {
                        pet.setEspecie(Especie.CACHORRO);
                    } else if (gatoCheckBox.isSelected()) {
                        pet.setEspecie(Especie.GATO);
                    }

                    if (machoCheckBox.isSelected()) {
                        pet.setSexo(Sexo.MACHO);
                    } else if (femeaCheckBox.isSelected()) {
                        pet.setSexo(Sexo.FEMEA);
                    }
                    pet.setNome(textFieldNome.getText());
                    pet.setIdade(Integer.parseInt(textFieldidade.getText()));
                    pet.setRaca(textFieldraca.getText());

                    if(checkBoxehcastrado.isSelected() && (pet.isEhCastrado() == false)){
                        pet.setEhCastrado(true);
                    }

                    if(checkBoxehvermifugado.isSelected() && (pet.isEhVermifugado() == false)){
                        pet.setEhVermifugado(true);
                    }
                    EntityManager em = JPAUtil.getEntityManager();
                    PetDAO petDAO = new PetDAO(em);
                    em.getTransaction().begin();
                    petDAO.atualizar(pet);
                    em.flush();
                    em.getTransaction().commit();
                    em.close();
                    PetsEditForm.this.setVisible(false);
                    JFrame frame = new TelaMenuPets(usuarioLogado);
                    frame.setVisible(true);
                }

            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    PetsEditForm.this.setVisible(false);
                });
            }
        });
    }

    public void createUI() {
        this.textFieldNome.setText(this.pet.getNome());
        this.textFieldidade.setText(String.valueOf(this.pet.getIdade()));
        this.textFieldraca.setText(this.pet.getRaca());

        if (this.pet.getEspecie().equals(Especie.CACHORRO)) {
            this.cachorroCheckBox.setSelected(true);
        } else if (this.pet.getEspecie().equals(Especie.GATO)) {
            this.gatoCheckBox.setSelected(true);
        }

        if (this.pet.getSexo().equals(Sexo.MACHO)) {
            this.machoCheckBox.setSelected(true);
        } else if (this.pet.getSexo().equals(Sexo.FEMEA)) {
            this.femeaCheckBox.setSelected(true);
        }

        if (this.pet.isEhVermifugado()) {
            this.checkBoxehvermifugado.setSelected(true);
        }

        if (this.pet.isEhCastrado()) {
            this.checkBoxehcastrado.setSelected(true);
        }



    }
}
