package com.service;

import java.util.Scanner;

import com.model.Items;
import com.model.Personagem;
import com.model.Items.TipoItem;

public class SantuarioService {

    private final Scanner scanner;
    private final LojaService lojaService;

    public SantuarioService(Scanner scanner, LojaService lojaService) {
        this.scanner = scanner;
        this.lojaService = lojaService;
    }

    public boolean abrirSantuario(Personagem jogador, int estagio, int fase) {
        while (true) {
            exibirMenu(jogador, estagio, fase);
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    jogador.setVida(jogador.getVidaMaxima());
                    System.out.println("Voce descansou na fogueira. Vida restaurada.");
                    break;
                case 2:
                    boolean continuarLoja = lojaService.abrirLoja(jogador);
                    if (!continuarLoja) {
                        return false;
                    }
                    break;
                case 3:
                    abrirMercador(jogador);
                    break;
                case 0:
                    return true;
                case 9:
                    return false;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void abrirMercador(Personagem jogador) {
        while (true) {
            System.out.println("\n----------- MERCADOR DE FIRELINK -----------");
            System.out.println("Ouro: " + (int) jogador.getOuro());
            System.out.println("1 - Frasco Estus (cura 35) - 28 ouro");
            System.out.println("2 - Estus Maior (cura 65) - 52 ouro");
            System.out.println("3 - Resina de Carvao (toque +4) - 48 ouro");
            System.out.println("4 - Cinza de Feiticeiro (DPS +1.5) - 50 ouro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    comprarItem(jogador, 28, new Items("Frasco Estus", TipoItem.CURA, 35));
                    break;
                case 2:
                    comprarItem(jogador, 52, new Items("Estus Maior", TipoItem.CURA, 65));
                    break;
                case 3:
                    comprarItem(jogador, 48, new Items("Resina de Carvao", TipoItem.DANO_POR_TOQUE, 4));
                    break;
                case 4:
                    comprarItem(jogador, 50, new Items("Cinza de Feiticeiro", TipoItem.DANO_POR_SEGUNDO, 2));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void comprarItem(Personagem jogador, int custo, Items item) {
        if (!jogador.gastarOuro(custo)) {
            System.out.println("Ouro insuficiente.");
            return;
        }
        if (!jogador.getBolsa().adicionarItem(item)) {
            jogador.receberOuro(custo);
            System.out.println("Compra cancelada. Bolsa cheia e ouro devolvido.");
            return;
        }
        System.out.println("Compra concluida: " + item.getNome() + ".");
    }

    private void exibirMenu(Personagem jogador, int estagio, int fase) {
        System.out.println("\n================ FIRELINK SHRINE ================");
        System.out.println("Estagio " + estagio + " | Fase " + fase + " | Ouro: " + (int) jogador.getOuro());
        System.out.println("Vida: " + jogador.getVida() + "/" + jogador.getVidaMaxima());
        System.out.println("1 - Descansar na fogueira (vida cheia)");
        System.out.println("2 - Andre da Forja (upgrades)");
        System.out.println("3 - Mercador de Firelink (comprar itens)");
        System.out.println("0 - Partir para o combate");
        System.out.println("9 - Encerrar partida");
        System.out.print("Escolha: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
