package com.model;

public class Items {
    private String nome;
    public enum TipoItem {
        CURA,
        DANO_POR_TOQUE,
        DANO_POR_SEGUNDO,
        CHANCE_CRITICA,
        DANO_CRITICO,
        OURO_BONUS
    }

    private TipoItem tipo;
    private double efeitoBase;
    private int nivel;
    private double custoBase;
    private double escalaCusto;
    private double crescimentoPorNivel;

    public Items(String nome, TipoItem tipo, int efeito) {
        this(nome, tipo, efeito, 1, 25, 1.22, 0.15);
    }

    public Items(String nome, TipoItem tipo, double efeitoBase, int nivel, double custoBase,
            double escalaCusto, double crescimentoPorNivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.efeitoBase = efeitoBase;
        this.nivel = Math.max(1, nivel);
        this.custoBase = Math.max(1, custoBase);
        this.escalaCusto = Math.max(1.01, escalaCusto);
        this.crescimentoPorNivel = Math.max(0, crescimentoPorNivel);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public int getEfeito() {
        return (int) Math.round(getBonusAtual());
    }

    public void setEfeito(int efeito) {
        this.efeitoBase = efeito;
    }

    public int getNivel() {
        return nivel;
    }

    public double getBonusAtual() {
        return efeitoBase * (1 + (nivel - 1) * crescimentoPorNivel);
    }

    public double getCustoProximoNivel() {
        return custoBase * Math.pow(escalaCusto, Math.max(0, nivel - 1));
    }

    public double getCrescimentoPorNivel() {
        return crescimentoPorNivel;
    }

    public boolean evoluir(Personagem jogador) {
        double custo = getCustoProximoNivel();
        if (!jogador.gastarOuro(custo)) {
            return false;
        }
        this.nivel++;
        return true;
    }

    public void usarItem(Personagem jogador) {
        double bonusAtual = getBonusAtual();
        if (tipo == TipoItem.CURA) {
            jogador.curar((int) Math.round(bonusAtual));
            System.out.println("Voce recuperou " + Math.round(bonusAtual) + " pontos de vida. Vida atual: " + jogador.getVida());
            return;
        }
        jogador.aplicarBonusPermanente(tipo, bonusAtual);
        System.out.println("Item " + nome + " aplicado. Bonus atual: +" + String.format("%.2f", bonusAtual));
    }
}
