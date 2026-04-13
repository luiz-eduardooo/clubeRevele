package com.revelecosmeticos.backend.controllers;


import com.revelecosmeticos.backend.dtos.ProdutoDTO;
import com.revelecosmeticos.backend.entities.Produto;
import com.revelecosmeticos.backend.repository.ProductRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid ProdutoDTO dto) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(dto, produto);
        if(produto.getAtivo() == null) produto.setAtivo(true);
        if(produto.getEmPromocaoClube() == null) produto.setEmPromocaoClube(false);
        if(produto.getDescontoEspecial() == null) produto.setDescontoEspecial(0.0);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(repository.findAll());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody @Valid ProdutoDTO dto, @PathVariable UUID id){
        Produto produto = repository.findById(id).orElseThrow(()->new RuntimeException("Produto não encontrado!"));
        BeanUtils.copyProperties(dto, produto, "id");
        return ResponseEntity.ok(repository.save(produto));
    }
    // Rota para BUSCAR um produto específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/ativarClubeProduto")
    public ResponseEntity<Produto> ativarClube(UUID id){
        return repository.findById(id).map((produto)-> {
            produto.setEmPromocaoClube(true);
            return ResponseEntity.ok(repository.save(produto));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/adicionarDescontoEspecial")
    public ResponseEntity<Produto> adicionarDesconto(UUID id, double desconto){
        return repository.findById(id).map((produto)->{
            produto.setDescontoEspecial(desconto);
            return ResponseEntity.ok(repository.save(produto));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizarPrecosGlobais")
    public ResponseEntity<Void> adicionarDescontoGlobal(double desconto){
        repository.atualizarDescontoGlobal(desconto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/atualizarDescontoClube")
    public ResponseEntity<Produto> atualizarDescontoClube(UUID id, double desconto){
        return repository.findById(id).map((produto)->{
            produto.setDescontoClubePadrao(desconto);
            return ResponseEntity.ok(repository.save(produto));
        }).orElse(ResponseEntity.notFound().build());
    }



}