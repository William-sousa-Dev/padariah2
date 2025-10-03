package com.javawilliam.padaria.infrastructure.repository;
import com.javawilliam.padaria.infrastructure.entitys.Padaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PadariaRepository extends JpaRepository<Padaria, Long> {

}