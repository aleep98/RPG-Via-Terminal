package com.teste;

import java.util.Scanner;
import com.teste.Personagem.Personagem;
import com.teste.Inimigo.Inimigo;
import com.teste.Items.Items;

public class Combate {

    private Scanner scanner = new Scanner(System.in);

    public void iniciar(Personagem jogador, Inimigo inimigo) {

        System.out.println("O inimigo " + inimigo.getTipoInimigos() + " apareceu!");
        System.out.println("Combate iniciado!");

      
        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {

    boolean turnoEncerrado = false;
    int defesaAtiva = 0;

    while (!turnoEncerrado) {

        System.out.println("-------MENU DE AÇÕES-------");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Abrir Bolsa");
        System.out.println("4 - Fugir");

        int escolherAcao = scanner.nextInt();

        switch (escolherAcao) {

            case 1:
                int dano = jogador.getAtaque() + (int)(Math.random() * 15);
                inimigo.receberDano(dano);
                System.out.println("Você causou " + dano + " de dano.");
                turnoEncerrado = true;
                break;

            case 2:
                defesaAtiva = jogador.getDefesa() + (int)(Math.random() * 10);
                System.out.println("Você vai reduzir o próximo dano em " + defesaAtiva);
                turnoEncerrado = true;
                break;

            case 3:
                jogador.getBolsa().listarItems();
                System.out.println("Escolha o item:");
                int escolhaItem = scanner.nextInt();

                Items item = jogador.getBolsa().getItems().get(escolhaItem);
                item.usarItem(jogador);
                jogador.getBolsa().removerItem(item);

                // NÃO encerra turno
                break;

            case 4:
                System.out.println("Você tentou fugir!");
                if (Math.random() > 0.5) {
                    System.out.println("Fuga bem sucedida!");
                    return;
                } else {
                    System.out.println("Fuga falhou!");
                    turnoEncerrado = true;
                }
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }

    // TURNO DO INIMIGO (só acontece após ação principal)

    if (inimigo.getVida() > 0) {

        int danoInimigo = inimigo.getAtaque() + (int)(Math.random() * 10);
        int danoFinal = Math.max(0, danoInimigo - defesaAtiva);

        jogador.receberDano(danoFinal);

        System.out.println("O inimigo causou " + danoFinal + " de dano!");
    }

    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
        if (inimigo.getVida() <= 0) {
            System.out.println("🎉 Você venceu!");
        } else if (jogador.getVida() <= 0) {
            System.out.println("💀 Você morreu!");
        }
    }
}