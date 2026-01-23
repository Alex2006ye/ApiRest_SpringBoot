package com.example.FazendoAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "pessoinhas")
@Entity
public class Pessoa implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //gera ids unicos, como se fosse algo aleatório por exemplo
    private String id;
    @NotNull
    @Column(unique = true)
    private String login;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PessoaFuncao role;

    public Pessoa(String id, String login, String password, PessoaFuncao role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Pessoa(String login, String password, PessoaFuncao role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Pessoa() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PessoaFuncao getRole() {
        return role;
    }

    public void setRole(PessoaFuncao role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == PessoaFuncao.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
