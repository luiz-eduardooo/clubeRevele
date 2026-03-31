package com.revelecosmeticos.backend.repository;

import com.revelecosmeticos.backend.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Produto, UUID> {
}
