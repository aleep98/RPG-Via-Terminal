package com.model;

import java.util.Objects;

public class Personagem {

    private String nome;
    private TipoClasse classe;
    private int ataque;
    private int vida;
    private Bolsa bolsa;
    private int defesa;
    private int xp;
    private int nivel;
    private int vidaMaxima;
    private double danoPorSegundo;
    private double chanceCritica;
    private double multiplicadorCritico;
    private double ouro;
    private double multiplicadorOuro;
    private int nivelUpgradeAtaque;
    private int nivelUpgradeDps;
    private int nivelUpgradeCritico;
    private int nivelUpgradeOuro;

    public Personagem(String nome, TipoClasse classe) {
        this.nome = nome;
        this.classe = Objects.requireNonNull(classe, "A classe nao pode ser nula.");
        this.vidaMaxima = classe.getVida();
        this.vida = this.vidaMaxima;
        this.ataque = classe.getAtaque();
        this.defesa = classe.getDefesa();
        this.bolsa = new Bolsa();
        this.xp = 0;
        this.nivel = 1;
        this.danoPorSegundo = classe.getDpsInicial();
        this.chanceCritica = classe.getChanceCritica();
        this.multiplicadorCritico = classe.getMultiplicadorCritico();
        this.ouro = 0.0;
        this.multiplicadorOuro = 1.0;
        this.nivelUpgradeAtaque = 0;
        this.nivelUpgradeDps = 0;
        this.nivelUpgradeCritico = 0;
        this.nivelUpgradeOuro = 0;
    }

