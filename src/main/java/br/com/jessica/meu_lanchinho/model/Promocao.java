package br.com.jessica.meu_lanchinho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Promocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double valorDesconto;

    public Promocao(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public Promocao(Promocao promocao){
        this.nome = promocao.getNome();
        this.descricao = promocao.getDescricao();
    }

}
