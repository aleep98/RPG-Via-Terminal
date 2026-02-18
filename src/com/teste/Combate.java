package com.teste;

import java.util.Scanner;
import com.teste.Personagem.Personagem;
import com.teste.Inimigo.Inimigo;

public class Combate {

    private Scanner scanner = new Scanner(System.in);

    public void iniciar(Personagem jogador, Inimigo inimigo) {

        System.out.println("O inimigo " + inimigo.getTipoInimigos() + " apareceu!");
        System.out.println("Combate iniciado!");

        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
        System.out.println("-------MENU DE AÇÕES-------");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Fugir");

        int escolherAcao = scanner.nextInt();

        switch (escolherAcao) {

            case 1:
                System.out.println("Você atacou o inimigo!");
                int dano = (int) (Math.random() * 20 + 1);
                inimigo.receberDano(dano);
                System.out.println("Você causou " + dano + " de dano");
                break;

            case 2:
                System.out.println("Você se defendeu!");
                int defesa = (int) (Math.random() * 10 + 1);
                System.out.println("Você reduziu o dano em " + defesa);
                break;

            case 3:
                System.out.println("Você tentou fugir!");
                int chance = (int) (Math.random() * 10 + 1);

                if (chance > 5) {
                    System.out.println("Fuga bem sucedida!");
                    return;
                } else {
                    System.out.println("Fuga falhou!");
                }
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

    
        try {
            System.out.println("\nAguardando o proximo turno...");
            Thread.sleep(5000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Turno do inimigo
        if (inimigo.getVida() > 0) {
            int danoInimigo = inimigo.getAtaque();
            jogador.receberDano(danoInimigo);
            System.out.println("O inimigo causou " + danoInimigo + " de dano");
        }

        if (inimigo.getVida() <= 0) {
            System.out.println("Você venceu!");
        } else if (jogador.getVida() <= 0) {
            System.out.println("Você morreu!");
        }
    }
}
}