    public Personagem(String nome, TipoClasse classe, int ataque, int defesa, int vida, int vidaMaxima, int xp, int nivel) {
        this.nome = nome;
        this.classe = Objects.requireNonNull(classe, "A classe nao pode ser nula.");
        this.ataque = ataque;
        this.defesa = defesa;
        this.vidaMaxima = vidaMaxima;
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
        this.bolsa = new Bolsa();
        this.xp = xp;
        this.nivel = nivel;
        this.danoPorSegundo = classe.getDpsInicial() + (nivel - 1) * 0.5;
        this.chanceCritica = classe.getChanceCritica();
        this.multiplicadorCritico = classe.getMultiplicadorCritico();
        this.ouro = 0.0;
        this.multiplicadorOuro = 1.0;
        this.nivelUpgradeAtaque = 0;
        this.nivelUpgradeDps = 0;
        this.nivelUpgradeCritico = 0;
        this.nivelUpgradeOuro = 0;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        } else {
            this.vida = vida;
        }
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
        if (this.vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        }
    }

    public boolean isVidaCheia() {
        return this.vida == this.vidaMaxima;
    }

    public String getNome() {
        return nome;
    }

    public void informacoes() {
        System.out.println("Eu sou: " + this.nome + " e minha classe e: " + this.classe);
        System.out.println("ATK: " + this.ataque + " | DPS: " + formatar(this.danoPorSegundo)
                + " | Crit: " + formatar(this.chanceCritica * 100) + "% x" + formatar(this.multiplicadorCritico));
    }

    public TipoClasse getClasse() {
        return classe;
    }

    public void curar(int cura) {
        setVida(this.vida + cura);
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = Math.max(1, ataque);
    }

    public void receberDano(int dano) {
        setVida(this.vida - dano);
        if (vida <= 0) {
            System.out.println("O personagem " + this.nome + " foi derrotado!");
        } else {
            System.out.println("O personagem " + this.nome + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int i) {
        this.xp = i;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int i) {
        this.nivel = i;
    }

    public double getDanoPorSegundo() {
        return danoPorSegundo;
    }

    public void setDanoPorSegundo(double danoPorSegundo) {
        this.danoPorSegundo = Math.max(0, danoPorSegundo);
    }

    public double getChanceCritica() {
        return chanceCritica;
    }

    public void setChanceCritica(double chanceCritica) {
        this.chanceCritica = Math.max(0, Math.min(0.95, chanceCritica));
    }

    public double getMultiplicadorCritico() {
        return multiplicadorCritico;
    }

    public void setMultiplicadorCritico(double multiplicadorCritico) {
        this.multiplicadorCritico = Math.max(1.1, multiplicadorCritico);
    }

    public double getOuro() {
        return ouro;
    }

    public double getMultiplicadorOuro() {
        return multiplicadorOuro;
    }

    public void setMultiplicadorOuro(double multiplicadorOuro) {
        this.multiplicadorOuro = Math.max(1.0, multiplicadorOuro);
    }

    public int getNivelUpgradeAtaque() {
        return nivelUpgradeAtaque;
    }

    public int getNivelUpgradeDps() {
        return nivelUpgradeDps;
    }

    public int getNivelUpgradeCritico() {
        return nivelUpgradeCritico;
    }

    public int getNivelUpgradeOuro() {
        return nivelUpgradeOuro;
    }

    public int atacarPorToque() {
        double dano = this.ataque;
        if (Math.random() < this.chanceCritica) {
            dano *= this.multiplicadorCritico;
        }
        return (int) Math.round(dano);
    }

    public int atacarPorTick() {
        return (int) Math.round(this.danoPorSegundo);
    }

    public void receberOuro(int recompensaBase) {
        this.ouro += recompensaBase * this.multiplicadorOuro;
    }

    public boolean gastarOuro(double custo) {
        if (custo <= 0 || custo > this.ouro) {
            return false;
        }
        this.ouro -= custo;
        return true;
    }

    public double getCustoUpgradeAtaque() {
        return 35 * Math.pow(1.32, this.nivelUpgradeAtaque);
    }

    public double getCustoUpgradeDps() {
        return 32 * Math.pow(1.30, this.nivelUpgradeDps);
    }

    public double getCustoUpgradeCritico() {
        return 48 * Math.pow(1.36, this.nivelUpgradeCritico);
    }

    public double getCustoUpgradeOuro() {
        return 55 * Math.pow(1.34, this.nivelUpgradeOuro);
    }

    public boolean comprarUpgradeAtaque() {
        double custo = getCustoUpgradeAtaque();
        if (!gastarOuro(custo)) {
            return false;
        }
        this.nivelUpgradeAtaque++;
        setAtaque(this.ataque + 2);
        return true;
    }

    public boolean comprarUpgradeDps() {
        double custo = getCustoUpgradeDps();
        if (!gastarOuro(custo)) {
            return false;
        }
        this.nivelUpgradeDps++;
        setDanoPorSegundo(this.danoPorSegundo + 0.7);
        return true;
    }

    public boolean comprarUpgradeCritico() {
        double custo = getCustoUpgradeCritico();
        if (!gastarOuro(custo)) {
            return false;
        }
        this.nivelUpgradeCritico++;
        setChanceCritica(this.chanceCritica + 0.015);
        return true;
    }

    public boolean comprarUpgradeOuro() {
        double custo = getCustoUpgradeOuro();
        if (!gastarOuro(custo)) {
            return false;
        }
        this.nivelUpgradeOuro++;
        setMultiplicadorOuro(this.multiplicadorOuro + 0.04);
        return true;
    }

    public void aplicarBonusPermanente(Items.TipoItem tipoItem, double bonus) {
        switch (tipoItem) {
            case DANO_POR_TOQUE:
                setAtaque((int) Math.round(this.ataque + bonus));
                break;
            case DANO_POR_SEGUNDO:
                setDanoPorSegundo(this.danoPorSegundo + bonus);
                break;
            case CHANCE_CRITICA:
                setChanceCritica(this.chanceCritica + bonus);
                break;
            case DANO_CRITICO:
                setMultiplicadorCritico(this.multiplicadorCritico + bonus);
                break;
            case OURO_BONUS:
                setMultiplicadorOuro(this.multiplicadorOuro + bonus);
                break;
            case CURA:
                curar((int) Math.round(bonus));
                break;
            default:
                break;
        }
    }

    private String formatar(double valor) {
        return String.format("%.2f", valor);
    }

}
