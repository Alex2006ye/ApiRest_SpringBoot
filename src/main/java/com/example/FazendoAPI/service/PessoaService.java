package com.example.FazendoAPI.service;

import com.example.FazendoAPI.dto.RegisterDTO;
import com.example.FazendoAPI.repository.PessoaRepository;
import com.example.FazendoAPI.strategy.CriarPessoaStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PessoaService {
    private final PessoaRepository repository;
    private final Map<String, CriarPessoaStrategy> strategies;

    public PessoaService(PessoaRepository repository, Map<String, CriarPessoaStrategy> strategies) {
        this.repository = repository;
        this.strategies = strategies;
    }


    public void registrar(RegisterDTO dto) {

        if (repository.findByLogin(dto.login()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        CriarPessoaStrategy strategy = strategies.get(dto.role().name());

        strategy.criarPessoa(dto, encryptedPassword);
    }
}
