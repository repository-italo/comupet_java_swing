package org.example.database.modelos;

import javax.persistence.*;

@Entity
public class PedidoAdocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    private Usuario usuario_pedinte;

    @OneToOne
    private Pet pet_pedido;

    private String nomeCompleto;
    private String telefone;
    private String email;
    private String justificativa;
    private boolean jaTevePets;
    private String localPetDuranteDiaENoite;
    private boolean estaCienteCompromisso;
    private String profissaoAtual;

    public PedidoAdocao(){

    }
    public PedidoAdocao(Usuario usuario_pedinte,
                        Pet pet_pedido, String nomeCompleto,
                        String telefone, String email,
                        String justificativa,
                        boolean jaTevePets,
                        String localPetDuranteDiaENoite,
                        boolean estaCienteCompromisso,
                        String profissaoAtual) {
        this.usuario_pedinte = usuario_pedinte;
        this.pet_pedido = pet_pedido;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.email = email;
        this.justificativa = justificativa;
        this.jaTevePets = jaTevePets;
        this.localPetDuranteDiaENoite = localPetDuranteDiaENoite;
        this.estaCienteCompromisso = estaCienteCompromisso;
        this.profissaoAtual = profissaoAtual;
    }

    public String getLocalPetDuranteDiaENoite() {
        return localPetDuranteDiaENoite;
    }

    public void setLocalPetDuranteDiaENoite(String localPetDuranteDiaENoite) {
        this.localPetDuranteDiaENoite = localPetDuranteDiaENoite;
    }

    public Usuario getUsuario_pedinte() {
        return usuario_pedinte;
    }

    public void setUsuario_pedinte(Usuario usuario_pedinte) {
        this.usuario_pedinte = usuario_pedinte;
    }

    public Pet getPet_pedido() {
        return pet_pedido;
    }

    public void setPet_pedido(Pet pet_pedido) {
        this.pet_pedido = pet_pedido;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public boolean isJaTevePets() {
        return jaTevePets;
    }

    public void setJaTevePets(boolean jaTevePets) {
        this.jaTevePets = jaTevePets;
    }


    public boolean isEstaCienteCompromisso() {
        return estaCienteCompromisso;
    }

    public void setEstaCienteCompromisso(boolean estaCienteCompromisso) {
        this.estaCienteCompromisso = estaCienteCompromisso;
    }

    public String getProfissaoAtual() {
        return profissaoAtual;
    }

    public void setProfissaoAtual(String profissaoAtual) {
        this.profissaoAtual = profissaoAtual;
    }
}
