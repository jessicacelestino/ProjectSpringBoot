package br.com.jessica.meu_lanchinho.model;

public enum EIngrediente {
    ALFACE(new Ingrediente("Alface", 0.42)),
    BACON(new Ingrediente("Bacon", 2.00)),
    HAMBURGUER(new Ingrediente("Hambúrguer", 3.00)),
    OVO(new Ingrediente("Ovo", 0.80)),
    QUEIJO(new Ingrediente("Queijo", 1.50));

    private EIngrediente(Ingrediente ingrediente){
        this.ingrediente = ingrediente;
    }
    private Ingrediente ingrediente;

    public Ingrediente getIngrediente(){
        return this.ingrediente;
    }

}
