package com.teste;

import java.util.Scanner;
import com.teste.Personagem.Personagem;
import com.teste.Inimigo.Inimigo;

public class Combate {
    public void iniciarCombate() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Combate Iniciado!");

        System.out.println("-------MENU DE AÇÕES-------");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Fugir");

        int escolherAcao = scanner.nextInt();

        switch (escolherAcao) {
            case 1:
                System.out.println("Você atacou o inimigo!");
                int dano = (int) (Math.random() * 20 + 1);
                System.out.println("Você causou " + dano + " de dano");
                break;
            case 2:
                System.out.println("Você se defendeu!");
                escolherAcao = 0 + (int) (Math.random() * 10 + 1);
                System.out.println("Você reduziu o dano em " + escolherAcao);
                break;
            case 3:
                System.out.println("Você fugiu do combate!");
                escolherAcao = 0 + (int) (Math.random() * 10 + 1);
                if (escolherAcao > 5) {
                    System.out.println("Fuga bem sucedida!");
                } else {
                    System.out.println("Fuga falhou! O inimigo te alcançou!");
                }
                break;
            default:
                System.out.println("Opção inválida!");
        }


        
        scanner.close();
    }

    

    public void Iniciar(Personagem jogador, Inimigo inimigo) {
        System.out.println("O inimigo " + inimigo.getNome() + " apareceu!");
        iniciarCombate();

      
    }

}
