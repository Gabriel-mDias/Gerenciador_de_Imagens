
package br.ufes.models;


public class Usuario {
    
    private Long id;
    private String login;
    private String senha;
    private boolean isAdimin;

    public Usuario(String login, String senha, boolean isAdimin) {
        this.login = login;
        this.senha = senha;
        this.isAdimin = isAdimin;
    }
    
    public Usuario(Long id, String login, boolean isAdimin) {
        this.id = id;
        this.login = login;
        this.isAdimin = isAdimin;
    }
    
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }


    public Usuario() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdimin() {
        return isAdimin;
    }

    public void setAdimin(boolean isAdimin) {
        this.isAdimin = isAdimin;
    }
    
    public Usuario comId(Long id){
        this.id = id;
        return this;
    }
}
