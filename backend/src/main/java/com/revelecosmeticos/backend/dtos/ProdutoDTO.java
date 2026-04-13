package com.revelecosmeticos.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        String descricao,

        String imagemUrl,

        @NotNull(message = "O preço base é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        Double precoBase,

        Boolean emPromocaoClube,

        Double descontoEspecial,

        Integer estoque
) {}