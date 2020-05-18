package br.com.jessica.meu_lanchinho.repository;

import br.com.jessica.meu_lanchinho.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
