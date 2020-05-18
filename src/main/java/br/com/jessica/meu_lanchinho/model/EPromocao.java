package br.com.jessica.meu_lanchinho.model;


public enum EPromocao {
    LIGHT(new Promocao("Light", "Se o lanche tem alface e não tem bacon, ganha 10% de desconto.")),
    MUITA_CARNE(new Promocao("Muita carne", "A cada 3 porções de hambúrguer o cliente só paga 2, a cada 6 porções, o cliente pagará 4 e assim sucessivamente.")),
    MUITO_QUEIJO(new Promocao("Muito queijo", "A cada 3 porções de queijo o cliente só paga 2, a cada 6 porções, o cliente pagará 4 e assim sucessivamente."));

    EPromocao(Promocao promocao){
        this.promocao = promocao;
    }
    private Promocao promocao;

    public Promocao getPromocao() {
        return promocao;
    }
}
