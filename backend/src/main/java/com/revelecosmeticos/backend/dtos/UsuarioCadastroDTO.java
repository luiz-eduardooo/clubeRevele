package com.revelecosmeticos.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioCadastroDTO(
        @NotBlank(message = "O nome não pode ficar em branco!") String nome,
        @NotBlank(message = "O email é obrigatorio!") @Email(message = "Formato de email inválido!") String email,
        @NotBlank(message = "A senha é obrigatoria!") String senha,
        @NotBlank(message = "O cpf é obrigatorio!") @CPF(message = "Formato de cpf invalido!") String cpf,
        @NotBlank(message = "O telefone é obrigatorio!") String telefone
) {}