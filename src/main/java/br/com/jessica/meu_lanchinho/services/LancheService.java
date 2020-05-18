package br.com.jessica.meu_lanchinho.services;

import br.com.jessica.meu_lanchinho.model.ELanche;
import br.com.jessica.meu_lanchinho.model.Lanche;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LancheService {
    public ResponseEntity<Lanche> get(int id) {
        try {
            Lanche lanche = ELanche.values()[id].getLanche();
            return ResponseEntity.ok(lanche);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity <List<Lanche >> get() {
        List<Lanche> lanches = new ArrayList<>();
        for(ELanche lanche: ELanche.values()){
            lanches.add(lanche.getLanche());
        }
        return ResponseEntity.ok(lanches);
    }

}
