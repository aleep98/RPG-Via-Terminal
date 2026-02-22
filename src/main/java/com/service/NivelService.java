package com.service;

import com.model.Personagem;
import com.repository.PersonagemRepository;

public class NivelService {
    private final PersonagemRepository personagemRepository;

    public NivelService() {
        this.personagemRepository = null;
    }

    public NivelService(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;
    }

    public void ganharXP(Personagem personagem, int xp) {
        personagem.setXp(personagem.getXp() + xp);

        while (personagem.getXp() >= xpNecessario(personagem.getNivel())) {
            subirNivel(personagem);
        }

        if (personagemRepository != null) {
            personagemRepository.salvarOuAtualizar(personagem);
        }
    }

    private void subirNivel(Personagem personagem) {
        personagem.setNivel(personagem.getNivel() + 1);
        personagem.setVidaMaxima(personagem.getVidaMaxima() + 20);
        personagem.setVida(personagem.getVida() + 20);
        personagem.setAtaque(personagem.getAtaque() + 5);

        System.out.println("Parabens! Voce subiu para o nivel " + personagem.getNivel() + "!");
    }

    private int xpNecessario(int nivel) {
        return nivel * 100;
    }
}
