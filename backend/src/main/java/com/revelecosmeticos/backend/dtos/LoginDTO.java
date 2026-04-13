package com.revelecosmeticos.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Este "Record" é uma forma moderna e curta de criar uma classe
 * que apenas transporta dados (Data Transfer Object).
 */
public record LoginDTO(
        @NotBlank @Email String email,
        @NotBlank String senha
) {}