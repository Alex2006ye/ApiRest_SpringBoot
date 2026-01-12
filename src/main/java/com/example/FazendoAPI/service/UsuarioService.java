package com.example.FazendoAPI.service;

import com.example.FazendoAPI.model.Usuario;
import com.example.FazendoAPI.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public List<Usuario> listar(){
        return repository.findAll();
    }

    public void deletar(Integer id){
        repository.deleteById(id);
    }

    public boolean consultar(Integer id){
        return repository.existsById(id);
    }
}
