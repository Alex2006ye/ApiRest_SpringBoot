package com.example.FazendoAPI.strategy;

import com.example.FazendoAPI.dto.RegisterDTO;
import com.example.FazendoAPI.model.Pessoa;
import com.example.FazendoAPI.model.PessoaFuncao;
import com.example.FazendoAPI.repository.PessoaRepository;
import org.springframework.stereotype.Component;

@Component("USER")
public class CriarPessoaUser implements CriarPessoaStrategy{
    private final PessoaRepository repository;

    public CriarPessoaUser(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void criarPessoa(RegisterDTO dto, String encryptedPassword) {
        Pessoa pessoa = new Pessoa(dto.login(), encryptedPassword, PessoaFuncao.USER);
        repository.save(pessoa);
    }
}
