package br.com.jessica.meu_lanchinho.services;

import br.com.jessica.meu_lanchinho.model.EIngrediente;
import br.com.jessica.meu_lanchinho.model.Ingrediente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredienteService {

    public ResponseEntity < Ingrediente >  get(int id) {
        try {
            Ingrediente ingrediente = EIngrediente.values()[id].getIngrediente();
            return ResponseEntity.ok(ingrediente);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity <List <Ingrediente >> get() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        for(EIngrediente ingrediente: EIngrediente.values()){
            ingredientes.add(ingrediente.getIngrediente());
        }
        return ResponseEntity.ok(ingredientes);
    }
}
