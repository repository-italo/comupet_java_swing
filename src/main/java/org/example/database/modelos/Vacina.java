package org.example.database.modelos;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vacina {
    @ManyToOne
    private Pet pet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Date data;
    private int dose;
    private boolean ehReforco;
    private String nomeVeterinario;

    public Vacina(){
    }
    public Vacina(String nome, Date data,int dose, boolean ehReforco, String nomeVeterinario, Pet pet){
        this.nome = nome;
        this.data = data;
        this.dose = dose;
        this.ehReforco = ehReforco;
        this.nomeVeterinario = nomeVeterinario;
        this.pet = pet;

    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public boolean isEhReforco() {
        return ehReforco;
    }

    public void setEhReforco(boolean ehReforco) {
        this.ehReforco = ehReforco;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    public void setNomeVeterinario(String nomeVeterinario) {
        this.nomeVeterinario = nomeVeterinario;
    }
}
