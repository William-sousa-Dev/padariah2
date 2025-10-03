package com.javawilliam.padaria.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompraRequestDTO {
    private Long usuarioId;           // Corrigido para Long, consistente com getters/setters
    private List<Long> produtosIds;
    private Integer quantidade;       // Quantidade adicionada

    // Como usa @Data, não precisa dos getters e setters manuais
}
