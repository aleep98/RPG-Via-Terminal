package com.service;

import com.model.Inimigo;
import com.model.Items;
import com.model.Personagem;

public class CombateService {

    private final NivelService nivelService;

    public CombateService() {
        this.nivelService = new NivelService();
    }

    public CombateService(NivelService nivelService) {
        this.nivelService = nivelService;
    }

    public int realizarAtaque(Personagem atacante, Inimigo alvo) {
        int danoToque = atacante.atacarPorToque();
        int danoTick = atacante.atacarPorTick();
        int dano = danoToque + danoTick;
        alvo.receberDano(dano);
        return dano;
    }

    public int calcularDefesa(Personagem jogador) {
        return jogador.getDefesa() + (int) (Math.random() * 10);
    }

    public boolean tentarFugir() {
        return Math.random() > 0.5;
    }

  
    public String usarItem(Personagem jogador, int indiceItem) {
        if (indiceItem < 0 || indiceItem >= jogador.getBolsa().getItems().size()) {
            return null; 
        }

        Items item = jogador.getBolsa().getItems().get(indiceItem);
        item.usarItem(jogador);
        jogador.getBolsa().removerItem(item);
        
  
        return "Item utilizado: " + item.getNome() + " - Efeito: " + item.getEfeito(); 
    }

    public int turnoInimigo(Inimigo inimigo, Personagem jogador, int defesaJogador) {
        if (inimigo.getVida() <= 0) {
            return 0;
        }

        int danoInimigo = inimigo.getAtaque() + (int) (Math.random() * 6);
        int danoFinal = Math.max(0, danoInimigo - defesaJogador);

        jogador.receberDano(danoFinal);
        return danoFinal;
    }

    public boolean isCombateAtivo(Personagem jogador, Inimigo inimigo) {
        return jogador.getVida() > 0 && inimigo.getVida() > 0;
    }

    public boolean jogadorVenceu(Inimigo inimigo, Personagem jogador) {
        if (inimigo.getVida() <= 0) {
            nivelService.ganharXP(jogador, inimigo.getRecompensaXp());
            jogador.receberOuro(inimigo.getRecompensaOuro());
            return true;
        }
        return false;
    }
}
