package com.teste.Classe;
import com.teste.TipoClasse.TipoClasse;
import com.teste.Personagem.Personagem;

public class Classe extends Personagem {

   public Classe(String nome, TipoClasse classe) {
      super(nome, classe, 100);

   }

    public void atacar() {
        System.out.println("Atacando como " + getArma());
    }

}