package br.com.jessica.meu_lanchinho.controller;

import br.com.jessica.meu_lanchinho.model.Ingrediente;
import br.com.jessica.meu_lanchinho.model.Lanche;
import br.com.jessica.meu_lanchinho.services.LancheServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lanches")
public class LancheController {
    private final LancheServices lancheServices;

    public LancheController(LancheServices lancheServices) {
        this.lancheServices = lancheServices;
    }

    @PostMapping
    public ResponseEntity < Lanche > post (@Valid @RequestBody Lanche lanche) {
        return LancheServices.post(lanche);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Lanche> get (@PathVariable Long id) {
        return lancheServices.get(id);
    }

    @GetMapping
    public ResponseEntity <List<Lanche>> get() {
        return lancheServices.get();
    }

    @PutMapping("/{id}")
    public ResponseEntity < Lanche > put (@Valid @RequestBody Lanche lanche, @PathVariable Long id) {
        return lancheServices.put(lanche, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Lanche > delete (@PathVariable Long id) {
        return lancheServices.delete(id);
    }
}
