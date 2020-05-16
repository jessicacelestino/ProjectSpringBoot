package br.com.jessica.meu_lanchinho.services;

import br.com.jessica.meu_lanchinho.model.Ingrediente;
import br.com.jessica.meu_lanchinho.repository.IngredienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteServices {
    private final IngredienteRepository ingredienteRepository;

    public IngredienteServices(IngredienteRepository ingredienteRepository){
        this.ingredienteRepository = ingredienteRepository;
    }

   public ResponseEntity < Ingrediente > post(Ingrediente ingrediente) {
        if (ingrediente.getId() != null ) {
            return ResponseEntity.notFound().build();
        }

        Ingrediente ingredienteSalvo = ingredienteRepository.save(ingrediente);
        return ResponseEntity.ok(ingredienteSalvo);
   }

    public ResponseEntity < Ingrediente >  get(Long id) {
        var ingredienteEncontrado = ingredienteRepository.findById(id);
        if (ingredienteEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredienteEncontrado.get());
    }

    public ResponseEntity <List <Ingrediente >> get() {
        return ResponseEntity.ok(ingredienteRepository.findAll());
    }

    public ResponseEntity<Ingrediente> put(Ingrediente ingrediente, Long id) {
        if (!ingredienteRepository.existsById(id)){
            return  ResponseEntity.notFound().build();
        }

        ingrediente.setId(id);
        ingredienteRepository.save(ingrediente);

        return ResponseEntity.ok(ingrediente);
    }

    public ResponseEntity<Ingrediente> delete(Long id) {
        if (!ingredienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ingredienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
