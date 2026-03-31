package com.revelecosmeticos.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome não pode ficar em branco!")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O email é obrigatorio!")
    @Email(message = "Formato de email inválido!")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatoria!")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "O cpf é obrigatorio!")
    @CPF(message = "Formato de cpf invalido!")
    @Column(nullable = false)
    private String cpf;

    @NotBlank(message = "O telefone é obrigatorio")
    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private Boolean clubeAtivo = false;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataCadastro;


}
