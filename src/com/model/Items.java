package com.model;

public class Items {
    private String nome;
    private String tipo;
    private int efeito;
    private int AtaqueTemporario;

    public Items(String nome, String tipo, int efeito){
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
        this.AtaqueTemporario = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEfeito() {
        return efeito;
    }

    public void setEfeito(int efeito) {
        this.efeito = efeito;
    }


public int getAtaqueTemporario(){
    return AtaqueTemporario;
}



    
    public void usarItem(){
        System.out.println("Usando o item " + this.getNome());
    }

    public void usarItem(Personagem jogador){
        if (tipo.equals("Cura")){
            jogador.curar(efeito);
            System.out.println("Você recuperou " + efeito + " pontos de vida. Vida Atual: " + jogador.getVida());
        }else if (tipo.equals("efeito")){
             jogador.setAtaque(efeito);
             System.out.println("O item " + this.efeito + " aumentou seu ataque temporariamente!");
        }else
            jogador.setAtaque(efeito);
            System.out.println("O item " + this.efeito + " aumentou seu ataque temporariamente!");
        };
    

    public void itemStatus(){
        if (tipo.equals("ataque")) {
            System.out.println();
            
        }
    }
}