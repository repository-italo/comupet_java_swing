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

public class PetsCreateForm extends JFrame {

    private JPanel mainPanel;
    private JTextField textFieldNome;
    private JTextField textFieldidade;
    private JTextField textFieldraca;
    private JCheckBox cachorroCheckBox;
    private JCheckBox gatoCheckBox;
    private JLabel especieLabel;
    private JLabel racaLabel;
    private JLabel idadeAproximadaLabel;
    private JLabel nomeLabel;
    private JLabel formLabel;
    private JButton confirmarButton;
    private JLabel errorLabel;
    private JCheckBox machoCheckBox;
    private JCheckBox femeaCheckBox;
    private JTextField textFieldIdade;
    private JLabel sexoLabel;
    private JCheckBox checkBoxehvermifugado;
    private JLabel ehvermifugado;
    private JCheckBox checkBoxehcastrado;
    private JLabel castradoLabel;
    private JButton voltarButton;
    private Usuario usuarioLogado;

    public PetsCreateForm(Usuario usuarioLogado){
        this.usuarioLogado = usuarioLogado;
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pet pet = new Pet();
                if((cachorroCheckBox.isSelected() && gatoCheckBox.isSelected()) || (machoCheckBox.isSelected() && femeaCheckBox.isSelected())){
                    errorLabel.setText("Assinale apenas uma opção");
                    errorLabel.setVisible(true);
                }else{
                if(cachorroCheckBox.isSelected()){
                    pet.setEspecie(Especie.CACHORRO);
                }else if(gatoCheckBox.isSelected()){
                    pet.setEspecie(Especie.GATO);
                }

                if(machoCheckBox.isSelected()){
                    pet.setSexo(Sexo.MACHO);
                }else if(femeaCheckBox.isSelected()){
                    pet.setSexo(Sexo.FEMEA);
                }
                pet.setNome(textFieldNome.getText());
                pet.setIdade(Integer.parseInt(textFieldidade.getText()));
                pet.setRaca(textFieldraca.getText());
                pet.setEhCastrado(checkBoxehcastrado.isSelected());
                pet.setEhVermifugado(checkBoxehvermifugado.isSelected());
                try{
                    EntityManager em = JPAUtil.getEntityManager();
                    PetDAO petDAO = new PetDAO(em);
                    em.getTransaction().begin();
                    petDAO.cadastrar(pet);
                    em.getTransaction().commit();
                    em.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }

                }

            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new TelaMenuPets(PetsCreateForm.this.usuarioLogado);
                    setVisible(false);
                    frame.setVisible(true);
                });
            }
        });
    }

}
