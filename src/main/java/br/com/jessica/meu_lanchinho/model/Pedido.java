package br.com.jessica.meu_lanchinho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Lanche lanche;
    @ManyToMany
    private List<Ingrediente> ingredientesExtras;
    @ManyToOne
    private Promocao promocao;
    private double preco;
    private double precoFinal;
    private String usuario;
}
