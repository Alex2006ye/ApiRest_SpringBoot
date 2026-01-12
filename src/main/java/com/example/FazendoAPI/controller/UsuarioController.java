package com.example.FazendoAPI.controller;

import com.example.FazendoAPI.dto.UsuarioCreateDTO;
import com.example.FazendoAPI.dto.UsuarioUpdateDTO;
import com.example.FazendoAPI.model.Usuario;
import com.example.FazendoAPI.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioCreateDTO> criar(@RequestBody UsuarioCreateDTO dto){ //Não se esquecer dessa annotation
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());

        Usuario salvo = service.salvar(usuario);

        if(salvo.getNome() != null && salvo.getIdade() != null)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return service.listar();
    }

    @DeleteMapping("/excluir/{id}")
    public void deletar(@PathVariable Integer id){
        service.deletar(id);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity alterar(@PathVariable Integer id, @RequestBody UsuarioUpdateDTO dto){
        boolean valor = service.consultar(id);
        if(valor){
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setIdade(dto.getIdade());
            usuario.setNome(dto.getNome());

            service.salvar(usuario);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
