package br.com.jessica.meu_lanchinho.controller;

import br.com.jessica.meu_lanchinho.model.Compra;
import br.com.jessica.meu_lanchinho.model.Pedido;
import br.com.jessica.meu_lanchinho.services.CompraService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService){
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<Compra> comprar(@RequestBody List<Pedido> pedidos){
        return compraService.comprar(pedidos);
    }

    @GetMapping
    public ResponseEntity<Page<Compra>> getAll(Pageable pageable){
        return compraService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getById(@PathVariable Long id){
       return compraService.getById(id);
    }

}
