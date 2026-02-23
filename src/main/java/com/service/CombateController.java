package com.service;

import java.util.Scanner;

import com.model.Inimigo;
import com.model.Personagem;

public class CombateController {
    public enum ResultadoCombate {
        VITORIA,
        DERROTA,
        FUGA
    }

    private Scanner scanner;
    private CombateService combateService;
    private CombateHudRenderer hudRenderer;

    public CombateController() {
        this.scanner = new Scanner(System.in);
        this.combateService = new CombateService();
        this.hudRenderer = new CombateHudRenderer();
    }

    public CombateController(CombateService combateService) {
        this.scanner = new Scanner(System.in);
        this.combateService = combateService;
        this.hudRenderer = new CombateHudRenderer();
    }

    public CombateController(CombateService combateService, Scanner scanner) {
        this.scanner = scanner;
        this.combateService = combateService;
        this.hudRenderer = new CombateHudRenderer();
    }

    public ResultadoCombate iniciarCombate(Personagem jogador, Inimigo inimigo) {
        String ultimoEvento = "O inimigo " + inimigo.getTipoInimigos() + " apareceu. Combate iniciado!";

        while (combateService.isCombateAtivo(jogador, inimigo)) {

            boolean turnoEncerrado = false;
            int defesaAtiva = 0;

            while (!turnoEncerrado) {
                hudRenderer.render(jogador, inimigo, ultimoEvento);
                exibirMenu();
                int opcao = lerOpcao();

                switch (opcao) {
                    case 1:
                        int dano = combateService.realizarAtaque(jogador, inimigo);
                        ultimoEvento = "Voce causou " + dano + " de dano.";
                        turnoEncerrado = true;
                        break;

                    case 2:
                        defesaAtiva = combateService.calcularDefesa(jogador);
                        ultimoEvento = "Postura defensiva ativada. Reducao de dano: " + defesaAtiva + ".";
                        turnoEncerrado = true;
                        break;

                    case 3:
                        jogador.getBolsa().listarItems();
                        System.out.println("Escolha o numero do item (ou -1 para voltar):");
                        int escolhaItem = lerOpcao();

                        if (escolhaItem >= 0) {
                            String resultado = combateService.usarItem(jogador, escolhaItem);
                            if (resultado != null) {
                                ultimoEvento = "Voce usou: " + resultado;
                            } else {
                                ultimoEvento = "Item invalido.";
                            }
                        } else {
                            ultimoEvento = "Selecao de item cancelada.";
                        }
                        break;

                    case 4:
                        hudRenderer.render(jogador, inimigo, "Voce usou o osso de retorno e voltou para Firelink Shrine.");
                        return ResultadoCombate.FUGA;

                    default:
                        ultimoEvento = "Opcao invalida.";
                }
            }

            if (inimigo.getVida() > 0) {
                esperar(1000);
                int danoRecebido = combateService.turnoInimigo(inimigo, jogador, defesaAtiva);
                ultimoEvento = "O inimigo atacou e causou " + danoRecebido + " de dano.";
            }

            esperar(1000);
        }

        if (combateService.jogadorVenceu(inimigo, jogador)) {
            ultimoEvento = "Vitoria! +" + inimigo.getRecompensaXp() + " XP e +" + inimigo.getRecompensaOuro() + " ouro base.";
            hudRenderer.render(jogador, inimigo, ultimoEvento);
            return ResultadoCombate.VITORIA;
        } else {
            hudRenderer.render(jogador, inimigo, "Voce morreu...");
            return ResultadoCombate.DERROTA;
        }
    }

    private void exibirMenu() {
        System.out.println("\n-------MENU DE ACOES-------");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Abrir Bolsa");
        System.out.println("4 - Retornar ao Santuario");
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
