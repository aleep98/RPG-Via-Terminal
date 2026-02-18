package com.teste.Items;

import com.teste.Personagem.Personagem;

public class Items {
    private String nome;
    private String tipo;
    private int efeito;

    public Items(String nome, String tipo, int efeito){
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
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

    public void usarItem(){
        System.out.println("Usando o item " + this.getNome());
    }

    public void usarItem(Personagem jogador){
        if (tipo.equals("Cura")){
            jogador.curar(efeito);
            System.out.println("Você recuperou " + efeito + " pontos de vida. Vida Atual: " + jogador.getVida());
        }
    }
}