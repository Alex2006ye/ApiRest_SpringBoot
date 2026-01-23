package com.example.FazendoAPI.controller;

import com.example.FazendoAPI.dto.AuthDTO;
import com.example.FazendoAPI.dto.RegisterDTO;
import com.example.FazendoAPI.model.Pessoa;
import com.example.FazendoAPI.model.PessoaFuncao;
import com.example.FazendoAPI.repository.PessoaRepository;
import com.example.FazendoAPI.service.PessoaService;
import com.example.FazendoAPI.strategy.CriarPessoaAdmin;
import com.example.FazendoAPI.strategy.CriarPessoaUser;
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
    private final PessoaService service;

    public AuthenticationController(AuthenticationManager authenticationManager, PessoaService service) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO dto){
        service.registrar(dto);
        return ResponseEntity.ok().build();
    }
}
