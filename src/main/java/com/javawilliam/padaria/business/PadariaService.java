package com.javawilliam.padaria.business;



import com.javawilliam.padaria.infrastructure.entitys.Padaria;
import com.javawilliam.padaria.infrastructure.repository.PadariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PadariaService {

    private final PadariaRepository padariaRepository;

    public void salvarPadaria(Padaria padaria) {
        padariaRepository.save(padaria);
    }

    public Padaria buscarPorId(Long id) {
        return padariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de padaria não encontrado com Id: " + id));
    }

    public List<Padaria> listarTodos() {
        return padariaRepository.findAll();
    }

    public void atualizarPadaria(Long id, Padaria novaPadaria) {
        Padaria existente = padariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de padaria não encontrado com Id: " + id));
        existente.setNome(novaPadaria.getNome());
        existente.setPreco(novaPadaria.getPreco());
        existente.setQuantidade(novaPadaria.getQuantidade());

        padariaRepository.save(existente);
    }

    public void deletarPorId(Long id) {
        padariaRepository.deleteById(id);
    }
}
