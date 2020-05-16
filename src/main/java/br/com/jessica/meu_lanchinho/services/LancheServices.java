package br.com.jessica.meu_lanchinho.services;

import br.com.jessica.meu_lanchinho.model.Lanche;
import br.com.jessica.meu_lanchinho.repository.LancheRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancheServices {
    private static LancheRepository lancheRepository;

    public LancheServices(LancheRepository lancheRepository) {
        this.lancheRepository = lancheRepository;
    }

    public static ResponseEntity<Lanche> post(Lanche lanche) {
       if (lanche.getId() != null){
           return ResponseEntity.notFound().build();
       }

       Lanche lancheSalvo = lancheRepository.save(lanche);
       return  ResponseEntity.ok(lancheSalvo);
    }

    public ResponseEntity<Lanche> get(Long id) {
        var lancheEncontrado = lancheRepository.findById(id);
        if(lancheEncontrado.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(lancheEncontrado.get());
    }

    public  ResponseEntity<List<Lanche>> get(){
        return ResponseEntity.ok(lancheRepository.findAll());
    }

    public ResponseEntity<Lanche> put(Lanche lanche, Long id) {
        if (! lancheRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        lanche.setId(id);
        lancheRepository.save(lanche);

        return ResponseEntity.ok(lanche);
    }

    public ResponseEntity<Lanche> delete(Long id) {
        if (! lancheRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        lancheRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
