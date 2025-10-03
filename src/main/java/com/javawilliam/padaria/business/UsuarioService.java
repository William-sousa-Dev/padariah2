package com.javawilliam.padaria.business;


import com.javawilliam.padaria.infrastructure.entitys.Usuario;
import com.javawilliam.padaria.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    /**
     * Salva um novo usuário no banco de dados.
     */
    public void salvarUsuario(Usuario usuario) {
        repository.saveAndFlush(usuario);
    }

    /**
     * Busca um usuário pelo CPF.
     *
     * @param cpf CPF do usuário
     * @return Usuário encontrado
     */
    public Usuario buscarUsuarioPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado."));
    }

    /**
     * Deleta um usuário com base no CPF.
     */
    public void deletarUsuarioPorCpf(String cpf) {
        repository.deleteByCpf(cpf);
    }

    /**
     * Atualiza os dados de um usuário com base no CPF.
     */
    public void atualizarUsuarioPorCpf(String cpf, Usuario usuario) {
        Usuario usuarioEntity = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (usuario.getNome() != null) {
            usuarioEntity.setNome(usuario.getNome());
        }
        if (usuario.getEmail() != null) {
            usuarioEntity.setEmail(usuario.getEmail());
        }
        if (usuario.getTelefone() != null) {
            usuarioEntity.setTelefone(usuario.getTelefone());
        }
        if (usuario.getDataNascimento() != null) {
            usuarioEntity.setDataNascimento(usuario.getDataNascimento());
        }

        repository.save(usuarioEntity);
    }

    /**
     * Lista todos os usuários cadastrados.
     */
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
}
