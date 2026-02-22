package com.service;

import java.util.Scanner;

import com.model.Inimigo;
import com.model.Personagem;

public class CombateController {

    private Scanner scanner;
    private CombateService combateService;

    public CombateController() {
        this.scanner = new Scanner(System.in);
        this.combateService = new CombateService();
    }

    public void iniciarCombate(Personagem jogador, Inimigo inimigo) {
        System.out.println("O inimigo " + inimigo.getTipoInimigos() + " apareceu!");
        System.out.println("Combate iniciado!");

        while (combateService.isCombateAtivo(jogador, inimigo)) {

            boolean turnoEncerrado = false;
            int defesaAtiva = 0;

            while (!turnoEncerrado) {
                exibirMenu();
                int opcao = lerOpcao();

                switch (opcao) {
                    case 1:
                        int dano = combateService.realizarAtaque(jogador, inimigo);
                        System.out.println("Voce causou " + dano + " de dano.");
                        turnoEncerrado = true;
                        break;

                    case 2:
                        defesaAtiva = combateService.calcularDefesa(jogador);
                        System.out.println("Voce assumiu postura defensiva! Reducao de dano: " + defesaAtiva);
                        turnoEncerrado = true;
                        break;

                    case 3:
                        jogador.getBolsa().listarItems();
                        System.out.println("Escolha o numero do item (ou -1 para voltar):");
                        int escolhaItem = lerOpcao();

                        if (escolhaItem >= 0) {
                            String resultado = combateService.usarItem(jogador, escolhaItem);
                            if (resultado != null) {
                                System.out.println("Voce usou: " + resultado);
                            } else {
                                System.out.println("Item invalido!");
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Tentando fugir...");
                        if (combateService.tentarFugir()) {
                            System.out.println("Fuga bem sucedida!");
                            return;
                        } else {
                            System.out.println("A fuga falhou! Voce perdeu o turno.");
                            turnoEncerrado = true;
                        }
                        break;

                    default:
                        System.out.println("Opcao invalida!");
                }
            }

            if (inimigo.getVida() > 0) {
                esperar(1000);
                int danoRecebido = combateService.turnoInimigo(inimigo, jogador, defesaAtiva);
                System.out.println("O inimigo atacou e causou " + danoRecebido + " de dano!");
                System.out.println("Sua vida: " + jogador.getVida() + " | Vida Inimigo: " + inimigo.getVida());
            }

            esperar(1000);
        }

        if (combateService.jogadorVenceu(inimigo, jogador)) {
            System.out.println("Voce venceu o combate! E ganhou 50 XP!");
        } else {
            System.out.println("Voce morreu...");
        }
    }

    private void exibirMenu() {
        System.out.println("\n-------MENU DE ACOES-------");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Abrir Bolsa");
        System.out.println("4 - Fugir");
        System.out.print("Escolha: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
