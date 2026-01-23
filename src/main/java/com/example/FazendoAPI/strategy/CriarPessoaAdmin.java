package com.example.FazendoAPI.strategy;

import com.example.FazendoAPI.dto.RegisterDTO;
import com.example.FazendoAPI.model.Pessoa;
import com.example.FazendoAPI.model.PessoaFuncao;
import com.example.FazendoAPI.repository.PessoaRepository;
import org.springframework.stereotype.Component;

@Component("ADMIN")
public class CriarPessoaAdmin implements CriarPessoaStrategy{
    private PessoaRepository repository;

    public CriarPessoaAdmin(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void criarPessoa(RegisterDTO dto, String encryptedPassword) {
        Pessoa pessoa = new Pessoa(dto.login(), encryptedPassword, PessoaFuncao.ADMIN);
        repository.save(pessoa);
    }
}
