package br.com.jessica.meu_lanchinho.controller;

import br.com.jessica.meu_lanchinho.model.Ingrediente;
import br.com.jessica.meu_lanchinho.services.IngredienteServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    private final IngredienteServices ingredienteServices;

    public IngredienteController(IngredienteServices ingredienteServices){
        this.ingredienteServices = ingredienteServices;
    }

    @PostMapping
    public ResponseEntity < Ingrediente > post(@Valid @RequestBody Ingrediente ingrediente){
        return ingredienteServices.post(ingrediente);
    }

    @GetMapping("/{id}")
    public ResponseEntity < Ingrediente > get (@PathVariable Long id) {
        return ingredienteServices.get(id);
    }

    @GetMapping
    public ResponseEntity <List <Ingrediente >> getall() {
        return ingredienteServices.get();
    }

    @PutMapping("/{id}")
    public ResponseEntity < Ingrediente > put (@Valid @RequestBody Ingrediente ingrediente, @PathVariable Long id) {
        return ingredienteServices.put(ingrediente, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Ingrediente > delete (@PathVariable Long id) {
        return ingredienteServices.delete(id);
    }
}
