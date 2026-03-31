package com.revelecosmeticos.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Mantendo o padrão UUID que você escolheu!
    private UUID id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String imagemUrl;

    @NotNull(message = "O preço normal é obrigatório")
    @PositiveOrZero(message = "O preço não pode ser negativo")
    @Column(nullable = false)
    private BigDecimal precoNormal;

    @NotNull(message = "O preço do clube é obrigatório")
    @PositiveOrZero(message = "O preço do clube não pode ser negativo")
    @Column(nullable = false)
    private BigDecimal precoClube;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    private Boolean ativo = true;
}
