package com.example.FazendoAPI.controller;

import com.example.FazendoAPI.dto.AuthDTO;
import com.example.FazendoAPI.dto.RegisterDTO;
import com.example.FazendoAPI.model.Pessoa;
import com.example.FazendoAPI.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final PessoaRepository repository;

    public AuthenticationController(AuthenticationManager authenticationManager, PessoaRepository repository) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO dto){
        if (repository.findByLogin(dto.login()).isPresent()) { //verifica se já existe no banco de dados salvo esse mesmo cadastro
            return ResponseEntity.badRequest().build(); // se já existe, manda um 400 de bad request
        }

        //faz a criptografia da senha para se salvar no banco de dados
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        Pessoa pessoa = new Pessoa(dto.login(), encryptedPassword, dto.role());

        repository.save(pessoa);

        return ResponseEntity.ok().build();
    }
}
