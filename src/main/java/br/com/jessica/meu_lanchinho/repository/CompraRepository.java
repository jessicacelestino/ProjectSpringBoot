package br.com.jessica.meu_lanchinho.repository;

import br.com.jessica.meu_lanchinho.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
