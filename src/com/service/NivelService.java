package com.service;

import com.model.Personagem;

public class NivelService {
    public void ganharXP(Personagem personagem, int xp){
        personagem.setXp(personagem.getXp() + xp);

        while (personagem.getXp() >= xpNecessario(personagem.getNivel())) {
            subirNivel(personagem);
        }
    }

   

    private void subirNivel(Personagem personagem) {
        personagem.setNivel(personagem.getNivel() + 1);
        personagem.setVida(personagem.getVida() + 20);
        personagem.setAtaque(personagem.getAtaque() + 5);

        System.out.println("Parabéns! Você subiu para o nível " + personagem.getNivel() + "!");
    }

    private int xpNecessario(int nivel) {
        return nivel * 100;
    }
}
