package com.teste.Items;

public class Items {
    private String pocao;
    private String tipo;
    private int efeito;

    public Items(String pocao, String tipo, int efeito){
        this.pocao = pocao;
        this.tipo = tipo;
        this.efeito = efeito;
    }

    public String getPocao() {
        return pocao;
    }

    public void setPocao(String pocao) {
        this.pocao = pocao;
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
        System.out.println("Usando o item " + this.pocao);
    }

}
