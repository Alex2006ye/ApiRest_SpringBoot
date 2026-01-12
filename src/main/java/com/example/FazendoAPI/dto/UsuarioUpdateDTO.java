package com.example.FazendoAPI.dto;

public class UsuarioUpdateDTO {

    private String nome;
    private Integer idade;
    private Integer id;

    public UsuarioUpdateDTO(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
