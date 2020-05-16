package br.com.jessica.meu_lanchinho.repository;

import br.com.jessica.meu_lanchinho.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository <Ingrediente, Long > {

}
