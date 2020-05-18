package br.com.jessica.meu_lanchinho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lanches")
public class Lanche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Basic(optional = false)
    private String nomeLanche;
    @ManyToMany
    private List<Ingrediente> ingrediente;

    public Lanche(String nomeLanche, Ingrediente... ingrediente){
        this.nomeLanche = nomeLanche;
        this.ingrediente = Arrays.asList(ingrediente);
    }

}
