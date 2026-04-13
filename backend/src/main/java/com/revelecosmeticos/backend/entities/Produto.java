package com.revelecosmeticos.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "imagem_url")
    private String imagemUrl;

    @Column(name = "preco_base", nullable = false)
    private Double precoBase;

    @Column(name = "em_promocao_clube", nullable = false)
    private Boolean emPromocaoClube = false;

    @Column(name = "desconto_clube_padrao")
    private Double descontoClubePadrao = 0.15; // 15% fixo inicial

    @Column(name = "desconto_especial")
    private Double descontoEspecial = 0.0;

    @Column(name = "estoque", nullable = false)
    private Integer estoque = 0;

    @Column(name = "ativo")
    private Boolean ativo = true;

    // Métodos de conveniência (não vão para o banco)
    @Transient
    public Double getPrecoComDesconto() {
        if (!emPromocaoClube) return precoBase;

        double taxaDesconto = (descontoEspecial > 0) ? descontoEspecial : descontoClubePadrao;
        return precoBase * (1 - taxaDesconto);
    }
}