import java.util.Random;
import java.util.Scanner;

import com.model.Inimigo;
import com.model.Personagem;
import com.model.TipoClasse;
import com.model.TipoInimigos;
import com.service.CombateController;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------MENU DE CRIACAO DE PERSONAGEM--------");
        System.out.println("Digite o nome do personagem:");
        String nome = scanner.nextLine();

        System.out.println("Escolha a classe do personagem: ");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Paladin");
        System.out.println("3 - Mage");

        TipoClasse classEscolhida = null;

        while (classEscolhida == null) {
            int escolhaClasse;
            try {
                escolhaClasse = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite 1, 2 ou 3.");
                continue;
            }

            switch (escolhaClasse) {
                case 1:
                    classEscolhida = TipoClasse.GUERREIRO;
                    break;
                case 2:
                    classEscolhida = TipoClasse.PALADIN;
                    break;
                case 3:
                    classEscolhida = TipoClasse.MAGE;
                    break;
                default:
                    System.out.println("Classe invalida! Digite 1, 2 ou 3.");
            }
        }

        Personagem personagem = new Personagem(nome, classEscolhida);
        personagem.informacoes();

        Random random = new Random();
        TipoInimigos[] tiposInimigos = TipoInimigos.values();
        TipoInimigos tipoAleatorio = tiposInimigos[random.nextInt(tiposInimigos.length)];
        Inimigo inimigo = new Inimigo(tipoAleatorio);

        CombateController combate = new CombateController();
        combate.iniciarCombate(personagem, inimigo);

        scanner.close();
    }

}
