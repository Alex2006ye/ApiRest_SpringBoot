package com.example.FazendoAPI.strategy;

import com.example.FazendoAPI.dto.RegisterDTO;
import com.example.FazendoAPI.model.Pessoa;

import java.util.Optional;

public interface CriarPessoaStrategy {
    void criarPessoa(RegisterDTO dto, String encryptedPassword);
}
