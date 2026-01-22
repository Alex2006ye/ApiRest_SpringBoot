package com.example.FazendoAPI.controller;

import com.example.FazendoAPI.dto.UsuarioCreateDTO;
import com.example.FazendoAPI.dto.UsuarioUpdateDTO;
import com.example.FazendoAPI.model.Usuario;
import com.example.FazendoAPI.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//serve para dar um nome para a API e a descrição do que ela faz
@Tag(name = "Usuario API", description = "Gerenciamento de Usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //Serve para descrever a operação realizada pela requisição
    @Operation(summary = "Criar um usuário", description = "Faz a criação de usuários")
    //aqui é para indicar, o que deve ser respondido, dependendo da resposta
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário Criado"),
            @ApiResponse(responseCode = "409", description = "Usuário já existe")
    })
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

    @Operation(summary = "Listar usuários", description = "Faz a listagem de todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrados"),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados")
    })
    @GetMapping("/listar")
    public List<Usuario> listar(){
        return service.listar();
    }

    @Operation(summary = "Exclusão de usuários", description = "Faz a exclusão de um usuário pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado"),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrado")
    })
    @DeleteMapping("/excluir/{id}")
    public void deletar(@PathVariable Integer id){
        service.deletar(id);
    }

    @Operation(summary = "Modificação de usuários", description = "Faz a alteração de dados de um usuário pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário alterado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
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
