package com.javawilliam.padaria.infrastructure.repository;
import com.javawilliam.padaria.infrastructure.entitys.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface CadastroRepository extends JpaRepository< Cadastro,Long> {
}
