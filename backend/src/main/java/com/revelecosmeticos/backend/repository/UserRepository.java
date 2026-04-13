package com.revelecosmeticos.backend.repository;

import com.revelecosmeticos.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
