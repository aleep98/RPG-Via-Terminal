import com.teste.Personagem.Personagem;
import com.teste.TipoClasse.TipoClasse;
import java.util.Scanner;

import com.teste.Combate;
import com.teste.Inimigo.Inimigo;

public class App {

        public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

      System.out.println("--------MENU DE CRIAÇÃO DE PERSONAGEM--------");
        System.out.println("Digite o nome do personagem:");
        String nome = scanner.nextLine();

        System.out.println("Escolha a classe do personagem: ");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Paladin");
        System.out.println("3 - Mage");

        int escolhaClasse = scanner.nextInt();
        TipoClasse classEscolhida = null;
        
        switch (escolhaClasse) {
            case 1:
                classEscolhida= TipoClasse.GUERREIRO;
                break;
            case 2:
                classEscolhida= TipoClasse.PALADIN;
                break;
            case 3:
                classEscolhida= TipoClasse.MAGE;
                break;
            default:
                System.out.println("Classe inválida!");
        }

        Personagem personagem = new Personagem (nome, classEscolhida, 100);
        personagem.informacoes();
        personagem.getArma();
    
        Inimigo  inimigo = new Inimigo("Goblin", 30, 5);
        Combate combate = new Combate();
        combate.Iniciar(personagem, inimigo);

        if (inimigo.getVida() <= 0) {
            System.out.println("Você venceu!");
        } else {
            personagem.receberDano(inimigo.getAtaque());
            if (personagem.getVida() <= 0) {
                System.out.println("Morte!");
            }
        }
        scanner.close();
    }

}
