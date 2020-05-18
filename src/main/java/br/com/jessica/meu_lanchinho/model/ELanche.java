package br.com.jessica.meu_lanchinho.model;

public enum ELanche {
    XBACON(new Lanche("X-Bacon", EIngrediente.BACON.getIngrediente(), EIngrediente.HAMBURGUER.getIngrediente(), EIngrediente.QUEIJO.getIngrediente())),
    XBURGUER(new Lanche("X-Burguer", EIngrediente.HAMBURGUER.getIngrediente(), EIngrediente.QUEIJO.getIngrediente())),
    XEGG(new Lanche("X-Egg", EIngrediente.OVO.getIngrediente(), EIngrediente.HAMBURGUER.getIngrediente(), EIngrediente.QUEIJO.getIngrediente())),
    XEGGBACON(new Lanche("X-Egg-Bacon", EIngrediente.OVO.getIngrediente(), EIngrediente.BACON.getIngrediente(), EIngrediente.HAMBURGUER.getIngrediente(), EIngrediente.QUEIJO.getIngrediente()));

    ELanche(Lanche lanche){
        this.lanche = lanche;
    }
    private Lanche lanche;

    public Lanche getLanche() {
        return lanche;
    }
}
