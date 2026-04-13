package com.revelecosmeticos.backend.controllers;

import com.revelecosmeticos.backend.dtos.LoginDTO;
import com.revelecosmeticos.backend.dtos.UsuarioCadastroDTO;
import com.revelecosmeticos.backend.dtos.UsuarioResponseDTO;
import com.revelecosmeticos.backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> fazerLogin(@RequestBody @Valid LoginDTO loginData) {
        return ResponseEntity.ok(service.login(loginData));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioCadastroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioCadastroDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/assinar")
    public ResponseEntity<UsuarioResponseDTO> assinarClube(@PathVariable UUID id) {
        return ResponseEntity.ok(service.assinarClube(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<UsuarioResponseDTO> cancelarAssinatura(@PathVariable UUID id) {
        return ResponseEntity.ok(service.cancelarAssinatura(id));
    }
}