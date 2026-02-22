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
        Inimigo inimigo = new Inimigo(TipoInimigos.ORC);

        inimigo.setVida(1000);
        int vidaInicialInimigo = inimigo.getVida();

        int dano = combateService.realizarAtaque(jogador, inimigo);

        assertTrue(dano >= jogador.getAtaque(), "Dano deve ser pelo menos o ataque base.");
        assertTrue(dano <= jogador.getAtaque() + 14, "Dano deve respeitar variacao maxima.");
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
        String retornoAtaque = combateService.usarItem(jogador, 0);

        assertTrue(retornoAtaque != null && retornoAtaque.contains("Pergaminho Flamejante"), "Item de ataque deve ser utilizado com retorno valido.");
        assertEquals(ataqueAntes + 10, jogador.getAtaque(), "Item de ataque deve somar ataque temporario.");
    }

    private static void testSubidaDeNivel() {
        NivelService nivelService = new NivelService();
        Personagem jogador = new Personagem("Tester", TipoClasse.PALADIN);

        int ataqueBase = jogador.getAtaque();

        nivelService.ganharXP(jogador, 100);

        assertEquals(2, jogador.getNivel(), "Com 100 XP, personagem deve subir para nivel 2.");
        assertEquals(120, jogador.getVidaMaxima(), "No nivel 2, vida maxima deve subir em +20.");
        assertEquals(120, jogador.getVida(), "Ao subir nivel, vida atual deve acompanhar a nova vida maxima.");
        assertEquals(ataqueBase + 5, jogador.getAtaque(), "Ao subir nivel, ataque deve subir em +5.");
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
