package com.revelecosmeticos.backend.dtos;

import com.revelecosmeticos.backend.entities.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nome,
        String email,
        String telefone,
        Boolean clubeAtivo,
        LocalDateTime dataCadastro
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getClubeAtivo(),
                usuario.getDataCadastro()
        );
    }
}