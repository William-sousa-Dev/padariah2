package com.javawilliam.padaria.infrastructure.repository;

import com.javawilliam.padaria.infrastructure.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCpf(String cpf);

    @Transactional
    void deleteByCpf(String cpf);
}