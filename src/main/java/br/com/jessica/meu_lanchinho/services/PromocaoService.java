package br.com.jessica.meu_lanchinho.services;

import br.com.jessica.meu_lanchinho.model.EPromocao;
import br.com.jessica.meu_lanchinho.model.Promocao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromocaoService {

    public ResponseEntity<Promocao> get(int id) {
        try {
            Promocao promocao = EPromocao.values()[id].getPromocao();
            return ResponseEntity.ok(promocao);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity <List<Promocao>> get() {
        List<Promocao> promocoes = new ArrayList<>();
        for(EPromocao promocao: EPromocao.values()){
            promocoes.add(promocao.getPromocao());
        }
        return ResponseEntity.ok(promocoes);
    }

}
