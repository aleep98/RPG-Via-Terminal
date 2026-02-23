package com.test;

import com.model.Inimigo;
import com.model.Personagem;
import com.model.TipoClasse;
import com.model.TipoInimigos;
import com.service.CombateService;
import com.service.NivelService;

public class GameSmokeTests {

    public static void main(String[] args) {
        testCombateAtaqueBasico();
        testUsoDeItens();
        testSubidaDeNivel();
        System.out.println("[OK] Todos os testes basicos passaram.");
    }

    private static void testCombateAtaqueBasico() {
        CombateService combateService = new CombateService();
        Personagem jogador = new Personagem("Tester", TipoClasse.GUERREIRO);
        Inimigo inimigo = new Inimigo(TipoInimigos.ORC, 1, 1);

        inimigo.setVida(1000);
        int vidaInicialInimigo = inimigo.getVida();

        int dano = combateService.realizarAtaque(jogador, inimigo);

        assertTrue(dano >= jogador.getAtaque() + jogador.atacarPorTick(), "Dano deve conter toque + tick.");
        assertEquals(vidaInicialInimigo - dano, inimigo.getVida(), "Vida do inimigo deve reduzir pelo dano causado.");
    }

    private static void testUsoDeItens() {
        CombateService combateService = new CombateService();
        Personagem jogador = new Personagem("Tester", TipoClasse.MAGE);

        jogador.receberDano(30);
        int vidaAntesDaCura = jogador.getVida();
        int tamanhoInicialBolsa = jogador.getBolsa().getItems().size();

        String retornoCura = combateService.usarItem(jogador, 0);

        assertTrue(retornoCura != null && retornoCura.contains("Pocao de Vida"), "Item de cura deve ser utilizado com retorno valido.");
        assertTrue(jogador.getVida() > vidaAntesDaCura, "Uso de cura deve aumentar a vida.");
        assertTrue(jogador.getVida() <= jogador.getVidaMaxima(), "Vida nao pode passar da vida maxima.");
        assertEquals(tamanhoInicialBolsa - 1, jogador.getBolsa().getItems().size(), "Item utilizado deve sair da bolsa.");

        int ataqueAntes = jogador.getAtaque();
        int dpsAntes = (int) Math.round(jogador.getDanoPorSegundo());
        String retornoAtaque = combateService.usarItem(jogador, 0);

        assertTrue(retornoAtaque != null && retornoAtaque.contains("Luva de Aco"), "Item de dano por toque deve ser utilizado com retorno valido.");
        assertEquals(ataqueAntes + 5, jogador.getAtaque(), "Item de toque deve somar ataque.");
        assertEquals(dpsAntes, (int) Math.round(jogador.getDanoPorSegundo()), "Item de toque nao deve alterar DPS.");
    }

    private static void testSubidaDeNivel() {
        NivelService nivelService = new NivelService();
        Personagem jogador = new Personagem("Tester", TipoClasse.PALADIN);

        int ataqueBase = jogador.getAtaque();

        nivelService.ganharXP(jogador, 100);

        assertEquals(2, jogador.getNivel(), "Com 100 XP, personagem deve subir para nivel 2.");
        assertEquals(160, jogador.getVidaMaxima(), "No nivel 2, vida maxima deve subir em +20.");
        assertEquals(160, jogador.getVida(), "Ao subir nivel, vida atual deve acompanhar a nova vida maxima.");
        assertEquals(ataqueBase + 5, jogador.getAtaque(), "Ao subir nivel, ataque deve subir em +5.");
        assertTrue(jogador.getDanoPorSegundo() > 0, "DPS deve existir no modelo incremental.");
    }

    private static void assertEquals(int esperado, int atual, String mensagem) {
        if (esperado != atual) {
            throw new AssertionError(mensagem + " Esperado: " + esperado + ", atual: " + atual);
        }
    }

    private static void assertTrue(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }
}
