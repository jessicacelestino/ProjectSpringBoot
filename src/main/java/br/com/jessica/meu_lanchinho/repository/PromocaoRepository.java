package br.com.jessica.meu_lanchinho.repository;

import br.com.jessica.meu_lanchinho.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
}
