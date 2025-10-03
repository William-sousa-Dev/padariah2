package com.javawilliam.padaria.infrastructure.entitys;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( name = "CADASTRO")
@Entity
@Getter
@Setter
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column (name = "idade")
    private Integer idade;
}
