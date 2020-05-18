package br.com.jessica.meu_lanchinho.controller;

import br.com.jessica.meu_lanchinho.model.Ingrediente;
import br.com.jessica.meu_lanchinho.services.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    private final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService){
        this.ingredienteService = ingredienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity < Ingrediente > get (@PathVariable Integer id) {
        return ingredienteService.get(id);
    }

    @GetMapping
    public ResponseEntity <List <Ingrediente >> getAll() {
        return ingredienteService.get();
    }
}
