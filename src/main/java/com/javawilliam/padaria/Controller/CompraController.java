package com.javawilliam.padaria.Controller;



import com.javawilliam.padaria.dto.CompraRequestDTO;
import com.javawilliam.padaria.business.CompraService;
import com.javawilliam.padaria.infrastructure.entitys.Compra;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> criarCompra(@RequestBody CompraRequestDTO dto) {
        Compra novaCompra = compraService.criarCompraComDTO(dto);
        return ResponseEntity.ok(novaCompra);
    }
}
