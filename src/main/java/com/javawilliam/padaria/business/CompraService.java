package com.javawilliam.padaria.business;

import com.javawilliam.padaria.infrastructure.entitys.Compra;
import com.javawilliam.padaria.infrastructure.entitys.Padaria;
import com.javawilliam.padaria.infrastructure.entitys.Usuario;
import com.javawilliam.padaria.infrastructure.repository.CompraRepository;
import com.javawilliam.padaria.infrastructure.repository.PadariaRepository;
import com.javawilliam.padaria.infrastructure.repository.UsuarioRepository;
import com.javawilliam.padaria.dto.CompraRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final UsuarioRepository usuarioRepository;
    private final PadariaRepository padariaRepository;

    /**
     * Cria uma nova compra com base nos dados recebidos via DTO.
     *
     * @param dto Objeto contendo ID do usuário e lista de IDs dos produtos
     * @return Compra salva no banco de dados
     */
    public Compra criarCompraComDTO(CompraRequestDTO dto) {
        // Busca o usuário pelo ID fornecido no DTO
        Usuario usuario = usuarioRepository.findById(dto.getQuantidade())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + dto.getUsuarioId() + " não encontrado."));

        // Busca os produtos (padarias) pelos IDs fornecidos
        List<Padaria> produtos = padariaRepository.findAllById(dto.getProdutosIds());

        if (produtos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado com os IDs fornecidos.");
        }

        // Cria a compra com os dados
        Compra compra = Compra.builder()
                .usuario(usuario)
                .produtos(produtos)
                .dataCompra(LocalDateTime.now())
                .valorTotal(calcularValorTotal(produtos))
                .build();

        // Salva e retorna a compra
        return compraRepository.save(compra);
    }

    /**
     * Calcula o valor total da compra somando os preços dos produtos.
     *
     * @param produtos Lista de produtos comprados
     * @return Valor total da compra
     */
    private Double calcularValorTotal(List<Padaria> produtos) {
        return produtos.stream()
                .mapToDouble(p -> p.getPreco() != null ? p.getPreco() : 0.0)
                .sum();
    }
}
