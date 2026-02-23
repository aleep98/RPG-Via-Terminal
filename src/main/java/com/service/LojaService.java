package com.service;

import java.util.Scanner;

import com.model.Items;
import com.model.Personagem;

public class LojaService {

    private final Scanner scanner;

    public LojaService(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean abrirLoja(Personagem jogador) {
        while (true) {
            exibirMenu(jogador);
            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    comprar(jogador.comprarUpgradeAtaque(), "Ataque por toque +2");
                    break;
                case 2:
                    comprar(jogador.comprarUpgradeDps(), "DPS +0.70");
                    break;
                case 3:
                    comprar(jogador.comprarUpgradeCritico(), "Chance critica +1.5%");
                    break;
                case 4:
                    comprar(jogador.comprarUpgradeOuro(), "Multiplicador de ouro +0.04");
                    break;
                case 5:
                    evoluirItem(jogador);
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

    private void comprar(boolean sucesso, String descricao) {
        if (sucesso) {
            System.out.println("Upgrade comprado: " + descricao);
        } else {
            System.out.println("Ouro insuficiente.");
        }
    }

    private void evoluirItem(Personagem jogador) {
        if (jogador.getBolsa().getItems().isEmpty()) {
            System.out.println("Nao ha itens na bolsa.");
            return;
        }

        System.out.println("Itens disponiveis para evoluir:");
        for (int i = 0; i < jogador.getBolsa().getItems().size(); i++) {
            Items item = jogador.getBolsa().getItems().get(i);
            double bonusAtual = item.getBonusAtual();
            double bonusProximo = bonusAtual * (1 + item.getCrescimentoPorNivel());
            System.out.println(i + " - " + item.getNome() + " (Nv " + item.getNivel() + ") Custo: "
                    + (int) item.getCustoProximoNivel()
                    + " | Bonus: +" + String.format("%.2f", bonusAtual)
                    + " -> +" + String.format("%.2f", bonusProximo));
        }
        System.out.print("Escolha o item (-1 para cancelar): ");
        int idx = lerOpcao();
        if (idx < 0 || idx >= jogador.getBolsa().getItems().size()) {
            System.out.println("Evolucao cancelada.");
            return;
        }

        Items item = jogador.getBolsa().getItems().get(idx);
        if (item.evoluir(jogador)) {
            System.out.println(item.getNome() + " evoluiu para o nivel " + item.getNivel() + ".");
        } else {
            System.out.println("Ouro insuficiente para evoluir " + item.getNome() + ".");
        }
    }

    private void exibirMenu(Personagem jogador) {
        System.out.println("\n=========== LOJA ENTRE FASES ===========");
        System.out.println("Ouro: " + (int) jogador.getOuro());
        System.out.println("Status atual -> ATK: " + jogador.getAtaque()
                + " | DPS: " + String.format("%.2f", jogador.getDanoPorSegundo())
                + " | Crit: " + String.format("%.1f", jogador.getChanceCritica() * 100) + "%"
                + " | Ouro x" + String.format("%.2f", jogador.getMultiplicadorOuro()));
        System.out.println();
        System.out.println("1 - Ataque +2 (Nv " + jogador.getNivelUpgradeAtaque() + ") - Custo: "
                + (int) jogador.getCustoUpgradeAtaque()
                + " | Preview: " + jogador.getAtaque() + " -> " + (jogador.getAtaque() + 2));
        System.out.println("2 - DPS +0.70 (Nv " + jogador.getNivelUpgradeDps() + ") - Custo: "
                + (int) jogador.getCustoUpgradeDps()
                + " | Preview: " + String.format("%.2f", jogador.getDanoPorSegundo())
                + " -> " + String.format("%.2f", jogador.getDanoPorSegundo() + 0.70));
        System.out.println("3 - Crit +1.5% (Nv " + jogador.getNivelUpgradeCritico() + ") - Custo: "
                + (int) jogador.getCustoUpgradeCritico()
                + " | Preview: " + String.format("%.1f", jogador.getChanceCritica() * 100) + "% -> "
                + String.format("%.1f", Math.min(95.0, jogador.getChanceCritica() * 100 + 1.5)) + "%");
        System.out.println("4 - Ouro +0.04x (Nv " + jogador.getNivelUpgradeOuro() + ") - Custo: "
                + (int) jogador.getCustoUpgradeOuro()
                + " | Preview: x" + String.format("%.2f", jogador.getMultiplicadorOuro())
                + " -> x" + String.format("%.2f", jogador.getMultiplicadorOuro() + 0.04));
        System.out.println("5 - Evoluir item da bolsa");
        System.out.println("0 - Proxima fase");
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
