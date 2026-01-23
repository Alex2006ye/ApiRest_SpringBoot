package com.example.FazendoAPI.dto;


import com.example.FazendoAPI.model.PessoaFuncao;

public record RegisterDTO(String login, String password, PessoaFuncao role) {
}
