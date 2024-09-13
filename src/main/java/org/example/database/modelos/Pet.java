package org.example.database.modelos;

import org.example.database.modelos.enums.Especie;
import org.example.database.modelos.enums.Sexo;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Especie especie;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String raca;
    private boolean ehVermifugado;
    private boolean ehCastrado;
    private int idade;

    public Pet(){

    }



    public Pet(String nome, int idade, Especie especie, Sexo sexo, String raca, boolean ehVermifugado, boolean ehCastrado) {
        this.nome = nome;
        this.especie = especie;
        this.sexo = sexo;
        this.raca = raca;
        this.ehVermifugado = ehVermifugado;
        this.ehCastrado = ehCastrado;
        this.idade = idade;
    }

    public int getId() {
        return Math.toIntExact(this.id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public boolean isEhVermifugado() {
        return ehVermifugado;
    }

    public void setEhVermifugado(boolean ehVermifugado) {
        this.ehVermifugado = ehVermifugado;
    }

    public boolean isEhCastrado() {
        return ehCastrado;
    }

    public void setEhCastrado(boolean ehCastrado) {
        this.ehCastrado = ehCastrado;
    }
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
