package com.revelecosmeticos.backend.services;

import com.revelecosmeticos.backend.dtos.LoginDTO;
import com.revelecosmeticos.backend.dtos.UsuarioCadastroDTO;
import com.revelecosmeticos.backend.dtos.UsuarioResponseDTO;
import com.revelecosmeticos.backend.entities.Usuario;
import com.revelecosmeticos.backend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UsuarioResponseDTO cadastrar(UsuarioCadastroDTO dto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        return UsuarioResponseDTO.fromEntity(repository.save(usuario));
    }

    public UsuarioResponseDTO login(LoginDTO dto) {
        return repository.findByEmailAndSenha(dto.email(), dto.senha())
                .map(UsuarioResponseDTO::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"));
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(UUID id) {
        return repository.findById(id)
                .map(UsuarioResponseDTO::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public UsuarioResponseDTO atualizar(UUID id, UsuarioCadastroDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setNome(dto.nome());
        usuario.setTelefone(dto.telefone());
        return UsuarioResponseDTO.fromEntity(repository.save(usuario));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        repository.deleteById(id);
    }

    public UsuarioResponseDTO assinarClube(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setClubeAtivo(true);
        return UsuarioResponseDTO.fromEntity(repository.save(usuario));
    }

    public UsuarioResponseDTO cancelarAssinatura(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setClubeAtivo(false);
        return UsuarioResponseDTO.fromEntity(repository.save(usuario));
    }
}