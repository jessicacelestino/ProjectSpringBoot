package br.com.jessica.meu_lanchinho.repository;

import br.com.jessica.meu_lanchinho.model.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface LancheRepository extends JpaRepository < Lanche, Long > {

}
