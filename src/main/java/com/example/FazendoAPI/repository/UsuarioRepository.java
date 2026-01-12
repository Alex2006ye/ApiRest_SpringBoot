package com.example.FazendoAPI.repository;

import com.example.FazendoAPI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
