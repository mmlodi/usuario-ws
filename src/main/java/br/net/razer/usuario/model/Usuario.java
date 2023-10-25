package br.net.razer.usuario.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name= "tb_usuario")
public class Usuario  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int id;
    @Column(name = "nomeUsuario")
    private String nome;
    @Column(name = "loginUsuario")
    private String login;
    @Column(name = "senhaUsuario")
    private String senha;
    @Column(name = "perfilUsuario")
    private String perfil;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    public Usuario () {
        super();
    }

    public Usuario(int id, String nome, String login, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }
    
}
