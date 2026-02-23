package com.service;

import com.model.Inimigo;
import com.model.Personagem;

public class CombateHudRenderer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public void render(Personagem jogador, Inimigo inimigo, String ultimoEvento) {
        limparTela();
        System.out.println(ANSI_CYAN + "====================== TAP SOULS HUD ======================" + ANSI_RESET);
        System.out.println("Jogador: " + jogador.getNome() + " | Classe: " + jogador.getClasse()
                + " | Nivel: " + jogador.getNivel()
                + " | Ouro: " + (int) jogador.getOuro());
        System.out.println("Estagio: " + inimigo.getEstagio() + " | Fase: " + inimigo.getIndiceDaFase()
                + " | Inimigo: " + inimigo.getTipoInimigos());
        System.out.println();

        System.out.println("HP Jogador " + ANSI_GREEN + barra(jogador.getVida(), jogador.getVidaMaxima(), 24) + ANSI_RESET
                + " " + jogador.getVida() + "/" + jogador.getVidaMaxima());
        System.out.println("HP Inimigo " + ANSI_RED + barra(inimigo.getVida(), inimigo.getVidaMaxima(), 24) + ANSI_RESET
                + " " + inimigo.getVida() + "/" + inimigo.getVidaMaxima());
        System.out.println("ATK: " + jogador.getAtaque()
                + " | DPS: " + String.format("%.2f", jogador.getDanoPorSegundo())
                + " | Crit: " + String.format("%.0f", jogador.getChanceCritica() * 100) + "%"
                + " x" + String.format("%.2f", jogador.getMultiplicadorCritico()));
        System.out.println();
        System.out.println(ANSI_YELLOW + "Ultimo evento: " + ultimoEvento + ANSI_RESET);
        System.out.println("============================================================");
    }

    private String barra(int atual, int maximo, int largura) {
        int max = Math.max(1, maximo);
        int clamped = Math.max(0, Math.min(atual, max));
        int preenchidos = (int) Math.round((clamped * 1.0 / max) * largura);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < largura; i++) {
            sb.append(i < preenchidos ? '#' : '.');
        }
        sb.append("]");
        return sb.toString();
    }

    private void limparTela() {
        System.out.print("\u001B[H\u001B[2J");
        System.out.flush();
    }
}
