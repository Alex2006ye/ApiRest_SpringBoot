package com.example.FazendoAPI.repository;

import com.example.FazendoAPI.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {
    Optional<Pessoa> findByLogin(String login);
}
