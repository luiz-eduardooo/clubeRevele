package com.revelecosmeticos.backend.repository;

import com.revelecosmeticos.backend.entities.Produto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Produto, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.descontoClubePadrao = :novoDesconto")
    void atualizarDescontoGlobal(Double novoDesconto);
}
