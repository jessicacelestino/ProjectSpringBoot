package br.com.jessica.meu_lanchinho.controller;

import br.com.jessica.meu_lanchinho.model.Lanche;
import br.com.jessica.meu_lanchinho.services.LancheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lanches")
public class LancheController {
    private final LancheService lancheServices;

    public LancheController(LancheService lancheServices) {
        this.lancheServices = lancheServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity <Lanche> get (@PathVariable Integer id) {
        return lancheServices.get(id);
    }

    @GetMapping
    public ResponseEntity <List<Lanche>> get() {
        return lancheServices.get();
    }
}
